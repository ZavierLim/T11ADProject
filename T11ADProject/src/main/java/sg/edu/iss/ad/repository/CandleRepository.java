package sg.edu.iss.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ad.model.Candle;

public interface CandleRepository extends JpaRepository<Candle, Long> {
	@Query("SELECT c from Candle c WHERE c.CandleName=:candlename")
	public Candle getcandlefromname(@Param("candlename") String candlename);
	
}
