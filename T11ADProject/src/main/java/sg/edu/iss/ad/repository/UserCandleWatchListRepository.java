package sg.edu.iss.ad.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ad.model.UserCandleWatchList;

public interface UserCandleWatchListRepository extends JpaRepository<UserCandleWatchList, Long> {
	@Query("SELECT ucwl FROM UserCandleWatchList ucwl WHERE ucwl.UserStockWatchList.User.username=:username AND ucwl.UserStockWatchList.Stock.StockTicker=:stockticker")
	public List<UserCandleWatchList> findwatchlistbyusernameandticker(@Param("username") String username,
																	  @Param("stockticker") String stockticker);

	@Transactional
	 @Modifying
	@Query("delete from UserCandleWatchList ucwl where ucwl.UserStockWatchList.Stock.StockTicker =: stockTicker")
	public void deletewatchlistbystockticker(@Param("stockTicker") String stockTicker);
}
