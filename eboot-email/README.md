# eboot-email简介

---
    基于springboot开发的email发送邮件实现工具类，旨在迅速搭建开发邮件服务平台。

# 使用技术

---
    1、基础框架：springboot + spring-boot-starter-mail
# 几个名词解释

---
    	什么是POP3、SMTP和IMAP？详细介绍-请移步至网易帮助文档
	IMAP和POP3有什么区别？详细介绍-请移步至网易帮助文档
	什么是免费邮箱客户端授权码功能？详细介绍-请移步至网易帮助文档


# Spring Boot中发送邮件步骤

---
    0.添加Starter模块依赖
    1.添加Spring Boot配置(QQ/网易系/Gmail)
    2.调用JavaMailSender接口发送邮件
    		0.1	添加Starter模块依赖
    			<dependency>
    				<groupId>org.springframework.boot</groupId>
    				<artifactId>spring-boot-starter-mail</artifactId>
			</dependency>
		1.1 添加Spring Boot配置
			在application.yml中添加邮件相关的配置，这里分别罗列几个常用邮件的配置比如QQ邮箱、网易系邮箱、Gmail邮箱。
			1.1.1	QQ邮箱配置：说明：开启SSL时使用587端口时无法连接QQ邮件服务器
						spring:
						  mail:
						    host: smtp.qq.com #发送邮件服务器
						    username: xx@qq.com #QQ邮箱
						    password: xxxxxxxxxxx #客户端授权码
						    protocol: smtp #发送邮件协议
						    properties.mail.smtp.auth: true
						    properties.mail.smtp.port: 465 #端口号465或587
						    properties.mail.display.sendmail: Javen #可以任意
						    properties.mail.display.sendname: Spring Boot Guide Email #可以任意
						    properties.mail.smtp.starttls.enable: true
						    properties.mail.smtp.starttls.required: true
						    properties.mail.smtp.ssl.enable: true
						    default-encoding: utf-8
						    from: xx@qq.com #与上面的username保持一致
					
			1.1.2	网易系(126/163/yeah)邮箱配置
					126邮箱SMTP服务器地址:smtp.126.com,端口号:465或者994
					163邮箱SMTP服务器地址:smtp.163.com,端口号:465或者994
					yeah邮箱SMTP服务器地址:smtp.yeah.net,端口号:465或者994
						spring:
						  mail:
						    host: smtp.126.com
						    username: xx@126.com
						    password: xxxxxxxx
						    protocol: smtp
						    properties.mail.smtp.auth: true
						    properties.mail.smtp.port: 994 #465或者994
						    properties.mail.display.sendmail: Javen
						    properties.mail.display.sendname: Spring Boot Guide Email
						    properties.mail.smtp.starttls.enable: true
						    properties.mail.smtp.starttls.required: true
						    properties.mail.smtp.ssl.enable: true
						    default-encoding: utf-8
						    from: xx@126.com
						    
			1.1.3	Gmail邮箱配置
						spring:
						  mail:
						    host: smtp.gmail.com
						    username:xxx@gmail.com
						    password: xxxxx #Gmail账号密码
						    protocol: smtp
						    properties.mail.smtp.auth: true
						    properties.mail.smtp.port: 465
						    properties.mail.display.sendmail: Javen
						    properties.mail.display.sendname: Spring Boot Guide Email
						    properties.mail.smtp.starttls.enable: true
						    properties.mail.smtp.starttls.required: true
						    properties.mail.smtp.ssl.enable: true
						    from: xxx@gmail.com
						    default-encoding: utf-8								    						    

# 其他
    欢迎各位有什么需要的可以Issue提出来，我会视情况而定集成进来，尽量简化大家的工作
    项目肯定有很多不足之处，大家多提宝贵意见，多谢^_^
# 部分页面截图
#![登录](https://gitee.com/uploads/images/2018/0514/173817_401f4989_660787.png "login.png")

