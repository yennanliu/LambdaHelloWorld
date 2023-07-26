package com.yen.dev;

import java.text.SimpleDateFormat;
import java.util.Date;

public class workSpace1 {
    public static void main(String[] args) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));


        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = new Date();
        System.out.println(formatter2.format(date2));

    }
}
