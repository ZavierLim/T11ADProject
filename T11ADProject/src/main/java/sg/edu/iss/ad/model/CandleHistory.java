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
	
	public CandleHistory() {};
}
