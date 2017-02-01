package org.lenin.app;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class AppMail {
	@Autowired
	private JavaMailSender javaMailSender;

	public void send(String from, String to, String subject, String message) {
		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(to);
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setText(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
		}
		javaMailSender.send(mail);
	}
}
