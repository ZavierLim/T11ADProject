package sg.edu.iss.ad.DTO;

public class CandleHistoryDTO {
	private String datetime;
	private String candle;
	private String stockticker;
	private String username;
	private String stockname;
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getCandle() {
		return candle;
	}
	public void setCandle(String candle) {
		this.candle = candle;
	}
	public String getStockticker() {
		return stockticker;
	}
	public void setStockticker(String stockticker) {
		this.stockticker = stockticker;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	
}
