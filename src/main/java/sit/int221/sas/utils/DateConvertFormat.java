package sit.int221.sas.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConvertFormat {
    public static String sqlToJson(String date){
        if(date==null) return null;
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Date dateConvert = Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
        return DateTimeFormatter.ISO_INSTANT.format(dateConvert.toInstant());

    }
    public static String jsonToSql(String date){
        if(date==null) return null;
        Instant instant = Instant.parse(date);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
