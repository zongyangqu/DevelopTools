package com.quzy.coding.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/5
 *
 * 类描述：日期工具类
 */

public class DateUtil {
    /** 时间日期格式化到年月日时分秒. */
    public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

    /** 时间日期格式化到年月日时. */
    public static final String dateFormatYMDH = "yyyy/MM/dd HH:00";

    /** 时间日期格式化到年月日. */
    public static final String dateFormatYMD = "yyyy-MM-dd";

    /** 时间日期格式化到年月. */
    public static final String dateFormatYM = "yyyy-MM";
    /** 时间日期格式化到年月. */
    public static final String dateFormatYM_CH = "yyyy年MM月";


    /** 时间日期格式化到年月日时分. */
    public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";

    /** 时间日期格式化到年月日时分. */
    public static final String dateFormatYMDHMFORMAT = "MM月dd日 HH:mm";

    /** 时间日期格式化到月日. */
    public static final String dateFormatMD = "MM/dd";

    /** 时分秒. */
    public static final String dateFormatHMS = "HH:mm:ss";

    /** 时分. */
    public static final String dateFormatHM = "HH:mm";

    /** 上午. */
    public static final String AM = "AM";

    /** 下午. */
    public static final String PM = "PM";



    /**
     * 描述：String类型的日期时间转化为Date类型.
     *
     * @param strDate String形式的日期时间
     * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate);
            mSimpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 描述：获取偏移之后的Date.
     * @param date 日期时间
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset 偏移(值大于0,表示+,值小于0,表示－)
     * @return Date 偏移之后的日期时间
     */
    public static Date getDateByOffset(Date date, int calendarField, int offset) {
        Calendar c = new GregorianCalendar();
        try {
            c.setTime(date);
            c.add(calendarField, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 描述：获取指定日期时间的字符串(可偏移).
     *
     * @param strDate String形式的日期时间
     * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset 偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getStringByOffset(String strDate, String format, int calendarField, int offset) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(mSimpleDateFormat.parse(strDate));
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：Date类型转化为String类型(可偏移).
     *
     * @param date the date
     * @param format the format
     * @param calendarField the calendar field
     * @param offset the offset
     * @return String String类型日期时间
     */
    public static String getStringByOffset(Date date, String format, int calendarField, int offset) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(date);
            c.add(calendarField, offset);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    /**
     * 描述：Date类型转化为String类型.
     *
     * @param date the date
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format 输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(dateFormatYMDHMS);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：获取milliseconds表示的日期时间的字符串.
     *
     * @param milliseconds the milliseconds
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 日期时间字符串
     */
    public static String getStringByFormat(long milliseconds, String format) {
        String thisDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisDateTime;
    }
    /**
     * 描述：获取表示当前日期时间的字符串.
     *
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String String类型的当前日期时间
     */
    public static String getCurrentDate(String format) {
        String curDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            curDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;

    }

    /**
     * 描述：获取表示当前日期时间的字符串(可偏移).
     *
     * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset 偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getCurrentDateByOffset(String format, int calendarField, int offset) {
        String mDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;

    }

    /**
     * 描述：计算两个日期所差的天数.
     *
     * @param milliseconds1 the milliseconds1
     * @param milliseconds2 the milliseconds2
     * @return int 所差的天数
     */
    public static int getOffectDay(long milliseconds1, long milliseconds2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(milliseconds1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(milliseconds2);
        //先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 + maxDays;
        } else if (y1 - y2 < 0) {
            maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 - maxDays;
        } else {
            day = d1 - d2;
        }
        return day;
    }

    /**
     * 描述：计算两个日期所差的小时数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的小时数
     */
    public static int getOffectHour(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h = 0;
        int day = getOffectDay(date1, date2);
        h = h1-h2+day*24;
        return h;
    }

    /**
     * 描述：计算两个日期所差的分钟数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static int getOffectMinutes(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int m1 = calendar1.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        int h = getOffectHour(date1, date2);
        int m = 0;
        m = m1-m2+h*60;
        return m;
    }

    /**
     * 描述：计算两个日期所差的日期.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static String getOffectDate(long date1, long date2) {
        long different=date1-date2;
        different=different-1000;
        if(different>0){
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;
            long between_days = different / (1000 * 3600 * 24);

            different = different % daysInMilli;
            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            long elapsedSeconds = different / secondsInMilli;
            String tiem = between_days + "天" + elapsedHours + "时" + elapsedMinutes + "分"
                    + elapsedSeconds + "秒";

            return tiem;
        }
        return null;
    }

    /**
     * 描述：计算两个日期所差的日期.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static String getOffectDateNomal(long date1, long date2) {
        long different=date1-date2;
        different=different-1000;
        if(different>0){
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;
            long between_days = different / (1000 * 3600 * 24);

            different = different % daysInMilli;
            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            long elapsedSeconds = different / secondsInMilli;
            String tiem =  between_days + ":" + elapsedHours + ":" + elapsedMinutes + ":"
                    + elapsedSeconds;

            return tiem;
        }
        return "00:00:00:00";
    }

    /**
     * 描述：获取本周一.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.MONDAY);
    }

    /**
     * 描述：获取本周日.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.SUNDAY);
    }

    /**
     * 描述：获取本周的某一天.
     *
     * @param format the format
     * @param calendarField the calendar field
     * @return String String类型日期时间
     */
    private static String getDayOfWeek(String format, int calendarField) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == calendarField){
                strDate = mSimpleDateFormat.format(c.getTime());
            }else{
                int offectDay = calendarField - week;
                if (calendarField == Calendar.SUNDAY) {
                    offectDay = 7- Math.abs(offectDay);
                }
                c.add(Calendar.DATE, offectDay);
                strDate = mSimpleDateFormat.format(c.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取本月第一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            //当前月的第一天
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;

    }

    /**
     * 描述：获取本月最后一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的最后一天
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }



    /**
     * 描述：获取表示当前日期的0点时间毫秒数.
     *
     * @return the first time of day
     */
    public static long getFirstTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate+" 00:00:00",dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 描述：获取表示当前日期24点时间毫秒数.
     *
     * @return the last time of day
     */
    public static long getLastTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate+" 24:00:00",dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 描述：判断是否是闰年()
     * <p>(year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
     *
     * @param year 年代（如2012）
     * @return boolean 是否为闰年
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 400 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 取指定日期为星期几.
     *
     * @param strDate 指定日期
     * @param inFormat 指定日期格式
     * @return String   星期几
     */
    public static String getWeekNumber(String strDate, String inFormat) {
        DateFormat df = new SimpleDateFormat(inFormat);
        Date time;
        try {
            time = df.parse(strDate);
        } catch (Exception e) {
            return "错误";
        }
        return getWeekNumber(Week.XINQI,time);
    }

    public enum Week{
        XINQI("星期"),ZHOU("周"),NULL("");
        String t;
        Week(String t){
            this.t = t;
        }

        public String getS() {
            return t;
        }
    }

    /**
     * 取指定日期为星期几
     * @param time 指定日期
     * @return
     */
    public static String getWeekNumber(Week type, Date time) {
        String week = "日";
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(time);
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return getWeekNumber(type,intTemp);
    }

    public static String getWeekNumber(Week type, int day) {
        String week = "日";
        switch (day){
            case 0:
                week = "日";
                break;
            case 1:
                week = "一";
                break;
            case 2:
                week = "二";
                break;
            case 3:
                week = "三";
                break;
            case 4:
                week = "四";
                break;
            case 5:
                week = "五";
                break;
            case 6:
                week = "六";
                break;
        }
        return type.getS()+week;
    }

    /**
     * 根据给定的日期判断是否为上下午.
     *
     * @param strDate the str date
     * @param format the format
     * @return the time quantum
     */
    public static String getTimeQuantum(String strDate, String format) {
        Date mDate = getDateByFormat(strDate, format);
        int hour  = mDate.getHours();
        if(hour >=12)
            return "PM";
        else
            return "AM";
    }

    /**
     * 根据给定的毫秒数算得时间的描述.
     *
     * @param milliseconds the milliseconds
     * @return the time description
     */
    public static String getTimeDescription(long milliseconds) {
        if(milliseconds > 1000){
            //大于一分
            if(milliseconds/1000/60>1){
                long minute = milliseconds/1000/60;
                long second = milliseconds/1000%60;
                return minute+"分"+second+"秒";
            }else{
                //显示秒
                return milliseconds/1000+"秒";
            }
        }else{
            return milliseconds+"毫秒";
        }
    }
    /**
     * a integer to xx:xx:xx
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr ="00:"+unitFormat(minute) + ":" + unitFormat(second);

            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


    // 添加大小月月份并将其转换为list,方便之后的判断
    static String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
    static String[] months_little = { "4", "6", "9", "11" };
    static List<String> list_big = Arrays.asList(months_big);
    static List<String> list_little = Arrays.asList(months_little);

    public static String orderTime(String orderCreateTime) {
        String data = orderCreateTime;
        try {
            String[] datas = orderCreateTime.split("-");
            int year = Integer.parseInt(datas[0]);
            int month;
            if (datas[1].charAt(0) == 0)
                month = datas[1].charAt(1);
            else
                month = Integer.parseInt(datas[1]);
            int day = Integer.parseInt(datas[2].split(" ")[0]);
            if (day == 30) {
                if (list_big.contains(String.valueOf(month))) {
                    day = 31;
                }
                if (list_little.contains(String.valueOf(month))) {
                    day = 1;
                    if (month == 12) {
                        year++;
                        month = 1;
                    } else {
                        month++;
                    }

                }
            } else if (day == 31) {
                day = 1;
                if (month == 12) {
                    year++;
                    month = 1;
                } else {
                    month++;
                }

            } else {
                String shijian = datas[2].split(" ")[1];
                String shi = shijian.split(":")[0];
                if (shi.contains("00")) {

                } else {
                    day++;
                }

            }
            data = year + "-" + month + "-" + day + " " + "01:00";
        } catch (Exception e) {
            // TODO: handle exception
        }

        return data;
    }

    public static boolean isOlderOutOfDate(String oldDate) throws Exception {
        Date olddate = new Date();
        Date tomdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        olddate = sdf.parse(oldDate);
        long oldtime = olddate.getTime();
        long tomtime = tomdate.getTime();
        if (oldtime >= tomtime)
            return false;
        else
            return true;
    }


    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Long date2TimeStamp(String date_str, String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return Long.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;// 2012年10月03日 23:41:31
    }

    public static String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;// 2012-10-03 23:41:31
    }

    public static int getCurrentYear() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
        String currentYear = format1.format(new Date(System.currentTimeMillis()));
        return Integer.parseInt(currentYear);// 2012-10-03 23:41:31
    }


    public static String getTimeShowString(Date currentTime){
        String dataString;
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date todaybegin = todayStart.getTime();
        Date tomorrowbegin = new Date(todaybegin.getTime() + 3600 * 24 * 1000);
        Date houtianbegin = new Date(tomorrowbegin.getTime() + 3600 * 24 * 1000);
        Date dahoutianbegin = new Date(houtianbegin.getTime() + 3600 * 24 * 1000);
        Date yesterdaybegin = new Date(todaybegin.getTime() - 3600 * 24 * 1000);
        Date preyesterday = new Date(yesterdaybegin.getTime() - 3600 * 24 * 1000);

        if(currentTime.getTime()>dahoutianbegin.getTime()){
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }
        else if(currentTime.getTime()>houtianbegin.getTime()){
            //dataString = "后天";
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }else if(currentTime.getTime()>tomorrowbegin.getTime()){
            //dataString = "明天";
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }
        else if(currentTime.getTime()>todaybegin.getTime()){
            dataString = "今天";
        }
        else if(currentTime.getTime()>yesterdaybegin.getTime()){
            //dataString = "昨天";
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }else if(currentTime.getTime()>preyesterday.getTime()){
            //dataString = "前天";
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }
        else{
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }
        return dataString;
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);
        return isSameDate;
    }

    private static SimpleDateFormat msFormat = new SimpleDateFormat("mm:ss");

    /**
     * MS turn every minute
     *
     * @param duration Millisecond
     * @return Every minute
     */
    public static String timeParse(long duration) {
        String time = "";
        if (duration > 1000) {
            time = timeParseMinute(duration);
        } else {
            long minute = duration / 60000;
            long seconds = duration % 60000;
            long second = Math.round((float) seconds / 1000);
            if (minute < 10) {
                time += "0";
            }
            time += minute + ":";
            if (second < 10) {
                time += "0";
            }
            time += second;
        }
        return time;
    }

    public static String timeParse2ms(long duration) {
        String time = "";
        if (duration > 600000) {
            time = timeParseMinute(duration).replace(":","m")+"s";
        } else {
//            分
            long minute = duration / 60000;
//            秒
            long seconds = duration % 60000;
            long second = Math.round((float) seconds / 1000);
            if (minute == 0) {
                time += second+"s";
            }else{
                time += minute + "m";
                time += second+"s";
            }

        }
        return time;
    }

    /**
     * MS turn every minute
     *
     * @param duration Millisecond
     * @return Every minute
     */
    public static String timeParseMinute(long duration) {
        try {
            return msFormat.format(duration);
        } catch (Exception e) {
            e.printStackTrace();
            return "0:00";
        }
    }

    /**
     * 判断两个时间戳相差多少秒
     *
     * @param d
     * @return
     */
    public static int dateDiffer(long d) {
        try {
            long l1 = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10));
            long interval = l1 - d;
            return (int) Math.abs(interval);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 计算两个时间间隔
     *
     * @param sTime
     * @param eTime
     * @return
     */
    public static String cdTime(long sTime, long eTime) {
        long diff = eTime - sTime;
        return diff > 1000 ? diff / 1000 + "秒" : diff + "毫秒";
    }
}
