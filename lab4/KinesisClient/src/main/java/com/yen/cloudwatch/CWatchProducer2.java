package com.yen.cloudwatch;

import com.yen.util.DataTimeUtil;

import java.util.Random;

public class CWatchProducer2 {

    public String handleRequest(){

        while (true){
            try {
                //System.out.println("--> send log to cloudwatch");
                System.out.println(getLog());
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


    private static String getLog(){

        int MAX_VAL = 100;
        int MIN_VAL = 0;
        Random rn = new Random();
        int n = rn.nextInt((MAX_VAL - MIN_VAL) + 1);

        String today = DataTimeUtil.getToday();

        int x = n % 2;

        switch (x){
            case 1:
                return today +  " [my-group-1] " + " INFO - {\"event\": \"my_event_1\", \"id\": 777,  \"my_val\": true, \"uuid\": \"43fefge-4c2d-4729-876c-45vefv\"}";
            case 0:
                return today +  " [my-group-2] " + " INFO - {\"event\": \"my_event_2\", \"id\": 9999,  \"my_val\": false, \"uuid\": \"vre-345-dfvd-ddfv-ddf\"}";
        }

        return today +  " default log";
    }
    
}
