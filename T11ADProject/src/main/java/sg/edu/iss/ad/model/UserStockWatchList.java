package sg.edu.iss.ad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="StocksInWatchList")
public class UserStockWatchList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	//@ManyToMany(mappedBy="UserStockWatchList")
	@ManyToOne
	private Stock Stock;
	
	@ManyToOne
	private User User;
	
	@OneToMany(mappedBy="UserStockWatchList")
	private List<UserCandleWatchList> UserCandleWatchList;
	
	public UserStockWatchList() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Stock getStock() {
		return Stock;
	}

	public void setStock(Stock stock) {
		Stock = stock;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public List<UserCandleWatchList> getUserCandleWatchList() {
		return UserCandleWatchList;
	}

	public void setUserCandleWatchList(List<UserCandleWatchList> userCandleWatchList) {
		UserCandleWatchList = userCandleWatchList;
	};
	
	
}
