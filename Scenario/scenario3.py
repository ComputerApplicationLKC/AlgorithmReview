# 김승진 작성

import subprocess, os
from time import sleep

def checkHttp(string: str):
    http_error_con = []
    for i in string.split('\n'):
        if 'Server Error' in i:
            tmpString = i.split('"')[1].strip('/api/').split('-')[0]
            if tmpString.strip() == "":
                http_error_con.append('api-gateway')
            else:
                http_error_con.append(tmpString)
        elif 'statuscode and service ->' in i:
            i = i.split('->')[1:]
            if 500 <= int(i[0].split()[0].strip()) < 600:
                http_error_con.append(i[1].split('-')[0])
    return http_error_con

def checkLogs(array : list):
    return subprocess.check_output(array).decode()

def startCon(container):
    os.system('docker start ' + container)
    print(container, "restarted")

def if5xx():
    print("5xx http status code detected")
    for _ in range(3):
        output = checkLogs(['docker','ps','-a', '-q', '-f' 'status=exited', '--format', '{{.Names}}'])
        
        for container in output.split('\n'):
            if container != 'frontend' and container.strip():
                startCon(container)
        sleep(3.3)
        
while True:
    print('Scenario3 Running')
    sleep(10) if not checkHttp(checkLogs(['docker','logs', 'api-gateway', '--since', '10s'])) else if5xx()
    