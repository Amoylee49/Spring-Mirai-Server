#!/bin/bash
#关闭程序
pid=$(ps -ef | grep "spring-mirai-client" | grep -v grep | awk '{print $2}')
if [ ! -n "$pid" ]; then
    echo "未找到pid，无关闭程序操作"
else
    for sPID in $pid
    do
        echo "找到pid:$sPID，进行关闭"
        kill -9 "$sPID"
    done
fi

#删除文件
fileNames=$(ls /root -t|grep -E "^spring-mirai-client.*?\.jar"|sed '1d')
for sName in $fileNames
do
    if [ -n "$sName" ]; then
        echo "找到文件名:$sName，进行删除"
        rm -f "/root/$sName"
    fi
done
myProjectName=$(ls /root -t|grep -E "^spring-mirai-client.*?\.jar"|head -n 1)
#进行启动
echo "启动项目"
nohup java -jar $myProjectName >/root/logs/client/logs.txt 2>&1 &