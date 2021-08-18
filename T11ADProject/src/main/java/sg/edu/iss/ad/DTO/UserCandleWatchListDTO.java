package sg.edu.iss.ad.DTO;

public class UserCandleWatchListDTO {
	private String username;
	private String stockticker;
	private boolean active;
	private String candlename;
	private String datetime;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStockticker() {
		return stockticker;
	}
	public void setStockticker(String stockticker) {
		this.stockticker = stockticker;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCandlename() {
		return candlename;
	}
	public void setCandlename(String candlename) {
		this.candlename = candlename;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetimeactive) {
		this.datetime = datetimeactive;
	}
	
	
	
}
