# 이정권 작성

import time
import os
import subprocess as sb


while(1):
    print('Scenario2 Running')
    docker_ps = sb.check_output(['docker','ps','-a']).decode()
    docker_ps = [x.strip() for x in docker_ps.split('\n') if x != '']

    docker_list = []
    for x in docker_ps[1:]:
        docker_list.append([y.strip() for y in x.split('   ') if y != ''])
        
    for con in filter(lambda docker: 'Exited' in docker[4] and docker[5] != 'frontend', docker_list):
        print('restarted')
        os.system('docker start ' + con[5])

    time.sleep(2)gi