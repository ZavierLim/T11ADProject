package sg.edu.iss.ad.utility;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.iss.ad.repository.CandleHistoryRepository;
import sg.edu.iss.ad.repository.CandleRepository;
import sg.edu.iss.ad.repository.StockRepository;
import sg.edu.iss.ad.repository.UserCandleWatchListRepository;
import sg.edu.iss.ad.repository.UserStockCommentRepository;
import sg.edu.iss.ad.repository.UserStockWatchListRepository;
import sg.edu.iss.ad.repository.UserRepository;

public class DbSeeding {
	@Autowired
	CandleHistoryRepository chrepo;
	@Autowired
	CandleRepository candlerepo;
	@Autowired
	StockRepository srepo;
	@Autowired
	UserCandleWatchListRepository usercandlewatchlistrepo;
	@Autowired
	UserRepository urepo;
	@Autowired
	UserStockCommentRepository commentrepo;
	@Autowired
	UserStockWatchListRepository userwatchlistrepo;
	
	
//	public static void 
}
