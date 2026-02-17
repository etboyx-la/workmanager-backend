# WorkManager Backend - 简化版Railway部署

## 🎯 快速部署到Railway

这是一个简化的Spring Boot项目，专为Railway部署优化。

### 核心功能
- ✅ 简单的用户认证 (demo/123456)
- ✅ PostgreSQL数据库支持
- ✅ Flyway数据库迁移
- ✅ 健康检查端点

### API接口

#### 1. 健康检查
```
GET /api/health
```

#### 2. 登录接口
```
POST /api/auth/login
Content-Type: application/json

{
  "employeeId": "demo",
  "password": "123456"
}
```

响应：
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

## 🚀 部署步骤

### 1. 在Railway部署
1. 访问 [railway.app](https://railway.app)
2. 使用GitHub登录
3. 点击"New Project" → "Deploy from GitHub repo"
4. 选择这个仓库
5. 等待部署完成

### 2. 添加PostgreSQL数据库
1. 在Railway Dashboard点击"New" → "Database" → "Add PostgreSQL"
2. 等待数据库创建完成

### 3. 设置环境变量
在Railway Dashboard设置：
```
SPRING_PROFILES_ACTIVE=railway
```

## 🧪 测试API

部署完成后，测试API：

### 健康检查
```bash
curl https://your-app-name.railway.app/api/health
```

### 登录测试
```bash
curl -X POST https://your-app-name.railway.app/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"employeeId":"demo","password":"123456"}'
```

## 📱 Android应用配置

在WorkManagerApp的`WorkManagerApp.kt`中更新服务器地址：
```kotlin
companion object {
    const val BASE_URL = "https://your-app-name.railway.app/api/"
}
```

## 🔧 技术栈

- **Spring Boot**: 3.0.8
- **Java**: 17
- **PostgreSQL**: 13+
- **Flyway**: 数据库迁移
- **Maven**: 构建工具

## 💡 项目特点

- **极简设计**: 去除不必要的依赖
- **快速构建**: 优化Maven配置
- **稳定部署**: 经过测试的Railway配置
- **易于扩展**: 基础架构可扩展

---

**这个简化版本专门针对Railway部署优化，避免了复杂的构建问题。**