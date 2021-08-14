package sg.edu.iss.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ad.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	@Query("SELECT s FROM Stock s WHERE s.StockTicker=:ticker")
	public Stock findByStockTicker(@Param("ticker") String ticker);
}
