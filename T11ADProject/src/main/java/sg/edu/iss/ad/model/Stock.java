package sg.edu.iss.ad.model;

import java.util.List;

import javax.persistence.Entity;
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
	
	@OneToMany(mappedBy="Stock")
	private List<CandleHistory> CandleHistory;
	
	@OneToMany(mappedBy="Stock")
	private List<UserStockComment> UserStockComment;
	
	//@ManyToMany
	//@JoinTable(name="User_Stock_Watchlist", joinColumns=@JoinColumn(name="stock_id"),inverseJoinColumns=@JoinColumn(name="watchlist_id"))
	@OneToMany(mappedBy="Stock")
	private List<UserStockWatchList> UserStockWatchList;
	
	public Stock() {};
}