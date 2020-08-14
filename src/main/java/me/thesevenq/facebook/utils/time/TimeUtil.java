package me.thesevenq.facebook.utils.time;

import org.apache.commons.lang.time.DurationFormatUtils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {

	private static final String HOUR_FORMAT = "%02d:%02d:%02d";
	private static final String MINUTE_FORMAT = "%02d:%02d";
	private static final String TIME_UNTIL_PATTERN = "d'd' H'h' mm'm'";

	public static String millisToTimer(long millis) {
		long seconds = millis / 1000L;

		if (seconds > 3600L) {
			return String.format(HOUR_FORMAT, seconds / 3600L, seconds % 3600L / 60L, seconds % 60L);
		} else {
			return String.format(MINUTE_FORMAT, seconds / 60L, seconds % 60L);
		}

	}

	private static SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy h:mma");
	public static long PERMANENT = Long.MAX_VALUE;
	private static ThreadLocal SECONDS = ThreadLocal.withInitial(() -> new DecimalFormat("0.#"));
	private static ThreadLocal TRAILING = ThreadLocal.withInitial(() -> new DecimalFormat("0"));

	public static String formatDuration(long input) {
		return DurationFormatUtils.formatDurationWords(input, true, true);
	}

	public static String formatDate(long value) {
		return FORMAT.format(new Date(value));
	}

	public static int parseTime(String time) {
		if (time.equals("0") || time.equals("")) {
			return 0;
		}

		String[] lifeMatch = new String[]{"y", "M", "w", "d", "h", "m", "s"};
		int[] lifeInterval = new int[]{31536000, 2592000, 604800, 86400, 3600, 60, 1};
		int seconds = 0;

		for (int i = 0; i < lifeMatch.length; i++) {
			Matcher matcher = Pattern.compile("([0-9]*)" + lifeMatch[i]).matcher(time);

			while (matcher.find()) {
				seconds += Integer.parseInt(matcher.group(1)) * lifeInterval[i];
			}
		}

		return seconds;
	}

		public static String millisToSeconds(long millis) {
		return new DecimalFormat("#0.0").format(millis / 1000.0F) + "s";
	}

	public static String dateToString(Date date, String secondaryColor) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return new SimpleDateFormat("dd.MM.yyyy. " + (secondaryColor == null ? "" : secondaryColor) +
				"(hh:mm aa zz)").format(date);
	}

	public static Timestamp addDuration(long duration) {
		return truncateTimestamp(new Timestamp(System.currentTimeMillis() + duration));
	}

	public static Timestamp truncateTimestamp(Timestamp timestamp) {
		if (timestamp.toLocalDateTime().getYear() > 2037) {
			timestamp.setYear(2037);
		}

		return timestamp;
	}


	public static Timestamp addDuration(Timestamp timestamp) {
		return truncateTimestamp(new Timestamp(System.currentTimeMillis() + timestamp.getTime()));
	}

	public static Timestamp fromMillis(long millis) {
		return new Timestamp(millis);
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String millisToRoundedTime(long millis) {


		if (millis == Integer.MAX_VALUE) {
			return "permanent";
		}


		millis += 1L;

		long seconds = millis / 1000L;
		long minutes = seconds / 60L;
		long hours = minutes / 60L;
		long days = hours / 24L;
		long weeks = days / 7L;
		long months = weeks / 4L;
		long years = months / 12L;

		if (years > 0) {
			return years + " year" + (years == 1 ? "" : "s");
		} else if (months > 0) {
			return months + " month" + (months == 1 ? "" : "s");
		} else if (weeks > 0) {
			return weeks + " week" + (weeks == 1 ? "" : "s");
		} else if (days > 0) {
			return days + " day" + (days == 1 ? "" : "s");
		} else if (hours > 0) {
			return hours + " hour" + (hours == 1 ? "" : "s");
		} else if (minutes > 0) {
			return minutes + " minute" + (minutes == 1 ? "" : "s");
		} else {
			return seconds + " second" + (seconds == 1 ? "" : "s");
		}
	}

	public static long fromString(String time) {
		if (time.equalsIgnoreCase("perm") || time.equalsIgnoreCase("permanent")) {
			return Integer.MAX_VALUE;
		}

		long totalTime = 0L;

		boolean found = false;

		final Matcher matcher = Pattern.compile("\\d+\\D+").matcher(time);

		while (matcher.find()) {

			final String s = matcher.group();

			final Long value = Long.parseLong(s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[0]);

			final String type = s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[1];

			switch (type) {
				case "s":
					totalTime += value;
					found = true;
					break;
				case "m":
					totalTime += value * 60;
					found = true;
					break;
				case "h":
					totalTime += value * 60 * 60;
					found = true;
					break;
				case "d":
					totalTime += value * 60 * 60 * 24;
					found = true;
					break;
				case "w":
					totalTime += value * 60 * 60 * 24 * 7;
					found = true;
					break;
				case "M":
					totalTime += value * 60 * 60 * 24 * 30;
					found = true;
					break;
				case "y":
					totalTime += value * 60 * 60 * 24 * 365;
					found = true;
					break;
			}

		}

		return !found ? -1 : totalTime * 1000;
	}

	public static String millisToRoundedShortTime(long millis) {

		if (millis > (86400 * 1000)) {
			return DurationFormatUtils.formatDuration(millis, TIME_UNTIL_PATTERN);
		}

		return millisToTimer(millis);
	}

}
