package sg.edu.iss.ad.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.DTO.UserStockWatchListDTO;
import sg.edu.iss.ad.model.Stock;
import sg.edu.iss.ad.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	StockRepository srepo;
	
	//Save Stock to DB
	@Transactional
	public void addnewstock(UserStockWatchListDTO newstock) {
			Stock stocktoadd=new Stock();
			stocktoadd.setStockTicker(newstock.getStockticker());
			stocktoadd.setStockName(newstock.getStockticker());
			srepo.save(stocktoadd);
	}
}
