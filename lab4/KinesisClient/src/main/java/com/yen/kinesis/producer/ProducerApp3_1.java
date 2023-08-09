package com.yen.kinesis.producer;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.yen.constant.KinesisName;
import com.yen.model.RawRecord;
import com.yen.model.StockTrade;
import com.yen.util.DataTimeUtil;

import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

// https://docs.aws.amazon.com/streams/latest/dev/tutorial-stock-data-kplkcl-producer.html

public class ProducerApp3_1 {

    public static void main(String[] args) {

        int n = 1;

        while (true){

            String today = DataTimeUtil.getToday();

            int x = n % 3;

            System.out.println(" n = " + n + " x = " + x);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            RawRecord rawRecord = new RawRecord();
            rawRecord.setId(UUID.randomUUID().toString());
            rawRecord.setTimeStamp(timestamp.getTime());

            switch (n % 3){
                case 0:
                    rawRecord.setEventType("event_a");
                    rawRecord.setEnv("qa");
                    rawRecord.setMachine("2002");
                    rawRecord.setPort(9999);
                    break;
                case 1:
                    rawRecord.setEventType("event_b");
                    rawRecord.setEnv("dev");
                    rawRecord.setMachine("1001");
                    rawRecord.setPort(3306);
                    break;
                case 2:
                    rawRecord.setEventType("event_c");
                    rawRecord.setEnv("dev");
                    rawRecord.setMachine("3003");
                    rawRecord.setPort(22);
                    break;
            }

            System.out.println(">>> record send to kinesis : " + rawRecord.toString());

            AmazonKinesis kinesisClient = ProducerClient.getKinesisClient();
            sendStockTrade(rawRecord, kinesisClient, KinesisName.KINESIS_STREAM_3_1.getValue());
            //sendStockTrade(rawRecord, kinesisClient, KinesisName.KINESIS_STREAM_DEV_1.getValue());

            n += 1;
        }

    }

    private static void sendStockTrade(RawRecord record, AmazonKinesis kinesisClient, String streamName) {

        final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

        byte[] bytes = record.toJsonAsBytes();
        if (bytes == null) {
            logger.warn("Could not get JSON bytes for record");
            return;
        }

        logger.debug("Putting record: " + record.toString());
        PutRecordRequest putRecord = new PutRecordRequest();
        putRecord.setStreamName(streamName);
        // We use the ticker symbol as the partition key, explained in the Supplemental Information section below.
        putRecord.setPartitionKey(record.getEventType());
        putRecord.setData(ByteBuffer.wrap(bytes));
        // NOTE : we need to setup partition key here when send to kinesis
        putRecord.setPartitionKey(UUID.randomUUID().toString());

        try {
            kinesisClient.putRecord(putRecord);
        } catch (AmazonClientException ex) {
            logger.warn("Error sending record to Amazon Kinesis.", ex);
        }
    }

}
