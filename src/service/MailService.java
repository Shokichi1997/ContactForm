package service;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/send-mail")
public class MailService extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doGet(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = "";
		String email = "hoangvanlamcntt@gmail.com";
		String phone = "";
		String messageFromUser = "";
		if(req.getParameter("name") != null) {
			name = req.getParameter("name");
		}
		
		if(req.getParameter("email") != null) {
			email = req.getParameter("email");
		}
		
		if(req.getParameter("phone") != null) {
			phone = req.getParameter("phone");
		}
		
		if(req.getParameter("message") != null) {
			messageFromUser = req.getParameter("message");
		}
		
		
		String host = "smtp.gmail.com";
		final String user = "hoangvanlam197@gmail.com";
		final String password = "456@ovi.com";//

		String to = email;

		// Get the session object
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(
					Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Send mail from web");
			message.setText(messageFromUser+"\n---------------------\nName: "+name+"\nPhone:"+phone);

			Transport.send(message);

			System.out.println("message sent successfully...");
			resp.sendRedirect("/SendMailServiceWeb/index.html");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

