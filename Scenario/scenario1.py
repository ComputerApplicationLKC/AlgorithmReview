from subprocess import Popen, PIPE
import os
import time

def run(process):
    while True:
        line = process.stdout.readline().rstrip()
        if not line:
            break
        yield line

def getUsage(result):
    result = result.split()

    cpu = result[2].decode('utf-8') 
    cpu = cpu[:-1]
    Container_name = result[1].decode('utf-8')

    memory = result[-2].decode('utf-8')
    memory = memory[:-1]
    mem_limit = result[5].decode('utf-8')
    mem_unit = result[5].decode('utf-8')[-3:] 

    if 'i' in mem_unit:
        mem_unit = mem_unit[0] + mem_unit[2]
        mem_unit.lower()

    if(memory != ''):
        if(float(memory) > 80.0): 
            print(Container_name, 'upper 80%')
            c = "docker update --memory " + "\"" + str(int(float(mem_limit[:-3]) + 4.0)) + \
                mem_unit + "\"" + " --memory-swap " + "\"" + str(int(float(mem_limit[:-3]) + 4.0)) + mem_unit + "\"" + " " + Container_name
            os.system(c)

        elif(float(memory) < 20.0): 
            print(Container_name, 'under 20%')
            c = "docker update --memory " + "\"" + str(int(float(mem_limit[:-3]) - 4.0)) + \
                mem_unit + "\"" + " --memory-swap " + "\"" + str(int(float(mem_limit[:-3]) - 4.0)) + mem_unit + "\"" + " " + Container_name
            os.system(c)

while True:
    print('Scenario1 running')
    p = Popen("docker stats --format \"table {{.Container}}\t{{.Name}}\t{{.CPUPerc}}\t{{.MemUsage}}\t{{.NetIO}}\t{{.BlockIO}}\t{{.MemPerc}}\t{{.PIDs}}\"", stdout=PIPE, shell=True)
    for path in run(p):
        getUsage(path)
        time.sleep(5)
