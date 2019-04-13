package com.lindsay.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtlis {
    public TimeUtlis() {
    }

    public static Integer getTimeSpan() {
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime() / 1000L);
        return Integer.valueOf(timestamp);
    }

    public static String timeTostr(Integer ms, String pattern) {
        String str = "";
        if (ms != null) {
            long msl = (long)ms * 1000L;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            if (ms != null) {
                try {
                    str = sdf.format(msl);
                } catch (Exception var7) {
                    var7.printStackTrace();
                }
            }
        }

        return str;
    }

    public static String timeTostr(Integer ms) {
        String str = "";
        if (ms != null) {
            long msl = (long)ms * 1000L;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (ms != null) {
                try {
                    str = sdf.format(msl);
                } catch (Exception var6) {
                    var6.printStackTrace();
                }
            }
        }

        return str;
    }

    public static int getDayByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String day = date.substring(8, 10);
        return Integer.parseInt(day);
    }

    public static String timeStampToDate(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static Long getDayOrigin() {
        return getDayOrigin(new Date());
    }

    public static Long getDayOrigin(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        return date2Timestamp(dateStr, "yyyy-MM-dd");
    }

    public static Long date2Timestamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr).getTime() / 1000L;
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0L;
        }
    }

    public static Long getDateTimestamp(Integer timeStamp) {
        return 1531929600000L;
    }

    public static Long getTimeTimestamp(Integer timeStamp) {
        return 1532660710000L;
    }
}
