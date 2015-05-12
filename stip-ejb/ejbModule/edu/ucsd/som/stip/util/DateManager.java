package edu.ucsd.som.stip.util;

import java.util.Calendar;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SECOND;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.jboss.seam.annotations.Name;

@Name("dateManager")
public class DateManager {

	private final static long DAY_IN_MS = 24 * 60 * 60 * 1000;
	private final static String DATE_FORMAT = "MMMM.dd.yyyy hh:mm aaa";

	public DateManager() {
	}

	public Date getDate() {
		Calendar cal = Calendar.getInstance();
		assert cal != null;
		cal.setTimeInMillis(System.currentTimeMillis());
		Date date = cal.getTime();
		return date;
	}

	public Date getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		assert cal != null;
		cal.setTimeInMillis(System.currentTimeMillis());
		return cal.getTime();
	}

	public String getDateStamp() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		cal.setTimeInMillis(System.currentTimeMillis());
		Date date = cal.getTime();
		String dateStamp = dateFormat.format(date);
		return dateStamp;
	}

	public Date getToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(HOUR_OF_DAY, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
		cal.set(MILLISECOND, 0);
		//
		Date today = cal.getTime();
		return today;
	}

	public static int getMinutesBetween(Date _start, Date _end) {
		Calendar cal = Calendar.getInstance();
		if (_start == null || _end == null) {
			throw new IllegalArgumentException("Start or end date null");
		}
		if (_start.after(_start)) {
		}
		assert cal != null;
		assert _start != null;
		assert _end != null;

		long diffInMillis = _end.getTime() - _start.getTime();
		int diffInMinutes = (int) (((double) diffInMillis) / 1000) / 60;
		return diffInMinutes;
	}

	public static Date getMondayOfWeek(Date _date) {
		Calendar cal = Calendar.getInstance();
		if (null == _date) {
			throw new IllegalArgumentException("Parameter date is null !");
		}
		cal.setTime(_date);
		cal.set(DAY_OF_WEEK, MONDAY);
		cal.set(HOUR_OF_DAY, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
		cal.set(MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getFridayOfWeek(Date _date) {
		Calendar cal = Calendar.getInstance();
		if (null == _date) {
			throw new IllegalArgumentException("Parameter date is null !");
		}
		cal.setTime(_date);
		cal.set(DAY_OF_WEEK, FRIDAY);
		cal.set(HOUR_OF_DAY, 23);
		cal.set(MINUTE, 59);
		cal.set(SECOND, 59);
		cal.set(MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date addTime(Date _date, int _hours, int _min) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		cal.set(HOUR_OF_DAY, _hours);
		cal.set(MINUTE, _min);
		return cal.getTime();
	}

	public static int getHour(Date _date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		return cal.get(HOUR_OF_DAY);
	}

	public static int getMinute(Date _date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		return cal.get(MINUTE);
	}

	public static int getSecond(Date _date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		return cal.get(SECOND);
	}

	public static boolean isToday(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& today.get(Calendar.DAY_OF_YEAR) == cal
						.get(Calendar.DAY_OF_YEAR);
	}

	public static boolean isThisWeek(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& today.get(Calendar.WEEK_OF_YEAR) == cal
						.get(Calendar.WEEK_OF_YEAR);
	}

	public static boolean isThisMonth(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& today.get(Calendar.MONTH) == cal.get(Calendar.MONTH);
	}

	public static boolean isThisQuarter(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& today.get(Calendar.MONTH) / 3 == cal.get(Calendar.MONTH) / 3;
	}

	public static boolean isThisYear(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR);

	}

	public static boolean isYesterday(Calendar cal) {
		Calendar yesterday = adjustDate(Calendar.getInstance(), -1);
		return yesterday.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& yesterday.get(Calendar.DAY_OF_YEAR) == cal
						.get(Calendar.DAY_OF_YEAR);
	}

	public static boolean isLastWeek(Calendar cal) {
		Calendar lastWeek = adjustDate(Calendar.getInstance(), -7);
		return lastWeek.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& lastWeek.get(Calendar.WEEK_OF_YEAR) == cal
						.get(Calendar.WEEK_OF_YEAR);
	}

	public static boolean isLastMonth(Calendar cal) {
		Calendar today = Calendar.getInstance();
		int thisMonth = today.get(Calendar.MONTH);
		if (thisMonth > 1) {
			return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
					&& thisMonth - 1 == cal.get(Calendar.MONTH);
		} else {
			return today.get(Calendar.YEAR) - 1 == cal.get(Calendar.YEAR)
					&& today.getActualMaximum(Calendar.MONTH) == cal
							.get(Calendar.MONTH);
		}
	}

	public static boolean isLastQuarter(Calendar cal) {
		Calendar today = Calendar.getInstance();
		int thisQuarter = today.get(Calendar.MONTH) / 3;
		if (thisQuarter > 1) {
			return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
					&& thisQuarter - 1 == cal.get(Calendar.MONTH) / 3;
		} else {
			return today.get(Calendar.YEAR) - 1 == cal.get(Calendar.YEAR)
					&& today.getActualMaximum(Calendar.MONTH) / 3 == cal
							.get(Calendar.MONTH) / 3;
		}
	}

	public static boolean isLastYear(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) - 1 == cal.get(Calendar.YEAR);

	}

	public static boolean isTomorrow(Calendar cal) {
		Calendar tomorrow = adjustDate(Calendar.getInstance(), 1);
		return tomorrow.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& tomorrow.get(Calendar.DAY_OF_YEAR) == cal
						.get(Calendar.DAY_OF_YEAR);
	}

	public static boolean isNextWeek(Calendar cal) {
		Calendar nextWeek = adjustDate(Calendar.getInstance(), 7);
		return nextWeek.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& nextWeek.get(Calendar.WEEK_OF_YEAR) == cal
						.get(Calendar.WEEK_OF_YEAR);
	}

	public static boolean isNextMonth(Calendar cal) {
		Calendar today = Calendar.getInstance();
		int thisMonth = today.get(Calendar.MONTH);
		if (thisMonth < today.getActualMaximum(Calendar.MONTH)) {
			return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
					&& thisMonth + 1 == cal.get(Calendar.MONTH);
		} else {
			return today.get(Calendar.YEAR) + 1 == cal.get(Calendar.YEAR)
					&& today.getMinimum(Calendar.MONTH) == cal
							.get(Calendar.MONTH);
		}
	}

	public static boolean isNextQuarter(Calendar cal) {
		Calendar today = Calendar.getInstance();
		int thisQuarter = today.get(Calendar.MONTH) / 3;
		if (thisQuarter < today.getActualMaximum(Calendar.MONTH) / 3) {
			return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
					&& thisQuarter + 1 == cal.get(Calendar.MONTH) / 3;
		} else {
			return today.get(Calendar.YEAR) + 1 == cal.get(Calendar.YEAR)
					&& today.getActualMinimum(Calendar.MONTH) / 3 == cal
							.get(Calendar.MONTH) / 3;
		}
	}

	public static boolean isNextYear(Calendar cal) {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) + 1 == cal.get(Calendar.YEAR);

	}

	public static boolean isAtMonth(Calendar cal, int month) {
		return cal.get(Calendar.MONTH) == month;
	}

	public static boolean isAtQuarter(Calendar cal, int quarter) {
		return cal.get(Calendar.MONTH) / 3 + 1 == quarter;
	}

	public static Calendar adjustDate(Calendar calendar, int differenceInDay) {
		calendar.setTimeInMillis(calendar.getTimeInMillis() + DAY_IN_MS
				* differenceInDay);
		return calendar;
	}

	public static Date Min(List<Date> dates) {
		long min = Long.MAX_VALUE;
		Date minDate = null;
		for (Date value : dates) {
			long v = value.getTime();
			if (v < min) {
				min = v;
				minDate = value;
			}
		}
		return minDate;
	}

	public static Date Max(List<Date> dates) {
		long max = Long.MIN_VALUE;
		Date maxDate = null;
		for (Date value : dates) {
			long v = value.getTime();
			if (v > max) {
				max = v;
				maxDate = value;
			}
		}
		return maxDate;
	}

	public static Calendar min(List<Calendar> calendars) {
		long min = Long.MAX_VALUE;
		Calendar minCalendar = null;
		for (Calendar value : calendars) {
			long v = value.getTimeInMillis();
			if (v < min) {
				min = v;
				minCalendar = value;
			}
		}
		return minCalendar;
	}

	public static Calendar max(List<Calendar> calendars) {
		long max = Long.MIN_VALUE;
		Calendar maxCalendar = null;
		for (Calendar value : calendars) {
			long v = value.getTimeInMillis();
			if (v > max) {
				max = v;
				maxCalendar = value;
			}
		}
		return maxCalendar;
	}
}
