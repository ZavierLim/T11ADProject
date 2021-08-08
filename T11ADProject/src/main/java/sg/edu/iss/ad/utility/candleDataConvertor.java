package sg.edu.iss.ad.utility;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sg.edu.iss.ad.model.CandleModel;

import java.text.SimpleDateFormat;
import java.util.*;

public class candleDataConvertor {

    public static List<CandleModel> candleResultToList(String candleData){

        List<CandleModel> candles= new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(candleData);
        JSONObject chart = JSON.parseObject(jsonObject.get("chart").toString());
        JSONArray result = JSON.parseArray(chart.get("result").toString());
        JSONArray timestamps = JSON.parseArray(result.getJSONObject(0).get("timestamp").toString());
        JSONObject indicators = JSON.parseObject(result.getJSONObject(0).get("indicators").toString());
        JSONArray quote = JSON.parseArray(indicators.get("quote").toString());
        JSONObject price = JSON.parseObject(quote.getJSONObject(0).toString());
        JSONArray high = JSON.parseArray(price.get("high").toString());
        JSONArray low = JSON.parseArray(price.get("low").toString());
        JSONArray open = JSON.parseArray(price.get("open").toString());
        JSONArray close = JSON.parseArray(price.get("close").toString());
        JSONArray volume = JSON.parseArray(price.get("volume").toString());

        List<Integer> timestampList = JSON.parseArray(timestamps.toString(),Integer.class);
        List<Integer> volumeList = JSON.parseArray(volume.toString(),Integer.class);
        List<Double> highList = JSON.parseArray(high.toString(),Double.class);
        List<Double> lowList = JSON.parseArray(low.toString(),Double.class);
        List<Double> openList = JSON.parseArray(open.toString(),Double.class);
        List<Double> closeList = JSON.parseArray(close.toString(),Double.class);

        for (int i = 0; i < low.size(); i++) {
            int t = timestampList.get(i);
            int v = volumeList.get(i);
            double h = highList.get(i);
            double l = lowList.get(i);
            double o = openList.get(i);
            double c = closeList.get(i);

            candles.add(new CandleModel(t,c,o,h,l,v));
//
//            String formats = "yyyy-MM-dd HH:mm:ss";
//            long timestamp = (long)t * 1000;
//            String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
//            System.out.println("time: "+date);
//            System.out.println("open: "+o);
//            System.out.println("close: "+c);
//            System.out.println("high: "+h);
//            System.out.println("low: "+l);
//            System.out.println("value: "+v);
//
        }
        return candles;
    }
}
