package com.mycompany.cdiary.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalendarUtil {
    private static final String FORMAT = "yyyy-MM-dd";
    
    /**
     * yyyy-MM-dd形式の文字列をDateになおす
     * @param str yyyy-MM-dd形式の文字列
     * @param lenient
     * @return Date
     */
    public static Date parseDate(String str) {
        Date date = null;
        
        String reFormat = Pattern.compile("d+|M+")
                .matcher(Matcher.quoteReplacement(FORMAT)).replaceAll("\\\\d{1,2}");
        reFormat = Pattern.compile("y+").matcher(reFormat).replaceAll("\\\\d{4}");
        if (Pattern.compile(reFormat).matcher(str).matches()) {
            SimpleDateFormat sdf = (SimpleDateFormat)DateFormat.getDateInstance();
            sdf.applyPattern(FORMAT);
            sdf.setLenient(false);
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                
            }
        }
        return date;
    }
    
    /**
     * yyyy-MM-dd形式の文字列をCalendarにする
     * @param str yyyy-MM-dd形式の文字列
     * @return Calendar
     */
    public static Calendar parseCalendar(String str) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(parseDate(str));
            return cal;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Calendarオブジェクトが比較可能か調べる
     * @param cal Calendarオブジェクト
     * @return 比較可能ならtrue
     */
    public static boolean isValid(Calendar cal) {
        Calendar now = Calendar.getInstance();
        try {
            now.compareTo(cal);
            return true;
        } catch (NullPointerException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * カレンダーをyyyy-MM-dd文字列にする
     * @param cal カレンダー
     * @return yyyy-MM-dd
     */
    public static String getString(Calendar cal) {
        if (cal == null) {
            return null;
        }
        return String.format("%02d", cal.get(Calendar.YEAR)) + "-" 
                + String.format("%02d", cal.get(Calendar.MONTH) + 1)
                + "-" + String.format("%02d", cal.get(Calendar.DATE));
    }
}
