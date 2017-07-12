package org.khmeracademy.auction.controllers.rest;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.khmeracademy.auction.SmtpMailSender;
import org.khmeracademy.auction.entities.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SmtpSendMailController {
	@Autowired
	private SmtpMailSender smtpMailSender;	
	@RequestMapping(value="/send-mail",method=RequestMethod.POST)	
	public ResponseEntity<Map<String,Object>> sendMail(@RequestBody SendMail sendMail) {
		Map<String,Object> map = new HashMap<String,Object>();
		try{
		smtpMailSender.send(
				sendMail.getTo(), 
				sendMail.getSubject(), 
				sendMail.getBody());
				// mail is sent
				map.put("MESSAGE", "Sending mail successfully");
				map.put("STATUS", true);
		} catch(MessagingException ex){
				map.put("MESSAGE", "Sending mail unsuccessfully");
				map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
}
