import subprocess, os
from time import sleep

string = []

def parsing(array: list, string: str):
    for i in string.split('\n'):
        if i not in string and 'exited' in i:
            startCon(i.split()[0])
            addString(i)
            array.append(i.split()[0])
    for i in filter(lambda line : line[0].strip() and 500 <= int(line[0].split()[0].strip()) < 600, 
                    map(lambda o: o.split('->')[1:], filter(lambda s: s not in string and 'statuscode and service ->' in s, string.split('\n')))):
        print(i[0].strip(), i[1].strip())
        array.append(i[1].split('-')[0])
    return array

def addString(line):
    if line not in string:
        string.extend(line)

def startCon(con):
    os.system('docker-compose up ' + con + ' -d')
    print(con, 'restarted')

def check(string : str):
    return parsing(list(), string)

def checkLogs(array : list):
    return subprocess.check_output(array).decode() 

def if5xx(con : str):
    print('5xx http status code detected')
    flag = True
    for i in range(5):
        if not flag:
            break
        sleep(2)
        for idx, i in enumerate(con):
            print(str(idx + 1) + 'ping to ', con[0])
            try:
                for j in checkLogs(['docker', 'logs', i, '--tail', '10']).split('\n'):
                    if j not in string and 'exited' in j:
                        print(j.split()[0], 'exited')
                        addString(j)
                        startCon(j.split()[0])
            except:
                startCon(i)
                sleep(8)
                flag = False
                break

cnt = 0
while True:
    if cnt == 15:
        string.clear()
        cnt = 0
    print('Scenario3 Monitoring')
    con = check(checkLogs(['docker','logs', 'api-gateway', '--tail', '10']))
    con = list(set(con))
    sleep(10) if not con else if5xx(con)
    cnt += 1
