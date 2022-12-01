# 조욱희
# https://manpages.ubuntu.com/manpages/bionic/man1/docker-container-stats.1.html 참조
from subprocess import Popen,PIPE
import time
import threading
import os

def run(command):
    # python에서 호출한 외부 스크립트의 exit code, stdout, stderr 내용을 변수에 쉽게 저장
    process = Popen(command,stdout=PIPE,stderr=PIPE,shell=True)
    
    while True:
        line = process.stdout.readline().rstrip() 
        if not line:
            break
        yield line

if __name__=="__main__":
    for path in run("docker stats --format \"table):
