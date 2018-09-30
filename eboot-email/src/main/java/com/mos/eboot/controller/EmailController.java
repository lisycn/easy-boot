package com.mos.eboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mos.eboot.email.impl.IMailServiceImpl;
import com.mos.eboot.tools.result.ResultModel;
/**
* @ClassName: EmailController 
* @Description: 邮件发送入口类
* @author Mr.zhou
* @date 2018年9月26日 下午2:46:26 
*
*/
@SuppressWarnings("static-access")
@RestController
@RequestMapping("email")
public class EmailController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private IMailServiceImpl mailService;
    @Autowired
    private TemplateEngine templateEngine;
    /**
    * @Title: index 
    * @Description: 发送普通的文字邮件
    * @param @return    设定文件 
    * @return JsonResult    返回类型 
    * @throws
    */
    @RequestMapping("/email")
    @ResponseBody
    public ResultModel<String> index(){
    	ResultModel<String> result=new ResultModel<>();
        try {
            mailService.sendSimpleMail("zhoulibo_vip@163.com","这是一封普通的邮件","这是一封普通的SpringBoot测试邮件");
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.info("邮件发送失败!"+ex);
            result.defaultError(ex.toString());
            return result;
        }
        return result.defaultSuccess("");
    }
    /**
    * @Title: htmlEmail 
    * @Description: 发送带有HTML的邮件
    * @param @return    设定文件 
    * @return JsonResult    返回类型 
    * @throws
    */
	@RequestMapping("/htmlEmail")
	@ResponseBody
    public ResultModel<String> htmlEmail(){
    	ResultModel<String> result=new ResultModel<>();
        try {
            mailService.sendHtmlMail("javendev@126.com","这是一HTML的邮件","<body style=\"text-align: center;margin-left: auto;margin-right: auto;\">\n"
                    + "	<div id=\"welcome\" style=\"text-align: center;position: absolute;\" >\n"
                    +"		<h3>欢迎使用IJPay -By Javen</h3>\n"
                    +"		<span>https://github.com/Javen205/IJPay</span>"
                    + "		<div\n"
                    + "			style=\"text-align: center; padding: 10px\"><a style=\"text-decoration: none;\" href=\"https://github.com/Javen205/IJPay\" target=\"_bank\" ><strong>IJPay 让支付触手可及,欢迎Start支持项目发展:)</strong></a></div>\n"
                    + "		<div\n" + "			style=\"text-align: center; padding: 4px\">如果对你有帮助,请任意打赏</div>\n"
                    + "		<img width=\"180px\" height=\"180px\"\n"
                    + "			src=\"https://oscimg.oschina.net/oscnet/8e86fed2ee9571eb133096d5dc1b3cb2fc1.jpg\">\n"
                    + "	</div>\n" + "</body>");
        }catch (Exception ex){
        	LOGGER.info("邮件发送失败!"+ex);
            return result.defaultError(ex.toString());
        }
        return result.defaultSuccess("");
    }
    /**
    * @Title: attachmentsMail 
    * @Description: 发送一份带有附件的邮件
    * @param @return    设定文件 
    * @return JsonResult    返回类型 
    * @throws
    */
    @RequestMapping("/attachmentsMail")
    @ResponseBody
    public ResultModel<String> attachmentsMail(){
    	ResultModel<String> result=new ResultModel<>();
        try {
            String filePath = "/Users/Javen/Desktop/IJPay.png";
            mailService.sendAttachmentsMail("javendev@126.com", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", filePath);
        }catch (Exception ex){
        	LOGGER.info("邮件发送失败!"+ex);
            return result.defaultError(ex.toString());
        }
        return result.defaultSuccess("");
    }
    /**
    * @Title: resourceMail 
    * @Description: 发送一封带有图片的邮件 
    * @param @return    设定文件 
    * @return JsonResult    返回类型 
    * @throws
    */
    @RequestMapping("/resourceMail")
    @ResponseBody
    public ResultModel<String> resourceMail(){
    	ResultModel<String> result=new ResultModel<>();
        try {
            String rscId = "IJPay";
            String content = "<html><body>这是有图片的邮件<br/><img src=\'cid:" + rscId + "\' ></body></html>";
            String imgPath = "/Users/Javen/Desktop/IJPay.png";
            mailService.sendResourceMail("javendev@126.com", "这邮件中含有图片", content, imgPath, rscId);

        }catch (Exception ex){
        	LOGGER.info("邮件发送失败!"+ex);
            return result.defaultError(ex.toString());
        }
        return result.defaultSuccess("");
    }
    /**
    * @Title: templateMail 
    * @Description: 发送一个带有模板的邮件
    * @param @return    设定文件 
    * @return JsonResult    返回类型 
    * @throws
    */
    @RequestMapping("/templateMail")
    @ResponseBody
    public ResultModel<String> templateMail(){
    	ResultModel<String> result=new ResultModel<>();
        try {
            Context context = new Context();
            context.setVariable("project", "IJPay");
            context.setVariable("author", "Javen");
            context.setVariable("url", "https://github.com/Javen205/IJPay");
            String emailContent = templateEngine.process("emailTemp", context);

            mailService.sendHtmlMail("javendev@126.com", "这是模板邮件", emailContent);
        }catch (Exception ex){
        	LOGGER.info("邮件发送失败!"+ex);
            return result.defaultError(ex.toString());
        }
        return result.defaultSuccess("");
    }
}
