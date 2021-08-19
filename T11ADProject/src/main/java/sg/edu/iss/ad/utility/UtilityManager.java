package sg.edu.iss.ad.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UtilityManager {
	
	//WithSeconds true to get just date. False to get Timing
	public static String UnixToString(long unixTime,Boolean WithSeconds) {
		LocalDateTime ldt= LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.ofHours(8));
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		DateTimeFormatter inputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		DateTimeFormatter outputFormatterWithoutSeconds=DateTimeFormatter.ofPattern("dd MMM yyyy");
		DateTimeFormatter outputFormatterWithSeconds=DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
		LocalDateTime input=LocalDateTime.of(2021, 12, 1,0,0,0);
		try {
			input=LocalDateTime.parse(ldt.toString(),inputFormatter);			
		}
		catch(Exception error) {}
		try {
			input=LocalDateTime.parse(ldt.toString(),inputFormatter2);	
			//because if market opens, the format will have ss
		}
		catch(Exception error) {}
		
		String formattedDate="";
		if(WithSeconds==true)
			formattedDate=outputFormatterWithSeconds.format(input);
		if(WithSeconds==false)
			formattedDate= outputFormatterWithoutSeconds.format(input);
		System.out.println(formattedDate);
		return formattedDate;
	}
	
	public static String UnixToDate (long unixTime,boolean WithTiming) {
		LocalDateTime ldt= LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.ofHours(8));
		String DATE_FORMATTER="";
		if(WithTiming==true)
			DATE_FORMATTER="dd MMM yyyy HH:mm";
		if(WithTiming==false)
			DATE_FORMATTER="dd MMM yyyy";
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern(DATE_FORMATTER);
		return ldt.format(formatter);
	}
	
	public static long DateToUnix(String date) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
		LocalDateTime input=LocalDateTime.parse(date,formatter);
		ZoneId sgZone= ZoneId.of("Asia/Singapore");
		ZonedDateTime dtz= ZonedDateTime.of(input, sgZone);
		System.out.println(dtz);
		return dtz.toEpochSecond();
	}
	
}
