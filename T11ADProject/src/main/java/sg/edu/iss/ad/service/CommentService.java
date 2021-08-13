package sg.edu.iss.ad.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.model.UserStockComment;
import sg.edu.iss.ad.repository.UserStockCommentRepository;

@Service
public class CommentService {
	@Autowired
	UserStockCommentRepository crepo;
	
	@Transactional
	public List<UserStockComment> getStockComments(String ticker){
		return crepo.findCommentsByStock(ticker);
	}
	
}
