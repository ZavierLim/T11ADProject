package sg.edu.iss.ad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.DTO.UserCandleWatchListDTO;
import sg.edu.iss.ad.model.UserCandleWatchList;
import sg.edu.iss.ad.repository.UserCandleWatchListRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserCandleWatchListService {
	@Autowired
	UserCandleWatchListRepository ucwlrepo;
	
	public List<UserCandleWatchList> findwatchlistbyusername(UserCandleWatchListDTO ucwldto){
		return ucwlrepo.findwatchlistbyusernameandticker(ucwldto.getUsername(),ucwldto.getStockticker());
	}

	public void deleteWatchListBystockTicker(String stockTicker){
		List<UserCandleWatchList> ucwlList = ucwlrepo.findUserCandleWatchListByStockTicker(stockTicker);
		System.out.println(ucwlList.size());
		for (UserCandleWatchList ucwl:ucwlList){
			ucwlrepo.deletewatchlistbyId(ucwl.getId());
		}
		List<UserCandleWatchList> ucwlList2 = ucwlrepo.findUserCandleWatchListByStockTicker(stockTicker);
		System.out.println(ucwlList2.size());
	}
	
//	public List<UserCandleWatchListDTO> watchlisttofrontend(String username,String ticker){
//		
//	}
}
