package com.cnews.guji.smart.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

/**
 * @author JSYL-DCL
 * @date 2018/8/22 14:48
 * @des 时间工具类
 */
@SuppressLint("SimpleDateFormat")
public class DateTimeUtils {

    // ==格式到年==
    /**
     * 日期格式，年份，例如：2004，2008
     */
    public static final String DATE_FORMAT_YYYY = "yyyy";


    // ==格式到年月 ==
    /**
     * 日期格式，年份和月份，例如：200707，200808
     */
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

    /**
     * 日期格式，年份和月份，例如：200707，2008-08
     */
    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";


    // ==格式到年月日==
    /**
     * 日期格式，年月日，例如：050630，080808
     */
    public static final String DATE_FORMAT_YYMMDD = "yyMMdd";

    /**
     * 日期格式，年月日，用横杠分开，例如：06-12-25，08-08-08
     */
    public static final String DATE_FORMAT_YY_MM_DD = "yy-MM-dd";

    /**
     * 日期格式，年月日，例如：20050630，20080808
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
     */
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式，年月日，例如：2016.10.05
     */
    public static final String DATE_FORMAT_POINTYYYYMMDD = "yyyy.MM.dd";

    /**
     * 日期格式，年月日，例如：2016年10月05日
     */
    public static final String DATE_TIME_FORMAT_YYYY年MM月DD日 = "yyyy年MM月dd日";


    // ==格式到年月日 时分 ==

    /**
     * 日期格式，年月日时分，例如：200506301210，200808081210
     */
    public static final String DATE_FORMAT_YYYYMMDDHHmm = "yyyyMMddHHmm";

    /**
     * 日期格式，年月日时分，例如：20001230 12:00，20080808 20:08
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";

    /**
     * 日期格式，年月日时分，例如：2000-12-30 12:00，2008-08-08 20:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";


    // ==格式到年月日 时分秒==
    /**
     * 日期格式，年月日时分秒，例如：20001230120000，20080808200808
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";

    /**
     * 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开
     * 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";


    // ==格式到年月日 时分秒 毫秒==
    /**
     * 日期格式，年月日时分秒毫秒，例如：20001230120000123，20080808200808456
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";


    // ==特殊格式==
    /**
     * 日期格式，月日时分，例如：10-05 12:00
     */
    public static final String DATE_FORMAT_MMDDHHMI = "MM-dd HH:mm";

    /**
     * 具体格式
     */
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_GLWZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String FORMAT_MDHM = "MM月dd日 HH:mm";
    public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat datetimeFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //xx月xx日 HH:mm
    public static final SimpleDateFormat MDHM = new SimpleDateFormat("MM月dd日 HH:mm");
    private static final SimpleDateFormat datetimeFormat_spe = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat HMFormat = new SimpleDateFormat("HH:mm");

    //设置时间格式
    public static final SimpleDateFormat sdf1 = new SimpleDateFormat("y-M-d h:m:s a E");
    public static final SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd hh:mm:ss a E");
    public static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MMM-ddd hhh:mmm:sss a E");
    public static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyy-MMMM-dddd hhhh:mmmm:ssss a E");
    public static final SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss a E");
    public static final SimpleDateFormat sdf6 = new SimpleDateFormat("MM月dd日 HH:mm a E");
    public static final SimpleDateFormat sdf7 = new SimpleDateFormat("MM月dd日 HH:mm");
    public static final SimpleDateFormat sdf8 = new SimpleDateFormat("yyyyMMddHHmmss");



    /* ************工具方法***************   */

    /**
     * 格式化Date时间
     *
     * @param time       Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat) {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    /**
     * 格式化Timestamp时间
     *
     * @param timestamp  Timestamp类型时间
     * @param timeFromat
     * @return 格式化后的字符串
     */
    public static String parseTimestampToStr(Timestamp timestamp, String timeFromat) {
        SimpleDateFormat df = new SimpleDateFormat(timeFromat);
        return df.format(timestamp);
    }

