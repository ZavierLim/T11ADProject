package sg.edu.iss.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.scheduling.annotation.EnableScheduling;
import sg.edu.iss.ad.utility.DbSeeding;

@EnableScheduling
@SpringBootApplication
public class T11AdProjectApplication {

	@Autowired
	DbSeeding DbSeeding;
	public static void main(String[] args) {
		SpringApplication.run(T11AdProjectApplication.class, args);
		
	}
	
	
	@Bean
	CommandLineRunner runner() {
		return args->{
			//Seed data only done once
			//DbSeeding.seedDB1();
			//DbSeeding.seedDB2();
	//			2 users
	//			4 candlesticks
	//			2 stocks AAPL and GOOG
	//			Both users have both AAPL and GOOG in the watchlist,
	//			Both users commented on both stocks before
	//			User 1,  added enabled tracking : 4 candles,  for AAPL stock , Morning star and evening star for GOOG stock
	//			User 2, added candle tracking: engulfing bullish and engulfing bearish for AAPL stock

		};
	}
}
