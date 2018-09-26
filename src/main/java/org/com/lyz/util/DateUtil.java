package org.com.lyz.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 名称：long2StrDate 功能：该函数将8位或12位或14位的数值型日期时间转换为10位或16位或19带格式的日期时间 型如：
     * 20040101 转换为 2004/01/01 或 200401010101 转换为 2004/01/01 01:01 或
     * 20040101010101 转换为 2004/01/01 01:01:01 输入参数：Long date 十二位或十四位的数字日期时间
     * 返回参数：String fmDate 16位或19位带格式的日期
     */
    public static String long2StrDate(String date) {
        if (date == null || date.equals(""))
            return "";
        else
            return long2StrDate(Long.valueOf(date));
    }

    public static String int2StrDate(Integer date) {
        if (date == null)
            return "";
        return long2StrDate(Long.valueOf(date));
    }

    /**
     * description 时间由数字转化为字符串,将年月日用splitYMD分隔
     * 
     * @param date
     * @param splitYMD
     *            年月日分隔符
     * @return String yyyy/mm/dd hh:mm:ss
     * @date 2012-8-24
     */
    public static String long2StrDate(Number date, String splitYMD) {
        String fmDate = new String();
        if (date == null) {
            fmDate = "";
        } else {
            fmDate = date.toString();
            String year = "";
            String month = "";
            String day = "";
            String hour = "";
            String minute = "";
            // 日期格式不合法则转化为空串
            if (fmDate.length() < 8) {
                fmDate = "";
            }
            if (fmDate.length() >= 8) {
                year = fmDate.substring(0, 4);
                month = fmDate.substring(4, 6);
                day = fmDate.substring(6, 8);
                fmDate = year + splitYMD + month + splitYMD + day;
            }
            if ((date.toString()).length() >= 12) {
                hour = (date.toString()).substring(8, 10);
                minute = (date.toString()).substring(10, 12);
                fmDate = fmDate + " " + hour + ":" + minute;
            }
            if ((date.toString()).length() == 14) {
                fmDate = fmDate + ":" + (date.toString()).substring(12, 14);
            }
        }
        return fmDate;
    }

    /**
     * description 时间由数字转化为字符串,将年月日用/分隔
     * 
     * @param date
     *            年月日分隔符
     * @return String yyyy/mm/dd hh:mm:ss
     * @date 2012-8-24
     */
    public static String long2StrDate(Number date) {
        return long2StrDate(date, "/");
    }

    /**
     * description 日期由数字转化为字符串,将年月日用splitYMD分隔
     * 
     * @param date
     * @param splitYMD
     *            年月日分隔符
     * @return String yyyy/mm/dd
     * @date 2012-8-24
     */
    public static String int2StrOnlyDate(Number date, String splitYMD) {
        String fmDate = new String();
        if (date == null) {
            fmDate = "";
        } else {
            String year = "";
            String month = "";
            String day = "";
            fmDate = date.toString();
            if (fmDate.length() < 8) {
                fmDate = "";
            }
            if (fmDate.length() >= 8) {
                year = fmDate.substring(0, 4);
                month = fmDate.substring(4, 6);
                day = fmDate.substring(6, 8);
                fmDate = year + splitYMD + month + splitYMD + day;
            }
        }
        return fmDate;
    }

    /**
     * description 日期由数字转化为字符串,将年月日用/分隔
     * 
     * @param date
     * @return String yyyy/mm/dd
     * @date 2012-8-24
     */
    public static String int2StrOnlyDate(Number date) {
        return int2StrOnlyDate(date, "/");
    }

    public static String int2StrCnDate(Integer date) {
        if (date == null)
            return "";
        return long2StrCnDate(Long.valueOf(date));
    }

    public static String long2StrCnDate(Long date) {
        String fmDate = new String();
        if (date == null) {
            fmDate = "";
        } else {
            fmDate = date.toString();
            String year = "";
            String month = "";
            String day = "";
            String hour = "";
            String minute = "";
            // 日期格式不合法则转化为空串
            if (fmDate.length() < 8) {
                fmDate = "";
            }
            if (fmDate.length() >= 8) {
                year = fmDate.substring(0, 4);
                month = fmDate.substring(4, 6);
                day = fmDate.substring(6, 8);
                fmDate = year + "年" + month + "月" + day + "日";
            }
            if ((date.toString()).length() >= 10) {
                hour = (date.toString()).substring(8, 10);
                fmDate = fmDate + hour + "时";
            }

            if ((date.toString()).length() >= 12) {
                minute = (date.toString()).substring(10, 12);
                fmDate = fmDate + minute + "分";
            }

            if ((date.toString()).length() == 14) {
                fmDate = fmDate + (date.toString()).substring(12, 14) + "秒";
            }
        }
        return fmDate;
    }

    public static String long2StrCnDate(Number date) {
        String fmDate = new String();
        if (date == null) {
            fmDate = "";
        } else {
            fmDate = String.valueOf(date.longValue());
            String year = "";
            String month = "";
            String day = "";
            String hour = "";
            String minute = "";
            // 日期格式不合法则转化为空串
            if (fmDate.length() < 8) {
                fmDate = "";
            }
            if (fmDate.length() >= 8) {
                year = fmDate.substring(0, 4);
                month = fmDate.substring(4, 6);
                day = fmDate.substring(6, 8);
                fmDate = year + "年" + month + "月" + day + "日";
            }
            if ((date.toString()).length() >= 12) {
                hour = (date.toString()).substring(8, 10);
                minute = (date.toString()).substring(10, 12);
                fmDate = fmDate + hour + "时" + minute + "分";
            }
            if ((date.toString()).length() == 14) {
                fmDate = fmDate + (date.toString()).substring(12, 14) + "秒";
            }
        }
        return fmDate;
    }

    public static String long2StrCnDate(Number date, int length) {
        if (date != null) {
            Long l = Long.valueOf(String.valueOf(date.longValue()).substring(0, length));
            return long2StrCnDate(l);
        }
        return null;
    }

    /**
     * 名称：str2LongDate 功能：该函数将10位或16位或19位带格式的日期时间转换为8位或12位或14位的数值型日期时间
     * 型如：2004/01/01 转换为 20040101 或 2004/01/01 01:01 转换为 200401010101 或
     * 2004/01/01 01:01:01 转换为 20040101010101 如果 16位或19位带格式的日期时间 非法 ，则返回 0
     * 输入参数：String strFmDate 16位或19位带格式的日期时间 返回参数：Long longDate 12位或14位的数值型日期时间
     */
    public static Integer str2IntDate(String strFmDate) {
        Long date = str2LongDate(strFmDate);
        if (date == null)
            return null;
        return Integer.valueOf(date.intValue());
    }

    public static Long str2LongDate(String strFmDate) {
        if (strFmDate == null || strFmDate.length() == 0) {
            return null;
        }
        Long longDate = new Long(0);
        if (strFmDate.matches("^([12]\\d{3}/[01]\\d/[0123]\\d)$")) {
            longDate = Long.valueOf(strFmDate.replaceAll("/", ""));
        }
        if (strFmDate.matches("^([12]\\d{3}/[01]\\d/[0123]\\d\\s[012]\\d:[0-5]\\d)$")) {
            strFmDate = strFmDate.replaceAll("/", "").replaceAll(":", "").replaceAll("\\s", "");
            longDate = Long.valueOf(strFmDate);
        }
        if (strFmDate.matches("^([12]\\d{3}/[01]\\d/[0123]\\d\\s[012]\\d:[0-5]\\d:[0-5]\\d)$")) {
            strFmDate = strFmDate.replaceAll("/", "").replaceAll(":", "").replaceAll("\\s", "");
            longDate = Long.valueOf(strFmDate);
        }
        return longDate;
    }

    /**
     * 编写人： 名称：getCurrentDateView 功能：得到系统当前时间显示，格式：yyyy/mm/dd主要是为页面显示用 输入参数：
     * 返回参数：String:系统当前时间
     */
    public static String getCurrentDateView() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
        String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
        // 整理格式
        if (strMonth.length() < 2) {
            strMonth = "0" + strMonth;
        }
        if (strDate.length() < 2) {
            strDate = "0" + strDate;
        }
        // 得出当天日期的字符串
        String StrCurrentCalendar = strYear + "/" + strMonth + "/" + strDate;
        return StrCurrentCalendar;
    }

    /**
     * 编写人： 名称：getCurrentDateTimeView 功能：得到系统当前时间显示，格式：yyyy/mm/dd hh:mm主要是为页面显示用
     * 输入参数： 返回参数：String:系统当前时间
     */
    public static String getCurrentDateTimeView() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
        String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
        String srtHours = String.valueOf(cldCurrent.get(Calendar.HOUR_OF_DAY));
        String strMinute = String.valueOf(cldCurrent.get(Calendar.MINUTE));
        // 整理格式
        if (strMonth.length() < 2) {
            strMonth = "0" + strMonth;
        }
        if (strDate.length() < 2) {
            strDate = "0" + strDate;
        }
        if (srtHours.length() < 2) {
            srtHours = "0" + srtHours;
        }
        if (strMinute.length() < 2) {
            strMinute = "0" + strMinute;
        }
        // 得出当天日期时间的字符串
        String StrCurrentCalendar = strYear + "/" + strMonth + "/" + strDate + " " + srtHours + ":" + strMinute;
        return StrCurrentCalendar;
    }

    public static Integer getIntCurrDate() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
        String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
        // 整理格式
        if (strMonth.length() < 2) {
            strMonth = "0" + strMonth;
        }
        if (strDate.length() < 2) {
            strDate = "0" + strDate;
        }
        return Integer.valueOf(strYear + strMonth + strDate);
    }

    public static Integer getIntFirstDayOfMonth() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);

        // 整理格式
        if (strMonth.length() < 2) {
            strMonth = "0" + strMonth;
        }
        return Integer.valueOf(strYear + strMonth + "01");
    }

    public static Integer getIntCurrYear() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        return Integer.valueOf(strYear);
    }

    public static Long getLongCurrDateTime() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
        String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
        String srtHours = String.valueOf(cldCurrent.get(Calendar.HOUR_OF_DAY));
        String strMinute = String.valueOf(cldCurrent.get(Calendar.MINUTE));
        // 整理格式
        if (strMonth.length() < 2) {
            strMonth = "0" + strMonth;
        }
        if (strDate.length() < 2) {
            strDate = "0" + strDate;
        }
        if (srtHours.length() < 2) {
            srtHours = "0" + srtHours;
        }
        if (strMinute.length() < 2) {
            strMinute = "0" + strMinute;
        }
        return Long.valueOf(strYear + strMonth + strDate + srtHours + strMinute);
    }

    public static Long getLongCurrDateTime8() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
        String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));

        // 整理格式
        if (strMonth.length() < 2) {
            strMonth = "0" + strMonth;
        }
        if (strDate.length() < 2) {
            strDate = "0" + strDate;
        }

        return Long.valueOf(strYear + strMonth + strDate);
    }

    /**
     * <p/>
     * <p/>
     * 获得系统当前时间。
     * 
     * @return String 日期格式：yyyyMMddHHmm 示例：200802261310
     */
    public static String getCurrentDateTime() {
        return getDateTime(0, 0);
    }

    /**
     * <p/>
     * <p/>
     * 获得相对于系统当前时间的前（后）month个月、前（后）date个日的时间。
     * 说明：当month的值为负时，为当前时间的前month个月，当month的值为正时
     * ，为当前时间的后month个月；当date的值为负时，为当前时间的前date个日，当date的值为正时，为当前时间的后date个日。
     * 
     * @param month
     *            前（后）个月
     * @param date
     *            前（后）个日
     * @return String 日期格式：yyyyMMddHHmm 示例：200802261310
     */
    public static String getDateTime(int month, int date) {
        // 获得当前日期
        Calendar calendar = Calendar.getInstance();
        if (month != 0) {
            calendar.add(Calendar.MONTH, month);
        }
        if (date != 0) {
            calendar.add(Calendar.DATE, date);
        }
        // 获得年月日
        String strYear = String.valueOf(calendar.get(Calendar.YEAR));
        String strMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String strDate = String.valueOf(calendar.get(Calendar.DATE));
        String srtHours = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String strMinute = String.valueOf(calendar.get(Calendar.MINUTE));
        // 整理格式
        if (strMonth.length() < 2) {
            strMonth = "0" + strMonth;
        }
        if (strDate.length() < 2) {
            strDate = "0" + strDate;
        }
        if (srtHours.length() < 2) {
            srtHours = "0" + srtHours;
        }
        if (strMinute.length() < 2) {
            strMinute = "0" + strMinute;
        }
        return strYear + strMonth + strDate + srtHours + strMinute;
    }

    /**
     * <p/>
     * <p/>
     * 获取当前年的第一天
     * 
     * @return String
     */
    public static String getCurrentYearFirstDate() {

        // 获得当前日期
        Calendar calendar = Calendar.getInstance();

        String strYear = String.valueOf(calendar.get(Calendar.YEAR));

        return strYear + "01" + "01" + "00" + "00" + "00";

    }

    public static String getCurrentYearEndDate() {
        Calendar calendar = Calendar.getInstance();
        String strYear = String.valueOf(calendar.get(Calendar.YEAR));
        return strYear + "12" + "31" + "24" + "00" + "00";
    }

    public static Long getLongCurrDateTime14() {
        return Long.valueOf(formateCalendar(Calendar.getInstance(), "yyyyMMddHHmmss"));
    }

    public static Long getLongCurrDateTime12() {
        return Long.valueOf(formateCalendar(Calendar.getInstance(), "yyyyMMddHHmm"));
    }

    /**
     * 当前日期增加多少天
     * 
     * @param addDays
     * @return
     */
    public static Long getLongAddOfDay12(int addDays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, addDays);
        return Long.valueOf(formateCalendar(cal, "yyyyMMddHHmm"));
    }

    /**
     * 增加天数
     * 
     * @param addDays
     * @return
     */
    public static Long getLongAddOfDay14(int addDays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, addDays);
        return Long.valueOf(formateCalendar(cal, "yyyyMMddHHmmss"));
    }
    
    /**
     * 当前时间增加天数
     * 
     * @param addDays
     * @return 8位时间
     */
    public static int getLongAddOfDay8(int addDays) {
    	
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, addDays);
        return Integer.valueOf(formateCalendar(cal, "yyyyMMdd"));
    }
    
    /**
     * 在指定时间增加天数
     * 
     * @param addDays
     * @return 8位long型日期
     */
    public static long getLongAddOfDay8(long startTime, int addDays) {    	
    	 Calendar cal = long2Calendar(startTime);
         cal.add(Calendar.DAY_OF_MONTH, addDays);
        return Long.valueOf(formateCalendar(cal, "yyyyMMdd"));
    }
    
    /**
     * 
     * @description 获取当前时间减去指定天数日期
     * @author zgj
     * @param minusDays 减去时间
     * @return int
     * @update record（update date、author、cause）
     */
    public static int getLongMinusDay8(int minusDays) {    	
    	Date beginDate = new Date();
    	Date date=new Date(beginDate.getTime() - minusDays * 24 * 60 * 60 * 1000);
    	SimpleDateFormat dateFormate = new SimpleDateFormat("yyyyMMdd");
    	return Integer.valueOf(dateFormate.format(date));
    }

    public static String transferDate(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormate.format(date);
    }

    public static Long transferDateToLong(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyyMMddHHmmss");
        return Long.valueOf(dateFormate.format(date));
    }

    /**
     * yyyy/MM/dd HH:mm:ss(yyyy年，MM月，dd日，HH时，mm分，ss秒)
     * 
     * @param calendar
     * @param format
     * @return
     */
    public static String formateCalendar(Calendar calendar, String format) {
        if (calendar == null)
            return null;
        SimpleDateFormat dateFormate = new SimpleDateFormat(format);
        return dateFormate.format(calendar.getTime());
    }

    /**
     * 将形如20070730形式的日期格式，转换成2007-07-30
     */
    public static String dateFormateStr(String strDate) {
        if (strDate == null)
            return "";
        String fmDate = "";
        String year = "";
        String month = "";
        String day = "";
        if (strDate.length() >= 8) {
            year = strDate.substring(0, 4);
            month = strDate.substring(4, 6);
            day = strDate.substring(6, 8);
            fmDate = year + "-" + month + "-" + day;
        }
        return fmDate;
    }

    public static Calendar long2Calendar(Long time) {
        Calendar calendar = null;
        if (time == null) {
            calendar = Calendar.getInstance();
        } else {
            String strTime = String.valueOf(time);
            int year = 0;
            int month = 0;
            int day = 0;
            int hour = 0;
            int minute = 0;
            int sec = 0;
            if (strTime.length() < 8) {
                throw new MisException("时间格式不合法，不能少与八位数字！");
            }
            if (strTime.length() >= 8) {
                year = Integer.valueOf(strTime.substring(0, 4));
                month = Integer.valueOf(strTime.substring(4, 6)) - 1;
                day = Integer.valueOf(strTime.substring(6, 8));
            }
            if (strTime.length() >= 10) {
                hour = Integer.valueOf(strTime.substring(8, 10));
            }
            if (strTime.length() >= 12) {
                minute = Integer.valueOf(strTime.substring(10, 12));
            }
            if (strTime.length() >= 14) {
                sec = Integer.valueOf(strTime.substring(12, 14));
            }
            calendar = new GregorianCalendar(year, month, day, hour, minute, sec);
        }
        return calendar;
    }

    /**
     * 计算时间添加天数计算，给添加评标活动时使用，用于计算评标截止时间 问题编号：[fix-005]
     * 
     * @param time
     *            原来的时间
     * @param days
     *            加的天加1天 1.5一天半
     * @return
     */
    public static long calcTimeAddOfDay(long time, double days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date1 = new Date();
        try {
            date1 = format.parse(String.valueOf(time));
        } catch (ParseException e) {
            throw new MisException(e);
        }
        int addDay = (int) (days * 24);
        long Time = (date1.getTime() / 1000) + (60 * 60 * addDay);

        date1.setTime(Time * 1000);

        String mydate = format.format(date1);

        return Long.parseLong(mydate);
    }

    /**
     * 时间添加天数计算 Author 张华 Jul 31, 2009
     * 
     * @param time
     *            原来的时间(如：20090731)
     * @param days
     *            加的天加1天 1.5一天半
     * @return
     */
    public static int calcTimeAddOfDay(int time, double days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        Date date1 = new Date();
        try {
            date1 = format.parse(String.valueOf(time));
        } catch (ParseException e) {
            throw new MisException(e);
        }
        int addDay = (int) (days * 24);
        long Time = (date1.getTime() / 1000) + (60 * 60 * addDay);

        date1.setTime(Time * 1000);

        String mydate = format.format(date1);

        return Integer.parseInt(mydate);
    }

    /**
     * 时间减少天数计算 Author 魏彩霞 Jul 31, 2009
     * 
     * @param time
     *            原来的时间(如：20090731)
     * @param days
     *            加的天加1天 1.5一天半
     * @return
     */
    public static int calcTimeMinusOfDay(int time, double days, double count) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        Date date1 = new Date();
        try {
            date1 = format.parse(String.valueOf(time));
        } catch (ParseException e) {
            throw new MisException(e);
        }

        long Time = 0;
        if (days <= count) {
            int delDay = (int) ((count - days) * 24);
            Time = (date1.getTime() / 1000) - (60 * 60 * delDay);
        } else {
            int delDay = (int) ((days - count) * 24);
            Time = (date1.getTime() / 1000) + (60 * 60 * (delDay));
        }

        date1.setTime(Time * 1000);
        String mydate = format.format(date1);
        return Integer.parseInt(mydate);
    }

    /**
     * 功能描述：得到给定时间减去给定天数后的时间<br>
     * 
     * @param time
     *            时间
     * @param days
     *            天数
     * @return long <BR>
     */
    public static long getTimeSubtractOfDay(long time, int days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");

        Date date1 = new Date();
        try {
            date1 = format.parse(String.valueOf(time));
        } catch (ParseException e) {
            throw new MisException(e);
        }
        int subtractDay = (int) (days * 24);
        long Time = (date1.getTime() / 1000) - (60 * 60 * subtractDay);

        date1.setTime(Time * 1000);

        String mydate = format.format(date1);

        return Long.parseLong(mydate);
    }

    public static long getTimeSubtractOfDay(long time, int days, String dateformat) {
        SimpleDateFormat format = new SimpleDateFormat(dateformat);

        Date date1 = new Date();
        try {
            date1 = format.parse(String.valueOf(time));
        } catch (ParseException e) {
        }
        int subtractDay = days * 24;
        long Time = date1.getTime() / 1000L + 3600 * subtractDay;

        date1.setTime(Time * 1000L);

        String mydate = format.format(date1);

        return Long.parseLong(mydate);
    }

    /**
     * 功能描述：得到当前时间之前或之后的数据<br>
     * 
     * @param number
     *            正数为以后时间 负数为之前时间 type 1 年 2 月 3 日 ws 返回的长度
     * @return long <BR>
     */
    public static long getDateToNow(int number, int type, int ws) {
        Long resultLong = null;
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        if (type == 1) {
            cal.add(cal.YEAR, number);
        } else if (type == 2) {
            cal.add(cal.MONTH, number);
        } else if (type == 3) {
            cal.add(cal.DAY_OF_MONTH, number);
        }
        resultLong = transferDateToLong(cal.getTime());
        if (ws == 8) {
            resultLong = resultLong / (1000000);
        }
        return resultLong;
    }

    /**
     * @param date
     * @return String
     * @date 2009-1-4
     * @discription : 20090104165030时间格式转换成16时50分30秒的格式
     */
    public static String getLongTime(Number date) {
        String fmDate = new String();
        if (date == null) {
            fmDate = "";
        } else {
            fmDate = String.valueOf(date.longValue());
            String hour = "";
            String minute = "";
            String seconds = "";
            // 日期格式不合法则转化为空串
            if (fmDate.length() < 14) {
                fmDate = "";
            }
            if (fmDate.length() == 14) {
                hour = fmDate.substring(8, 10);
                minute = fmDate.substring(10, 12);
                seconds = fmDate.substring(12, 14);
                fmDate = hour + ":" + minute + ":" + seconds;
            }
        }
        return fmDate;
    }

    /**
     * 取得月份的第一天
     * 
     * @param time
     *            Long
     * @return String
     */
    public static Long getMonthBegin(Long time) {
        Calendar cal = long2Calendar(time);
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH);
        cal.set(year, month, 1);
        return calendarToLong8(cal);
    }

    /**
     * 转化日历为长整形
     * 
     * @param cal
     * @return
     */
    public static Long calendarToLong8(Calendar cal) {
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int date = cal.get(cal.DATE);
        String strMonth = month < 10 ? ("0" + month) : String.valueOf(month);
        String strDate = date < 10 ? ("0" + date) : String.valueOf(date);

        return Long.parseLong(year + strMonth + strDate);
    }

    /**
     * 取得指定月份的最后一天
     * 
     * @param time
     *            Long
     * @return String
     */
    public static Long getMonthEnd(Long time) {
        Calendar cal = long2Calendar(getMonthBegin(time));
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        int yeah = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH);
        int day = cal.get(cal.DATE);
        return calendarToLong8(cal);
    }

    /**
     * 获取指定日期date的星期dayOfWeek
     * 
     * @param date
     *            日期如:20090803
     * @param dayOfWeek
     *            周几(0-周日,1-周一...6-周六)
     * @return
     */
    public static Integer getIntDayOfWeek(int date, int dayOfWeek) {
        Date date2 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            date2 = format.parse(String.valueOf(date));
        } catch (ParseException e) {
            throw new MisException(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek + 1);
        return Integer.valueOf(dateFormat.format((calendar.getTime())));
    }

    /**
     * 获得当前周的“星期几”
     * 
     * @param dayOfWeek
     *            周几(从2开始),请使用常量(如：Calendar.MONDAY)
     * @return 返回八位的整型数字(如：20090629)
     */
    public static Integer getIntDayOfWeek(int dayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return Integer.valueOf(dateFormat.format((calendar.getTime())));
    }

    /**
     * 计算此日期是一周中的第几天
     * @param date
     *            长整型日期(如:20090730)
     * @return
     */
    public static Integer getDayOfWeek(Long date) {
        Calendar calendar = long2Calendar(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 计算月份的加减
     * 
     * @param date
     *            起始日期(如:20090801)
     * @param addMonth
     *            待加的月数(如:1)
     * @return 返回形如:20090901
     */
    public static int calcTimeAddOfMonth(int date, int addMonth) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date dateTmp = new Date();
        try {
            dateTmp = format.parse(String.valueOf(date));
        } catch (ParseException e) {
            throw new MisException(e);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTmp);

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + addMonth);

        return Integer.parseInt(format.format(calendar.getTime()));
    }

    public static long getDecDayCount(long endtime) {
        long starttime = getLongCurrDateTime14();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = format.parse(String.valueOf(endtime) + "00");
            date2 = format.parse(String.valueOf(starttime));
        } catch (ParseException e) {
            throw new MisException(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        long timethis = calendar.getTimeInMillis();
        calendar.setTime(date2);
        long timeend = calendar.getTimeInMillis();
        long theday = (timeend - timethis) / (1000 * 60 * 60 * 24);

        return theday;

    }

    /**
     * 只取小时分钟或者秒 201302121223 结果是12:23
     * 
     * @param date
     * @return
     */
    public static String long2StrCnDay(Long date) {
        String fmDate = new String();
        if (date == null) {
            fmDate = "";
        } else {
            fmDate = "";
            String hour = "";
            String minute = "";
            // 日期格式不合法则转化为空串
            if (date.toString().length() < 12) {
                fmDate = "";
            }
            if (date.toString().length() >= 12) {
                hour = (date.toString()).substring(8, 10);
                minute = (date.toString()).substring(10, 12);
                fmDate = fmDate + " " + hour + ":" + minute;
            }
            if (date.toString().length() == 14) {
                fmDate = fmDate + ":" + (date.toString()).substring(12, 14);
            }
        }
        return fmDate;
    }

    /**
     * 计算日期差值（2天前 2小时前 刚刚）
     * 
     * @param startTime
     * @param endTime
     *            （long 14位）(如果计算到当前时间，endTime可以直接传null)
     * @return
     */
    public static String dateMinus(String startTime, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = "";
        try {
            if (endTime == null) {
                endTime = DateUtil.getLongCurrDateTime14().toString();
            }
            Date d1 = df.parse(endTime);
            Date d2 = df.parse(startTime);
            Long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
            Long days = diff / (1000 * 60 * 60 * 24);
            Long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            Long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            if (days != 0 && days >= 1) {
                str = "" + days + "天前";
            } else if (hours != 0 && hours < 24) {
                str = hours + "小时前";
            } else if (minutes != 0 && minutes < 60) {
                str = minutes + "分钟前";
            } else {
                str = "刚刚";
            }
        } catch (Exception e) {
        }
        return str;
    }

    /**
     * 开始时间和结束时间之间的天数
     * @参数: startDate 开始时间
     * @参数: endDate 结束时间
     * @返回值: list
     * @修改记录（修改时间、作者、原因）：
     */
    public static List<Date> dateSplit(String startDate, String endDate) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date d1 = df.parse(startDate);
        Date d2 = df.parse(endDate);
        Long spi = d2.getTime() - d1.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(d1);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime() + (24 * 60 * 60 * 1000)));// 比上一天加一
        }
        return dateList;
    }

    /**
     * 计算消息通知时间已发生多久
     *
     * @参数: tzsj 消息通知时间
     * @返回值: String
     * @修改记录（修改时间、作者、原因）：
     */
    public static String timeBefore(Long tzsj) {
        if (tzsj != null) {
            Long currentTime = new Date().getTime();
            Long tcsltime = DateUtil.long2Calendar(tzsj).getTime().getTime();
            long nM = 1000 * 30 * 24 * 60 * 60;// 一月的毫秒数
            long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
            long nh = 1000 * 60 * 60;// 一小时的毫秒数
            long nm = 1000 * 60;// 一分钟的毫秒数
            long diff = currentTime - tcsltime;
            if (diff / nM > 0) {
                return diff / nM + "月前";
            }
            if (diff / nd > 0) {
                return diff / nd + "天前";
            }
            if (diff / nh > 0) {
                return diff / nh + "小时前";
            }
            if (diff / nm > 0) {
                return diff / nm + "分钟前";
            }

            return "刚刚";
        }

        return "";
    }

    /**
     * 计算消息通知时间还有多久到来
     *
     * @参数: tzsj 消息通知时间
     * @返回值: String
     * @修改记录（修改时间、作者、原因）：
     */
    public static String timeAfter(Long tzsj) {
        if (tzsj != null) {
            Long currentTime = new Date().getTime();
            Long tcsltime = DateUtil.long2Calendar(tzsj).getTime().getTime();
            long nM = 1000 * 30 * 24 * 60 * 60;// 一月的毫秒数
            long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
            long nh = 1000 * 60 * 60;// 一小时的毫秒数
            long nm = 1000 * 60;// 一分钟的毫秒数
            long diff = tcsltime - currentTime;
            if (diff / nM > 0) {
                return diff / nM + "月后";
            }
            if (diff / nd > 0) {
                return diff / nd + "天后";
            }
            if (diff / nh > 0) {
                return diff / nh + "小时后";
            }
            if (diff / nm > 0) {
                return diff / nm + "分钟后";
            }

            return "即将";
        }

        return "";
    }

    /****
     * 当前时间加小时
     */
    public static String calcTimeAddOfHour(int hour) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
    }

    /**
     * 获取当天所在周的开始及结束时间
     *
     * 
     * @参数：
     * @返回值： long
     * 
     * @修改记录（修改时间、作者、原因）：
     */
    public static Map<String, String> getCurrDayWeekTime(long currTime) {
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        Calendar cal = Calendar.getInstance();
        String currSJ = dateFormateStr(ConvertUtils.createString(currTime));
        Date time;
        try {
            time = sdf.parse(currSJ);
        } catch (ParseException e) {
            throw new MisException(e);
        }
        cal.setTime(time);

        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String kssj = sdf.format(cal.getTime());
        kssj = kssj.replaceAll("-", "") + "000000";
        cal.add(Calendar.DATE, 6);
        String jssj = sdf.format(cal.getTime());
        jssj = jssj.replaceAll("-", "") + "235959";
        map.put("kssj", kssj);
        map.put("jssj", jssj);
        return map;
    }
    
    public static double datedays(String startTime, String endTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Date d1 = df.parse(endTime);
            Date d2 = df.parse(startTime);
            Long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
            double days = ConvertUtils.createDouble(diff) /ConvertUtils.createDouble(1000 * 60 * 60 * 24);
			
            
            return days;
            
        }

    /**
     * 格式化8位格式日期为Date对象
     *
     * @参数： dateTime 日期
     * @返回值： Date
     *
     * @修改记录（修改时间、作者、原因）：
     */
    public static Date formateDate8(Integer dateTime)  {
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMdd");

            return df.parse(ConvertUtils.createString(dateTime));
        } catch (ParseException e) {
            throw new MisException(e);
        }
    }

    /**
     * 格式化12位格式日期为Date对象
     *
     *
     * @参数： dateTime 日期
     * @返回值： Date
     *
     * @修改记录（修改时间、作者、原因）：
     */
    public static Date formateDate12(long dateTime) {
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");

            return df.parse(ConvertUtils.createString(dateTime));
        } catch (ParseException e) {
            throw new MisException(e);
        }
    }

    /**
     * 格式化14位格式日期为Date对象
     *
     *
     * @参数： dateTime 日期
     * @返回值： Date
     *
     * @修改记录（修改时间、作者、原因）：
     */
    public static Date formateDate14(long dateTime)  {
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

            return df.parse(ConvertUtils.createString(dateTime));
        } catch (ParseException e) {
            throw new MisException(e);
        }
    }

    /**
     * 获取当前时间的前后几小时几分钟时间
     * 
     * @param hour
     * @param minute
     * @return
     */
    public static long getTimeByHourMinute(int hour,int minute) {
    	Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)-hour);
	    calendar.add(Calendar.MINUTE, minute);
	    String time=new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
	    return ConvertUtils.createLong(time);
    }

    /**
     * 获取当前系统时间(毫秒数)
     * @return 毫秒数
     */
    public static Long getNowTimeToOldTime(){
        Date d = new Date();
        return d.getTime();
    }
}
