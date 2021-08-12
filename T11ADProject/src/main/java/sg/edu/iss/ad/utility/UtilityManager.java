package sg.edu.iss.ad.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class UtilityManager {
	public static LocalDateTime UnixToDate (long unixTime) {
		LocalDateTime ldt=LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.ofHours(8));
		return ldt;
		
	}
	
	public static String UnixToString(long unixTime) {
		LocalDateTime ldt= LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.ofHours(8));
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		DateTimeFormatter inputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		DateTimeFormatter outputFormatter=DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDate input=LocalDate.of(2021, 12, 1);
		try {
			input=LocalDate.parse(ldt.toString(),inputFormatter);			
		}
		catch(Exception error) {}
		try {
			input=LocalDate.parse(ldt.toString(),inputFormatter2);	
			//because if market opens, the format will have ss
		}
		catch(Exception error) {}
		String formattedDate= outputFormatter.format(input);
		System.out.println(formattedDate);
		return formattedDate;

	}
}
