package com.zbcn.combootmail.controller;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MailController {

	@Autowired
	private JavaMailSender mailSender;

	//自动注入
	@Autowired
	freemarker.template.Configuration freemarkerConfig;


	/**
	 * 纯文本发送
	 * @return
	 */
	@GetMapping("/simple")
	public String simpleSend(){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1363653611@qq.com");
		message.setTo("zbcn810@163.com");
		message.setSubject("主题：来自zbcn邮件");
		message.setText("zbcn 测试邮件发送.....");
		mailSender.send(message);
		return "发送成功!";
	}

	/**
	 * 带附件发送
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/attach")
	public String attachSend() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1363653611@qq.com");
		helper.setTo("zbcn810@163.com");
		helper.setSubject("主题：来自zbcn邮件(带附件)");
		helper.setText("带附件。。。。");
		//添加附件
		File qrCode = new File("com-boot-mail/IMG_1156.JPG");
		//建议文件带上后缀，可支持在线预览
		helper.addAttachment("附件.jpg", qrCode);
		mailSender.send(mimeMessage);
		return "附件邮件发送成功!";
	}


	/**
	 * html 格式
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/html")
	public String htmlSend() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1363653611@qq.com");
		helper.setTo("zbcn810@163.com");
		helper.setSubject("主题：来自zbcn邮件(html格式)");
		helper.setText("<html><body><div>执笔成念</div><div><img src='cid:winxinQr'></div></body></html>",true);

		//抄送人
//        helper.setCc("");
		//密送人
//        helper.setBcc("");

		//添加附件
		File qrCode = new File("com-boot-mail/IMG_1156.JPG");
		//建议文件带上后缀，可支持在线预览
		helper.addAttachment("附件.jpg", qrCode);
		helper.addInline("winxinQr", qrCode);
		mailSender.send(mimeMessage);
		return "success";
	}

	@GetMapping("/template")
	public String template(@RequestParam String name) throws MessagingException, IOException, TemplateException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1363653611@qq.com");
		helper.setTo("zbcn810@163.com");
		helper.setSubject("主题"+name +"你有一份来信。（from 模板）");
		//设置替换的参数对象
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", StringUtils.isEmpty(name) ? "zbcn" : name);
		//使用FreeMarker模版时，可直接使用Spring提供的工具类FreeMarkerTemplateUtils的processTemplateIntoString方法进行模版文件的替换
		//而freemarker.template.Template类，可以直接从配置类freemarker.template.Configuration中获取
		String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("mail.ftl"), model);
		helper.setText(templateString,true);
		//抄送人
//		helper.setCc("");
		//密送人
//		helper.setBcc("");
		//添加附件
		File qrCode = new File("com-boot-mail/IMG_1156.JPG");
		//建议文件带上后缀，可支持在线预览
		helper.addAttachment("附件.jpg", qrCode);
		helper.addInline("winxin", qrCode);
		mailSender.send(mimeMessage);

		return "模版文件发送成功!";
	}

}
