package de.wolfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
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

    public static String calculateDate(String type, String date, String publication) {
        String actualDate = null;
        String desc = null;
        Date publicationDate = null;
        try {
            publicationDate = simpleDateFormat.parse(publication);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();


        // DATE
        switch (type) {
            case "DATE":
                if (date.matches("FUTURE_REF")) {
                    gregorianCalendar.setTime(publicationDate);
                    gregorianCalendar.add(GregorianCalendar.MONTH, 3);
                    //System.out.println("FUTURE_REF: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "FUTURE_REF";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                } else if (date.matches("PAST_REF")) {
                    gregorianCalendar.setTime(publicationDate);
                    gregorianCalendar.add(GregorianCalendar.MONTH, -3);
                    //System.out.println("PAST_REF: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "PAST_REF";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                } else if (date.matches("PRESENT_REF")) {
                    gregorianCalendar.setTime(publicationDate);
                    gregorianCalendar.add(GregorianCalendar.MONTH, 0);
                    //System.out.println("PRESENT_REF: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "PRESENT_REF";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                // adjustable

                else if (date.matches("^\\d{4}$")) {
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
                            yearMonth = YearMonth.of(Integer.parseInt(date), Integer.parseInt(d[1]));
                            day = Integer.parseInt(d[0]);
                    }
                    gregorianCalendar.set(Integer.parseInt(date), yearMonth.getMonthValue() - 1, day);
                    //System.out.println("Year: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Year";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^\\d{4}-\\d{2}$")) {
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
                    gregorianCalendar.set(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]) - 1, day);
                    //System.out.println("Year with Month: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Year with Month";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^\\d{4}-W\\d{2}$")) {
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
                    //System.out.println("Year with Week: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Year with Week";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^\\d{4}-W\\d{2}-WE$")) {
                    String[] dat = date.split("-W");
                    if (weekend.equals("su"))
                        gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 1);
                    else
                        gregorianCalendar.setWeekDate(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), 7); //1 is sunday
                    //System.out.println("Year with Week + Weekend: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Year with Week + Weekend";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^\\d{4}-H\\d$")) {
                    String[] dat = date.split("-H");
                    YearMonth yearMonth = null;
                    int month = 0;
                    int day = 0;
                    if (dat[1].equals("1")) month = 2;
                    else if (dat[1].equals("2")) month = 8;
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
                    gregorianCalendar.set(Integer.parseInt(dat[0]), yearMonth.getMonthValue() - 1, day);
                    //System.out.println("Half year: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Half year";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^\\d{4}-[A-Z]{2}")) {
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
                    gregorianCalendar.set(Integer.parseInt(dat[0]), yearMonth.getMonthValue() - 1, day);
                    //System.out.println("Season: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Season";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^\\d{4}-Q\\d$")) {
                    String[] dat = date.split("-Q");
                    int month = 0;
                    int day = 0;
                    switch (dat[1]) {
                        case "0":
                            dat[0] = String.valueOf(Integer.parseInt(dat[0]) - 1);
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
                            dat[0] = String.valueOf(Integer.parseInt(dat[0]) + 1);
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
                    gregorianCalendar.set(Integer.parseInt(dat[0]), yearMonth.getMonthValue() - 1, day);
                    //System.out.println("Quarter: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Quarter";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }
                break;

            // TIME
            case "TIME":
                if (date.matches("^\\d{4}-\\d{2}-\\d{2}(T[A-Z]{2}$)?")) {
                    String[] dat = date.split("T");
                    try {
                        String _pattern = "yyyy-MM-dd";
                        SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(_pattern);
                        //System.out.println("Time: (" + date + ")" + simpleDateFormat.format(_simpleDateFormat.parse(dat[0])));
                        //return simpleDateFormat.format(_simpleDateFormat.parse(dat[0]));
                        desc = "Time";
                        actualDate = simpleDateFormat.format(_simpleDateFormat.parse(dat[0]));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;

            // DURATION
            case "DURATION":
                gregorianCalendar.setTime(publicationDate);
                if (date.matches("^PT(X|\\d*)?[SMH]$")) {
                    //System.out.println("Duration sec/min/h: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Duration sec/min/h";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^PX[DWMQY]$")) {
                    switch (date.replace("PX", "")) {
                        case "D":
                            gregorianCalendar.add(GregorianCalendar.DAY_OF_YEAR, 3);
                            break;
                        case "W":
                            gregorianCalendar.add(GregorianCalendar.WEEK_OF_YEAR, 3);
                            break;
                        case "M":
                        case "Q":
                            gregorianCalendar.add(GregorianCalendar.MONTH, 3);
                            break;
                        case "Y":
                            gregorianCalendar.add(GregorianCalendar.YEAR, 3);
                            break;
                    }
                    //System.out.println("Inexact Duration: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Inexact Duration";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^P\\d+(D|W|M|Q|Y|DE|CE)$")) {
                    int amount = Integer.parseInt(date.replaceAll("[A-Z]", ""));
                    switch (date.split(String.valueOf(amount))[1]) {
                        case "D":
                            gregorianCalendar.add(GregorianCalendar.DAY_OF_YEAR, amount);
                            break;
                        case "W":
                            gregorianCalendar.add(GregorianCalendar.WEEK_OF_YEAR, amount);
                            break;
                        case "M":
                            gregorianCalendar.add(GregorianCalendar.MONTH, amount);
                            break;
                        case "Q":
                            gregorianCalendar.add(GregorianCalendar.MONTH, amount * 3);
                            break;
                        case "Y":
                            gregorianCalendar.add(GregorianCalendar.YEAR, amount);
                            break;
                        case "DE":
                            gregorianCalendar.add(GregorianCalendar.YEAR, amount * 10);
                            break;
                        case "CE":
                            gregorianCalendar.add(GregorianCalendar.YEAR, amount * 100);
                            break;
                    }
                    //System.out.println("Exact Duration: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Exact Duration";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }
                break;

            //SET
            case "SET":
                gregorianCalendar.setTime(publicationDate);
                if (date.matches("^PT\\d*[SMH]$")) {
                    //System.out.println("Set sec/min/h: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Set sec/min/h";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^P\\d+([DWMQY])$")) {
                    int amount = Integer.parseInt(date.replaceAll("[A-Z]", ""));
                    switch (date.split(String.valueOf(amount))[1]) {
                        case "D":
                            gregorianCalendar.add(GregorianCalendar.DAY_OF_YEAR, amount);
                            break;
                        case "W":
                            gregorianCalendar.add(GregorianCalendar.WEEK_OF_YEAR, amount);
                            break;
                        case "M":
                            gregorianCalendar.add(GregorianCalendar.MONTH, amount);
                            break;
                        case "Q":
                            gregorianCalendar.add(GregorianCalendar.MONTH, amount * 3);
                            break;
                        case "Y":
                            gregorianCalendar.add(GregorianCalendar.YEAR, amount);
                            break;
                    }
                    //System.out.println("Repetition: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Repetition";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^X{4}(-\\d+)?$")) {
                    gregorianCalendar.add(GregorianCalendar.YEAR, 1);
                    //System.out.println("Yearly: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Yearly";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^X{4}-X{2}$")) {
                    gregorianCalendar.add(GregorianCalendar.MONTH, 1);
                    //System.out.println("Monthly: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Monthly";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^X{4}(-X{2}){2}$")) {
                    gregorianCalendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
                    //System.out.println("Daily: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Daily";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }

                else if (date.matches("^X{4}-WX{2}.*")) {
                    gregorianCalendar.add(GregorianCalendar.WEEK_OF_YEAR, 1);
                    //System.out.println("Weekly: (" + date + ")" + simpleDateFormat.format(gregorianCalendar.getTime()));
                    //return simpleDateFormat.format(gregorianCalendar.getTime());
                    desc = "Weekly";
                    actualDate = simpleDateFormat.format(gregorianCalendar.getTime());
                }
                break;
        }
        //System.out.println(type + ": (IN: " + date + " / " + publication + ") " + desc + " ==> " + actualDate);
        return actualDate == null ? publication : actualDate;
    }
}
