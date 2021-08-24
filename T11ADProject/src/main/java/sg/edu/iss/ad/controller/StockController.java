package sg.edu.iss.ad.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ad.DTO.UserStockWatchListDTO;
import sg.edu.iss.ad.model.MailVo;
import sg.edu.iss.ad.model.Stock;
import sg.edu.iss.ad.model.UserStockWatchList;
import sg.edu.iss.ad.repository.StockRepository;
import sg.edu.iss.ad.service.UserService;

@RestController
@CrossOrigin
public class StockController {

    /*
    * just test the email function, can ignore this one
    * */

    @Autowired
    private UserService userService;

    @GetMapping("/sendEmail")
    public void sendEmail(){
        MailVo mailVo = new MailVo("PCXGUdrew@163.com","hello","hello","hello");
        try{
            userService.sendEmailNotification(mailVo);
        }
        catch (MailSendException mse){
            System.out.println("target mail does not exist.");
        }
        catch (Exception e){
            System.out.println("an error occur.");
        }
    }
}
