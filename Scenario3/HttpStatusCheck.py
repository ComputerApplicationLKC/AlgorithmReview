# 김승진 작성

import subprocess, os
from time import sleep

def checkHttp(array: list, string: str):
    http_error_con = []
    for i in string.split('\n'):
        if 'Server Error' in i:
            http_error_con.append(i.split('"')[1].strip('/api/').split('-')[0])
        elif 'statuscode and service ->' in i:
            i = i.split('->')[1:]
            if 500 <= i[0].split()[0].strip() < 600:
                http_error_con.append(i[1].split('-')[0])
    return http_error_con

def checkLogs(array : list):
    return subprocess.check_output(array).decode()

def startCon(container):
    os.system('docker start ' + container)
    print(container, "restarted")

def if5xx():
    for _ in range(3):
        output = checkLogs(['docker','ps','-a', '-q', '-f' 'status=exited', '--format', '{{.Names}}'])
        
        for container in output.split('\n'):
            if not container == 'frontend':
                startCon(container)
        sleep(3.3)
        
while True:
    sleep(10) if not checkHttp(list(), checkLogs(['docker','logs', 'api-gateway', '--since', '10s'])) else if5xx()
    