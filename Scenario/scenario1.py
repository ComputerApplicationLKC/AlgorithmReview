from subprocess import Popen, PIPE
import os
import threading
import time

def memory_dynamic(Container_name, upper, mem_unit):
    updateCommand = "docker update --memory " + "\"" + upper + mem_unit + "\"" + " --memory-swap " + "\"" + upper + mem_unit + "\"" + " " + Container_name
    print(updateCommand)
    os.system(updateCommand)
    return

def memory_unit_conversion(mem_unit):
    if(mem_unit=="GiB"):
        mem_unit="gb"
        if(mem_unit=="MiB"):
            mem_unit="mb"
            
            return mem_unit


def extractResourceUsage(out):
    out = out.split()

    memory = out[-2].decode('utf-8') # memory
    memory = memory[:-1]
    cpu = out[2].decode('utf-8') # cpu
    cpu = cpu[:-1]
    Container_id = out[0].decode('utf-8') # Container_id
    Container_name = out[1].decode('utf-8') # Container_name
    mem_limit = out[5].decode('utf-8') # memory_limit
    mem_unit = out[5].decode('utf-8')[-3:] # memory_unit

    mem_unit = memory_unit_conversion(mem_unit) # mem_unit 변환

    try:
        if(memory != ''):
            if(float(memory) > 80.0): # 자원 할당
                print(Container_name, 'upper 80%')
                memory_dynamic(Container_name, str(int(float(mem_limit[:-3]) + 5.0)), mem_unit)
            elif(float(memory) < 20.0): # 자원 회수
                print(Container_name, 'under 20%')
                memory_dynamic(Container_name, str(int(float(mem_limit[:-3]) - 5.0)), mem_unit)
    except:
        return

    return



def run(command):
    process = Popen(command, stdout=PIPE, shell=True)
    while True:
        line = process.stdout.readline().rstrip()
        if not line:
            break
        yield line


if __name__ == "__main__":
    for path in run("docker stats --format \"table {{.Container}}\t{{.Name}}\t{{.CPUPerc}}\t{{.MemUsage}}\t{{.NetIO}}\t{{.BlockIO}}\t{{.MemPerc}}\t{{.PIDs}}\""):
        extractResourceUsage(path)
        time.sleep(5)
        # print path (result equals 'docker stats')