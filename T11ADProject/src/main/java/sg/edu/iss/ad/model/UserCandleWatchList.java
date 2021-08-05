package sg.edu.iss.ad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CandlesWatchList")
public class UserCandleWatchList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private long DateTimeActive;
	
	@ManyToOne
	private Candle Candle;
	
	@ManyToOne
	private UserStockWatchList UserStockWatchList;
	
	public UserCandleWatchList() {};
}
