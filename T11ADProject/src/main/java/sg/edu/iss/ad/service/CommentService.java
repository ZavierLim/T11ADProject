package sg.edu.iss.ad.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.DTO.CommentsDTO;
import sg.edu.iss.ad.model.UserStockComment;
import sg.edu.iss.ad.repository.StockRepository;
import sg.edu.iss.ad.repository.UserRepository;
import sg.edu.iss.ad.repository.UserStockCommentRepository;
import sg.edu.iss.ad.utility.UtilityManager;

@Service
public class CommentService {
	@Autowired
	UserStockCommentRepository crepo;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	StockRepository srepo;
	
	@Transactional
	public List<CommentsDTO> getStockComments(String ticker){
		List<UserStockComment> comments=crepo.findCommentsByStock(ticker);
		List<UserStockComment> sortedcomments=comments.stream().sorted(Comparator.comparing(UserStockComment::getCommentDateTime)).collect(Collectors.toList());
		List<CommentsDTO> commentDTOList=new ArrayList<CommentsDTO>();
		for(UserStockComment eachcomment:sortedcomments) {
			CommentsDTO mapcomments=new CommentsDTO();
			mapcomments.setUsername(eachcomment.getUser().getUsername());
			mapcomments.setStockticker(eachcomment.getStock().getStockTicker());
			mapcomments.setCommentDateTime(UtilityManager.UnixToString(eachcomment.getCommentDateTime(),true));
			mapcomments.setComment(eachcomment.getComment());
			commentDTOList.add(mapcomments);
		}
		Collections.reverse(commentDTOList);
		return commentDTOList;
	}
	
	@Transactional
	public void SaveComment(CommentsDTO comment) {
		UserStockComment newcomment=new UserStockComment();
		newcomment.setUser(urepo.findByUsername(comment.getUsername()));
		newcomment.setStock(srepo.findByStockTicker(comment.getStockticker()));
		newcomment.setCommentDateTime(Long.parseLong(comment.getCommentDateTime()));
		newcomment.setComment(comment.getComment());
		crepo.save(newcomment);
	}
	
}
