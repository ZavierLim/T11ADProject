package sg.edu.iss.ad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Stock {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String StockTicker;
	private String StockName;
	
//	@OneToMany(fetch = FetchType.LAZY,mappedBy="Stock")
//	private List<CandleHistory> CandleHistory;
//	
//	@OneToMany(fetch = FetchType.LAZY,mappedBy="Stock")
//	private List<UserStockComment> UserStockComment;
//	
//	//@ManyToMany
//	//@JoinTable(name="User_Stock_Watchlist", joinColumns=@JoinColumn(name="stock_id"),inverseJoinColumns=@JoinColumn(name="watchlist_id"))
//	@OneToMany(fetch = FetchType.LAZY,mappedBy="Stock")
//	private List<UserStockWatchList> UserStockWatchList;
	
	public Stock() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStockTicker() {
		return StockTicker;
	}

	public void setStockTicker(String stockTicker) {
		StockTicker = stockTicker;
	}

	public String getStockName() {
		return StockName;
	}

	public void setStockName(String stockName) {
		StockName = stockName;
	}

//	public List<CandleHistory> getCandleHistory() {
//		return CandleHistory;
//	}
//
//	public void setCandleHistory(List<CandleHistory> candleHistory) {
//		CandleHistory = candleHistory;
//	}
//
//	public List<UserStockComment> getUserStockComment() {
//		return UserStockComment;
//	}
//
//	public void setUserStockComment(List<UserStockComment> userStockComment) {
//		UserStockComment = userStockComment;
//	}
//
//	public List<UserStockWatchList> getUserStockWatchList() {
//		return UserStockWatchList;
//	}
//
//	public void setUserStockWatchList(List<UserStockWatchList> userStockWatchList) {
//		UserStockWatchList = userStockWatchList;
//	};
	
	
}