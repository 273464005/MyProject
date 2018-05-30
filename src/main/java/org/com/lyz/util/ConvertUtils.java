package org.com.lyz.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConvertUtils
{

    public ConvertUtils()
    {
    }

    public static int toInt(Object obj)
    {
        return toInt(obj, 0);
    }

    public static int toInt(Object obj, int defaultValue)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return defaultValue;
        else
            return Integer.parseInt(obj.toString());
    }

    public static long toLong(Object obj)
    {
        return toLong(obj, 0L);
    }

    public static long toLong(Object obj, long defaultValue)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return defaultValue;
        else
            return Long.parseLong(obj.toString());
    }

    public static float toFloat(Object obj)
    {
        return toFloat(obj, 0.0F);
    }

    public static float toFloat(Object obj, float defaultValue)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return defaultValue;
        else
            return Float.parseFloat(obj.toString());
    }

    public static double toDouble(Object obj)
    {
        return toDouble(obj, 0.0D);
    }

    public static double toDouble(Object obj, double defaultValue)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return defaultValue;
        else
            return Double.parseDouble(obj.toString());
    }

    public static Float createFloat(Object obj)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return null;
        else
            return Float.valueOf(obj.toString());
    }

    public static Double createDouble(Object obj)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return null;
        else
            return Double.valueOf(obj.toString());
    }

    public static Integer createInteger(Object obj)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return null;
        else
            return Integer.valueOf(obj.toString());
    }

    public static Long createLong(Object obj)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return null;
        else
            return Long.valueOf(obj.toString());
    }

    public static BigInteger createBigInteger(Object obj)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return null;
        else
            return new BigInteger(obj.toString());
    }

    public static BigDecimal createBigDecimal(Object obj)
    {
        if(obj == null || StringUtils.isBlank(obj.toString()))
            return null;
        else
            return new BigDecimal(obj.toString());
    }

    public static String createString(Object obj)
    {
        if(obj == null)
            return null;
        else
            return obj.toString();
    }

    public static Number min(Number array[])
    {
        Number min = array[0];
        for(int i = 1; i < array.length; i++)
            if(array[i].doubleValue() < min.doubleValue())
                min = array[i];

        return min;
    }

    public static Number max(Number array[])
    {
        Number max = array[0];
        for(int j = 1; j < array.length; j++)
            if(array[j].doubleValue() > max.doubleValue())
                max = array[j];

        return max;
    }

    public static Calendar string2Date(String format, Long value)
        throws ParseException
    {
        Date date = (new SimpleDateFormat(format)).parse(String.valueOf(value));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String list2SqlInString(List list, String key)
    {
        if(list != null && list.size() > 0)
        {
            StringBuffer inString = new StringBuffer(" ");
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                Map map = (Map)iterator.next();
                if(map.containsKey(key))
                {
                    inString.append("'");
                    inString.append(map.get(key) != null ? map.get(key).toString() : "");
                    inString.append("',");
                } else
                {
                    new Exception((new StringBuilder("not contains key ")).append(key).toString());
                }
            }

            String in = inString.toString();
            in = StringUtils.equals(in, "") ? "''" : in.endsWith(",") ? StringUtils.removeEnd(in, ",") : in;
            return (new StringBuilder("(")).append(in).append(")").toString();
        } else
        {
            return "(' ')";
        }
    }
}
