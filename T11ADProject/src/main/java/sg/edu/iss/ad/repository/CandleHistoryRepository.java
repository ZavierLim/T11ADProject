package sg.edu.iss.ad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ad.model.CandleHistory;

public interface CandleHistoryRepository extends JpaRepository<CandleHistory, Long> {
	@Query("Select ch from CandleHistory ch WHERE ch.Stock.StockTicker=:stockticker AND ch.Candle.CandleName=:candlename")
	public List<CandleHistory> getcandlehistory(@Param("stockticker") String stockticker,
												@Param("candlename") String candlename);

	@Query("select ch from CandleHistory ch where ch.Stock.id=:stockId and ch.Candle.id=:candleId and ch.DateTimeTrigger=:datetime")
	public CandleHistory getCandleHistoryByStockAndCandleAndTime(@Param("stockId") Long stockId,
																 @Param("candleId") Long candleId,
																 @Param("datetime") Long datetime);
}
