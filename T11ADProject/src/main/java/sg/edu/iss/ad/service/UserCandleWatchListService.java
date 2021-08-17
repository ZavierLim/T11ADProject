package sg.edu.iss.ad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.DTO.UserCandleWatchListDTO;
import sg.edu.iss.ad.model.UserCandleWatchList;
import sg.edu.iss.ad.repository.UserCandleWatchListRepository;

@Service
public class UserCandleWatchListService {
	@Autowired
	UserCandleWatchListRepository ucwlrepo;
	
	public List<UserCandleWatchList> findwatchlistbyusername(UserCandleWatchListDTO ucwldto){
		return ucwlrepo.findwatchlistbyusernameandticker(ucwldto.getUsername(),ucwldto.getStockticker());
	}

	public void deletewatchlistbystockwatchlistid(String stockTicker){
		ucwlrepo.deletewatchlistbystockticker(stockTicker);
	}
	
//	public List<UserCandleWatchListDTO> watchlisttofrontend(String username,String ticker){
//		
//	}
}
