package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Utils {

	public String addMinutesToDate(String dateString, String format, int minute)
	{
		 DateFormat formatter = new SimpleDateFormat(format);
		 Date dateObtained = null;
		try {
			dateObtained = formatter.parse(dateString);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		 Calendar cal = Calendar.getInstance();
		  cal.setTime(dateObtained);
		  cal.add(Calendar.MINUTE, minute);
		  dateObtained = cal.getTime();
		  return formatter.format(dateObtained);
	}
}
