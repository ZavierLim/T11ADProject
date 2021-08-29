package sg.edu.iss.ad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sg.edu.iss.ad.DTO.CommentsDTO;
import sg.edu.iss.ad.service.CommentService;
import sg.edu.iss.ad.service.SentimentService;

@CrossOrigin
@RestController
public class SentimentController {

	@Autowired
    private SentimentService sentimentService;
	@Autowired
	private CommentService cservice;
	
	@GetMapping("/getSentiment/{ticker}")
    public String getSentiment(@PathVariable String ticker){
        String url = "https://ad-proj-sentiment-analysis.herokuapp.com/sentiment/search_term=" + ticker;

        String sentiment = sentimentService.getSentiment(url);

        if (sentiment == null){
            return JSON.toJSONString("no sentiment data of this ticker found");
        }

        return JSON.toJSONString(sentiment);
    }
	
	
	@GetMapping("/getCommentSentiment/{ticker}")
	public String getCommentSentiment(@PathVariable String ticker){

		List<CommentsDTO> comments = cservice.getStockComments(ticker);

		String url = "https://ad-proj-sentiment-analysis.herokuapp.com/comments-sentiment";

		String sentiment = sentimentService.getCommentSentiment(url, comments);

        if (sentiment == null){
            return JSON.toJSONString("no comments data found");
        }

        return JSON.toJSONString(sentiment);
		
				
	}

}
