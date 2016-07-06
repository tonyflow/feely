package application.support;

import java.time.Clock;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;


public class DateTimeHelper {

	@Autowired
	private Clock appClock;
	
	public static ZonedDateTime now(Clock appClock) {
		return ZonedDateTime.now(appClock);
	}
	
	public static Long zonedDateTimeToMillis(ZonedDateTime time){
		return time.toInstant().toEpochMilli();
	}
	
	
}
