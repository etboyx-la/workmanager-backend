# WorkManager Backend - Railway部署指南

## 🎯 快速部署到Railway

### 步骤1: 准备代码
确保你的项目包含以下文件：
- `pom.xml` - Maven项目配置
- `Procfile` - Railway启动配置
- `system.properties` - Java版本配置
- `railway.json` - Railway配置

### 步骤2: 推送到GitHub
```bash
# 初始化Git
git init
git add .
git commit -m "Initial commit for Railway deployment"

# 推送到GitHub
git remote add origin https://github.com/your-username/workmanager-backend.git
git push -u origin main
```

### 步骤3: 在Railway部署
1. 访问 [railway.app](https://railway.app)
2. 使用GitHub登录
3. 点击"New Project" → "Deploy from GitHub repo"
4. 选择你的仓库
5. 等待部署完成

### 步骤4: 添加PostgreSQL数据库
1. 在Railway Dashboard中点击"New" → "Database" → "Add PostgreSQL"
2. 等待数据库创建完成
3. Railway会自动设置`DATABASE_URL`环境变量

### 步骤5: 配置环境变量
在Railway Dashboard中设置以下环境变量：
```
SPRING_PROFILES_ACTIVE=railway
JWT_SECRET=workmanager-railway-secret-key-for-jwt-token-2024
```

## 🧪 测试API

### 登录测试
```bash
curl -X POST https://your-app-name.railway.app/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"employeeId":"demo","password":"123456"}'
```

预期响应：
```json
{
  "success": true,
  "message": "登录成功",
  "data": {
    "employeeId": "demo",
    "name": "测试用户",
    "department": "技术部",
    "role": "user"
  }
}
```

### 文件上传测试
```bash
curl -X POST https://your-app-name.railway.app/api/files/upload \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/path/to/test-image.jpg" \
  -F "categoryId=construction" \
  -F "categoryName=施工管理" \
  -F "employeeId=demo"
```

## 📱 Android应用配置

在WorkManagerApp的`WorkManagerApp.kt`中更新服务器地址：
```kotlin
companion object {
    const val BASE_URL = "https://your-app-name.railway.app/api/"
}
```

## 🔧 本地开发

### 启动应用
```bash
# 编译项目
mvn clean package

# 运行应用
mvn spring-boot:run
```

### 本地测试
```bash
# 测试登录
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"employeeId":"demo","password":"123456"}'
```

## 📊 API接口

### 认证接口
- `POST /api/auth/login` - 用户登录
- `POST /api/auth/logout` - 用户登出

### 文件接口
- `POST /api/files/upload` - 文件上传
- `GET /api/files/download/{filename}` - 文件下载

### 健康检查
- `GET /api/health` - 健康检查
- `GET /` - 根路径

## 💡 注意事项

1. **免费限制**: Railway免费版每月500小时使用时间
2. **文件存储**: Railway临时文件系统会在重启后清空
3. **数据库**: PostgreSQL数据库会在免费版过期后暂停
4. **域名**: 默认域名格式为 `your-app-name.railway.app`

## 🚀 升级到付费版

如需无限使用，可以升级到Railway Pro版 ($5/月)：
- 无限使用时间
- 8GB内存
- 自定义域名
- 优先支持

---

**部署完成后，你的WorkManagerApp就可以连接到这个后端API了！**