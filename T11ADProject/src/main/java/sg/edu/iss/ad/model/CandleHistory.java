package sg.edu.iss.ad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CandleHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private User User;
	
	@ManyToOne
	private Stock Stock;
	
	@ManyToOne
	private Candle Candle;
	
	private long DateTimeTrigger;
	
	public CandleHistory() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Stock getStock() {
		return Stock;
	}

	public void setStock(Stock stock) {
		Stock = stock;
	}

	public Candle getCandle() {
		return Candle;
	}

	public void setCandle(Candle candle) {
		Candle = candle;
	}

	public long getDateTimeTrigger() {
		return DateTimeTrigger;
	}

	public void setDateTimeTrigger(long dateTimeTrigger) {
		DateTimeTrigger = dateTimeTrigger;
	};
	
	
}
