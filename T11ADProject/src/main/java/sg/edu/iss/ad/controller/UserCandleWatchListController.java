package sg.edu.iss.ad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ad.DTO.UserCandleWatchListDTO;
import sg.edu.iss.ad.DTO.UserStockWatchListDTO;
import sg.edu.iss.ad.model.UserCandleWatchList;
import sg.edu.iss.ad.service.UserCandleWatchListService;

@RestController
@CrossOrigin
public class UserCandleWatchListController {
	@Autowired
	UserCandleWatchListService ucwlservice;

	@PostMapping("/watchlist/autoAddCandle")
	public void autoAddCandle(String username,String stockname,String stockticker){
		UserStockWatchListDTO uswlDTO = new UserStockWatchListDTO();
		uswlDTO.setStockname(stockname);
		uswlDTO.setUsername(username);
		uswlDTO.setStockticker(stockticker);
		ucwlservice.AutoGenerateCandleWatchList(uswlDTO);
	}
	
	@PostMapping("/watchlist/candlewatchlist")
	public ResponseEntity<List<UserCandleWatchListDTO>> getcandlesdata(@RequestBody UserCandleWatchListDTO input){
		List<UserCandleWatchListDTO> candlewatchlist=ucwlservice.WatchListByUserAndTicker(input);
		return ResponseEntity.ok(candlewatchlist);
	}
	
	@PostMapping("/watchlist/candlewatchlist/update")
	public ResponseEntity<List<UserCandleWatchListDTO>> updatecandledata(@RequestBody List<UserCandleWatchListDTO> input){
		List<UserCandleWatchListDTO> updatedcandlewatchlist=ucwlservice.UpdateUserWatchList(input);
		return ResponseEntity.ok(updatedcandlewatchlist);
	}
}
