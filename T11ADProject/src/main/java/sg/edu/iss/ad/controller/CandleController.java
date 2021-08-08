package sg.edu.iss.ad.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sg.edu.iss.ad.model.CandleModel;
import sg.edu.iss.ad.utility.candleDataConvertor;

import java.util.Date;
import java.util.List;

@RestController
public class CandleController {
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/getCandleData")
    public String getCandleData(){
        String url="https://yfapi.net/v8/finance/chart/AAPL?range=1d&region=US&interval=1m";
        RestTemplate restTemplate =new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Accept","application/json");
        headers.add("x-api-key","eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null,headers);
        ResponseEntity<String> rawResult = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);


        List<CandleModel> result = candleDataConvertor.candleResultToList(rawResult.getBody());


        System.out.println(result.size());

        return JSON.toJSONString(result);
//        return rawResult.getBody();
    }
}
