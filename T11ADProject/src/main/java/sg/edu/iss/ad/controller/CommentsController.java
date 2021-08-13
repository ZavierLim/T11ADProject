package sg.edu.iss.ad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ad.model.Stock;
import sg.edu.iss.ad.model.User;
import sg.edu.iss.ad.model.UserStockComment;
import sg.edu.iss.ad.service.CommentService;

@RestController
@CrossOrigin
public class CommentsController {
	@Autowired
	CommentService cservice;
	
	@GetMapping("/comments/{ticker}")
	public ResponseEntity<List<UserStockComment>> GetCommentsByStock(@PathVariable String ticker) {
		List<UserStockComment> comments= cservice.getStockComments(ticker);
		return ResponseEntity.ok(comments);
		
	}
}
