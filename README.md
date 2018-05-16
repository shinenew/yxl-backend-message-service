# yxl-backend-message-service
宜讯联后端代码-消息中心服务

# 本机环境步骤
将doc/settings目录拷贝到c:\\opt目录下

# 本机作为邮箱服务器发送邮件
如果需要本机作为服务端发送邮件通知，则需要进入QQ邮箱进行如下设置：
(1)、点击上方设置
(2)、点击账户
(3)、拖动到下方开启pop3，点击生成授权码
(4)、将邮箱，授权码，填写到application-dev-default.properties  spring.mail.password 和 spring.mail.username处
