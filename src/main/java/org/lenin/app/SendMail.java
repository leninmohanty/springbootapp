package org.lenin.app;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class SendMail {
	@Autowired
	private JavaMailSender javaMailSender;

	public void send() {
		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo("leninmohanty@gmail.com");
			//helper.setReplyTo("someone@localhost");
			helper.setFrom("admin@leninmohanty.com");
			helper.setSubject("Lorem ipsum");
			helper.setText("Lorem ipsum dolor sit amet [...]");
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
		}
		javaMailSender.send(mail);
		// return helper;
	}
}
