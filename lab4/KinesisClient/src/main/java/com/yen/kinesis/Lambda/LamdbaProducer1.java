package com.yen.kinesis.Lambda;

import com.yen.util.DataTimeUtil;
import java.util.Random;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

/** Lambda send log to cloud watch
 *
 *  Lambda func name : LamdbaProducer-send-log-to-cloudwatch-1
 */

public class LamdbaProducer1 implements RequestHandler<List<Integer>, Integer>{

//    public String handleRequest() throws InterruptedException {
//        while (true){
//            System.out.println("--> send log to cloudwatch");
//            System.out.println(getLog());
//            Thread.sleep(3);
//        }
//
//    }

    @Override
    public Integer handleRequest(List<Integer> integers, Context context) {

           while (true){
               try {
                   System.out.println("--> send log to cloudwatch");
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
                return today +  " [my-group-1] " + " INFO - DUMMY_EVENT_TYPE_1 id=1001, machine=fwerfe-ve24r52-42r5423fervr-43r43ce, port=3306, env=dev";
            case 0:
                return today +  " [my-group-2] " + " INFO - DUMMY_EVENT_TYPE_2 id=20002, machine=24525-ve24r52-efere-43r43ce, port=9999, env=qa";
        }

        return today +  " default log";
    }

}
