package sg.edu.iss.ad.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.DTO.UserCandleWatchListDTO;
import sg.edu.iss.ad.DTO.UserStockWatchListDTO;
import sg.edu.iss.ad.model.Candle;
import sg.edu.iss.ad.model.UserCandleWatchList;
import sg.edu.iss.ad.model.UserStockWatchList;
import sg.edu.iss.ad.repository.*;
import sg.edu.iss.ad.utility.UtilityManager;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserCandleWatchListService {
	@Autowired
	UserCandleWatchListRepository ucwlrepo;

	@Autowired
	CandleRepository crepo;

	@Autowired
	UserStockWatchListRepository uswlrepo;
	
	//Find user WatchList
	public List<UserCandleWatchList> findwatchlistbyusername(UserCandleWatchListDTO ucwldto){
		return ucwlrepo.findwatchlistbyusernameandticker(ucwldto.getUsername(),ucwldto.getStockticker());
	}
	
	//Delete WatchList
	public void deleteWatchListBystockTicker(UserStockWatchListDTO candletodelete){
		List<UserCandleWatchList> ucwlList = ucwlrepo.findUserCandleWatchListByStockTickerandUsername(candletodelete.getStockticker(),candletodelete.getUsername());
		for (UserCandleWatchList ucwl:ucwlList){
			ucwlrepo.deletewatchlistbyId(ucwl.getId());
		}
	}

	//Generate all candles tracking as false when user adds a stock
	public void AutoGenerateCandleWatchList(UserStockWatchListDTO uswlDTO){
		UserStockWatchList uswl = uswlrepo.FindStockByUserandTicker(uswlDTO.getUsername(),uswlDTO.getStockticker());
		List<Candle> candleList = crepo.findAll();
		//Date now = new Date();
		for (int i = 0; i < 4; i++) {
			UserCandleWatchList ucwl = new UserCandleWatchList();
			ucwl.setActive(false);
			//ucwl.setDateTimeActive(-1);
			ucwl.setCandle(candleList.get(i));
			ucwl.setUserStockWatchList(uswl);
			ucwlrepo.save(ucwl);
		}
	}
	
	//Get status of candles based on stock ticker
	public List<UserCandleWatchListDTO> WatchListByUserAndTicker(UserCandleWatchListDTO userinput){
		List<UserCandleWatchListDTO> tofrontend=new ArrayList<UserCandleWatchListDTO>();
		List<UserCandleWatchList> allcandles=ucwlrepo.findUserCandleWatchListByStockTickerandUsername(userinput.getStockticker(), userinput.getUsername());
		List<UserCandleWatchList> sortedallcandles=allcandles.stream().sorted(Comparator.comparing(UserCandleWatchList::getId)).collect(Collectors.toList());
		for(UserCandleWatchList candle:sortedallcandles) {
			System.out.println(candle.getId());
			UserCandleWatchListDTO temp=new UserCandleWatchListDTO();
			temp.setUsername(candle.getUserStockWatchList().getUser().getUsername());
			temp.setStockticker(candle.getUserStockWatchList().getStock().getStockTicker());
			temp.setActive(candle.getActive());
			temp.setCandlename(candle.getCandle().getCandleName());
			if(candle.getDateTimeActive()!=0L)
				temp.setDatetime(UtilityManager.UnixToString(candle.getDateTimeActive(), true));
			else
				temp.setDatetime("0");
			tofrontend.add(temp);
		}
		return tofrontend;
	}
	
	//Updates CandleWatchList when user saves candle tracking
	public List<UserCandleWatchListDTO> UpdateUserWatchList(List<UserCandleWatchListDTO> userinput){
		List<UserCandleWatchListDTO> tofrontend=new ArrayList<UserCandleWatchListDTO>();
		List<UserCandleWatchList> allcandles=ucwlrepo.findUserCandleWatchListByStockTickerandUsername(userinput.get(0).getStockticker(), userinput.get(0).getUsername());
		for (int i=0;i<allcandles.size();i++) {			
			UserCandleWatchList tosave=ucwlrepo.findUserCandleWatchListByStockTickerandUsernameandCandlename(userinput.get(i).getStockticker(), userinput.get(i).getUsername(), userinput.get(i).getCandlename());			
			tosave.setActive(userinput.get(i).getActive());
			tosave.setDateTimeActive(Long.parseLong(userinput.get(i).getDatetime()));   
			ucwlrepo.save(tosave);
			
			//if active is true, means user tracking, return DD-MM-YYYY hh:ss;
			if(userinput.get(i).getActive())
				userinput.get(i).setDatetime(UtilityManager.UnixToDate(Long.parseLong(userinput.get(i).getDatetime()), true));
		}
		return userinput;
		
	}
	
	
}
