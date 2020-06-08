package me.thesevenq.facebook.utils.time;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeUtilties {

    private static final String HOUR_FORMAT = "%02d:%02d:%02d";
    private static final String MINUTE_FORMAT = "%02d:%02d";
    private static final DecimalFormat SECONDS_FORMAT = new DecimalFormat("#0.0");

    public String formatTimeMillis(long millis) {
        long seconds = millis / 1000L;

        if (seconds <= 0) {
            return "0 seconds";
        }

        long minutes = seconds / 60;
        seconds = seconds % 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        long days = hours / 24;
        hours = hours % 24;
        long years = days / 365;
        days = days % 365;

        StringBuilder time = new StringBuilder();

        if (years != 0) {
            time.append(parseTimeSpec(years, "year"));
        }

        if (days != 0) {
            time.append(parseTimeSpec(days, "day"));
        }

        if (hours != 0) {
            time.append(parseTimeSpec(hours, "hour"));
        }

        if (minutes != 0) {
            time.append(parseTimeSpec(minutes, "minute"));
        }

        if (seconds != 0) {
            time.append(parseTimeSpec(seconds, "second"));
        }

        return time.toString().trim();
    }

    private String parseTimeSpec(long time, String spec) {
        return time + " " + (time == 1 ? spec : spec + "s") + " ";
    }

    public String formatTimeSeconds(long seconds) {
        return formatTimeMillis(seconds * 1000);
    }

    public String formatTimeMillisToClock(long millis) {
        return millis / 1000L <= 0 ? "0:00" : String.format("%01d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public String formatTimeSecondsToClock(long seconds) {
        return formatTimeMillisToClock(seconds * 1000);
    }

    public int parseTime(String time) {
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

    public String formatDateDiff(Calendar fromDate, Calendar toDate) {
        boolean future = false;

        if (toDate.equals(fromDate)) {
            return "now";
        } else {
            if (toDate.after(fromDate)) {
                future = true;
            }

            StringBuilder sb = new StringBuilder();
            int[] types = new int[]{1, 2, 5, 11, 12, 13};
            String[] names = new String[]{"year", "years", "month", "months", "day", "days", "hour", "hours",
                    "minute", "minutes", "second", "seconds"};
            int accuracy = 0;

            for (int i = 0; i < types.length && accuracy <= 2; ++i) {
                int diff = dateDiff(types[i], fromDate, toDate, future);

                if (diff > 0) {
                    ++accuracy;
                    sb.append(" ").append(diff).append(" ").append(names[i * 2 + (diff > 1 ? 1 : 0)]);
                }
            }

            return sb.length() == 0 ? "now" : sb.toString().trim();
        }
    }

    private int dateDiff(int type, Calendar fromDate, Calendar toDate, boolean future) {
        int diff = 0;

        long savedDate;

        for (savedDate = fromDate.getTimeInMillis(); future && !fromDate.after(toDate) || !future && !fromDate.before
                (toDate); ++diff) {
            savedDate = fromDate.getTimeInMillis();
            fromDate.add(type, future ? 1 : -1);
        }

        --diff;
        fromDate.setTimeInMillis(savedDate);
        return diff;
    }
}