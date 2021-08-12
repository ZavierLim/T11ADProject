package sg.edu.iss.ad.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
}
