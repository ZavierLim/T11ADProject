package sg.edu.iss.ad.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import sg.edu.iss.ad.model.CandleModel;
import sg.edu.iss.ad.service.CandleService;
import sg.edu.iss.ad.utility.candleDataConvertor;

/*
* yahoo api key
* Harrison's key:   VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7
* Pan's key:        eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp
* Zavier's Key:     NgFvWshO6n9prAD0asbdT6tRTZVZCQal32gC5ylu
* */

@CrossOrigin
@RestController
public class CandleController {

    @Autowired
    private CandleService candleService;

    @GetMapping("/getLatestPrice/{ticker}")
    public String getLastestPrice(@PathVariable String ticker){
        String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=1d&region=US&interval=1m";

        List<CandleModel> result = candleService.getCandleData(url);

        if (result == null||result.size()==0){
            return JSON.toJSONString("no candle data of this ticker found");
        }

        return JSON.toJSONString(result.get(result.size()-1));
    }

    @GetMapping("/getCandleData/{ticker}")
    public String getCandleData(@PathVariable("ticker") String ticker){
        String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=200d&region=US&interval=1d";

        List<CandleModel> result = candleService.getCandleData(url);

        if (result == null||result.size()==0){
            return JSON.toJSONString("no data");
        }
        
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
