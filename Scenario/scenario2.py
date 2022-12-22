# 이정권 작성

import time
import os
import subprocess as sb


while(1):
    print('Scenario2 Running')
    docker_ps = [x.strip() for x in sb.check_output(['docker','ps','-a']).decode().split('\n') if x != ''][1:]

    docker_list = []
    for x in docker_ps:
        docker_list.append([y.strip() for y in x.split('   ') if y != ''])
        
    for con in filter(lambda docker: 'Exited' in docker[4] and docker[5] != 'frontend', docker_list):
        os.system('docker start ' + con[5])
        print('restarted')
        
    time.sleep(4)
