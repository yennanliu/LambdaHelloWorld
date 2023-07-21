package com.yen.Lambda;

// https://www.youtube.com/watch?v=G9nSwSd64RU
// Lambda func name : kinesis-order-lambda-1

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

import java.nio.charset.StandardCharsets;

public class KinesisOrderLambda1 {

    /**
     *  NOTE !!!
     *
     *   if Lambda func read event from Kinesis, then event type is KinesisEvent
     *   if Lambda func read event from S3, then event type is S3Event
     *   ... and so on
     */
    public String handleRequest(KinesisEvent event, Context context){

        System.out.println(">>> context = " + context.toString());

        for (KinesisEvent.KinesisEventRecord record: event.getRecords()){
            // decode data in kinesis stream
            String data = StandardCharsets.UTF_8.decode(record.getKinesis().getData()).toString();
            System.out.println("--> data = " + data);
        }

        return "OK";
    }

}
