package org.aist.formexpert.services;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

@Service
public class DateClassifierService {
    private final static String DATE_FORMAT = "MM/dd/yyyy";

    public boolean isValidDate(String plainDate) {
        Date date = getDate(plainDate);
        return date != null;
    }

    public boolean isOver18(String plainDate){
        Date givenDate = getDate(plainDate);
        return givenDate != null && getDiffYears(givenDate, new Date()) >= 18;
    }

    public boolean isOver21(String plainDate){
        Date givenDate = getDate(plainDate);
        return givenDate != null && getDiffYears(givenDate, new Date()) >= 21;
    }

    public Date getDate(String plainDate){
        Pattern p = Pattern.compile("\\d{1,2}/\\d{1,2}/\\d+");
        if(!p.matcher(plainDate).matches()){
            return null;
        }

        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(plainDate);
            return df.parse(plainDate);
        } catch (Exception e) {
            return null;
        }
    }

    public int getDiffYears(Date first, Date last) {
        Calendar from = getCalendar(first);
        Calendar to = getCalendar(last);
        int diff = to.get(YEAR) - from.get(YEAR);
        if(from.get(MONTH) > to.get(MONTH) || (from.get(MONTH) == to.get(MONTH) && from.get(DATE) > to.get(DATE))) {
            diff--;
        }
        return diff;
    }

    private Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.US);
        calendar.setTime(date);
        return calendar;
    }
}
