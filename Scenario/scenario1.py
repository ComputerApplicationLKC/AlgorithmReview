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
    
    con = result[1].decode('utf-8')
    memory = result[-2].decode('utf-8')
    memory = memory[:-1]
    limit = result[5].decode('utf-8')
    unit = result[5].decode('utf-8')[-3:] 

    if 'i' in unit:
        unit = unit[0] + unit[2]
        unit.lower()

    if(memory != ''):
        if(float(memory) > 80.0): 
            print(con, 'upper 80%')
            c = "docker update --memory " + "\"" + str(int(float(limit[:-3]) + 4.0)) + \
                unit + "\"" + " --memory-swap " + "\"" + str(int(float(limit[:-3]) + 4.0)) + unit + "\"" + " " + con
            os.system(c)

        elif(float(memory) < 20.0): 
            print(con, 'under 20%')
            c = "docker update --memory " + "\"" + str(int(float(limit[:-3]) - 4.0)) + \
                unit + "\"" + " --memory-swap " + "\"" + str(int(float(limit[:-3]) - 4.0)) + unit + "\"" + " " + con
            os.system(c)

while True:
    print('Scenario1 running')
    p = Popen("docker stats --format \"table {{.Container}}\t{{.Name}}\t{{.CPUPerc}}\t{{.MemUsage}}\t{{.NetIO}}\t{{.BlockIO}}\t{{.MemPerc}}\t{{.PIDs}}\"", stdout=PIPE, shell=True)
    for path in run(p):
        getUsage(path)
        time.sleep(5)
