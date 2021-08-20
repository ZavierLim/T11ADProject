package sg.edu.iss.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import sg.edu.iss.ad.DTO.CandleHistoryDTO;
import sg.edu.iss.ad.model.CandleHistory;
import sg.edu.iss.ad.model.CandleModel;
import sg.edu.iss.ad.model.MailVo;
import sg.edu.iss.ad.model.UserCandleWatchList;
import sg.edu.iss.ad.repository.CandleHistoryRepository;
import sg.edu.iss.ad.repository.CandleRepository;
import sg.edu.iss.ad.repository.StockRepository;
import sg.edu.iss.ad.repository.UserCandleWatchListRepository;
import sg.edu.iss.ad.utility.candleDataConvertor;
import sg.edu.iss.ad.utility.UtilityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class CandleService{
	@Autowired
	UserCandleWatchListRepository uwclrepo;
	
	@Autowired
	CandleHistoryRepository chrepo;

	@Autowired
	JavaMailSenderImpl javaMailSender;

    public List<CandleModel> getCandleData(String ticker) {
        String url="https://yfapi.net/v8/finance/chart/" + ticker + "?range=200d&region=US&interval=1d";
        RestTemplate restTemplate =new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Accept","application/json");
        //headers.add("x-api-key","eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp");
        //headers.add("x-api-key","VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7");
        headers.add("x-api-key","3xoXzXZBYw9YffOybSpCZ5lnG3brJAzK4apdKB6r");
        
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null,headers);
        ResponseEntity<String> rawResult = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);

        List<CandleModel> result = candleDataConvertor.candleResultToList(rawResult.getBody());

        return result;
    }
    
    //To get list of bullish engulfing for past 200 days, returns MM-DD-YYYY
    public List<String> getBullishEngulfingCandleSignal(List<CandleModel> result){
    	List<String> Dates= new ArrayList<>();
    	for(int i=0;i<200-1;i++) {
    		if(result.get(i).getOpen()>result.get(i).getClose() //0 opening lesser than 0 close
    			&& result.get(i+1).getOpen()<result.get(i+1).getClose() //1 open lesser than 1 close
    			&& result.get(i+1).getOpen()<result.get(i).getClose() //1 open lesser than 0 close
    			&& result.get(i+1).getClose()>result.get(i).getOpen()) { //1 close > 1 open 
    				Dates.add(UtilityManager.UnixToString(result.get(i+1).getTimestamp(),false));
    				System.out.println(result.get(i+1).getTimestamp());
    		}

    	}
    	Collections.reverse(Dates); //to get latest first
    	return Dates;
    }
    //same as above, returns UNIX
    public List<Long> getBullishEngulfingCandleSignalUNIX(List<CandleModel> result){
    	List<Long> timestamps= new ArrayList<>();
    	for(int i=0;i<200-1;i++) {
    		if(result.get(i).getOpen()>result.get(i).getClose() //0 opening lesser than 0 close
    				&& result.get(i+1).getOpen()<result.get(i+1).getClose() //1 open lesser than 1 close
    				&& result.get(i+1).getOpen()<result.get(i).getClose() //1 open lesser than 0 close
    				&& result.get(i+1).getClose()>result.get(i).getOpen()) { //1 close > 1 open 
    				timestamps.add(result.get(i+1).getTimestamp());
    		}
    	}
    	return timestamps;
    }
    
    public List<String> getBearishEngulfingCandleSignal(List<CandleModel> result){
       	List<String> Dates= new ArrayList<>();
    	for(int i=0;i<200-1;i++) {
    		if(result.get(i).getOpen()<result.get(i).getClose()
    			&& result.get(i+1).getOpen()>result.get(i+1).getClose() //1 close lower
    			&& result.get(i+1).getOpen()>result.get(i).getClose() //1 open more than 0 close
    			&& result.get(i+1).getClose()<result.get(i).getOpen()) { //1 close less than 0 open 
    				Dates.add(UtilityManager.UnixToString(result.get(i+1).getTimestamp(),false));   			
    		}
    	}
    	Collections.reverse(Dates); //to get latest first
    	return Dates;
    }
    //same as above, return UNIX instead
    public List<Long> getBearishEngulfingCandleSignalUNIX(List<CandleModel> result){
       	List<Long> Dates= new ArrayList<>();
    	for(int i=0;i<200-1;i++) {
    		if(result.get(i).getOpen()<result.get(i).getClose()
    			&& result.get(i+1).getOpen()>result.get(i+1).getClose() //1 open lesser than 1 close
    			&& result.get(i+1).getOpen()>result.get(i).getClose() //1 open more than 0 close
    			&& result.get(i+1).getClose()<result.get(i).getOpen()) { //1 close less than 0 open 
    				Dates.add(result.get(i+1).getTimestamp());   			
    		}
    	}
    	Collections.reverse(Dates); //to get latest first
    	return Dates;
    }
    
    
    public List<String> getEveningStar(List<CandleModel> result){
    	List<String> Dates=new ArrayList<>();
    	for(int i=9;i<200-1;i++) {
    		//1. Find the max for the past 10 days
    		List<CandleModel> sublist=result.subList(i-9,i);
    		double tendayhigh=0;
    		for(CandleModel close:sublist ) {
    			if(close.getHigh()>tendayhigh)
    				tendayhigh=close.getHigh();
    		}
    		
    		//2. Find higher and lower between day's closing and opening
    		double daymax,daymin=0;
    		if(result.get(i).getClose()>result.get(i).getOpen()) {
    			daymax=result.get(i).getClose();
    			daymin=result.get(i).getOpen();    			
    		}
    		else {
    			daymax=result.get(i).getOpen();
    			daymin=result.get(i).getClose();
    		}

    		//3.Check that top wick is higher than bottom wick by at least 3x
    		boolean isTopHigherThanBottom=false;
    		if(result.get(i).getHigh()-daymax>(daymin-result.get(i).getLow())*3) {
    			isTopHigherThanBottom=true;
    		}
    		
    		//4. Check that the absolute between Open and Close is less than (high-low)*.25
    		boolean isAbsoluteLowerThanHigh=false;
    		if(Math.abs(daymax-daymin)<(result.get(i).getHigh()-result.get(i).getLow())*0.25){
    			isAbsoluteLowerThanHigh=true;
    		}
    		
    		if(result.get(i).getHigh()>tendayhigh
    			&& isTopHigherThanBottom && isAbsoluteLowerThanHigh ) {
    			Dates.add(UtilityManager.UnixToString(result.get(i).getTimestamp(),false));
    		}
    			
    	}
    	Collections.reverse(Dates); //to get latest first
    	return Dates;
    }
    //Same as above, return UNIX instead
    public List<Long> getEveningStarUNIX(List<CandleModel> result){
    	List<Long> Dates=new ArrayList<>();
    	for(int i=9;i<200-1;i++) {
    		//1. Find the max for the past 10 days
    		List<CandleModel> sublist=result.subList(i-9,i);
    		double tendayhigh=0;
    		for(CandleModel close:sublist ) {
    			if(close.getHigh()>tendayhigh)
    				tendayhigh=close.getHigh();
    		}
    		
    		//2. Find higher and lower between day's closing and opening
    		double daymax,daymin=0;
    		if(result.get(i).getClose()>result.get(i).getOpen()) {
    			daymax=result.get(i).getClose();
    			daymin=result.get(i).getOpen();    			
    		}
    		else {
    			daymax=result.get(i).getOpen();
    			daymin=result.get(i).getClose();
    		}

    		//3.Check that top wick is higher than bottom wick by at least 3x
    		boolean isTopHigherThanBottom=false;
    		if(result.get(i).getHigh()-daymax>(daymin-result.get(i).getLow())*3) {
    			isTopHigherThanBottom=true;
    		}
    		
    		//4. Check that the absolute between Open and Close is less than (high-low)*.25
    		boolean isAbsoluteLowerThanHigh=false;
    		if(Math.abs(daymax-daymin)<(result.get(i).getHigh()-result.get(i).getLow())*0.25){
    			isAbsoluteLowerThanHigh=true;
    		}
    		
    		if(result.get(i).getHigh()>tendayhigh
    			&& isTopHigherThanBottom && isAbsoluteLowerThanHigh ) {
    			Dates.add(result.get(i).getTimestamp());
    		}
    			
    	}
    	Collections.reverse(Dates); //to get latest first
    	return Dates;
    }
    
    public List<String> getMorningStarCandle (List<CandleModel> result){
    	List<String> Dates=new ArrayList<>();
    	for(int i=9;i<200-1;i++) {
    		//1. Find the min for the past 10 days
    		List<CandleModel> sublist=result.subList(i-9,i);
    		double tendaylow=Double.MAX_VALUE;
    		for(CandleModel close:sublist ) {
    			if(close.getLow()<tendaylow)
    				tendaylow=close.getLow();
    		}
    		
    		//2. Find higher and lower between day's closing and opening
    		double daymax,daymin=0;
    		//if opening is higher than closing, then max = open, min-close
    		if(result.get(i).getOpen()>result.get(i).getClose()) {
    			daymax=result.get(i).getOpen();
    			daymin=result.get(i).getClose();
    		}
    		else {
    			daymax=result.get(i).getClose();
    			daymin=result.get(i).getOpen();
    		}


    		//3.Check that bottom wick is higher than top wick by at least 3x
    		boolean isBottomLowerThanTop=false;
    		//if the lowest point minus minimum
    		if(daymin-result.get(i).getLow()>(result.get(i).getHigh()-daymax)*3) {
    			isBottomLowerThanTop=true;
    		}
    		
    		//4. Check that the absolute between Open and Close is less than (high-low)*.25
    		boolean isAbsoluteLowerThanHigh=false;
    		if(Math.abs(daymax-daymin)<(result.get(i).getHigh()-result.get(i).getLow())*0.25){
    			isAbsoluteLowerThanHigh=true;
    		}
    		
    		if(result.get(i).getLow()<tendaylow
    			&& isBottomLowerThanTop 
    			&& isAbsoluteLowerThanHigh 
    			) {
    			Dates.add(UtilityManager.UnixToString(result.get(i).getTimestamp(),false));
    		}
    			
    	}
    	Collections.reverse(Dates); //to get latest first
    	return Dates;
    }
    //Same as above,return UNIX instead
    public List<Long> getMorningStarCandleUNIX (List<CandleModel> result){
    	List<Long> Dates=new ArrayList<>();
    	for(int i=9;i<200-1;i++) {
    		//1. Find the min for the past 10 days
    		List<CandleModel> sublist=result.subList(i-9,i);
    		double tendaylow=Double.MAX_VALUE;
    		for(CandleModel close:sublist ) {
    			if(close.getLow()<tendaylow)
    				tendaylow=close.getLow();
    		}
    		
    		//2. Find higher and lower between day's closing and opening
    		double daymax,daymin=0;
    		//if opening is higher than closing, then max = open, min-close
    		if(result.get(i).getOpen()>result.get(i).getClose()) {
    			daymax=result.get(i).getOpen();
    			daymin=result.get(i).getClose();
    		}
    		else {
    			daymax=result.get(i).getClose();
    			daymin=result.get(i).getOpen();
    		}


    		//3.Check that bottom wick is higher than top wick by at least 3x
    		boolean isBottomLowerThanTop=false;
    		//if the lowest point minus minimum
    		if(daymin-result.get(i).getLow()>(result.get(i).getHigh()-daymax)*3) {
    			isBottomLowerThanTop=true;
    		}
    		
    		//4. Check that the absolute between Open and Close is less than (high-low)*.25
    		boolean isAbsoluteLowerThanHigh=false;
    		if(Math.abs(daymax-daymin)<(result.get(i).getHigh()-result.get(i).getLow())*0.25){
    			isAbsoluteLowerThanHigh=true;
    		}
    		
    		if(result.get(i).getLow()<tendaylow
    			&& isBottomLowerThanTop 
    			&& isAbsoluteLowerThanHigh 
    			) {
    			Dates.add(result.get(i).getTimestamp());
    		}
    			
    	}
    	Collections.reverse(Dates); //to get latest first
    	return Dates;
    }
    
    @Transactional
    public List<CandleHistoryDTO> getcandlehistory(String username,String stockticker){
    	//this is all the stocks regardless true or false
    	List<UserCandleWatchList> candlewatchlist=uwclrepo.findUserCandleWatchListByUsername(username);
    	//create new list
    	List<CandleHistoryDTO> tofrontend=new ArrayList<CandleHistoryDTO>();
    	
    	//for each history
    	for(UserCandleWatchList candle:candlewatchlist) {
    		//if candle is active: 
    		if(candle.getActive()==true && candle.getUserStockWatchList().getUser().getUsername().toString().equals(username)
    				&& candle.getUserStockWatchList().getStock().getStockTicker().equals(stockticker.toUpperCase())) {
    			
    			//i will get the candle history of that particular stock and candle
    			List<CandleHistory> candlehistory=chrepo.getcandlehistory(candle.getUserStockWatchList().getStock().getStockTicker(),
    																		candle.getCandle().getCandleName());
    			//for each history
    			for(CandleHistory ch:candlehistory) {
    				//if the stock is equals to the current stock, and 
        			CandleHistoryDTO toadd=new CandleHistoryDTO();
        			toadd.setCandle(candle.getCandle().getCandleName());
        			toadd.setUsername(username);
        			//toadd.setDatetime(UtilityManager.UnixToDate(ch.getDateTimeTrigger(), false));
        			toadd.setDatetime(String.valueOf(ch.getDateTimeTrigger()));
        			toadd.setStockticker(candle.getUserStockWatchList().getStock().getStockTicker());
        			toadd.setStockname(candle.getUserStockWatchList().getStock().getStockName());
        			tofrontend.add(toadd);
    			}    			
    		}
    	}
    	List<CandleHistoryDTO>tofrontend2= tofrontend.stream()
    	        .sorted(Comparator.comparing(CandleHistoryDTO::getDatetime).reversed())
    	        .collect(Collectors.toList());
    	for(CandleHistoryDTO format:tofrontend2){
    		String toset=UtilityManager.UnixToDate(Long.parseLong(format.getDatetime()),false);
    		format.setDatetime(toset);
    	}
		return tofrontend2;    
    }

	public void sendEmailNotification(MailVo mail) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom(mail.getFrom());
		simpleMailMessage.setTo(mail.getTo());
		simpleMailMessage.setSubject(mail.getSubject());
		simpleMailMessage.setText(mail.getText());
		javaMailSender.send(simpleMailMessage);
	}
	@Autowired
	CandleRepository crepo;
	
	@Autowired
	StockRepository srepo;

	
	//save all 4 candle history data
	public void savecandlehistoryonnewstock(String stockticker) {
		List<Long> candle1=getBullishEngulfingCandleSignalUNIX(getCandleData(stockticker));
		for(Long candle:candle1) {
			CandleHistory candletostoreindb=new CandleHistory();
			if(candle%100==0) {
				candletostoreindb.setCandle(crepo.findById(1L).orElse(null));
				candletostoreindb.setStock(srepo.findByStockTicker(stockticker));
				candletostoreindb.setDateTimeTrigger(candle);
				chrepo.save(candletostoreindb);
			}
		}
		List<Long> candle2=getBearishEngulfingCandleSignalUNIX(getCandleData(stockticker));
		for(Long candle:candle2) {
			CandleHistory candletostoreindb=new CandleHistory();
			if(candle%100==0) {
				candletostoreindb.setCandle(crepo.findById(2L).orElse(null));
				candletostoreindb.setStock(srepo.findByStockTicker(stockticker));
				candletostoreindb.setDateTimeTrigger(candle);
				chrepo.save(candletostoreindb);
			}
		}		
		
		
		List<Long> candle3=getMorningStarCandleUNIX(getCandleData(stockticker));
		for(Long candle:candle3) {
			CandleHistory candletostoreindb=new CandleHistory();
			if(candle%100==0) {
				candletostoreindb.setCandle(crepo.findById(3L).orElse(null));
				candletostoreindb.setStock(srepo.findByStockTicker(stockticker));
				candletostoreindb.setDateTimeTrigger(candle);
				chrepo.save(candletostoreindb);
			}
		}
		
		List<Long> candle4=getEveningStarUNIX(getCandleData(stockticker));
		for(Long candle:candle4) {
			CandleHistory candletostoreindb=new CandleHistory();
			if(candle%100==0) {
				candletostoreindb.setCandle(crepo.findById(4L).orElse(null));
				candletostoreindb.setStock(srepo.findByStockTicker(stockticker));
				candletostoreindb.setDateTimeTrigger(candle);
				chrepo.save(candletostoreindb);
			}
		}
		
	}
}
