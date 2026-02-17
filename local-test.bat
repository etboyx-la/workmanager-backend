@echo off
echo ================================
echo WorkManager Backend 本地测试
echo 简化版本 - 无复杂依赖
echo ================================

echo 正在编译项目...
cd /d "%~dp0"
mvn clean compile
if %errorlevel% neq 0 (
    echo 编译失败！请检查Java环境
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
echo 编译成功！
echo 运行命令启动应用：
echo mvn spring-boot:run
echo ================================
echo.
echo 或直接运行：
echo java -jar target/workmanager-backend-1.0.0.jar

pause