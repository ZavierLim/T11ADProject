package sg.edu.iss.ad.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sg.edu.iss.ad.model.CandleModel;
import sg.edu.iss.ad.utility.candleDataConvertor;

import java.util.List;

@Service
public class CandleService{

    public List<CandleModel> getCandleData(String url) {
        RestTemplate restTemplate =new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Accept","application/json");
        //headers.add("x-api-key","eg3Z4ml4ik5Grz5tGNMlc7qsZz18VnEo21ERKTYp");
        headers.add("x-api-key","VTr2Z2gNmk7rVPuHnVMnyWw6tfGcEsbaHFWUixU7");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null,headers);
        ResponseEntity<String> rawResult = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);

        List<CandleModel> result = candleDataConvertor.candleResultToList(rawResult.getBody());

        return result;
    }
}
