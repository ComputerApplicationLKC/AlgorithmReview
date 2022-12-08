# 조욱희
# https://manpages.ubuntu.com/manpages/bionic/man1/docker-container-stats.1.html 참조
from subprocess import Popen,PIPE
import time
import os

def restrict_mem(temp):
    temp = temp.split()

    mem = temp[-2].decode('utf-8') # mem
    mem = mem[:-1]
    cpu = temp[2].decode('utf-8') # cpu
    cpu = cpu[:-1]
    Con_name = temp[1].decode('utf-8') # Container_name
    mem_limit = temp[5].decode('utf-8') # mem_limit
    unit = temp[5].decode('utf-8')[-3:] # mem_unit

    unit = unit_change(unit) # mem_unit 변환

    try:
        if(mem != ''):
            if(float(mem) > 80.0): # 자원 할당
                restrict_mem(Con_name, str(int(float(mem_limit[:-3]) + 5.0)), unit)
            elif(float(mem) < 20.0): # 자원 회수
                restrict_mem(Con_name, str(int(float(mem_limit[:-3]) - 5.0)), unit)
    except:
        return None

    return None


def mem(con_name,unit):
    cmd_update_mem_swap = "docker update --mem " + '--' + unit + "--mem-swap " + '--' +con_name
    time(0.5)
    print(cmd_update_mem_swap)
    os.system(cmd_update_mem_swap)

    return None

def unit_change(unit):
    if unit=="MiB":
        unit="mb"
    elif unit=="GiB":
        unit="gb"

    return unit



def run(cmd):

    # python에서 호출한 외부 스크립트의 exit code, stdout, stderr 내용을 변수에 쉽게 저장
    process = Popen(cmd,stdout=PIPE,stderr=PIPE,shell=True)
    
    while True:
        trace = process.stdout.readline().rstrip() # 줄
        return trace


if __name__=="__main__":
    for path in run("docker stats --format \"table {{.Container}}\t{{.Name}}\t{{.CPUPerc}}\t{{.MemUsage}}\t{{.NetIO}}\t{{.BlockIO}}\t{{.MemPerc}}\t{{.PIDs}}\""):
        restrict_mem(path) # path 출력
        time.sleep(4) 
