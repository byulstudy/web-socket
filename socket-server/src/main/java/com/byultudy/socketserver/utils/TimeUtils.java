package com.byultudy.socketserver.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {

    public static long toEpochMilli(LocalDateTime localDateTime) {
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    public static int randomMillSecond(int min, int max) {
        int range = max - min;
        return (int) (Math.random() * range) + min;
    }
}
