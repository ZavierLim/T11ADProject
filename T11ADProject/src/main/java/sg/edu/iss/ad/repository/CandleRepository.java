package sg.edu.iss.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ad.model.Candle;

public interface CandleRepository extends JpaRepository<Candle, Long> {

}
