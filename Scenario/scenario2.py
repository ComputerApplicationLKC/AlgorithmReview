# 이정권 작성

import subprocess
import os
import time

while True:
    result = subprocess.check_output(['docker','ps','-a']).decode()

    result = [i.strip() for i in result.split('\n') if i != '']

    docker_health = []
    for i in result[1:]:
        docker_health.append([k.strip() for k in i.split('   ') if k != ''])

    for container in docker_health:
        if 'Exited' in container[4]:
            if not container[5]=='frontend':
                print("\ncontainer restart ")
                os.system('docker start ' + container[5])
    time.sleep(5)