package com.yen.dev;

import java.text.SimpleDateFormat;
import java.util.Date;

public class workSpace1 {
    public static void main(String[] args) {

//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        System.out.println(formatter.format(date));
//
//
//        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date2 = new Date();
//        System.out.println(formatter2.format(date2));

        String mystr1 = "2023-07-26 10:37:50 [my-group-2]  INFO - DUMMY_EVENT_TYPE_2 id=20002, machine=24525-ve24r52-efere-43r43ce, port=9999, env=qa";
        String cleanStr = cleanString(mystr1);
        String[] _list1 = cleanStr.split(" ");
        String eventType = "";
//        for (String x : _list1){
//            System.out.println(x);
//        }
//
        if (_list1[5].equals("DUMMY_EVENT_TYPE_2")){
            System.out.println(mystr1);
        }

        //System.out.println(_list1[5]);
    }

    public static String cleanString(String input){

        // https://stackoverflow.com/questions/2932392/java-how-to-replace-2-or-more-spaces-with-single-space-in-string-and-delete-lead#:~:text=You%20just%20need%20a%3A,quicker%20as%20someone%20pointed%20out).
        try{
            String _input = input.replaceAll("\\s{2,}", " ").trim();
            return _input;
        }catch (Exception e){
            System.out.println("clean string failed : " + e);
            return input;
        }
    }

}
