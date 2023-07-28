package com.yen.kinesis.Lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

import java.nio.charset.StandardCharsets;

import static com.yen.dev.workSpace1.cleanString;

public class KLogLambda1 {

    /**
     *  NOTE !!!
     *
     *   if Lambda func read event from Kinesis, then event type is KinesisEvent
     *   if Lambda func read event from S3, then event type is S3Event
     *   ... and so on
     */
    public String handleRequest(KinesisEvent event, Context context){

        System.out.println(">>> context = " + context.toString());

        int cnt = 0;
        for (KinesisEvent.KinesisEventRecord record: event.getRecords()){
            // decode data in kinesis stream
            cnt += 1;
            String data = StandardCharsets.UTF_8.decode(record.getKinesis().getData()).toString();
            String cleanStr = cleanString(data);
            String[] dataArray = cleanStr.split(" ");
            if (dataArray[5].equals("DUMMY_EVENT_TYPE_2")){
                System.out.println(data);
            }
        }

        System.out.println("------> process " + cnt + " records");

        return "OK";
    }

}
