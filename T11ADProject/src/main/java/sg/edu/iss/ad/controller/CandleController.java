package sg.edu.iss.ad.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import sg.edu.iss.ad.model.CandleModel;
import sg.edu.iss.ad.utility.candleDataConvertor;

/*
* yahoo api key
* Harrison's key:   VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7
* Pan's key:        eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp
* */

@CrossOrigin
@RestController
public class CandleController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/getLatestPrice/{ticker}")
    public String getLastestPrice(@PathVariable String ticker){
        String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=1d&region=US&interval=1m";
        RestTemplate restTemplate =new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Accept","application/json");

        //headers.add("x-api-key","eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp");

        headers.add("x-api-key","VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null,headers);
        ResponseEntity<String> rawResult = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);

        List<CandleModel> result = candleDataConvertor.candleResultToList(rawResult.getBody());

        if (result == null||result.size()==0){
            return JSON.toJSONString("no candle data of this ticker found");
        }

        return JSON.toJSONString(result.get(result.size()-1));
    }

    @GetMapping("/getCandleData/{ticker}")
    public String getCandleData(@PathVariable("ticker") String ticker){
        String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=200d&region=US&interval=1d";
        RestTemplate restTemplate =new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Accept","application/json");
      //headers.add("x-api-key","eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp");
        headers.add("x-api-key","VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null,headers);
        ResponseEntity<String> rawResult = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);

        List<CandleModel> result = candleDataConvertor.candleResultToList(rawResult.getBody());

        if (result == null){
            return JSON.toJSONString("no data");
        }

        System.out.println(result.size());

        return JSON.toJSONString(result);
    }


    @GetMapping("/getStockSymbol/{keyword}")
    public String getStockSymbol(@PathVariable String keyword){
        String url = "https://finnhub.io/api/v1/search?q="+keyword+"&token=c44j0b2ad3i82cb9pe4g";
        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<String> rawResult = restTemplate.getForEntity(url,String.class);
        List<Map<String,String>> result = candleDataConvertor.getTopFiveSymbols(rawResult.getBody());
        return JSON.toJSONString(result);
    }
}
