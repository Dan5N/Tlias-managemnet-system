# Tlias 员工管理系统

基于黑马程序员 2024 年 Tlias Java+AI 项目的员工管理系统。本项目和官方实现有略微修改。

## 📋 项目介绍

Tlias是一个现代化的员工管理系统，提供完整的员工、部门、班级、日志等管理功能。系统采用前后端分离架构，支持 JWT 认证、分页查询、数据验证等功能。

### 核心功能

- 👤 **员工管理**：新增、编辑、删除、查询员工信息
- 🏢 **部门管理**：部门信息维护
- 📚 **班级管理**：班级信息管理
- 🎓 **学生管理**：学生信息管理
- 📊 **数据报表**：员工和学生统计报表
- 📝 **操作日志**：系统操作审计日志
- 🔐 **用户认证**：基于 JWT 的安全认证

## 🛠️ 技术栈

### 后端

- **Java 17** + **Spring Boot 3.4.8**
- **MyBatis** ORM 框架
- **MySQL 8** 数据库
- **JWT** 安全认证
- **PageHelper** 分页插件
- **Lombok** 代码生成
- **Maven** 项目管理

### 前端

- **Vue 3** 渐进式框架
- **Vite** 构建工具
- **Vue Router** 路由管理
- **Axios** HTTP 客户端
- **Element Plus** UI 组件库
- **Nginx** Web 服务器

## 🚀 快速开始

### 方式一：Docker Compose（推荐）

#### 前置要求

- Docker & Docker Compose
- 8GB+ 可用内存（推荐）

#### 启动步骤

```bash
# 1. 克隆或下载项目
cd 新建文件夹

# 2. 使用 Docker Compose 启动
docker-compose up -d

# 3. 等待服务启动完成（约 30-60 秒）
docker-compose logs -f

# 4. 访问应用
# 前端：http://localhost
# 后端 API：http://localhost:8080
# MySQL：localhost:3307（用户：root，密码：123456）
```

#### 停止服务

```bash
docker-compose down

# 清理所有数据（包括数据库）
docker-compose down -v
```

#### 查看日志

```bash
# 查看所有服务日志
docker-compose logs -f

# 查看特定服务日志
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f mysql
```

---

### 方式二：本地开发

#### 前置要求

- Java 17+
- Node.js 16+
- MySQL 8
- Maven 3.6+

#### 后端启动

```bash
cd tlias-web-management

# 1. 配置数据库连接（src/main/resources/application.yml）
# 修改以下配置：
#   spring.datasource.url: jdbc:mysql://localhost:3306/tlias
#   spring.datasource.username: root
#   spring.datasource.password: 你的密码

# 2. 初始化数据库
# 使用 MySQL 客户端执行 mysql_backup.sql：
mysql -u root -p < mysql_backup.sql

# 3. 编译并打包
mvn clean package -DskipTests

# 4. 运行应用
java -jar target/tlias-web-management-0.0.1-SNAPSHOT.jar

# 应用启动后访问：http://localhost:8080
```

#### 前端启动

```bash
cd vue-tlias-management

# 1. 安装依赖
npm install

# 2. 启动开发服务器（热更新）
npm run dev

# 3. 构建生产版本
npm run build

# 前端访问：http://localhost:5173（开发模式）
```

---

## 📁 项目结构

```
.
├── docker-compose.yml              # Docker 容器编排
├── README.md                        # 项目说明文档
│
├── tlias-web-management/           # 后端项目
│   ├── Dockerfile                  # 后端容器镜像
│   ├── pom.xml                     # Maven 依赖配置
│   ├── mysql_backup.sql            # 数据库初始化脚本
│   ├── wait-for-it.sh              # 容器启动等待脚本
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/example/
│   │   │   │   ├── config/         # Spring 配置类
│   │   │   │   ├── controller/     # REST 控制器
│   │   │   │   ├── service/        # 业务逻辑层
│   │   │   │   ├── mapper/         # MyBatis 映射器
│   │   │   │   ├── pojo/           # 数据模型类
│   │   │   │   ├── exception/      # 自定义异常
│   │   │   │   ├── interceptor/    # 拦截器
│   │   │   │   ├── filter/         # 过滤器
│   │   │   │   └── util/           # 工具类
│   │   │   └── resources/          # 配置文件
│   │   │       ├── application.yml # 应用配置
│   │   │       └── logback.xml     # 日志配置
│   │   └── test/                   # 单元测试
│   └── target/                     # 编译输出
│
└── vue-tlias-management/           # 前端项目
    ├── Dockerfile                  # 前端容器镜像
    ├── nginx.conf                  # Nginx 配置
    ├── vite.config.js              # Vite 构建配置
    ├── package.json                # NPM 依赖配置
    ├── index.html                  # HTML 入口
    ├── public/                     # 静态资源
    └── src/
        ├── main.js                 # 应用入口
        ├── App.vue                 # 根组件
        ├── api/                    # API 请求模块
        ├── views/                  # 页面组件
        │   ├── login/              # 登录页
        │   ├── layout/             # 布局页
        │   ├── emp/                # 员工管理
        │   ├── dept/               # 部门管理
        │   ├── clazz/              # 班级管理
        │   ├── stu/                # 学生管理
        │   ├── report/             # 报表统计
        │   ├── log/                # 操作日志
        │   └── index/              # 首页面板
        ├── router/                 # 路由配置
        ├── components/             # 可复用组件
        ├── utils/                  # 工具函数
        └── assets/                 # 样式资源
```

---

## 🔧 配置说明

### 数据库连接（后端）

**本地开发**：编辑 `tlias-web-management/src/main/resources/application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tlias
    username: root
    password: 123456
```

**Docker 环境**：自动通过 `docker-compose.yml` 中的环境变量配置

### API 地址（前端）

**本地开发**：编辑 `vue-tlias-management/src/utils/request.js`

```javascript
// 修改 API 基础 URL
const baseURL = 'http://localhost:8080';
```

**Docker 环境**：通过 Nginx 反向代理自动转发到后端

---

## 📝 默认账户

| 用户名 | 密码 | 角色   |
| ------ | ---- | ------ |
| admin  | -    | 管理员 |

*注：具体账户信息请参考 `mysql_backup.sql` 中的初始化数据*

---

## 🐛 常见问题

### 1. Docker Compose 启动失败

```bash
# 检查端口是否被占用
netstat -ano | findstr "3307\|8080\|80"

# 清理旧容器和卷
docker-compose down -v
docker system prune

# 重新启动
docker-compose up -d
```

### 2. MySQL 连接失败

- ✅ 确保 MySQL 容器已启动：`docker-compose ps`
- ✅ 检查密码是否正确：`docker-compose logs mysql`
- ✅ 等待 MySQL 完全初始化（约 10-20 秒）

### 3. 前端无法访问后端 API

- ✅ 检查后端是否正运行：`docker-compose logs backend`
- ✅ 验证 API 基础 URL 配置
- ✅ 检查跨域 (CORS) 配置

### 4. 数据库初始化失败

```bash
# 手动导入初始化脚本
docker-compose exec mysql mysql -u root -ppassword tlias < mysql_backup.sql
```

---

## 📚 相关资源

- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [Vue 3 官方文档](https://vuejs.org/)
- [MyBatis 官方文档](https://mybatis.org/)
- [Docker 官方文档](https://docs.docker.com/)

---

## 📄 许可证

本项目基于黑马程序员 Tlias 项目，供学习交流使用。

---

**最后更新**：2026年3月3日