    /**
     * 格式化Date时间
     *
     * @param time         Date类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 默认值为当前时间Date
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat, final Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.format(time);
        } catch (Exception e) {
            if (defaultValue != null)
                return parseDateToStr(defaultValue, timeFromat);
            else
                return parseDateToStr(new Date(), timeFromat);
        }
    }

    /**
     * 格式化Date时间
     *
     * @param time         Date类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 默认时间值String类型
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat, final String defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.format(time);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 格式化String时间
     *
     * @param time       String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String time, String timeFromat) {
        if (time == null || time.equals("")) {
            return null;
        }

        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            date = dateFormat.parse(time);
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 格式化String时间
     *
     * @param strTime      String类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 异常时返回的默认值
     * @return
     */
    public static Date parseStrToDate(String strTime, String timeFromat, Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.parse(strTime);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 当strTime为2008-9时返回为2008-9-1 00:00格式日期时间，无法转换返回null.
     *
     * @param strTime
     * @return
     */
    public static Date strToDate(String strTime) {
        if (strTime == null || strTime.trim().length() <= 0)
            return null;

        Date date = null;
        List<String> list = new ArrayList<String>(0);

        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
        list.add(DATE_FORMAT_YYYY_MM_DD);
        //list.add(DATE_FORMAT_YY_MM_DD);
        list.add(DATE_FORMAT_YYYYMMDD);
        list.add(DATE_FORMAT_YYYY_MM);
        list.add(DATE_FORMAT_YYYYMM);
        list.add(DATE_FORMAT_YYYY);


        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            String format = (String) iter.next();
            if (strTime.indexOf("-") > 0 && format.indexOf("-") < 0)
                continue;
            if (strTime.indexOf("-") < 0 && format.indexOf("-") > 0)
                continue;
            if (strTime.length() > format.length())
                continue;
            date = parseStrToDate(strTime, format);
            if (date != null)
                break;
        }

        return date;
    }

