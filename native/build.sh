#!/bin/bash
gcc -I ${JAVA_HOME}/include -I ${JAVA_HOME}/include/linux -shared -o ../src/main/resources/linuxio.so src/linuxio.c