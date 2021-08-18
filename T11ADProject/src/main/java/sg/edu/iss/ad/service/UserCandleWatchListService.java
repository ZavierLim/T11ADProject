package sg.edu.iss.ad.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.DTO.UserCandleWatchListDTO;
import sg.edu.iss.ad.DTO.UserStockWatchListDTO;
import sg.edu.iss.ad.model.Candle;
import sg.edu.iss.ad.model.UserCandleWatchList;
import sg.edu.iss.ad.model.UserStockWatchList;
import sg.edu.iss.ad.repository.*;

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
	
	public List<UserCandleWatchList> findwatchlistbyusername(UserCandleWatchListDTO ucwldto){
		return ucwlrepo.findwatchlistbyusernameandticker(ucwldto.getUsername(),ucwldto.getStockticker());
	}

	public void deleteWatchListBystockTicker(String stockTicker){
		List<UserCandleWatchList> ucwlList = ucwlrepo.findUserCandleWatchListByStockTicker(stockTicker);
		for (UserCandleWatchList ucwl:ucwlList){
			ucwlrepo.deletewatchlistbyId(ucwl.getId());
		}
	}

	public void AutoGenerateCandleWatchList(UserStockWatchListDTO uswlDTO){
		UserStockWatchList uswl = uswlrepo.FindStockByUserandTicker(uswlDTO.getUsername(),uswlDTO.getStockticker());
		List<Candle> candleList = crepo.findAll();
		//Date now = new Date();
		for (int i = 1; i < 5; i++) {
			UserCandleWatchList ucwl = new UserCandleWatchList();
			ucwl.setActive(false);
			//ucwl.setDateTimeActive(-1);
			ucwl.setCandle(candleList.get(i));
			ucwl.setUserStockWatchList(uswl);
			ucwlrepo.save(ucwl);
		}
	}
	
	public List<UserCandleWatchListDTO> WatchListByUserAndTicker(UserCandleWatchListDTO userinput){
		List<UserCandleWatchListDTO> tofrontend=new ArrayList<UserCandleWatchListDTO>();
		List<UserCandleWatchList> allcandles=ucwlrepo.findUserCandleWatchListByStockTickerandUsername(userinput.getStockticker(), userinput.getUsername());
		for(UserCandleWatchList candle:allcandles) {
			UserCandleWatchListDTO temp=new UserCandleWatchListDTO();
			temp.setUsername(candle.getUserStockWatchList().getUser().getUsername());
			temp.setStockticker(candle.getCandle().getCandleName());
			temp.setActive(candle.getActive());
			temp.setCandlename(candle.getCandle().getCandleName());
			temp.setDatetimeactive(candle.getDateTimeActive());
			tofrontend.add(temp);
		}
		return tofrontend;
	}
}
