package sg.edu.iss.ad.controller;

import java.time.LocalDateTime;
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
* Wanling's key:    3xoXzXZBYw9YffOybSpCZ5lnG3brJAzK4apdKB6r
<<<<<<< HEAD
* Zavier's key: 	NgFvWshO6n9prAD0asbdT6tRTZVZCQal32gC5ylu
=======
* Zavier's Key:     NgFvWshO6n9prAD0asbdT6tRTZVZCQal32gC5ylu
>>>>>>> 911a1f247bf9f7b6bbed411bd57f2d3142d03a77
* */

@CrossOrigin
@RestController
public class CandleController {

    @Autowired
    private CandleService candleService;

    @GetMapping("/getLatestPrice/{ticker}")
    public String getLastestPrice(@PathVariable String ticker){
        String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=1d&region=US&interval=1d";

//        RestTemplate restTemplate =new RestTemplate();
//        HttpHeaders headers=new HttpHeaders();
//        headers.add("Accept","application/json");
//
//        headers.add("x-api-key","eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp");
//        //headers.add("x-api-key","NgFvWshO6n9prAD0asbdT6tRTZVZCQal32gC5ylu");
//        
//        //headers.add("x-api-key","VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7");
//        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null,headers);
//        ResponseEntity<String> rawResult = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);
//
//        List<CandleModel> result = candleDataConvertor.candleResultToList(rawResult.getBody());

        List<CandleModel> result = candleService.getCandleData(url);

        if (result == null||result.size()==0){
            return JSON.toJSONString("no candle data of this ticker found");
        }

        return JSON.toJSONString(result.get(result.size()-1));
    }

    @GetMapping("/getCandleData/{ticker}")
    public String getCandleData(@PathVariable("ticker") String ticker){
        String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=200d&region=US&interval=1d";
//
//        RestTemplate restTemplate =new RestTemplate();
//        HttpHeaders headers=new HttpHeaders();
//        headers.add("Accept","application/json");
//      headers.add("x-api-key","eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp");
//        //headers.add("x-api-key","NgFvWshO6n9prAD0asbdT6tRTZVZCQal32gC5ylu");
//        //headers.add("x-api-key","VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7");
//        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null,headers);
//        ResponseEntity<String> rawResult = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);


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
    
    @GetMapping("/signalforbullish/{ticker}")
    public List<String> getBullishEngulfingforstock(@PathVariable String ticker) {
    	String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=200d&region=US&interval=1d";
        List<CandleModel> result = candleService.getCandleData(url);
        List<String> dates=candleService.getBullishEngulfingCandleSignal(result);
        System.out.println(dates);
        return dates;
    }
    
    @GetMapping("/signalforbearish/{ticker}")
    public List<String> getBearishEngulfingforstock(@PathVariable String ticker) {
    	String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=200d&region=US&interval=1d";
        List<CandleModel> result = candleService.getCandleData(url);
        List<String> dates=candleService.getBearishEngulfingCandleSignal(result);
        System.out.println(dates);
        return dates;
    }
    
    @GetMapping("/signalforeveningstar/{ticker}")
    public List<String> getEveningStarForStock(@PathVariable String ticker) {
    	String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=200d&region=US&interval=1d";
        List<CandleModel> result = candleService.getCandleData(url);
        List<String> dates=candleService.getEveningStar(result);
        System.out.println(dates);
        return dates;
    }
    
    @GetMapping("/signalformorningstar/{ticker}")
    public List<String> getMorningStarForStock(@PathVariable String ticker) {
    	String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=200d&region=US&interval=1d";
        List<CandleModel> result = candleService.getCandleData(url);
        List<String> dates=candleService.getMorningStarCandle(result);
        System.out.println(dates);
        return dates;
    }
    
    //when the user adds to watchlist
    //1. get the stock symbol
    //2. use API to get the 200 day data
    //3. store the stock symbol into userStockWatchList
    //4. calculate using service class the 4 candlesticks using Closing-opening, opening - closing
    //5. Store the candlehistory if 4. returns true
    
    //2nd method - when user adds to watchlist
    //1. get the stock symbol
    //2. use the api , store into userStock
    
    //-when the user clicks' track' candle in the watchlist
    //1. post request sent from front end - bullish engulfing
    //2. add the candle into UserCandleWatchList
    //3. then we call the API and store all the CandleHistory
    
    //- when the user logs in.call the api for all the stocks
}
