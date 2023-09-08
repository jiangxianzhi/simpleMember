# simpleMember
a practice of vue+spring boot+mysql 

# 目的
一个group内部员工让其他人知道自己是否需要定中饭系统。
业务逻辑：
员工先自行添加自己的姓名到候选列表，如果自己中午需要订餐，那么点击自己的名字添加到订饭列表。
如果要取消，那么在订饭列表中点击自己名字就删除了。
## 前端是vue3+element-plus(我也不知道什么时候用plus，什么时候是element-ui)
下载companyOrderLunch即可
## npm install 下载依赖，node版本 17.9.1
## npm run dev 运行即可

# 后端是 spring-boot 用jdbcTemplate连接 mysql
安装mysql 8.0
java 20

## 先运行sql文件中的.sql, source +"abosolute path"
## 然后mvn spring-boot:run 即可

# TODO
其中装的是我想升级的地方，有没有大佬帮下忙，或者提供思路也行
另外我还搞不懂什么是多线程，线程打架怎么办


