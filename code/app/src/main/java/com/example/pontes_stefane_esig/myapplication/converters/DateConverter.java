package com.example.pontes_stefane_esig.myapplication.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateConverter {

    public static Date toDate(String stringDate) {
        try {
            DateFormat dateFormatFR = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.FRENCH);
            return dateFormatFR.parse(stringDate);
        } catch (ParseException e) {
            try {
                DateFormat dateFormatEN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return dateFormatEN.parse(stringDate);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public static long toLong(String stringDate) {
        return toDate(stringDate).getTime();
    }

    public static String toStringFR(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.FRENCH);
        return dateFormat.format(date);
    }

    public static String toStringEN(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}


