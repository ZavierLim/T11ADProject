package sg.edu.iss.ad.service;

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
		for (int i = 0; i < 4; i++) {
			UserCandleWatchList ucwl = new UserCandleWatchList();
			ucwl.setActive(false);
			ucwl.setDateTimeActive(new Date().getTime());
			ucwl.setCandle(candleList.get(i));
			ucwl.setUserStockWatchList(uswl);
			ucwlrepo.save(ucwl);
		}
	}
	
//	public List<UserCandleWatchListDTO> watchlisttofrontend(String username,String ticker){
//		
//	}
}
