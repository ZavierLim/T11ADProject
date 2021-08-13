package sg.edu.iss.ad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ad.model.UserStockComment;


public interface UserStockCommentRepository extends JpaRepository<UserStockComment,Long> {
	@Query("Select usc FROM UserStockComment usc JOIN FETCH usc.Stock s JOIN FETCH usc.User u WHERE usc.Stock.StockTicker=:tickername")
	public List<UserStockComment> findCommentsByStock(@Param("tickername")String tickername);
}
