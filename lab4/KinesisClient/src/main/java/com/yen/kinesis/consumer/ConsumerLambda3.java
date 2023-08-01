package com.yen.kinesis.consumer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

import java.nio.charset.StandardCharsets;

/** consume Kinesis : my_kinesis_stream_3 */
public class ConsumerLambda3 {

    public String handleRequest(KinesisEvent event, Context context){

        System.out.println(">>> context = " + context.toString());
        System.out.println(">>> event = " + event.toString());

        int cnt = 0;

        for (KinesisEvent.KinesisEventRecord record: event.getRecords()){
            // decode data in kinesis stream
            String data = StandardCharsets.UTF_8.decode(record.getKinesis().getData()).toString();
            System.out.println("--> data = " + data);
            cnt += 1;
        }

        System.out.println("--> process " + cnt + " kinesis records");

        return "OK";
    }

}
