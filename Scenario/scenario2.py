# 이정권 작성

import time
import os
import subprocess as sb


while(1):
    docker_ps = sb.check_output(['docker','ps','-a']).decode()
    docker_ps = [x.strip() for x in docker_ps.split('\n') if x != '']

    docker_list = []
    for x in docker_ps[1:]:
        docker_list.append([y.strip() for y in x.split('   ') if y != ''])

    for container in docker_list:
        if 'Exited' in container[4]:
            if not container[5]=='frontend':
                print("\ncontainer restart ")
                os.system('docker start ' + container[5])
    time.sleep(2)