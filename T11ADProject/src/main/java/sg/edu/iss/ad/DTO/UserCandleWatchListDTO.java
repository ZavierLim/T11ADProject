package sg.edu.iss.ad.DTO;

public class UserCandleWatchListDTO {
	private String username;
	private String stockticker;
	private boolean active;
	private String candlename;
	private long datetimeactive;
	
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
	public boolean isActive() {
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
	public long getDatetimeactive() {
		return datetimeactive;
	}
	public void setDatetimeactive(long datetimeactive) {
		this.datetimeactive = datetimeactive;
	}
	
	
	
}
