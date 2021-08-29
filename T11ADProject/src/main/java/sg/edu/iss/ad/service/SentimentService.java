package sg.edu.iss.ad.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sg.edu.iss.ad.DTO.CommentsDTO;
import sg.edu.iss.ad.model.CandleModel;
import sg.edu.iss.ad.utility.candleDataConvertor;

@Service
public class SentimentService {

	public String getSentiment(String url) {
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers= new HttpHeaders();

        
        ResponseEntity<String> rawResult = restTemplate.getForEntity(url, String.class);
        JSONObject jsonObject = JSON.parseObject(rawResult.getBody());
        String sentiment = jsonObject.get("sentiment").toString();
	      
        return sentiment;
	}
	
	public String getCommentSentiment(String url, List<CommentsDTO> comments) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		
//		Map<String, List<CommentsDTO>> map = new HashMap<>();
//		map.put("data", comments);
//		HttpEntity<Map<String, List<CommentsDTO>>> entity = new HttpEntity<>(map, headers);
//
        ResponseEntity<String> rawResult = restTemplate.postForEntity(url, comments, String.class);
        JSONObject jsonObject = JSON.parseObject(rawResult.getBody());
        String sentiment = jsonObject.get("sentiment").toString();
//	      
//        return sentiment;
		return sentiment;
	}
}
