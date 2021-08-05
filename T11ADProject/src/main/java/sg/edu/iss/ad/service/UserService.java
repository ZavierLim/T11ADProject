package sg.edu.iss.ad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.model.User;
import sg.edu.iss.ad.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository urepo;
	
	public List<User> findallUsers(){
		return urepo.findAll();
	}
	
	public User FindUser(Long id){
		return urepo.findById(id).orElse(null);
	}
}
