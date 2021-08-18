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
import sg.edu.iss.ad.repository.CandleRepository;
import sg.edu.iss.ad.repository.StockRepository;
import sg.edu.iss.ad.repository.UserCandleWatchListRepository;
import sg.edu.iss.ad.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserCandleWatchListService {
	@Autowired
	UserCandleWatchListRepository ucwlrepo;

	@Autowired
	CandleRepository crepo;

	@Autowired
	UserRepository urepo;

	@Autowired
	StockRepository srepo;
	
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
		UserCandleWatchList ucwl = new UserCandleWatchList();
		UserStockWatchList uswl = new UserStockWatchList();
		uswl.setUser(urepo.findByUsername(uswlDTO.getUsername()));
		uswl.setStock(srepo.findByStockTicker(uswlDTO.getStockticker()));
		uswl.setId(uswl.getId());
		List<Candle> candleList = crepo.findAll();
		for (int i = 0; i < 4; i++) {
			ucwl.setCandle(candleList.get(i));
			ucwl.setDateTimeActive(new Date().getTime());
			ucwl.setUserStockWatchList(uswl);
			ucwl.setUserStockWatchList(uswl);
			ucwlrepo.save(ucwl);
		}
	}
	
//	public List<UserCandleWatchListDTO> watchlisttofrontend(String username,String ticker){
//		
//	}
}
