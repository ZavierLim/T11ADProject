package sg.edu.iss.ad.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Candle Candle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserStockWatchList UserStockWatchList;
	
	public UserCandleWatchList() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDateTimeActive() {
		return DateTimeActive;
	}

	public void setDateTimeActive(long dateTimeActive) {
		DateTimeActive = dateTimeActive;
	}

	public Candle getCandle() {
		return Candle;
	}

	public void setCandle(Candle candle) {
		Candle = candle;
	}

	public UserStockWatchList getUserStockWatchList() {
		return UserStockWatchList;
	}

	public void setUserStockWatchList(UserStockWatchList userStockWatchList) {
		UserStockWatchList = userStockWatchList;
	};
	
	
}
