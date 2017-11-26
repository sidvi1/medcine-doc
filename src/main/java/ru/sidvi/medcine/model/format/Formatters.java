package ru.sidvi.medcine.model.format;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Vitaly Sidorov
 */
public class Formatters {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static ThreadLocal<DecimalFormat> df = new ThreadLocal<DecimalFormat>() {
        @Override
        protected DecimalFormat initialValue() {
            DecimalFormat df = new DecimalFormat();
            df.setGroupingUsed(true);
            df.setGroupingSize(3);
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            return df;
        }
    };
    private static ThreadLocal<DateFormat> date = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DATE_PATTERN);
        }
    };

    private Formatters() {
    }

    public static String formatMoney(BigDecimal value) {
        return df.get().format(value);
    }

    public static String formatDate(Date value) {
        return date.get().format(value);
    }

    public static String getDatePattern() {
        return DATE_PATTERN;
    }

    public static String formatFileSize(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
