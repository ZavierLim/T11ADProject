package sg.edu.iss.ad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Candle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String CandleName;
	
	@OneToMany(mappedBy="Candle")
	private List<CandleHistory> CandleHistory;
	
	@OneToMany(mappedBy="Candle")
	private List<UserCandleWatchList>  CandleWatchList;
	
	public Candle() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCandleName() {
		return CandleName;
	}

	public void setCandleName(String candleName) {
		CandleName = candleName;
	}

	public List<CandleHistory> getCandleHistory() {
		return CandleHistory;
	}

	public void setCandleHistory(List<CandleHistory> candleHistory) {
		CandleHistory = candleHistory;
	}

	public List<UserCandleWatchList> getCandleWatchList() {
		return CandleWatchList;
	}

	public void setCandleWatchList(List<UserCandleWatchList> candleWatchList) {
		CandleWatchList = candleWatchList;
	};
	
}
