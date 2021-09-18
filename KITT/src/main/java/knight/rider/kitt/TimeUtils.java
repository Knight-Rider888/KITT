package knight.rider.kitt;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import knight.rider.kitt.type.TimeFormat;


public class TimeUtils {

    // 一天的时间
    static long oneDayTime = 86400000;

    /**
     * 获取今日开始时间
     */
    public static long getTodayStartTime() {

        long startTime = 0;

        long current = System.currentTimeMillis();

        try {

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String format = simpleDateFormat.format(current);

            String[] split = format.split(" ");
            String[] time = split[1].split(":");

            // 获取今日开始时间
            startTime = current - Long.parseLong(time[0]) * 60 * 60 * 1000 - Long.parseLong(time[1]) * 60 * 1000 - Long.parseLong(time[2]) * 1000 - Long.parseLong(time[3]);

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return startTime;
    }

    /**
     * 获取目标时间的当日开始时间
     */
    public static long getTargetDayStartTime(long targetTime) {

        long startTime = 0;

        try {

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String format = simpleDateFormat.format(targetTime);

            String[] split = format.split(" ");
            String[] time = split[1].split(":");

            // 获取今日开始时间
            startTime = targetTime - Long.parseLong(time[0]) * 60 * 60 * 1000 - Long.parseLong(time[1]) * 60 * 1000 - Long.parseLong(time[2]) * 1000 - Long.parseLong(time[3]);

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return startTime;
    }


    /**
     * 获取今日结束时间
     */
    public static long getTodayEndTime() {

        long endTime = 0;

        try {
            endTime = getTodayStartTime() + oneDayTime - 1;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return endTime;
    }


    /**
     * 获取目标时间的当日结束时间
     */
    public static long getTargetDayEndTime(long targetTime) {

        long endTime = 0;

        try {
            endTime = getTargetDayStartTime(targetTime) + oneDayTime - 1;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return endTime;
    }

    /**
     * 获取本周的开始时间
     */
    public static long getWeekStartTime() {

        long current = System.currentTimeMillis();
        long todayEndTime = getTargetDayEndTime(current);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(current);
        int weekDays = 0;
        int cweek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (cweek) {
            case 1:
                weekDays = 7;
                break;
            case 2:
                weekDays = 1;
                break;
            case 3:
                weekDays = 2;
                break;
            case 4:
                weekDays = 3;
                break;
            case 5:
                weekDays = 4;
                break;
            case 6:
                weekDays = 5;
                break;
            case 7:
                weekDays = 6;
                break;
        }
        return todayEndTime + 1 - weekDays * oneDayTime;
    }

    /**
     * 获取目标时间的周的开始时间
     */
    public static long getTargetWeekStartTime(long targetTime) {

        long todayEndTime = getTargetDayEndTime(targetTime);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(targetTime);
        int weekDays = 0;
        int cweek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (cweek) {
            case 1:
                weekDays = 7;
                break;
            case 2:
                weekDays = 1;
                break;
            case 3:
                weekDays = 2;
                break;
            case 4:
                weekDays = 3;
                break;
            case 5:
                weekDays = 4;
                break;
            case 6:
                weekDays = 5;
                break;
            case 7:
                weekDays = 6;
                break;
        }
        return todayEndTime + 1 - weekDays * oneDayTime;
    }

    /**
     * 获取指定毫秒数的对应星期
     */
    public static String getWeek(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        String week = "";
        int cweek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (cweek) {
            case 1:
                week = "星期日";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            case 7:
                week = "星期六";
                break;
        }
        return week;
    }

    /**
     * 格式化时间
     * 去年及以前 显示 2020/10/12 10:20
     * 今年 显示为简短日期 10/12 20:22（此为根据TimeFormat传值）
     * 本周内 显示 昨天15:05、星期几15:05、15:20（此为根据TimeFormat传值）
     * 未来时间 显示为年月日时分（此为根据TimeFormat传值）
     */
    @SuppressLint("SimpleDateFormat")
    public static String covertShortTime(long time, TimeFormat format) {

        // 原路返回
        if (time <= 0 || format == null)
            return String.valueOf(time);

        long todayEndTime = getTodayEndTime();

        // 目标时间
        String targetFormat = new SimpleDateFormat(format.getFormat()).format(time);
        String[] split = targetFormat.split(" ");
        int i = split[0].length() + 1;
        // 15:05
        String shortTime = targetFormat.substring(i);
        // 年
        int targetYear = Integer.parseInt(targetFormat.substring(0, 4));

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(todayEndTime));
        int year = c.get(Calendar.YEAR);


        // 今天23:59:59:999之后
        // 即传入的时间为未来时间，直接显示格式化的内容
        if (time > todayEndTime) {
            return targetFormat;
        } else if (time < getWeekStartTime()) {
            // 此周前（本周星期一之前）
            // 显示年月日
            if (targetYear != year)
                return targetFormat;
            else
                return targetFormat.substring(5);
        } else {
            // 显示星期 时分
            if (time < todayEndTime - oneDayTime * 2) {
                // 显示星期
                return getWeek(time) + " " + shortTime;
            } else if (time < todayEndTime - oneDayTime) {
                // 显示昨天
                return "昨天 " + shortTime;
            } else {
                // 显示今天的具体时间
                return shortTime;
            }
        }
    }
}
