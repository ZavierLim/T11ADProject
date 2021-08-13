package sg.edu.iss.ad.utility;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ad.model.Candle;
import sg.edu.iss.ad.model.CandleHistory;
import sg.edu.iss.ad.model.RoleType;
import sg.edu.iss.ad.model.Stock;
import sg.edu.iss.ad.model.User;
import sg.edu.iss.ad.model.UserCandleWatchList;
import sg.edu.iss.ad.model.UserStockComment;
import sg.edu.iss.ad.model.UserStockWatchList;
import sg.edu.iss.ad.repository.CandleHistoryRepository;
import sg.edu.iss.ad.repository.CandleRepository;
import sg.edu.iss.ad.repository.StockRepository;
import sg.edu.iss.ad.repository.UserCandleWatchListRepository;
import sg.edu.iss.ad.repository.UserStockCommentRepository;
import sg.edu.iss.ad.repository.UserStockWatchListRepository;
import sg.edu.iss.ad.service.CandleService;
import sg.edu.iss.ad.repository.UserRepository;

@Service
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
	
	@Autowired
	CandleService cservice;
	
	@Transactional
	public void seedDB1() {
		User user1=new User();
		user1.setUsername("testing");
		user1.setPassword("testing");
		user1.setEmail("testing@gmail.com");
		user1.setRole(RoleType.USER);	
		urepo.save(user1);
		
		User user2=new User();
		user2.setUsername("zavier");
		user2.setPassword("zavier");
		user2.setEmail("zavier@gmail.com");
		user2.setRole(RoleType.USER);	
		urepo.save(user2);
		
		Stock stock1=new Stock();
		stock1.setStockTicker("AAPL");
		stock1.setStockName("APPLE INC");
		srepo.save(stock1);
		
		Stock stock2=new Stock();
		stock2.setStockTicker("GOOG");
		stock2.setStockName("ALPHABET INC-CL C");
		srepo.save(stock2);
	
		Candle candle1=new Candle();
		candle1.setCandleName("Bullish Engulfing");
		Candle candle2=new Candle();
		candle2.setCandleName("Bearish Engulfing");
		Candle candle3=new Candle();
		candle3.setCandleName("Morning Star");
		Candle candle4=new Candle();
		candle4.setCandleName("Evening Star");
		candlerepo.save(candle1);
		candlerepo.save(candle2);
		candlerepo.save(candle3);
		candlerepo.save(candle4);
	}
	
	@Transactional
	public void seedDB2() {
		UserStockWatchList user1stock1= new UserStockWatchList();
		user1stock1.setUser(urepo.getById(1L));
		user1stock1.setStock(srepo.getById(1L));
		userwatchlistrepo.save(user1stock1);
		
		UserStockWatchList user1stock2=new UserStockWatchList();
		user1stock2.setUser(urepo.getById(1L));
		user1stock2.setStock(srepo.getById(2L));
		userwatchlistrepo.save(user1stock2);
		
		UserStockWatchList user2stock1= new UserStockWatchList();
		user2stock1.setUser(urepo.getById(2L));
		user2stock1.setStock(srepo.getById(1L));
		userwatchlistrepo.save(user2stock1);
		
		UserStockWatchList user2stock2=new UserStockWatchList();
		user2stock2.setUser(urepo.getById(2L));
		user2stock2.setStock(srepo.getById(2L));
		userwatchlistrepo.save(user2stock2);	
		
		//user 1 track all 4 candles for AAPL
		UserCandleWatchList user1stock1candle1=new UserCandleWatchList();
		user1stock1candle1.setCandle(candlerepo.getById(1L));
		user1stock1candle1.setDateTimeActive(1628777993);
		user1stock1candle1.setUserStockWatchList(userwatchlistrepo.getById(1L));
		usercandlewatchlistrepo.save(user1stock1candle1);
		
		UserCandleWatchList user1stock1candle2=new UserCandleWatchList();
		user1stock1candle2.setCandle(candlerepo.getById(2L));
		user1stock1candle2.setDateTimeActive(1628777993);
		user1stock1candle2.setUserStockWatchList(userwatchlistrepo.getById(1L));
		usercandlewatchlistrepo.save(user1stock1candle2);

		UserCandleWatchList user1stock1candle3=new UserCandleWatchList();
		user1stock1candle3.setCandle(candlerepo.getById(3L));
		user1stock1candle3.setDateTimeActive(1628777993);
		user1stock1candle3.setUserStockWatchList(userwatchlistrepo.getById(1L));
		usercandlewatchlistrepo.save(user1stock1candle3);
		
		UserCandleWatchList user1stock1candle4=new UserCandleWatchList();
		user1stock1candle4.setCandle(candlerepo.getById(4L));
		user1stock1candle4.setDateTimeActive(1628777993);
		user1stock1candle4.setUserStockWatchList(userwatchlistrepo.getById(1L));
		usercandlewatchlistrepo.save(user1stock1candle4);
		
		//User 1 Track candle 3 and 4 for GOOG
		UserCandleWatchList user1stock2candle3=new UserCandleWatchList();
		user1stock2candle3.setCandle(candlerepo.getById(3L));
		user1stock2candle3.setDateTimeActive(1628777993);
		user1stock2candle3.setUserStockWatchList(userwatchlistrepo.getById(2L));
		usercandlewatchlistrepo.save(user1stock2candle3);
		
		UserCandleWatchList user1stock2candle4=new UserCandleWatchList();
		user1stock2candle4.setCandle(candlerepo.getById(4L));
		user1stock2candle4.setDateTimeActive(1628777993);
		user1stock2candle4.setUserStockWatchList(userwatchlistrepo.getById(2L));
		usercandlewatchlistrepo.save(user1stock2candle4);
		
		//User 2 Track Candle 1 and 2 for AAPL
		
		UserCandleWatchList user2stock1candle1=new UserCandleWatchList();
		user2stock1candle1.setCandle(candlerepo.getById(1L));
		user2stock1candle1.setDateTimeActive(1628777993);
		user2stock1candle1.setUserStockWatchList(userwatchlistrepo.getById(3L));
		usercandlewatchlistrepo.save(user2stock1candle1);
		
		UserCandleWatchList user2stock1candle2=new UserCandleWatchList();
		user2stock1candle2.setCandle(candlerepo.getById(2L));
		user2stock1candle2.setDateTimeActive(1628777993);
		user2stock1candle2.setUserStockWatchList(userwatchlistrepo.getById(3L));
		usercandlewatchlistrepo.save(user2stock1candle2);	
		
		//set comment for apple stock user 1
		UserStockComment user1comment=new UserStockComment();
		user1comment.setUser(urepo.getById(1L));
		user1comment.setComment("Hi i love apple stock!");
		user1comment.setStock(srepo.getById(1L));
		user1comment.setCommentDateTime(1628777993);
		commentrepo.save(user1comment);
		
		//set comment for google stock user 1
		UserStockComment user1comment2=new UserStockComment();
		user1comment2.setUser(urepo.getById(1L));
		user1comment2.setComment("i hate google stock!");
		user1comment2.setStock(srepo.getById(2L));
		user1comment2.setCommentDateTime(1628777993);
		commentrepo.save(user1comment2);
		
		
		//set comment for apple stock user 2
		UserStockComment user2comment=new UserStockComment();
		user2comment.setUser(urepo.getById(2L));
		user2comment.setComment("Hi i love apple stock!");
		user2comment.setStock(srepo.getById(1L));
		user2comment.setCommentDateTime(1628777993);
		commentrepo.save(user2comment);
		
		//set comment for google stock user 2
		UserStockComment user2comment2=new UserStockComment();
		user2comment2.setUser(urepo.getById(2L));
		user2comment2.setComment("i hate google stock!");
		user2comment2.setStock(srepo.getById(2L));
		user2comment2.setCommentDateTime(1628777993);
		commentrepo.save(user2comment2);
		
		//User 1: set history of apple stock, bullish engulfing
		List<Long> applehistoryuser1candle1=cservice.getBullishEngulfingCandleSignalUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:applehistoryuser1candle1) {
			CandleHistory candletostoreindb=new CandleHistory();
			candletostoreindb.setDateTimeTrigger(perhistory);
			candletostoreindb.setCandle(candlerepo.getById(1L));
			candletostoreindb.setStock(srepo.getById(1L));
			candletostoreindb.setUser(urepo.getById(1L));
			chrepo.save(candletostoreindb);
		}
		
		//User 1: set history of apple stock, Bearish Engulfing
		List<Long> applehistoryuser1candle2=cservice.getBearishEngulfingCandleSignalUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:applehistoryuser1candle2) {
			CandleHistory candletostoreindb=new CandleHistory();
			candletostoreindb.setDateTimeTrigger(perhistory);
			candletostoreindb.setCandle(candlerepo.getById(2L));
			candletostoreindb.setStock(srepo.getById(1L));
			candletostoreindb.setUser(urepo.getById(1L));
			chrepo.save(candletostoreindb);
		}
		
		//User 1: set history of apple stock, Morning
		List<Long> applehistoryuser1candle3=cservice.getMorningStarCandleUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:applehistoryuser1candle3) {
			CandleHistory candletostoreindb=new CandleHistory();
			candletostoreindb.setDateTimeTrigger(perhistory);
			candletostoreindb.setCandle(candlerepo.getById(3L));
			candletostoreindb.setStock(srepo.getById(1L));
			candletostoreindb.setUser(urepo.getById(1L));
			chrepo.save(candletostoreindb);
		}
		
		//User 1: set history of apple stock, eveningstar
		List<Long> applehistoryuser1candle4=cservice.getEveningStarUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:applehistoryuser1candle4) {
			CandleHistory candletostoreindb=new CandleHistory();
			candletostoreindb.setDateTimeTrigger(perhistory);
			candletostoreindb.setCandle(candlerepo.getById(4L));
			candletostoreindb.setStock(srepo.getById(1L));
			candletostoreindb.setUser(urepo.getById(1L));
			chrepo.save(candletostoreindb);
		}
		//User 1: set history of goog stock, morningstar
		List<Long> googhistoryuser1candle3=cservice.getMorningStarCandleUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:googhistoryuser1candle3) {
			CandleHistory candletostoreindb=new CandleHistory();
			candletostoreindb.setDateTimeTrigger(perhistory);
			candletostoreindb.setCandle(candlerepo.getById(3L));
			candletostoreindb.setStock(srepo.getById(2L));
			candletostoreindb.setUser(urepo.getById(1L));
			chrepo.save(candletostoreindb);
		}

		//User 1: set history of goog stock, eveningstar
		List<Long> googhistoryuser1candle4=cservice.getEveningStarUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:googhistoryuser1candle4) {
			CandleHistory candletostoreindb=new CandleHistory();
			candletostoreindb.setDateTimeTrigger(perhistory);
			candletostoreindb.setCandle(candlerepo.getById(4L));
			candletostoreindb.setStock(srepo.getById(2L));
			candletostoreindb.setUser(urepo.getById(1L));
			chrepo.save(candletostoreindb);
		}
		
		//User 2: set history of AAPL stock, morningstar
		List<Long> aaplhistoryuser2candle3=cservice.getMorningStarCandleUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:aaplhistoryuser2candle3) {
			CandleHistory candletostoreindb=new CandleHistory();
			candletostoreindb.setDateTimeTrigger(perhistory);
			candletostoreindb.setCandle(candlerepo.getById(3L));
			candletostoreindb.setStock(srepo.getById(1L));
			candletostoreindb.setUser(urepo.getById(2L));
			chrepo.save(candletostoreindb);
		}
		
		//User 2: set history of AAPL stock, evening
		List<Long> aaplhistoryuser2candle4=cservice.getEveningStarUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:aaplhistoryuser2candle4) {
			CandleHistory candletostoreindb=new CandleHistory();
			candletostoreindb.setDateTimeTrigger(perhistory);
			candletostoreindb.setCandle(candlerepo.getById(4L));
			candletostoreindb.setStock(srepo.getById(1L));
			candletostoreindb.setUser(urepo.getById(2L));
			chrepo.save(candletostoreindb);
		}
		
	}
}
