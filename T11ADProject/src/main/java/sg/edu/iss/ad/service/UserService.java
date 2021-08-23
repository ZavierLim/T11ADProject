package sg.edu.iss.ad.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import sg.edu.iss.ad.model.MailVo;
import sg.edu.iss.ad.model.User;
import sg.edu.iss.ad.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository urepo;

	@Autowired
	JavaMailSender javaMailSender;

	@Transactional
	public List<User> findallUsers(){
		return urepo.findAll();
	}
	
	@Transactional
	public User FindUser(Long id){
		return urepo.findById(id).orElse(null);
	}

	@Transactional
	public User findUserByUsername(String username){
		return urepo.findByUsername(username);
	}
	
	public User createUser(User user) {
		urepo.save(user);
		return user;
	}
	
	public User updateUser(User user) {
		User founduser=urepo.findById(user.getId()).orElse(null);
		founduser.setUsername(user.getUsername());
		founduser.setEmail(user.getEmail());
		founduser.setPassword(user.getPassword());
		urepo.save(founduser);
		return user;
	}
	
	public User deleteUser(User user) {
		User founduser=urepo.findById(user.getId()).orElse(null);
		urepo.delete(founduser);
		return user;
	}
	
	public User AuthenticateUser(User user) {
		User okuser=urepo.finduser(user.getUsername(), user.getPassword());
		if(okuser!=null) 		
			return okuser;
		return null;
	}

	public void sendEmailNotification(MailVo mail) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom(mail.getFrom());
		simpleMailMessage.setTo(mail.getTo());
		simpleMailMessage.setSubject(mail.getSubject());
		simpleMailMessage.setText(mail.getText());
		javaMailSender.send(simpleMailMessage);
	}
	
	public Boolean findexistinguser(User user) {
		User isuserexisting=urepo.findByUsername(user.getUsername());
		//if username used, return true
		if(isuserexisting!=null) {
			return true;
		}
		return false;
	}
	
	
}
