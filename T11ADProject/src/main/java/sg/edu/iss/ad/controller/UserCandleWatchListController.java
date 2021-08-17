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
import sg.edu.iss.ad.model.UserCandleWatchList;
import sg.edu.iss.ad.service.UserCandleWatchListService;

@RestController
@CrossOrigin
public class UserCandleWatchListController {
	@Autowired
	UserCandleWatchListService ucwlservice;
	
//	@PostMapping("/watchlist/candlewatchlist")
//	
//	}
}
