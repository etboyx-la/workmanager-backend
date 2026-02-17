@echo off
echo ================================
echo WorkManager Backend 本地测试
echo ================================

echo 正在编译项目...
mvn clean compile
if %errorlevel% neq 0 (
    echo 编译失败！
    pause
    exit /b 1
)

echo 正在运行测试...
mvn test
if %errorlevel% neq 0 (
    echo 测试失败！
    pause
    exit /b 1
)

echo 正在打包项目...
mvn package -DskipTests
if %errorlevel% neq 0 (
    echo 打包失败！
    pause
    exit /b 1
)

echo ================================
echo 编译成功！现在可以部署到Railway
echo 或者运行 mvn spring-boot:run 本地测试
echo ================================

pause