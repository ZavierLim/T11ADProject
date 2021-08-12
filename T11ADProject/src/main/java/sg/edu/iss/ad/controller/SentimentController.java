package sg.edu.iss.ad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import sg.edu.iss.ad.service.SentimentService;

@CrossOrigin
@RestController
public class SentimentController {

	@Autowired
    private SentimentService sentimentService;
	
	@GetMapping("/getSentiment/{ticker}")
    public String getSentiment(@PathVariable String ticker){
        String url = "https://ad-proj-sentiment-analysis.herokuapp.com/sentiment/search_term=" + ticker;

        String sentiment = sentimentService.getSentiment(url);

        if (sentiment == null){
            return JSON.toJSONString("no sentiment data of this ticker found");
        }

        return JSON.toJSONString(sentiment);
    }

}
