package com.yen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTimeUtil {

    public static String getToday(){
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = new Date();
        //System.out.println(formatter2.format(date2));
        return formatter2.format(date2);
    }
}
