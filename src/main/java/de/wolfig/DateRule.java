package de.wolfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateRule {

    private static final String pattern = "yyyy-MM-dd";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    private static String linesBasedOn = "ht";
    private static String week = "";
    private static String month = "";
    private static String year = "";
    private static String quarter = "";
    private static String halfYear = "";
    private static String weekend = "";
    private static String season = "";

    public static void set(String _linesBasedOn, String _week, String _month, String _year, String _quarter, String _halfYear, String _weekend, String _season) {
        linesBasedOn = _linesBasedOn;
        week = _week;
        month = _month;
        year = _year;
        quarter = _quarter;
        halfYear = _halfYear;
        weekend = _weekend;
        season = _season;
    }

    public static String getLinesBasedOn() {
        return linesBasedOn;
    }

    public static String calculateDate(String date, String publication) {
        Date publicationDate = null;
        try {
            publicationDate = simpleDateFormat.parse(publication);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();



        if(date.matches("FUTURE_REF")) {
            gregorianCalendar.setTime(publicationDate);
            gregorianCalendar.add(Calendar.MONTH, 3);
            //System.out.println("FUTURE_REF: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("PAST_REF")) {
            gregorianCalendar.setTime(publicationDate);
            gregorianCalendar.add(Calendar.MONTH, -3);
            //System.out.println("PAST_REF: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("PRESENT_REF")) {
            gregorianCalendar.setTime(publicationDate);
            gregorianCalendar.add(Calendar.MONTH, 0);
            //System.out.println("PRESENT_REF: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("^\\d{4}-\\d{2}-\\d{2}(T[A-Z]{2}$)?")) {
            String[] dat = date.split("T");
            try {
                String _pattern = "yyyy-MM-dd";
                SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(_pattern);
                //System.out.println("Date: " + simpleDateFormat.format(_simpleDateFormat.parse(dat[0])));
                return simpleDateFormat.format(_simpleDateFormat.parse(dat[0]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // adjustable

        if(date.matches("^\\d{4}$")) {
            YearMonth yearMonth = null;
            int day = 0;
            switch (year) {
                case "first":
                    yearMonth = YearMonth.of(Integer.parseInt(date), 1);
                    day = 1;
                    break;
                case "mid":
                    yearMonth = YearMonth.of(Integer.parseInt(date), 6);
                    day = yearMonth.lengthOfMonth();
                    break;
                case "last":
                    yearMonth = YearMonth.of(Integer.parseInt(date), 12);
                    day = yearMonth.lengthOfMonth();
                    break;
                default:
                    String[] d = year.split("\\.");
                    gregorianCalendar.set(Integer.parseInt(date), Integer.parseInt(d[1]), Integer.parseInt(d[0]));
                    return simpleDateFormat.format(gregorianCalendar.getTime());
            }
            gregorianCalendar.set(Integer.parseInt(date), yearMonth.getMonthValue()-1, day);
            //System.out.println("Year: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("^\\d{4}-\\d{2}$")) {
            String[] dat = date.split("-");
            int day = 0;
            YearMonth yearMonth = YearMonth.of(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]));
            switch (month) {
                case "first":
                    day = 1;
                    break;
                case "mid":
                    day = yearMonth.lengthOfMonth() / 2;
                    break;
                case "last":
                    day = yearMonth.lengthOfMonth();
                    break;
                default:
                    day = Integer.parseInt(month.replace("d", ""));
                    break;
            }
            gregorianCalendar.set(Integer.parseInt(dat[0]), Integer.parseInt(dat[1])-1, day);
            //System.out.println("Year with Month: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("^\\d{4}-W\\d{2}$")) {
            String[] dat = date.split("-W");
            switch (week) {
                case "mo":
                    gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 2);
                    break;
                case "tu":
                    gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 3);
                    break;
                case "we":
                    gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 4);
                    break;
                case "th":
                    gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 5);
                    break;
                case "fr":
                    gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 6);
                    break;
                case "sa":
                    gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 7);
                    break;
                case "su":
                    gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 1);
                    break;
            }
            //System.out.println("Year with Week: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("^\\d{4}-W\\d{2}-WE$")) {
            String[] dat = date.split("-W");
            if(weekend.equals("su")) gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 1);
            else gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 7); //1 is sunday
            //System.out.println("Year with Week + Weekend: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("^\\d{4}-H\\d$")) {
            String[] dat = date.split("-H");
            YearMonth yearMonth = null;
            int month = 0;
            int day = 0;
            if(dat[1].equals("1")) month = 2;
            else if(dat[1].equals("2")) month = 8;
            switch (halfYear) {
                case "first":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month - 2);
                    day = 1;
                    break;
                case "mid":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month);
                    day = yearMonth.lengthOfMonth();
                    break;
                case "last":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month + 3);
                    day = yearMonth.lengthOfMonth();
                    break;
            }
            gregorianCalendar.set(Integer.parseInt(dat[0]), yearMonth.getMonthValue()-1, day);
            //System.out.println("Half year: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("^\\d{4}-[A-Z]{2}")) {
            String[] dat = date.split("-");
            int month = 0;
            int day = 0;
            switch (dat[1]) {
                case "SP":
                    month = 4;
                    break;
                case "SU":
                    month = 7;
                    break;
                case "FA":
                    month = 10;
                    break;
                case "WI":
                    month = 1;
                    break;
            }
            YearMonth yearMonth = null;
            switch (season) {
                case "first":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month - 1);
                    day = 1;
                    break;
                case "mid":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month);
                    day = yearMonth.lengthOfMonth() / 2;
                    break;
                case "last":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month + 1);
                    day = yearMonth.lengthOfMonth();
                    break;
            }
            gregorianCalendar.set(Integer.parseInt(dat[0]), yearMonth.getMonthValue()-1, day);
            //System.out.println("Season: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }

        if(date.matches("^\\d{4}-Q\\d$")) {
            String[] dat = date.split("-Q");
            int month = 0;
            int day = 0;
            switch (dat[1]) {
                case "0":
                    dat[0] = String.valueOf(Integer.parseInt(dat[0])-1);
                    month = 11;
                    break;
                case "1":
                    month = 2;
                    break;
                case "2":
                    month = 5;
                    break;
                case "3":
                    month = 8;
                    break;
                case "4":
                    month = 11;
                    break;
                case "5":
                    dat[0] = String.valueOf(Integer.parseInt(dat[0])+1);
                    month = 2;
                    break;
            }
            YearMonth yearMonth = null;
            switch (quarter) {
                case "first":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month - 1);
                    day = 1;
                    break;
                case "mid":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month);
                    day = yearMonth.lengthOfMonth() / 2;
                    break;
                case "last":
                    yearMonth = YearMonth.of(Integer.parseInt(dat[0]), month + 1);
                    day = yearMonth.lengthOfMonth();
                    break;
            }
            gregorianCalendar.set(Integer.parseInt(dat[0]), yearMonth.getMonthValue()-1, day);
            //System.out.println("Quarter: " + simpleDateFormat.format(gregorianCalendar.getTime()));
            return simpleDateFormat.format(gregorianCalendar.getTime());
        }
        return publication;
    }
}
