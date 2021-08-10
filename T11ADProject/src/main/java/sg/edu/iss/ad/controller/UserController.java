package sg.edu.iss.ad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ad.model.User;
import sg.edu.iss.ad.service.UserService;

@CrossOrigin
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
	
	//Create rest api
	@PostMapping("/users/add")
	public User createUser(@RequestBody User user) {
		return Uservice.createUser(user);
	}
	
	//Delete user api
	@PostMapping("/users/delete")
	public User deleteUser(@RequestBody User user) {
		return Uservice.deleteUser(user);
	}
	
	//update user API
	@PutMapping("/users/update/{id}")
	public User updateUser(@PathVariable String id,@RequestBody User user) {
		return Uservice.updateUser(user);
	}
	
	@DeleteMapping("/users/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id){
		User user= Uservice.FindUser(id);
		Uservice.deleteUser(user);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
