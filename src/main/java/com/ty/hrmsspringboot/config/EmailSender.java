package com.ty.hrmsspringboot.config;

import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ty.hrmsspringboot.dao.BatchDao;
import com.ty.hrmsspringboot.dao.UserDao;
import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.Batch;
import com.ty.hrmsspringboot.entity.User;
import com.ty.hrmsspringboot.exceptions.UserNotfoundException;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailSender {

	public boolean sendEmail(String to, String from, String subject, String text) {

		boolean flag = false;

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		String userName = "hrmstest1322";
		String password = "wfxc jrtl lfnd wfgo";
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		try {
			Message mailMessage = new MimeMessage(session);

			mailMessage.setFrom(new InternetAddress(from));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mailMessage.setSubject(subject);
			mailMessage.setText(text);
			Transport.send(mailMessage);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	public ResponseEntity<ResponseStructure<Batch>> name(Batch batch, int userId) {
		UserDao userDao= new UserDao();
		BatchDao batchDao= new BatchDao();
		User user = userDao.getUserById(userId);
		if (user != null) {
			Batch batchReceived = batchDao.saveBatch(batch);
			user.getBatches().add(batch);
			userDao.saveUser(user);
			EmailSender emailSender = new EmailSender();
			String toemail = "hrmstest1322@gmail.com";
			String from = "hrmstest1322@gmail.com";
			String subject1 = "New Batch Assigned!!";
			String text = "Hello " + user.getName() + ", \nNew Batch for the " + batch.getSubject()
					+ " subject is assigned to you\nrefer the link below ";

			boolean result = emailSender.sendEmail(toemail, from, subject1, text);

			if (result) {
				ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Batch is assigned to " + user.getName());
				responseStructure.setData(batchReceived);
				return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.CREATED);
			}

		}
		throw new UserNotfoundException();
	}
	

//	@PostMapping("/name/{userid}")
//	public ResponseEntity<ResponseStructure<Batch>> name(@RequestBody Batch batch, @PathVariable int userid){
//		return batchService.name(batch, userid);
//	}
	
}
