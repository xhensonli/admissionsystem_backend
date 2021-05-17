# admissionsystem_backend
广东工业大学课程设计
数据库课程设计
平行志愿录取系统（后端代码，广东工业大学数据库大作业）
基于java、spring、MySQL数据库、vue.js的课程设计

# 更新
* 2020-1-13 
  删除了几个状态，重写首页UI，新增导出退档队列，新增启动自动建表
* 2021-5-17
  发现后端代码有未上推部分，重新提交上推

# 开发环境
* JDK8
* MySQL5.7
* springboot2.3
* maven3.6.3（需要安装，否则没有依赖）
* vue2.0（前端开发环境，并不必需）
* vue-cli3（前端开发环境，并不必需）

# 前端仓库
https://github.com/Baibair/admissionsystem_frontend

# 下载项目
* 找一个文件夹，打开cmd，在这个文件夹下运行
```bash
git clone https://github.com/Baibair/admissionsystem_backend.git
```
* 用IDEA打开，前端已打包放置在静态资源文件夹下

# 环境配置
* 打开Mysql，创建数据库
```sql
CREATE DATABASE `<你的数据库名>` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
-- 例如：CREATE DATABASE `db_enroll` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
```
* 进入数据库，运行sql文件，在sql文件夹下，可以把sql放在一个没有中文路径的地方，否则有可能出错
```sql
-- 进入刚刚创建的数据库
use db_enroll;
-- 运行路径下的sql文件
source /path/to/sql/db_enroll.sql
```
* 修改springboot配置文件application.yml，找到下面配置
```yaml
enroll:
  login:
    # 登录用户名
    adminName: admin
    # 登录密码
    adminPass: 123456
  # 改为自己的数据库名
  database: DATABASE_NAME
  # 改为自己的数据库密码（账号默认root）
  dbpass: MYSQL_PASSWORD
```

# 运行
* 找到启动类`EnrollSystemApplication`，并运行
* 打开浏览器，访问`http://localhost:8080/admission/index.html`

# 效果
## 登录
![img](https://github.com/Baibair/admissionsystem_backend/blob/master/images/Snipaste_2020-10-26_13-34-25.png)
## 导入（测试文件在excel文件夹下，数据为随机模拟）
## 表格信息
![img](https://github.com/Baibair/admissionsystem_backend/blob/master/images/Snipaste_2020-10-26_13-38-37.png)
## 统计信息
![img](https://github.com/Baibair/admissionsystem_backend/blob/master/images/Snipaste_2020-10-26_13-39-28.png)
## 生源地分布
![img](https://github.com/Baibair/admissionsystem_backend/blob/master/images/Snipaste_2020-10-26_13-40-11.png)
## 导出结果
![img](https://github.com/Baibair/admissionsystem_backend/blob/master/images/Snipaste_2020-10-26_13-41-04.png)
