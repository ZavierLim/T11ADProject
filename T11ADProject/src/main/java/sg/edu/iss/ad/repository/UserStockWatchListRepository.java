package sg.edu.iss.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ad.model.UserStockWatchList;

public interface UserStockWatchListRepository extends JpaRepository<UserStockWatchList, Long> {

}
