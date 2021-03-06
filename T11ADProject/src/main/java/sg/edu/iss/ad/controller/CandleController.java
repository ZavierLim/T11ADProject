package sg.edu.iss.ad.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import sg.edu.iss.ad.DTO.CandleHistoryDTO;
import sg.edu.iss.ad.model.CandleModel;
import sg.edu.iss.ad.model.MailVo;
import sg.edu.iss.ad.model.User;
import sg.edu.iss.ad.service.CandleService;
import sg.edu.iss.ad.service.UserService;
import sg.edu.iss.ad.utility.candleDataConvertor;

/*
* yahoo api key
* Harrison's key:   VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7
* Pan's key:        eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp
* Wanling's key:    3xoXzXZBYw9YffOybSpCZ5lnG3brJAzK4apdKB6r
* Zavier's key: 	NgFvWshO6n9prAD0asbdT6tRTZVZCQal32gC5ylu
* */

@CrossOrigin
@RestController
public class CandleController {

    @Autowired
    private CandleService candleService;

    @Autowired
    private UserService userService;

    //Get latest closing price of stock
    @GetMapping("/getLatestPrice/{ticker}")
    public String getLastestPrice(@PathVariable String ticker){
        String url = "https://finnhub.io/api/v1/stock/profile2?symbol="+ticker+"&token=c44j0b2ad3i82cb9pe4g";
        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<String> rawCompanyProfile = restTemplate.getForEntity(url,String.class);
        JSONObject companyProfile = JSON.parseObject(rawCompanyProfile.getBody());
        String companyName = companyProfile.getString("name");

        List<CandleModel> result = candleService.getCandleData(ticker);

        if (result == null||result.size()==0){
            return JSON.toJSONString("no candle data of this ticker found");
        }

        Map<String,Object> latestPrice = new HashMap();
        latestPrice.put("description",companyName);
        latestPrice.put("close",result.get(result.size()-1).getClose());

        return JSON.toJSONString(latestPrice);
    }

    //Get 200 days close, high,low,open,timestamp,volume
    @GetMapping("/getCandleData/{ticker}")
    public String getCandleData(@PathVariable("ticker") String ticker){
        List<CandleModel> result = candleService.getCandleData(ticker);
        if (result == null||result.size()==0){
            return JSON.toJSONString("no data");
        }
        return JSON.toJSONString(result);
    }

    //Gets the symbol of the stock and stockname
    @GetMapping("/getStockSymbol/{keyword}")
    public String getStockSymbol(@PathVariable String keyword){
        String url = "https://finnhub.io/api/v1/search?q="+keyword+"&token=c44j0b2ad3i82cb9pe4g";
        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<String> rawResult = restTemplate.getForEntity(url,String.class);
        List<Map<String,String>> result = candleDataConvertor.getTopFiveSymbols(rawResult.getBody());
        return JSON.toJSONString(result);
    }
    
    //Below 4 methods show the dates for the candle signals for counter checking
    @GetMapping("/signalforbullish/{ticker}")
    public List<String> getBullishEngulfingforstock(@PathVariable String ticker) {
        List<CandleModel> result = candleService.getCandleData(ticker);
        List<String> dates=candleService.getBullishEngulfingCandleSignal(result);
        System.out.println(dates);
        return dates;
    }
    
    @GetMapping("/signalforbearish/{ticker}")
    public List<String> getBearishEngulfingforstock(@PathVariable String ticker) {
        List<CandleModel> result = candleService.getCandleData(ticker);
        List<String> dates=candleService.getBearishEngulfingCandleSignal(result);
        System.out.println(dates);
        return dates;
    }
    
    @GetMapping("/signalforeveningstar/{ticker}")
    public List<String> getEveningStarForStock(@PathVariable String ticker) {
        List<CandleModel> result = candleService.getCandleData(ticker);
        List<String> dates=candleService.getEveningStar(result);
        System.out.println(dates);
        return dates;
    }
    
    @GetMapping("/signalformorningstar/{ticker}")
    public List<String> getMorningStarForStock(@PathVariable String ticker) {
        List<CandleModel> result = candleService.getCandleData(ticker);
        List<String> dates=candleService.getMorningStarCandle(result);
        System.out.println(dates);
        return dates;
    }
    
    //Scan stock and find all signals + send email
    @PostMapping("/candlehistory")
    public ResponseEntity<List<CandleHistoryDTO>> getCandleHistoryByWatchList(@RequestBody CandleHistoryDTO userinput){

        User userResult = userService.findUserByUsername(userinput.getUsername());
        MailVo mailVo = new MailVo();
        mailVo.setFrom("PCXGudrew@163.com");
        mailVo.setTo(userResult.getEmail());
    	List<CandleHistoryDTO> allcandlehistory=candleService.getcandlehistory(userinput.getUsername(),userinput.getStockticker());
//    	List<CandleHistoryDTO> result = allcandlehistory.stream().filter(ach->ach.getCandle().equals(userinput.getCandle())).sorted().collect(Collectors.toList());
        String title;//subject
        String text;
    	if(allcandlehistory.size()>0){
            CandleHistoryDTO latestCandle = allcandlehistory.get(0);
            title = "Candle Notification";
            text = "Hi "+userResult.getUsername()+", your latest candle type is "+latestCandle.getCandle()+" that appeared on "+latestCandle.getDatetime();
        }
        else{
            title = "No candle found";
            text = title;
        }
        mailVo.setSubject(title);
        mailVo.setText(text);
        try{
            userService.sendEmailNotification(mailVo);
        }
        catch (MailSendException mse){
            System.out.println("target mail does not exist.");
        }
        catch (Exception e){
            System.out.println("an error occur.");
        }
        return ResponseEntity.ok(allcandlehistory);
    }
}
