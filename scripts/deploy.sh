#!/bin/bash
PID=$(ps -ef | grep SmartToiletBE-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo Application is already stopped
else
    echo kill $PID
	echo backend has stopped
    kill -9 $PID
fi

nohup java -jar SmartToiletBE-0.0.1-SNAPSHOT.jar > nohup.out &
