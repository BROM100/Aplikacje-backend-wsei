package com.company;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class Main5 {

    public static void main(String[] args) throws IOException {


        Date now = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
        System.out.println(now);
        TimeZone.setDefault( TimeZone.getTimeZone("UTC"));
        System.out.println(now);
    }
}