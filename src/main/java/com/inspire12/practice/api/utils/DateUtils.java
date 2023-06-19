package com.inspire12.practice.api.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static String convertUtcDate(LocalDateTime time) {
        time = time.minus(9, ChronoUnit.HOURS);
        ZonedDateTime ldtZoned = time.atZone(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        return ldtZoned.format(formatter);
    }

    public static String convertDbDate(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(formatter);
    }

    public static LocalDateTime calculateLocalTime(LocalDateTime time) {
        time = time.plus(9, ChronoUnit.HOURS);
        return time;
    }

    public static LocalDate localDateConvertToString(String strDate, String format) {
        //
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        return localDate;

    }

    public static long localDateTimeMillisConvertToStringYYYYMMddHHmmssS(String strDate, String format) {
        //
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime localDateTime = LocalDateTime.parse(strDate, formatter);
            ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            return zdt.toInstant().toEpochMilli();
        } catch (Exception e) {
            return ZonedDateTime.now().toInstant().toEpochMilli();
        }
    }

    public static long localDateMillisConvertToString(String strDate, String format) {
        //
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();

    }

    public static long localDateMillisConvertToStringArray(String strDate, String[] formats) {
        //
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        for (String format : formats) {
            builder.appendOptional(DateTimeFormatter.ofPattern(format));
        }
        DateTimeFormatter formatter = builder.toFormatter();
        LocalDateTime localDateTime = LocalDateTime.parse(strDate, formatter);
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
//        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return zdt.toInstant().toEpochMilli();

    }


    public static String oneDayAgoConvertToStringYYYYMMDD() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minus(1, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return localDate.format(formatter);
    }

    public static String eightDayAgoConvertToStringYYYYMMDD() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minus(8, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return localDate.format(formatter);
    }


}
