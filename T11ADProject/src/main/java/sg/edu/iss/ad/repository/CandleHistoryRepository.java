package sg.edu.iss.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ad.model.CandleHistory;

public interface CandleHistoryRepository extends JpaRepository<CandleHistory, Long> {

}
