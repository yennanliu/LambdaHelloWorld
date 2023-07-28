package com.yen.cloudwatch;

// https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class CWatchConsumer1<IntegerRecord> implements RequestHandler<IntegerRecord, Integer> {

    @Override
    public Integer handleRequest(IntegerRecord integerRecord, Context context) {

//        LambdaLogger logger = context.getLogger();
//        logger.log("String found: " + event.message());
//        return event.x() + event.y();
//
//        byte[] bytes = new byte[10];
//        ByteBuffer buffer = ByteBuffer.wrap(bytes);
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(integerRecord);

        // String data = StandardCharsets.UTF_8.decode(record.getKinesis().getData()).toString();
        System.out.println("--> context = " + context);
        System.out.println("--> integerRecord = " + integerRecord);
        return 0;
    }

}
