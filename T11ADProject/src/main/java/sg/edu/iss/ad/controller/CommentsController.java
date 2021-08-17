package sg.edu.iss.ad.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ad.DTO.CommentsDTO;
import sg.edu.iss.ad.model.Stock;
import sg.edu.iss.ad.model.User;
import sg.edu.iss.ad.model.UserStockComment;
import sg.edu.iss.ad.service.CommentService;
import sg.edu.iss.ad.utility.UtilityManager;

@RestController
@CrossOrigin
public class CommentsController {
	@Autowired
	CommentService cservice;
	
	@GetMapping("/comments/{ticker}")
	public ResponseEntity<List<CommentsDTO>> GetCommentsByStock(@PathVariable String ticker) {
		List<CommentsDTO> comments= cservice.getStockComments(ticker);
		return ResponseEntity.ok(comments);
	}
	
	@PostMapping("/comments/{ticker}")
	public ResponseEntity<CommentsDTO> PostNewComment(@PathVariable String ticker,@RequestBody CommentsDTO comment){
		cservice.SaveComment(comment);
		comment.setCommentDateTime(UtilityManager.UnixToDate(Long.parseLong(comment.getCommentDateTime()), true));
		return ResponseEntity.ok(comment);
	}
	
	
	
}
