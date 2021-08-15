package sg.edu.iss.ad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ad.model.UserStockWatchList;

public interface UserStockWatchListRepository extends JpaRepository<UserStockWatchList, Long> {
	@Query("SELECT uswl from UserStockWatchList uswl WHERE uswl.User.username=:username")
	public List<UserStockWatchList> GetWatchList(@Param("username")String username);
	
	@Query("SELECT uswl from UserStockWatchList uswl WHERE uswl.User.username=:username AND uswl.Stock.StockTicker=:stockticker")
	public UserStockWatchList FindStockByUserandTicker(@Param("username")String username,
													   @Param("stockticker") String stockticker);
}
