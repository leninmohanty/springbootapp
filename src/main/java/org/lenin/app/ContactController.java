package org.lenin.app;

import java.util.ArrayDeque;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
	@Autowired
	private AppMail email;

	private static Queue<Contact> queue = new ArrayDeque<>();

	@RequestMapping(value = "/contactme", method = RequestMethod.POST)
	public String greeting(@ModelAttribute Contact contact, BindingResult result) {
		queue.add(contact);
		return "Thank you for contacting me.";
	}

	@Scheduled(fixedDelay = 10000)
	public void sendEmail() {
		if (!queue.isEmpty()) {
			Contact contact = queue.poll();
			email.send("admin@leninmohanty.com", contact.getEmail(), "Thank you for contacting me.",
					getMessage(contact));
			email.send("admin@leninmohanty.com", "info@leninmohanty.com", contact.getName()+" contacted you.",
					getInfoMessage(contact));
			System.out.println("mail sent successfully");
		}

	}

	private String getInfoMessage(Contact contact) {
		StringBuilder builder = new StringBuilder();
		builder.append("Dear Admin" + "\n");
		builder.append(contact.getName() + " contacted with you his query.\n");
		builder.append("Query details\n---------------------\n");
		builder.append("\nEmail : "+ contact.getEmail());
		builder.append("\nMessage : "+contact.getMessage());
		builder.append("\n\n");
		return builder.toString();
	}

	private String getMessage(Contact contact) {
		StringBuilder builder = new StringBuilder();
		builder.append("Dear " + contact.getName() + "\n");
		builder.append("Thank you for contacting me. I will get you back with your query.\n");
		builder.append("Thanks & Regards,\n Lenin Mohanty");
		return builder.toString();
	}
}
