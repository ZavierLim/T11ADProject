package sg.edu.iss.ad.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sg.edu.iss.ad.model.*;
import sg.edu.iss.ad.repository.UserCandleWatchListRepository;
import sg.edu.iss.ad.repository.UserStockWatchListRepository;
import sg.edu.iss.ad.service.CandleService;
import sg.edu.iss.ad.service.UserService;
import sg.edu.iss.ad.service.UserStockWatchListService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CandleScheduleTask {

    private CandleService candleService;
    private UserCandleWatchListRepository userCandleWatchListRepository;

    @Autowired
    public void setCandleService(CandleService cs) {
        this.candleService = cs;
    }

    @Autowired
    public void setUserCandleWatchListRepository(UserCandleWatchListRepository userCandleWatchListRepository) {
        this.userCandleWatchListRepository = userCandleWatchListRepository;
    }

//    @Scheduled(cron = "*/20 * * * * ?")
//    public void checkCandle() throws ParseException {
//        System.out.println("time now: "+new Date());
//        List<UserCandleWatchList> ucwlLists = userCandleWatchListRepository.findAll();
//        for (UserCandleWatchList userCandleWatchList : ucwlLists){
//            if (userCandleWatchList.getActive()){
//                checkCandleAndSendNotification(userCandleWatchList);
//            }
//        }
//    }
//
//    private void checkCandleAndSendNotification(UserCandleWatchList userCandleWatchList) throws ParseException {
//        UserStockWatchList currentUserStockWatchList = userCandleWatchList.getUserStockWatchList();
//        User currentUser = currentUserStockWatchList.getUser();
//        String currentEmail = currentUser.getEmail();
//        String currentTicker = currentUserStockWatchList.getStock().getStockTicker();
//        List<CandleModel> result = candleService.getCandleData(currentTicker);
//        List<String> dates;
//
//        /*
//         * check if the candle exists and send Email
//         * */
//        if (userCandleWatchList.getCandle().getId() == 1){
//            dates = candleService.getBullishEngulfingCandleSignal(result);
//            String latestTimeCandleAppear = dates.get(0);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//注意月份是MM
//            Date latestDate = simpleDateFormat.parse(latestTimeCandleAppear);
//
//            //check the latestDate and now, if less and a day, sendEmail
//            if (new Date().getTime()-latestDate.getTime()<86400000){
//                /*
//                * send Notification
//                * */
//            }
//        }
//        else if(userCandleWatchList.getCandle().getId() == 2){
//            dates = candleService.getBearishEngulfingCandleSignal(result);
//        }
//        else if(userCandleWatchList.getCandle().getId() == 3){
//            dates = candleService.getMorningStarCandle(result);
//        }
//        else{
//            dates = candleService.getEveningStar(result);
//        }
//    }
}
