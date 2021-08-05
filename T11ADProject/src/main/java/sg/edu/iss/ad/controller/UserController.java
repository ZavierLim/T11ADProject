package sg.edu.iss.ad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ad.model.User;
import sg.edu.iss.ad.service.UserService;

@RestController
@RequestMapping("")
public class UserController {
	@Autowired
	private UserService Uservice;
	
	@GetMapping("/users")
	public List<User> Findallusers(){
		return Uservice.findallUsers();
	}
	
	@GetMapping("/users/{id}")
	public User Findallusers(@PathVariable Long id){
		return Uservice.FindUser(id);
	}
}
