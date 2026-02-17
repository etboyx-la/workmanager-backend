#!/bin/bash

echo "================================"
echo "WorkManager Backend 本地测试"
echo "================================"

echo "正在编译项目..."
mvn clean compile
if [ $? -ne 0 ]; then
    echo "编译失败！"
    exit 1
fi

echo "正在运行测试..."
mvn test
if [ $? -ne 0 ]; then
    echo "测试失败！"
    exit 1
fi

echo "正在打包项目..."
mvn package -DskipTests
if [ $? -ne 0 ]; then
    echo "打包失败！"
    exit 1
fi

echo "================================"
echo "编译成功！现在可以部署到Railway"
echo "或者运行 mvn spring-boot:run 本地测试"
echo "================================"