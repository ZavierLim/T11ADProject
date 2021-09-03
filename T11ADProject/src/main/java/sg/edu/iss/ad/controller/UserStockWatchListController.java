package sg.edu.iss.ad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ad.DTO.CommentsDTO;
import sg.edu.iss.ad.DTO.UserStockWatchListDTO;
import sg.edu.iss.ad.model.UserStockWatchList;
import sg.edu.iss.ad.repository.UserStockWatchListRepository;
import sg.edu.iss.ad.service.UserCandleWatchListService;
import sg.edu.iss.ad.service.UserStockWatchListService;

@RestController
@CrossOrigin
public class UserStockWatchListController {
	@Autowired
	UserStockWatchListService uswlservice;

	@Autowired
	UserCandleWatchListService ucwlservice;
	
	//get watchlist by username
	@GetMapping("/watchlist/{username}")
	public ResponseEntity<List<UserStockWatchListDTO>> getwatchlist(@PathVariable String username){
		List<UserStockWatchListDTO> watchlist= uswlservice.getuserstockwatchlist(username);
		return ResponseEntity.ok(watchlist);
	}
	
	//add stocks into watchlist
	@PostMapping("/watchlist/add")
	public ResponseEntity<UserStockWatchListDTO> addstocktowatchlist(@RequestBody UserStockWatchListDTO addstock){
		uswlservice.addstocktowatchlist(addstock);
		ucwlservice.AutoGenerateCandleWatchList(addstock);
		return ResponseEntity.ok(addstock);
	}
	
	//delete stock from watchlist
	@DeleteMapping("/watchlist/delete")
	public ResponseEntity<UserStockWatchListDTO> deletestockfromwatchlist(@RequestBody UserStockWatchListDTO deletestock){
		ucwlservice.deleteWatchListBystockTicker(deletestock);
		uswlservice.deletestockfromwatchlist(deletestock);
		return ResponseEntity.ok(deletestock);
	}

}