    /**
     * 解析两个日期之间的所有月份
     *
     * @param beginDateStr 开始日期，至少精确到yyyy-MM
     * @param endDateStr   结束日期，至少精确到yyyy-MM
     * @return yyyy-MM日期集合
     */
    public static List<String> getMonthListOfDate(String beginDateStr, String endDateStr) {
        // 指定要解析的时间格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        // 返回的月份列表
        String sRet = "";

        // 定义一些变量
        Date beginDate = null;
        Date endDate = null;

        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        List<String> list = new ArrayList<String>();

        try {
            // 将字符串parse成日期
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);

            // 设置日历
            beginGC = new GregorianCalendar();
            beginGC.setTime(beginDate);

            endGC = new GregorianCalendar();
            endGC.setTime(endDate);

            // 直到两个时间相同
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
                sRet = beginGC.get(Calendar.YEAR) + "-"
                        + (beginGC.get(Calendar.MONTH) + 1);
                list.add(sRet);
                // 以月为单位，增加时间
                beginGC.add(Calendar.MONTH, 1);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析两个日期段之间的所有日期
     *
     * @param beginDateStr 开始日期  ，至少精确到yyyy-MM-dd
     * @param endDateStr   结束日期  ，至少精确到yyyy-MM-dd
     * @return yyyy-MM-dd日期集合
     */
    public static List<String> getDayListOfDate(String beginDateStr, String endDateStr) {
        // 指定要解析的时间格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        // 定义一些变量
        Date beginDate = null;
        Date endDate = null;

        Calendar beginGC = null;
        Calendar endGC = null;
        List<String> list = new ArrayList<String>();

        try {
            // 将字符串parse成日期
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);

            // 设置日历
            beginGC = Calendar.getInstance();
            beginGC.setTime(beginDate);

            endGC = Calendar.getInstance();
            endGC.setTime(endDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // 直到两个时间相同
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {

                list.add(sdf.format(beginGC.getTime()));
                // 以日为单位，增加时间
                beginGC.add(Calendar.DAY_OF_MONTH, 1);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当下年份指定前后数量的年份集合
     *
     * @param before 当下年份前年数
     * @param behind 当下年份后年数
     * @return 集合
     */
    public static List<Integer> getYearListOfYears(int before, int behind) {
        if (before < 0 || behind < 0) {
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();
        Calendar c = null;
        c = Calendar.getInstance();
        c.setTime(new Date());
        int currYear = Calendar.getInstance().get(Calendar.YEAR);

        int startYear = currYear - before;
        int endYear = currYear + behind;
        for (int i = startYear; i < endYear; i++) {
            list.add(Integer.valueOf(i));
        }
        return list;
    }

    /**
     * 获取当前日期是一年中第几周
     *
     * @param date
     * @return
     */
    public static Integer getWeekthOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取某一年各星期的始终时间
     * 实例：getWeekList(2016)，第52周(从2016-12-26至2017-01-01)
     *
     * @param year 年份
     * @return
     */
    public static HashMap<Integer, String> getWeekTimeOfYear(int year) {
        HashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        int count = getWeekthOfYear(c.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dayOfWeekStart = "";
        String dayOfWeekEnd = "";
        for (int i = 1; i <= count; i++) {
            dayOfWeekStart = sdf.format(getFirstDayOfWeek(year, i));
            dayOfWeekEnd = sdf.format(getLastDayOfWeek(year, i));
            map.put(Integer.valueOf(i), "第" + i + "周(从" + dayOfWeekStart + "至" + dayOfWeekEnd + ")");
        }
        return map;

    }

    /**
     * 获取某一年的总周数
     *
     * @param year
     * @return
     */
    public static Integer getWeekCountOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        int count = getWeekthOfYear(c.getTime());
        return count;
    }

    /**
     * 获取指定日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 获取指定日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 获取某年某周的第一天
     *
     * @param year 目标年份
     * @param week 目标周数
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 获取某年某周的最后一天
     *
     * @param year 目标年份
     * @param week 目标周数
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 获取某年某月的第一天
     *
     * @param year  目标年份
     * @param month 目标月份
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);

        int day = c.getActualMinimum(c.DAY_OF_MONTH);

        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year  目标年份
     * @param month 目标月份
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        int day = c.getActualMaximum(c.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 获取某个日期为星期几
     *
     * @param date
     * @return String "星期*"
     */
    public static String getDayWeekOfDate1(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 获得指定日期的星期几数
     *
     * @param date
     * @return int
     */
    public static Integer getDayWeekOfDate2(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int weekDay = aCalendar.get(Calendar.DAY_OF_WEEK);
        return weekDay;
    }

    /**
     * 验证字符串是否为日期
     * 验证格式:YYYYMMDD、YYYY_MM_DD、YYYYMMDDHHMISS、YYYYMMDD_HH_MI、YYYY_MM_DD_HH_MI、YYYYMMDDHHMISSSSS、YYYY_MM_DD_HH_MI_SS
     *
     * @param strTime
     * @return null时返回false;true为日期，false不为日期
     */
    public static boolean validateIsDate(String strTime) {
        if (strTime == null || strTime.trim().length() <= 0)
            return false;

        Date date = null;
        List<String> list = new ArrayList<String>(0);

        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
        list.add(DATE_FORMAT_YYYY_MM_DD);
        //list.add(DATE_FORMAT_YY_MM_DD);
        list.add(DATE_FORMAT_YYYYMMDD);
        //list.add(DATE_FORMAT_YYYY_MM);
        //list.add(DATE_FORMAT_YYYYMM);
        //list.add(DATE_FORMAT_YYYY);

        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            String format = (String) iter.next();
            if (strTime.indexOf("-") > 0 && format.indexOf("-") < 0)
                continue;
            if (strTime.indexOf("-") < 0 && format.indexOf("-") > 0)
                continue;
            if (strTime.length() > format.length())
                continue;
            date = parseStrToDate(strTime.trim(), format);
            if (date != null)
                break;
        }
        if (date != null) {
            System.out.println("生成的日期:" + DateTimeUtils.parseDateToStr(date, DateTimeUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS, "--null--"));
            return true;
        }
        return false;
    }

    /**
     * 将指定日期的时分秒格式为零
     *
     * @param date
     * @return
     */
    public static Date formatHhMmSsOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定时间加减参数后的日期(不计算则输入0)
     *
     * @param date        指定日期
     * @param year        年数，可正可负
     * @param month       月数，可正可负
     * @param day         天数，可正可负
     * @param hour        小时数，可正可负
     * @param minute      分钟数，可正可负
     * @param second      秒数，可正可负
     * @param millisecond 毫秒数，可正可负
     * @return 计算后的日期
     */
    public static Date addDate(Date date, int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);//加减年数
        c.add(Calendar.MONTH, month);//加减月数
        c.add(Calendar.DATE, day);//加减天数
        c.add(Calendar.HOUR, hour);//加减小时数
        c.add(Calendar.MINUTE, minute);//加减分钟数
        c.add(Calendar.SECOND, second);//加减秒
        c.add(Calendar.MILLISECOND, millisecond);//加减毫秒数

        return c.getTime();
    }

    /**
     * 获得两个日期的时间戳之差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDistanceTimestamp(Date startDate, Date endDate) {
        long daysBetween = (endDate.getTime() - startDate.getTime() + 1000000) / (3600 * 24 * 1000);
        return daysBetween;
    }

    /**
     * 判断二个时间是否为同年同月
     *
     * @param date1
     * @param date2
     * @return
     */
    public Boolean compareIsSameMonth(Date date1, Date date2) {
        boolean flag = false;
        int year1 = getYear(date1);
        int year2 = getYear(date2);
        if (year1 == year2) {
            int month1 = getMonth(date1);
            int month2 = getMonth(date2);
            if (month1 == month2) flag = true;
        }
        return flag;
    }

    /**
     * 获得两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param one 时间参数 1 格式：1990-01-01 12:00:00
     * @param two 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTime1(Date one, Date two) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {

            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }


    /**
     * 两个时间之间相差距离多少天
     *
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差天数
     */
    public static Long getDistanceDays(String str1, String str2) throws Exception {
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date getDayBeginTime(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date getDayEndTime(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }


    /**
     * （int）时间戳转Date
     *
     * @param timestamp Integer类型时间戳
     * @return
     */
    public static Date stampForDate(Integer timestamp) {
        return new Date((long) timestamp * 1000);
    }

    /**
     * （long）时间戳转Date
     *
     * @param timestamp long型时间戳
     * @return
     */
    public static Date longStampForDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * date转String
     *
     * @param date
     * @return
     * @author zx
     * @date 2018年2月26日 下午5:11:51
     */
    public static String dateForString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//时间的格式
        return sdf.format(date);
    }

    /**
     * String转Date
     *
     * @param time
     * @return
     */
    public static Date stringForDate(String time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//时间的格式
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date转时间戳
     *
     * @param data
     * @return
     */
    public static Integer dateForStamp(Date data) {
        return (int) (data.getTime() / 1000);
    }


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param seconds
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //------------------------------------常用工具---------------------------

        /**
         * 获取星期
         * @return
         */
        public static String getWeek() {
            Calendar cal = Calendar.getInstance();
            int i = cal.get(Calendar.DAY_OF_WEEK);
            switch (i) {
                case 1:
                    return "星期日";
                case 2:
                    return "星期一";
                case 3:
                    return "星期二";
                case 4:
                    return "星期三";
                case 5:
                    return "星期四";
                case 6:
                    return "星期五";
                case 7:
                    return "星期六";
                default:
                    return "";
            }
        }

        public static Date str2Date(String str) {
            return str2Date(str, null);
        }

        /**
         * 字符串转时间
         * @param str
         * @param format
         * @return
         */
        public static Date str2Date(String str, String format) {
            if (str == null || str.length() == 0) {
                return null;
            }
            if (format == null || format.length() == 0) {
                format = FORMAT;
            }
            Date date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(str);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return date;

        }

        /**
         * 字符串转时间
         * @param str
         * @param format
         * @return
         */
        public static Date str2DateFormat(String str, SimpleDateFormat format) {
            if (str == null || str.length() == 0) {
                return null;
            }
            Date date = null;
            try {
                date = format.parse(str);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return date;

        }


        public static String getCurDateStr() {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-"
                    + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        }


        public static Calendar str2Calendar(String str) {
            return str2Calendar(str, null);

        }

        public static Calendar str2Calendar(String str, String format) {
            Date date = str2Date(str, format);
            if (date == null) {
                return null;
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            return c;

        }

        public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
            return date2Str(c, null);
        }

        public static String date2Str(Calendar c, String format) {
            if (c == null) {
                return null;
            }
            return date2Str(c.getTime(), format);
        }

        public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
            return date2Str(d, null);
        }

        public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
            if (d == null) {
                return null;
            }
            if (format == null || format.length() == 0) {
                format = FORMAT;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String s = sdf.format(d);
            return s;
        }


        /**
         * 获得当前日期的字符串格式
         *
         * @param format
         * @return
         */
        public static String getCurDateStr(String format) {
            Calendar c = Calendar.getInstance();
            return date2Str(c, format);
        }

        /**
         * 格式到秒
         *
         * @param time
         * @return
         */
        public static String getMillon(long time) {
            return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

        }

        /**
         * 格式到天
         *
         * @param time
         * @return
         */
        public static String getDay(long time) {
            return new SimpleDateFormat("yyyy-MM-dd").format(time);

        }

        /**
         * 格式到毫秒
         *
         * @param time
         * @return
         */
        public static String getSMillon(long time) {
            return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

        }

        /**
         * 字符串转换到时间格式
         *
         * @param dateStr
         *            需要转换的字符串
         * @param formatStr
         *            需要格式的目标字符串 举例 yyyy-MM-dd
         * @return Date 返回转换后的时间
         * @throws ParseException
         *             转换异常
         */
        public static Date StringToDate(String dateStr, String formatStr) {
            DateFormat sdf = new SimpleDateFormat(formatStr);
            Date date = null;
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }

        /**
         * 转化时间输入时间与当前时间的间隔
         *
         * @param timestamp
         * @return
         */
        public static String converTime(long timestamp) {
            long currentSeconds = System.currentTimeMillis() / 1000;
            long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
            String timeStr = null;
            if (timeGap > 24 * 60 * 60) {// 1天以上
                timeStr = timeGap / (24 * 60 * 60) + "天前";
            } else if (timeGap > 60 * 60) {// 1小时-24小时
                timeStr = timeGap / (60 * 60) + "小时前";
            } else if (timeGap > 60) {// 1分钟-59分钟
                timeStr = timeGap / 60 + "分钟前";
            } else {// 1秒钟-59秒钟
                timeStr = "刚刚";
            }
            return timeStr;
        }

        /**
         * 把字符串转化为时间格式
         *
         * @param timestamp
         * @return
         */
        public static String getStandardTime(long timestamp) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
            Date date = new Date(timestamp * 1000);
            sdf.format(date);
            return sdf.format(date);
        }

        /**
         * 获得当前日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
         *
         * @return
         */
        public static String currentDatetime() {
            return datetimeFormat.format(now());
        }

        /**
         * 格式化日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
         *
         * @return
         */
        public static String formatDatetime(Date date) {
            return datetimeFormat.format(date);
        }

        /**
         * 格式化日期时间 日期时间格式yyyy-MM-dd HH:mm
         *
         * @return
         */
        public static String formatDateNoSecond(Date date) {
            return datetimeFormat1.format(date);
        }

        /**
         * 格式化日期时间 日期时间格式HH:mm
         *
         * @return
         */
        public static String formatHourMinuter(Date date) {
            return HMFormat.format(date);
        }

        /**
         * 获得当前时间 时间格式HH:mm:ss
         *
         * @return
         */
        public static String currentTime() {
            return timeFormat.format(now());
        }

        /**
         * 获得当前时间 时间格式HH:mm:ss
         *long类型
         * @return
         */
        public static long currentLongTime() {
            String fromDate = timeFormat.format(now());
            long time = 0;
            try {
                time = timeFormat.parse(fromDate).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return time;
        }

        /**
         * 获得当前时间 时间格式HH:mm:ss
         * string转换成long类型
         * @return
         */
        public static long String2LongTime(String timeStr,SimpleDateFormat format) {
            long time = 0;
            try {
//            time = timeFormat.parse(timeStr).getTime();
                time = format.parse(timeStr).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return time;
        }

        /**
         * 获得当前时间 时间格式HH:mm:ss
         *
         * @return
         */
        public static String getCurrentDateStr() {
            return datetimeFormat_spe.format(now());
        }
        public static String getCurrentDateStr1() {
            return datetimeFormat.format(now());
        }

//        /**
//         * 格式化时间 时间格式HH:mm:ss
//         *
//         * @return
//         */
//        public static String formatTime(Date date) {
//            return timeFormat.format(date);
//        }

        /**
         * 获得当前时间的<code>java.util.Date</code>对象
         *
         * @return
         */
        public static Date now() {
            return new Date();
        }

        public static Calendar calendar() {
            Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            return cal;
        }

        /**
         * 获得当前时间的毫秒数
         *
         * 详见{@link System#currentTimeMillis()}
         *
         * @return
         */
        public static long millis() {
            return System.currentTimeMillis();
        }

        /**
         *
         * 获得当前Chinese月份
         *
         * @return
         */
        public static int month() {
            return calendar().get(Calendar.MONTH) + 1;
        }

        /**
         * 获得月份中的第几天
         *
         * @return
         */
        public static int dayOfMonth() {
            return calendar().get(Calendar.DAY_OF_MONTH);
        }

        /**
         * 今天是星期的第几天
         *
         * @return
         */
        public static int dayOfWeek() {
            return calendar().get(Calendar.DAY_OF_WEEK);
        }

        /**
         * 今天是年中的第几天
         *
         * @return
         */
        public static int dayOfYear() {
            return calendar().get(Calendar.DAY_OF_YEAR);
        }

        /**
         * 判断原日期是否在目标日期之前
         *
         * @param src
         * @param dst
         * @return
         */
        public static boolean isBefore(Date src, Date dst) {
            return src.before(dst);
        }

        /**
         * 判断原日期是否在目标日期之后
         *
         * @param src
         * @param dst
         * @return
         */
        public static boolean isAfter(Date src, Date dst) {
            return src.after(dst);
        }

        /**
         * 判断两日期是否相同
         *
         * @param date1
         * @param date2
         * @return
         */
        public static boolean isEqual(Date date1, Date date2) {
            return date1.compareTo(date2) == 0;
        }

        /**
         * 判断某个日期是否在某个日期范围
         *
         * @param beginDate
         *            日期范围开始
         * @param endDate
         *            日期范围结束
         * @param src
         *            需要判断的日期
         * @return
         */
        public static boolean between(Date beginDate, Date endDate, Date src) {
            return beginDate.before(src) && endDate.after(src);
        }

        /**
         * 获得当前月的最后一天
         *
         * HH:mm:ss为0，毫秒为999
         *
         * @return
         */
        public static Date lastDayOfMonth() {
            Calendar cal = calendar();
            cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
            cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
            cal.set(Calendar.MINUTE, 0);// m置零
            cal.set(Calendar.SECOND, 0);// s置零
            cal.set(Calendar.MILLISECOND, 0);// S置零
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
            cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
            return cal.getTime();
        }

        /**
         * 获得当前月的第一天
         *
         * HH:mm:ss SS为零
         *
         * @return
         */
        public static Date firstDayOfMonth() {
            Calendar cal = calendar();
            cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
            cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
            cal.set(Calendar.MINUTE, 0);// m置零
            cal.set(Calendar.SECOND, 0);// s置零
            cal.set(Calendar.MILLISECOND, 0);// S置零
            return cal.getTime();
        }

        private static Date weekDay(int week) {
            Calendar cal = calendar();
            cal.set(Calendar.DAY_OF_WEEK, week);
            return cal.getTime();
        }

        /**
         * 获得周五日期
         *
         * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
         *
         * @return
         */
        public static Date friday() {
            return weekDay(Calendar.FRIDAY);
        }

        /**
         * 获得周六日期
         *
         * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
         *
         * @return
         */
        public static Date saturday() {
            return weekDay(Calendar.SATURDAY);
        }

        /**
         * 获得周日日期 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
         *
         * @return
         */
        public static Date sunday() {
            return weekDay(Calendar.SUNDAY);
        }

        /**
         * 将字符串日期时间转换成java.util.Date类型 日期时间格式yyyy-MM-dd HH:mm:ss
         *
         * @param datetime
         * @return
         */
        public static Date parseDatetime(String datetime) throws ParseException {
            return datetimeFormat.parse(datetime);
        }

        /**
         * 将字符串日期转换成java.util.Date类型 日期时间格式yyyy-MM-dd
         *
         * @param date
         * @return
         * @throws ParseException
         */
        public static Date parseDate(String date) throws ParseException {
            return dateFormat.parse(date);
        }

        /**
         * 将字符串日期转换成java.util.Date类型 时间格式 HH:mm:ss
         *
         * @param time
         * @return
         * @throws ParseException
         */
        public static Date parseTime(String time) throws ParseException {
            return timeFormat.parse(time);
        }

        /**
         * 根据自定义pattern将字符串日期转换成java.util.Date类型
         *
         * @param datetime
         * @param pattern
         * @return
         * @throws ParseException
         */
        public static Date parseDatetime(String datetime, String pattern) throws ParseException {
            SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
            format.applyPattern(pattern);
            return format.parse(datetime);
        }

        /**
         * 把秒格式化为分种小时
         *
         * @param second
         * @return
         */
        public static String parseSecond(int second) {
            if (second >= 60) {
                return second / 60 + "分";
            } else if (second >= 60 * 60) {
                return second / 60 * 60 + "时";
            } else if (second >= 60 * 60 * 24) {
                return second / 60 * 60 + "天";
            } else {
                return second + "秒";
            }
        }


        /**
         * 比较时间大小
         * @param begin
         * @param end
         * @return
         */
        public static int compareDate(String begin, String end) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            try {
                Date beginDate = df.parse(begin);
                Date endDate = df.parse(end);
                if (beginDate.getTime() < endDate.getTime()) {
                    return 1;
                } else if (beginDate.getTime() > endDate.getTime()) {
                    return -1;
                } else {
                    return 0;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return 0;
        }

        /**
         * 获得年份
         * @param date
         * @return
         */
        public int getYear(Date date){
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.YEAR);
        }

        /**
         * 获得月份
         * @param date
         * @return
         */
        public int getMonth(Date date){
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.MONTH) + 1;
        }

        /**
         * 获得星期几
         * @param date
         * @return
         */
        public int getWeek(Date date){
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.WEEK_OF_YEAR);
        }

        /**
         * 获得日期
         * @param date
         * @return
         */
        public int getDay(Date date){
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.DATE);
        }

        /**
         * 获得天数差
         * @param begin
         * @param end
         * @return
         */
        public long getDayDiff(Date begin, Date end){
            long day = 1;
            if(end.getTime() < begin.getTime()){
                day = -1;
            }else if(end.getTime() == begin.getTime()){
                day = 1;
            }else {
                day += (end.getTime() - begin.getTime())/(24 * 60 * 60 * 1000) ;
            }
            return day;
        }



        //Todo:-----------------------------

        private static SimpleDateFormat sf;
        private static SimpleDateFormat sdf;

        /**
         * 获取系统时间 格式为："yyyy/MM/dd "
         **/
        public static String getCurrentDate() {
            Date d = new Date();
            sf = new SimpleDateFormat("yyyy年MM月dd日");
            return sf.format(d);
        }

        /**
         * 获取系统时间 格式为："yyyy "
         **/
        public static String getCurrentYear() {
            Date d = new Date();
            sf = new SimpleDateFormat("yyyy");
            return sf.format(d);
        }

        /**
         * 获取系统时间 格式为："MM"
         **/
        public static String getCurrentMonth() {
            Date d = new Date();
            sf = new SimpleDateFormat("MM");
            return sf.format(d);
        }

        /**
         * 获取系统时间 格式为："dd"
         **/
        public static String getCurrentDay() {
            Date d = new Date();
            sf = new SimpleDateFormat("dd");
            return sf.format(d);
        }

        /**
         * 获取当前时间戳
         *
         * @return
         */
        public static long getCurrentTime() {
            long d = new Date().getTime() / 1000;
            return d;
        }

        /**
         * 时间戳转换成字符窜
         */
        public static String getLongDateToString(long date_data) {
            //时间戳转化为Sting或Date
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long time= new Long(date_data);
            String d = format.format(time);

            return d;
        }

    /**
     * @Description: long类型转换成日期
     *
     * @param stamp 毫秒数
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String stampToTime(long stamp) {
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp*1000);
        time = simpleDateFormat.format(date);
        return time;
    }

        /**
         * 时间戳转换成字符窜(年月日)
         */
        public static String getLongDateToString1(long date_data,SimpleDateFormat format) {
            //时间戳转化为Sting或Date
            Long time= new Long(date_data);
            String d = format.format(time);
            return d;
        }


        /**
         * 时间戳转换成期日期格式
         */
        public static Date getLongDateToDate(long date_data) throws ParseException {
            //时间戳转化为Sting或Date
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long time= new Long(date_data);
            String d = format.format(time);
            Date date_1 = format.parse(d);

            return  date_1;
        }


        /**
         * 时间戳中获取年
         */
        public static String getYearFromTime(long time) {
            Date d = new Date(time * 1000);
            sf = new SimpleDateFormat("yyyy");
            return sf.format(d);
        }

        /**
         * 时间戳中获取月
         */
        public static String getMonthFromTime(long time) {
            Date d = new Date(time * 1000);
            sf = new SimpleDateFormat("MM");
            return sf.format(d);
        }

        /**
         * 时间戳中获取日
         */
        public static String getDayFromTime(long time) {
            Date d = new Date(time * 1000);
            sf = new SimpleDateFormat("dd");
            return sf.format(d);
        }

        /**
         * 将字符串转为时间戳
         */
        public static long getStringToDate(String time) {
            sdf = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = new Date();
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return date.getTime();
        }

        /**
         * 时间格式HH:mm:ss'T' JJ
         *
         * @return
         */
        public static String formatDateWithT(String date) {
            return datetimeFormat_spe.format(date);
        }

        /**
         * 两个时间相差距离多少天多少小时多少分多少秒
         * @param str1 时间参数 1 格式：1990-01-01 12:00:00
         * @param str2 时间参数 2 格式：2009-01-01 12:00:00
         * @return String 返回值为：xx天xx小时xx分xx秒
         */
        public static String getDistanceTime(String str1, String str2) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date one;
            Date two;
            long day = 0;
            long hour = 0;
            long min = 0;
            long sec = 0;
            try {
                one = df.parse(str1);
                two = df.parse(str2);
                long time1 = one.getTime();
                long time2 = two.getTime();
                long diff ;
                if(time1<time2) {
                    diff = time2 - time1;
                } else {
                    diff = time1 - time2;
                }
                day = diff / (24 * 60 * 60 * 1000);
                hour = (diff / (60 * 60 * 1000) - day * 24);
                min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
                sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        }

        /**
         * 两个时间相差距离多少秒
         * @param str1 时间参数 1 格式：12:00:00
         * @param str2 时间参数 2 格式：12:00:00
         * @return String 返回值为：xx天xx小时xx分xx秒
         */
        public static long[] getDistanceTimeSeconds(String str1, String str2) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date one;
            Date two;
            long day = 0;
            long hour = 0;
            long min = 0;
            long sec = 0;
            try {
                one = df.parse(str1);
                two = df.parse(str2);
                long time1 = one.getTime();
                long time2 = two.getTime();
                long diff ;
                if(time1<time2) {
                    diff = time2 - time1;
                } else {
                    diff = time1 - time2;
                }
                day = diff / (24 * 60 * 60 * 1000);
                hour = (diff / (60 * 60 * 1000) - day * 24);
                min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
                sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long[] times = {day, hour, min, sec};
            return times;
//        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        }

        /**
         * 时间转换
         *      String标准时间转换成Date格式时间
         *      提交时格林威治时间
         */
        private static StringBuffer stringBuffer;
        public static Date StringToDateGLWZ(String dateTimeStr){
            if (dateTimeStr.contains("/")) {
                //endTimeStr.replaceAll("/","-");
                String[] split = dateTimeStr.split("\\/");
                stringBuffer = new StringBuffer();
                stringBuffer.append(split[0]+"-");
                if (split[1].length() < 2){
                    stringBuffer.append("0"+split[1]+"-");
                }else {
                    stringBuffer.append(split[1]+"-");
                }
                if (split[2].length() < 11){
                    stringBuffer.append("0"+split[2]);
                }else {
                    stringBuffer.append(split[2]);
                }
                String s = stringBuffer.toString();
                return parseStrDate(s);
            }else {
                return parseStrDate(dateTimeStr);
            }
        }

        private static Date parseStrDate(String s) {
            Date date = null;
            if (!TextUtils.isEmpty(s)) {
                SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
                try {
                    date = sdf.parse(s);
                } catch (Exception e) {
                    Log.e("tag", "日期解析出错！", e);
                }
                return date;
            }else {
                throw new NullPointerException("dateTimeStr不能为空");
            }
        }

        /**
         * 解析标准时间格式的String类型时间
         * @param s
         * @param format
         * @return
         */
        public static String parseMultiDate(String s, SimpleDateFormat format) {
            Date date2 = str2Date(s);
            String date = null;
            if (!TextUtils.isEmpty(s)) {
                try {
                    date = format.format(date2.getTime());
                } catch (Exception e) {
                    Log.e("tag", "日期解析出错！", e);
                }
                return date;
            }else {
                throw new NullPointerException("dateTimeStr不能为空");
            }
        }

    /**
     * 比较时间
     * @param dateFrom
     * @param dateTo
     * @param format
     * @param isForward
     * @return
     */
        public static boolean compareBetweenLongDate(String dateFrom, String dateTo, SimpleDateFormat format, int isForward){
            long l = String2LongTime(dateFrom, format);
            long l1 = DateTimeUtils.String2LongTime(dateTo, format);
            if (isForward == 0){
                if (l1 >= l){
                    return true;
                }else {
                    return false;
                }
            }else if (isForward == 1){
                if (l1 > l){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
//        String trim = etArriveStartTime.getText().toString().trim();
//        if (!TextUtils.isEmpty(trim)){
//            long l = TimeTools.String2LongTime(trim, TimeTools.HMFormat);
//            etArriveEndllTime.setText(TimeTools.formatHourMinuter(date));
//            String trim1 = etArriveEndllTime.getText().toString().trim();
//            long l1 = TimeTools.String2LongTime(trim1, TimeTools.HMFormat);
//            if (l1 > l) {
//                etArriveEndllTime.setText(TimeTools.formatHourMinuter(date));
//            }else {
//                etArriveEndllTime.setText(null);
//                SnackbarUtil.LongSnackbar(rlFilterRoot,"结束时间不能早于/等于开始时间", R.color.white,R.color.red);
//            }
//        }
        }


    /**
     * 视频时长转换
     * @param timeTotal
     * @return
     *
     *  在项目中有关于视频的是时间展示，要求如下：
     *              时间＜1小时显示分秒，显示样式 00:20
     *              时间≥1小时显示时分秒，显示样式 01:11:12
     */
    public static String formatVideoTime(double timeTotal) {
        try {
            String time = "";
            int milliseconds = Integer.parseInt(new DecimalFormat("0").format(timeTotal));
            if (milliseconds < 60) {
                time = String.format("00:%02d", milliseconds % 60);
            } else if (milliseconds < 3600) {
                time = String.format("%02d:%02d", milliseconds / 60, milliseconds % 60);
            } else {
                time = String.format("%02d:%02d:%02d", milliseconds / 3600, milliseconds % 3600 / 60, milliseconds % 60);
            }
            return time;
        }catch (NumberFormatException e){
            return "";
        }catch (Exception e){
            return "";
        }
    }


}
