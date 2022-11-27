#!/bin/bash
#关闭程序
pid=$(ps -ef | grep "Go-Mirai-Client" | grep -v grep | awk '{print $2}')
if [ ! -n "$pid" ]; then
    echo "未找到pid，无关闭程序操作"
else
    for sPID in $pid
    do
        echo "找到pid:$sPID，进行关闭"
        kill -9 "$sPID"
    done
fi

echo "启动项目"
nohup Go-Mirai-Client >/root/logs/client/logs.txt 2>&1 &