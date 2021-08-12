package sg.edu.iss.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ad.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
