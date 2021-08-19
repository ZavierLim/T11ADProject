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
		
		//user 1 AAPL
		for (long i=1;i<5;i++) {
			UserCandleWatchList user1stock1candle1=new UserCandleWatchList();
			user1stock1candle1.setCandle(candlerepo.getById(i));
	
			user1stock1candle1.setUserStockWatchList(userwatchlistrepo.getById(1L));
			user1stock1candle1.setActive(false);
			usercandlewatchlistrepo.save(user1stock1candle1);			
		}	
		
		//User 1 GOOG
		for (long i=1;i<5;i++) {
			UserCandleWatchList user1stock1candle1=new UserCandleWatchList();
			user1stock1candle1.setCandle(candlerepo.getById(i));
		
			user1stock1candle1.setUserStockWatchList(userwatchlistrepo.getById(2L));
			user1stock1candle1.setActive(false);
			usercandlewatchlistrepo.save(user1stock1candle1);			
		}	
		//User 2 AAPL
		for (long i=1;i<5;i++) {
			UserCandleWatchList user1stock1candle1=new UserCandleWatchList();
			user1stock1candle1.setCandle(candlerepo.getById(i));
		
			user1stock1candle1.setUserStockWatchList(userwatchlistrepo.getById(3L));
			user1stock1candle1.setActive(false);
			usercandlewatchlistrepo.save(user1stock1candle1);			
		}	
		//User 2 GOOG
		for (long i=1;i<5;i++) {
			UserCandleWatchList user1stock1candle1=new UserCandleWatchList();
			user1stock1candle1.setCandle(candlerepo.getById(i));
		
			user1stock1candle1.setUserStockWatchList(userwatchlistrepo.getById(4L));
			user1stock1candle1.setActive(true);
			user1stock1candle1.setDateTimeActive(1607610600);
			usercandlewatchlistrepo.save(user1stock1candle1);			
		}
		
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
		
		//set history of apple stock, bullish engulfing
		List<Long> applehistorycandle1=cservice.getBullishEngulfingCandleSignalUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:applehistorycandle1) {
			CandleHistory candletostoreindb1=new CandleHistory();
			candletostoreindb1.setDateTimeTrigger(perhistory);
			candletostoreindb1.setCandle(candlerepo.getById(1L));
			candletostoreindb1.setStock(srepo.getById(1L));
			chrepo.save(candletostoreindb1);
		}
		
		//set history of apple stock, Bearish Engulfing
		List<Long> applehistorycandle2=cservice.getBearishEngulfingCandleSignalUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:applehistorycandle2) {
			CandleHistory candletostoreindb2=new CandleHistory();
			candletostoreindb2.setDateTimeTrigger(perhistory);
			candletostoreindb2.setCandle(candlerepo.getById(2L));
			candletostoreindb2.setStock(srepo.getById(1L));
			chrepo.save(candletostoreindb2);
		}
		
		//Set history of apple stock, Morning
		List<Long> applehistorycandle3=cservice.getMorningStarCandleUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:applehistorycandle3) {
			CandleHistory candletostoreindb3=new CandleHistory();
			candletostoreindb3.setDateTimeTrigger(perhistory);
			candletostoreindb3.setCandle(candlerepo.getById(3L));
			candletostoreindb3.setStock(srepo.getById(1L));
			chrepo.save(candletostoreindb3);
		}
		
		//Set history of apple stock eveningstar
		List<Long> applehistorycandle4=cservice.getEveningStarUNIX(cservice.getCandleData("AAPL"));
		for(Long perhistory:applehistorycandle4) {
			CandleHistory candletostoreindb4=new CandleHistory();
			candletostoreindb4.setDateTimeTrigger(perhistory);
			candletostoreindb4.setCandle(candlerepo.getById(4L));
			candletostoreindb4.setStock(srepo.getById(1L));
			chrepo.save(candletostoreindb4);
		}
		
		//set history of goog stock, bullish engulfing
		List<Long> googistorycandle1=cservice.getBullishEngulfingCandleSignalUNIX(cservice.getCandleData("GOOG"));
		for(Long perhistory:googistorycandle1) {
			CandleHistory candletostoreindb5=new CandleHistory();
			candletostoreindb5.setDateTimeTrigger(perhistory);
			candletostoreindb5.setCandle(candlerepo.getById(1L));
			candletostoreindb5.setStock(srepo.getById(2L));
			chrepo.save(candletostoreindb5);
		}
		
		//set history of goog stock, Bearish Engulfing
		List<Long> googhistorycandle2=cservice.getBearishEngulfingCandleSignalUNIX(cservice.getCandleData("GOOG"));
		for(Long perhistory:googhistorycandle2) {
			CandleHistory candletostoreindb6=new CandleHistory();
			candletostoreindb6.setDateTimeTrigger(perhistory);
			candletostoreindb6.setCandle(candlerepo.getById(2L));
			candletostoreindb6.setStock(srepo.getById(2L));
			chrepo.save(candletostoreindb6);
		}
		
		
		
		//set history of goog stock, morningstar
		List<Long> googhistorycandle3=cservice.getMorningStarCandleUNIX(cservice.getCandleData("GOOG"));
		for(Long perhistory:googhistorycandle3) {
			CandleHistory candletostoreindb7=new CandleHistory();
			candletostoreindb7.setDateTimeTrigger(perhistory);
			candletostoreindb7.setCandle(candlerepo.getById(3L));
			candletostoreindb7.setStock(srepo.getById(2L));
			chrepo.save(candletostoreindb7);
		}

		//set history of goog stock, eveningstar
		List<Long> googhistorycandle4=cservice.getEveningStarUNIX(cservice.getCandleData("GOOG"));
		for(Long perhistory:googhistorycandle4) {
			CandleHistory candletostoreindb8=new CandleHistory();
			candletostoreindb8.setDateTimeTrigger(perhistory);
			candletostoreindb8.setCandle(candlerepo.getById(4L));
			candletostoreindb8.setStock(srepo.getById(2L));
			chrepo.save(candletostoreindb8);
		}
		
	}
}
