package com.anjile.shineourlove.rxjavaapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CP on 2016/9/12.
 * 时间格式化工具类
 */
public class DateFormatTime {
    public static String getTimeDataForYeas(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static String getTimeDataForYearsToDate(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateFormat.format(currentTime);
        return time;
    }

    /**
     * 只保留数字 时间格式化
     *
     * @param currentTime
     * @return
     */
    public static String getTimeDataForYearsToNumber(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static String getTimeDataForYearsMonthDay(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static long getTimeDataForYearsMonthDayLong(String currentTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(currentTime);
            long time = date.getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTimeDataForHour(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static String getTimeDataForMonth(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static String getTimeDataForSS(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static String getTimeDataAll(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static long getTimeDataAllLong(String currentTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        try {
            Date date = format.parse(currentTime);
            long time = date.getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTimeyyyyMMddHH(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static long getTimeDataForMS(String currentTime) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date date = format.parse(currentTime);
            long time = date.getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 格式化倒计时
     *
     * @param scTimeValue 需要格式化的时间参数
     * @return
     */
    public static String countDownFormat(String scTimeValue) {
        int scTime = Integer.parseInt(scTimeValue);
        String left = String.valueOf(scTime / 60);
        String right = String.valueOf(scTime % 60);
        int leftLength = left.length();
        int rightLength = right.length();
        if (leftLength == 1 && rightLength != 1) {
            left = "0" + left;
        } else if (rightLength == 1 && leftLength != 1) {
            right = "0" + right;
        } else if (leftLength == 1 && rightLength == 1) {
            left = "0" + left;
            right = "0" + right;
        }
        return left + ":" + right;
    }

    public static String getTimeDateToRecord(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static String getTimeDateDevice(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        String time = dateFormat.format(currentTime);
        return time;
    }

    public static int getTimeHour(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        String time = dateFormat.format(currentTime);
        return Integer.valueOf(time).intValue();
    }

    public static String getFromSecond(long times) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(times - 8 * 60 * 1000 * 60);
        return time;
    }

    public static int getMonthFromCurrent(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        String time = dateFormat.format(currentTime);
        return Integer.valueOf(time).intValue();
    }

    public static int getDayFromCurrent(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String time = dateFormat.format(currentTime);
        return Integer.valueOf(time).intValue();
    }

    public static int getHourFromCurrent(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        String time = dateFormat.format(currentTime);
        return Integer.valueOf(time).intValue();
    }

    public static int getMiniuteFromCurrent(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
        String time = dateFormat.format(currentTime);
        return Integer.valueOf(time).intValue();
    }

    public static int getSecondFromCurrent(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ss");
        String time = dateFormat.format(currentTime);
        return Integer.valueOf(time).intValue();
    }

    public static int getYearMonthDayInteger(long currentTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String time = dateFormat.format(currentTime);
        return Integer.parseInt(time);
    }

    /**
     * 选择器的时间
     *
     * @return for example : 6.7, 6.8
     */
    public static String getPickMonthDay(Long time) {
        int month = getMonthFromCurrent(time);
        int day = getDayFromCurrent(time);
        return month + "." + day;
    }
}
