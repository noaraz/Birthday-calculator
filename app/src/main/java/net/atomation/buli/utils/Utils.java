package net.atomation.buli.utils;

import android.util.Log;

import net.atomation.buli.models.Age;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by noaraz on 16/10/2017.
 */

public class Utils {

    private static String DATE_FORMAT = "dd/MM/yyyy";


    public static String calenderToString(Calendar date) {
        if (date != null) {
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            return formatter.format(date.getTime());
        } else {
            return null;
        }

    }

    public static Calendar stringToCalender(String strDate) {
        SimpleDateFormat curFormater = new SimpleDateFormat(DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(curFormater.parse(strDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;


    }

    public static int mod(int x, int y)
    {
        int result = x % y;
        return result < 0? result + y : result;
    }

}
