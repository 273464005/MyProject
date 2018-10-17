package org.com.lyz.util;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{

    public StringUtils()
    {
    }
    
    public static boolean isEmpty(String str)
    {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str)
    {
        int strLen;
        if(str == null || (strLen = str.length()) == 0)
            return true;
        for(int i = 0; i < strLen; i++)
            if(!Character.isWhitespace(str.charAt(i)))
                return false;

        return true;
    }

    public static boolean isNotBlank(String str)
    {
        return !isBlank(str);
    }
    
    public static boolean equals(String str1, String str2)
    {
        return str1 != null ? str1.equals(str2) : str2 == null;
    }
    
    public static String removeEnd(String str, String remove)
    {
        if(isEmpty(str) || isEmpty(remove))
            return str;
        if(str.endsWith(remove))
            return str.substring(0, str.length() - remove.length());
        else
            return str;
    }

    public static String toString(String strArray[], String separator)
    {
        String strResult = "";
        if(strArray == null || separator == null)
        {
            strResult = null;
        } else
        {
            StringBuffer strBuffer = new StringBuffer();
            for(int i = 0; i < strArray.length; i++)
                if(strArray[i] != null && strArray[i].length() != 0)
                {
                    strBuffer.append(separator);
                    strBuffer.append(strArray[i]);
                }

            if(strBuffer.length() > 0)
                strBuffer.delete(0, separator.length());
            strResult = strBuffer.toString();
        }
        return strResult;
    }

    /**
     * 将阿拉伯数字转换为汉字数字
     * @param a 需要转换的数字
     * @return 汉字数字
     */
    public static String translateNumToChinese(int a)
    {
        String units[] = {
            // "", "十", "百", "千", "万", "十", "百", "千", "亿"
            "", "\u5341", "\u767E", "\u5343", "\u4E07", "\u5341", "\u767E", "\u5343", "\u4EBF"
        };
        String nums[] = {
            // "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"
            "\u4E00", "\u4E8C", "\u4E09", "\u56DB", "\u4E94", "\u516D", "\u4E03", "\u516B", "\u4E5D", "\u5341"
        };
        String result = "";
        if(a < 0)
        {
            result = "\u8D1F";
            a = Math.abs(a);
        }
        String t = String.valueOf(a);
        for(int i = t.length() - 1; i >= 0; i--)
        {
            int r = (int)((double)a / Math.pow(10D, i));
            if(r % 10 != 0)
            {
                String s = String.valueOf(r);
                String l = s.substring(s.length() - 1, s.length());
                result = (new StringBuilder(String.valueOf(result))).append(nums[Integer.parseInt(l) - 1]).toString();
                result = (new StringBuilder(String.valueOf(result))).append(units[i]).toString();
            } else
            if(!result.endsWith("\u96F6"))//"零"
                result = (new StringBuilder(String.valueOf(result))).append("\u96F6").toString();
        }

        return result;
    }

    public static String convertStr(Object str)
    {
        if(str == null || str.toString().equals(""))
            return null;
        else
            return str.toString();
    }

    public static Integer convertInt(Object str)
    {
        if(str == null || "".equals(str.toString()))
            return null;
        else
            return Integer.valueOf(str.toString());
    }

    public static Long convertLong(Object str)
    {
        if(str == null || "".equals(str.toString()))
            return null;
        else
            return Long.valueOf(str.toString());
    }

    public static Double convertDouble(Object obj)
    {
        if(obj == null || "".equals(obj.toString()))
            return null;
        else
            return Double.valueOf(obj.toString());
    }

    public static BigDecimal convertBigDecimal(Object obj)
    {
        if(obj == null || "".equals(obj.toString()))
            return null;
        else
            return BigDecimal.valueOf(Double.valueOf(obj.toString()).doubleValue());
    }

    public static String join(Object src[], String separator, String quot, String defaultValue)
    {
        StringBuffer sb = new StringBuffer();
        if(src == null || src.length == 0)
            return defaultValue;
        for(int i = 0; i < src.length; i++)
        {
            if(sb.length() > 0)
                sb.append(separator);
            if(quot != null)
                sb.append(quot);
            sb.append(src[i]);
            if(quot != null)
                sb.append(quot);
        }

        return sb.toString();
    }

    public static String join(List list, String separator, String quot, String defaultValue)
    {
        StringBuffer sb = new StringBuffer();
        if(list == null || list.size() == 0)
            return defaultValue;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            String value = (String)iterator.next();
            if(sb.length() > 0 && separator != null)
                sb.append(separator);
            if(quot != null)
                sb.append(quot);
            sb.append(value);
            if(quot != null)
                sb.append(quot);
        }

        return sb.toString();
    }

    public static String format(String pattern, Object value)
    {
        if(value == null)
            return "";
        if(value instanceof Number)
            return (new DecimalFormat(pattern)).format(value);
        if(value instanceof Date)
            return (new SimpleDateFormat(pattern)).format(value);
        if(value instanceof Calendar)
            return (new SimpleDateFormat(pattern)).format(((Calendar)value).getTime());
        try
        {
            Double douValue = ConvertUtils.createDouble(value);
            return (new DecimalFormat(pattern)).format(douValue);
        }
        catch(Exception e)
        {
            return value.toString();
        }
    }

    public static String numToPercent(String num)
    {
        if(num == null)
            return "";
        else
            return (new StringBuilder(String.valueOf(num))).append("%").toString();
    }

    public static String strToLable(String str)
    {
        if(str == null)
            return "";
        else
            // "\uFF1A" --> "："
            return (new StringBuilder(String.valueOf(str))).append("\uFF1A").toString();
    }

    public static String toJavaScriptString(String s)
    {
        StringBuffer sb = new StringBuffer(s);
        for(int i = 0; i < sb.length(); i++)
        {
            char c = sb.charAt(i);
            if(c == '\n')
            {
                sb.replace(i, i + 1, "\\n");
                i++;
            } else
            if(c == '"')
                sb.replace(i, i + 1, "'");
        }

        return sb.toString();
    }

    public static String textarea2htmlString(String s)
    {
        if(s != null)
            return s.replaceAll("\n", "<p>").replaceAll(" ", "&nbsp;");
        else
            return null;
    }

    public static String int2Excel(int i)
    {
        String s = "";
        int m = i % 26;
        for(i /= 26; i != 0 || m != 0; i /= 26)
        {
            if(m == 0)
            {
                i--;
                m = 26;
            }
            s = (new StringBuilder(String.valueOf((char)((m + 65) - 1)))).append(s).toString();
            m = i % 26;
        }

        return s;
    }

    public static String subMaxStr(String str, int maxLength)
    {
        String result = null;
        if(str != null)
            if(str.length() <= maxLength)
                result = str;
            else
                result = str.substring(0, maxLength);
        return result;
    }

    public static String subMaxStrToEtc(String str, int maxLength, String flag)
    {
        String result = null;
        if(str != null)
            if(str.length() <= maxLength)
                result = str;
            else
                result = (new StringBuilder(String.valueOf(str.substring(0, maxLength)))).append(flag).toString();
        return result;
    }

    public static String removeNewline(String str)
    {
        String result = null;
        if(str != null)
        {
            result = str.replaceAll("\\r\\n", "");
            result = result.replaceAll(new String(new char[] {
                '\n'
            }), "");
        }
        return result;
    }

    public static Integer[] convertStringArray2IntegerArray(String values[])
    {
        List list = new ArrayList();
        if(values != null)
        {
            String as[];
            int j = (as = values).length;
            for(int i = 0; i < j; i++)
            {
                String value = as[i];
                list.add(Integer.valueOf(value));
            }

            return (Integer[])list.toArray(new Integer[0]);
        } else
        {
            return null;
        }
    }

    public static String number2Cn(String strNum)
    {
        String result = null;
        if(strNum != null && strNum.length() > 0)
        {
            result = strNum;
            result = result.replaceAll("0", "\u96F6");// 零
            result = result.replaceAll("1", "\u4E00");// 一
            result = result.replaceAll("2", "\u4E8C");// 二
            result = result.replaceAll("3", "\u4E09");// 三
            result = result.replaceAll("4", "\u56DB");// 四
            result = result.replaceAll("5", "\u4E94");// 五
            result = result.replaceAll("6", "\u516D");// 六
            result = result.replaceAll("7", "\u4E03");// 七
            result = result.replaceAll("8", "\u516B");// 八
            result = result.replaceAll("9", "\u4E5D");// 九
        }
        return result;
    }

    public static String getAWXX(String DH)
    {
        DH = "***********";
        return DH;
    }

    public static String LowerToUpperOfNum(int je)
    {
        if(je == 0)
            return "\u96F6";
        String money = "";
        String num = "\u96F6\u4E00\u4E8C\u4E09\u56DB\u4E94\u516D\u4E03\u516B\u4E5D\u5341";
        String unit[] = {
            "", "\u5341", "\u767E", "\u5343", "\u4E07", "\u5341\u4E07", "\u767E\u4E07", "\u5343\u4E07", "\u4EBF", "\u5341\u4EBF", 
            "\u767E\u4EBF", "\u5343\u4EBF"
        };
        String beforeDot = String.valueOf(je);
        int bl = beforeDot.length();
        boolean zero = false;
        int z = 0;
        int j = 0;
        for(int i = bl - 1; j <= bl - 1; i--)
        {
            int number = Integer.parseInt(String.valueOf(beforeDot.charAt(j)));
            if(number == 0)
            {
                zero = true;
                z++;
            } else
            {
                zero = false;
                z = 0;
            }
            if(zero && z == 1)
                money = (new StringBuilder(String.valueOf(money))).append("\u96F6").toString();
            else
            if(!zero && !zero)
                money = (new StringBuilder(String.valueOf(money))).append(num.substring(number, number + 1)).append(unit[i]).toString();
            j++;
        }

        for(int i = 1; i <= 2; i++)
        {
            String ss = "";
            if(i == 1)
                ss = "\u4E07";
            else
                ss = "\u4EBF";
            int last = money.lastIndexOf(ss);
            if(last != -1)
            {
                String moneySub1 = money.substring(0, last);
                String moneySub2 = money.substring(last, money.length());
                for(int last2 = moneySub1.indexOf(ss); last2 != -1; last2 = moneySub1.indexOf(ss))
                    moneySub1 = (new StringBuilder(String.valueOf(moneySub1.substring(0, last2)))).append(moneySub1.substring(last2 + 1, moneySub1.length())).toString();

                money = (new StringBuilder(String.valueOf(moneySub1))).append(moneySub2).toString();
            }
        }

        int zi = money.lastIndexOf("\u96F6");
        if(zi == money.length() - 1)
            money = money.substring(0, money.length() - 1);
        int type = money.indexOf("\u4E00\u5341");
        if(type == 0)
            money = money.substring(1);
        return money;
    }

    public static String LowerToUpperOfNum(double n)
    {
        String fraction[] = {
            "\u89D2", "\u5206"
        };
        String digit[] = {
            "\u96F6", "\u58F9", "\u8D30", "\u53C1", "\u8086", "\u4F0D", "\u9646", "\u67D2", "\u634C", "\u7396"
        };
        String unit[][] = {
            {
                "\u5143", "\u4E07", "\u4EBF"
            }, {
                "", "\u62FE", "\u4F70", "\u4EDF"
            }
        };
        String head = n >= 0.0D ? "" : "\u8D1F";
        n = Math.abs(n);
        String s = "";
        for(int i = 0; i < fraction.length; i++)
            s = (new StringBuilder(String.valueOf(s))).append((new StringBuilder(String.valueOf(digit[(int)(Math.floor(n * 10D * Math.pow(10D, i)) % 10D)]))).append(fraction[i]).toString().replaceAll("(\u96F6.)+", "")).toString();

        if(s.length() < 1)
            s = "\u6574";
        int integerPart = (int)Math.floor(n);
        for(int i = 0; i < unit[0].length && integerPart > 0; i++)
        {
            String p = "";
            for(int j = 0; j < unit[1].length && n > 0.0D; j++)
            {
                p = (new StringBuilder(String.valueOf(digit[integerPart % 10]))).append(unit[1][j]).append(p).toString();
                integerPart /= 10;
            }

            s = (new StringBuilder(String.valueOf(p.replaceAll("(\u96F6.)*\u96F6$", "").replaceAll("^$", "\u96F6")))).append(unit[0][i]).append(s).toString();
        }

        return (new StringBuilder(String.valueOf(head))).append(s.replaceAll("(\u96F6.)*\u96F6\u5143", "\u5143").replaceFirst("(\u96F6.)+", "").replaceAll("(\u96F6.)+", "\u96F6").replaceAll("^\u6574$", "\u96F6\u5143\u6574")).toString();
    }

    public static String addComma(String str, int split)
    {
        if(str == null || "".equals(str))
            return null;
        int point = str.indexOf(".");
        str = str.replaceAll(",", "");
        if(point < 0)
        {
            StringBuffer tmp = (new StringBuffer()).append(str).reverse();
            String retNum = Pattern.compile((new StringBuilder("(\\d{")).append(split).append("})(?=\\d)").toString()).matcher(tmp.toString()).replaceAll("$1,");
            return (new StringBuffer()).append(retNum).reverse().toString();
        } else
        {
            StringBuffer retValue = new StringBuffer();
            StringBuffer left = (new StringBuffer()).append(str.substring(0, point)).reverse();
            String retLeft = Pattern.compile((new StringBuilder("(\\d{")).append(split).append("})(?=\\d)").toString()).matcher(left.toString()).replaceAll("$1,");
            retValue.append(retLeft).reverse().append(".");
            String right = str.substring(point + 1, str.length());
            return retValue.append(right).toString();
        }
    }

    public static String getMoneyString(String str)
    {
        return addComma(str, 3);
    }

    public static void main(String args[])
    {
//        System.out.println(format("\uFFE5#,##0.00\u5143", Integer.valueOf(66778899)));
//        System.out.println(LowerToUpperOfNum(1300));
//        System.out.println(addComma("100111111111113000", 3));
        String sfzh = "008123456789789000";
        String sjh = "13100000000";
//        String s = sfzh.substring(3, 14);
//        String ss = numToAsterisk(s);
//        System.out.println("ss-->"+ss+"\tssLen-->"+ss.length());
        System.out.println(idAndMobileNumToAsterisk(sjh));
    }

    public static String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String doFilter(String input)
    {
        if(input != null)
            return input.replaceAll("'", "''");
        else
            return input;
    }

    public static boolean isSQLInject(String in_str)
    {
        if(!isEmpty(in_str))
        {
            String str_Lower = in_str.toLowerCase();
            for(int i = 0; i < inj_sqls.length; i++)
                if(str_Lower.indexOf((new StringBuilder(" ")).append(inj_sqls[i]).append(" ").toString()) >= 0)
                    return true;

        }
        return false;
    }

    public static boolean isHTMLInject(String in_str)
    {
        if(!isEmpty(in_str))
        {
            Matcher matcher = html_pattern.matcher(in_str);
            return matcher.find();
        } else
        {
            return false;
        }
    }

    public static String decodeURIEx(String in_str)
    {
        if(isEmpty(in_str))
            return in_str;
        try
        {
            in_str = URLDecoder.decode(in_str, "UTF-8");
            in_str = in_str.replaceAll("\uFF05", "%");
        }
        catch(Exception e)
        {
        }
        return in_str;
    }

    public static String getHTMLMessage(String msg)
    {
        String str_msg = (new StringBuilder("<html><head>\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\" /></head><body>\t<br><br><br>\t<center><H2>")).append(msg).append("</H2></center>").append("</body>").append("</html>").toString();
        return str_msg;
    }

    public static String getSubString(String str, int len)
    {
        if(isNotBlank(str) && len > 0)
        {
            byte b[] = str.getBytes();
            if(len < b.length)
            {
                if(str.length() == b.length)
                    return new String(b, 0, len);
                if(len == 1)
                    if(b[0] < 0)
                        return null;
                    else
                        return new String(b, 0, len);
                if(b[len - 1] < 0)
                {
                    int cloneLen;
                    for(cloneLen = len + 1; (cloneLen -= 2) > 0 && b[cloneLen] < 0 && b[cloneLen - 1] < 0;);
                    if(cloneLen == 0 || cloneLen > 0 && b[cloneLen] < 0)
                        return new String(b, 0, len - 1);
                    else
                        return new String(b, 0, len);
                } else
                {
                    return new String(b, 0, len);
                }
            } else
            {
                return str;
            }
        } else
        {
            return null;
        }
    }

    /**
     * 将身份证或者手机号转换为*
     * @param s 需要转换的数字
     * @return 转换结果
     */
    public static String idAndMobileNumToAsterisk(String s){
        if(isEmpty(s)){
            return null;
        }
        if(s.length()==11){
            //手机号
            return numToAsterisk(s, 3);
        } else if(s.length() == 15 || s.length() == 18){
            //身份证号，15位（老版）或18位（新版）
            return numToAsterisk(s, 3,14);
        } else{
            return s + "不是合法的手机号或身份证号";
        }
    }

    /**
     * 将字符串从指定位置替换为*
     * @param s 需要替换的字符串
     * @param start 开始位置
     * @param end 结束位置
     * @return 替换结果
     */
    public static String numToAsterisk(String s,Integer start,Integer end){
        StringBuffer sb = new StringBuffer();
        if (isEmpty(s) || start == null) {
            return null;
        }
        if (start > s.length()) {
            return s;
        }
        if (end == null || end > s.length()) {
            end = s.length();
        }
        sb.append(s.substring(0, start)).append(numToAsterisk(s.substring(start, end))).append(s.substring(end, s.length()));
        return sb.toString();
    }

    /**
     * 将字符串从指定位置开始替换为*
     * @param s 需要替换的字符串
     * @param start 开始位置
     * @return 替换结果
     */
    public static String numToAsterisk(String s,Integer start){
        return numToAsterisk(s,start,null);
    }

    /**
     * 将数字转换为*
     * @param s 需要转换的数字
     * @return 转换结果
     */
    public static String numToAsterisk(String s){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length() ;i ++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private static String inj_sqls[] = {
        "and", "exec", "insert", "select", "delete", "update", "count", "chr", "mid", "master", 
        "truncate", "char", "declare", "union", "from", "%"
    };
    private static Pattern html_pattern = Pattern.compile("<(.*)>(.*)<\\/(.*)>|<(.*)\\/>");

}


