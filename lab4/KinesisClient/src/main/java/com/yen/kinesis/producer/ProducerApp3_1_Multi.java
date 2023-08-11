package com.yen.kinesis.producer;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.yen.constant.KinesisName;
import com.yen.model.RawRecord;
import com.yen.util.DataTimeUtil;
import com.yen.util.EncodeDecodeUtil;

import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
public class ProducerApp3_1_Multi {


    public static void main(String[] args) {

        MyThread thread1 = new MyThread("thread1");
        MyThread thread2 = new MyThread("thread2");
        MyThread thread3 = new MyThread("thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }


    static class MyThread extends Thread{

        AmazonKinesis kinesisClient = ProducerClient.getKinesisClient();

        @Override
        public void run() {

            // main logic
            //super.run();
            while (true) {
                System.out.println("--> run with thread = " + Thread.currentThread().getName());
                sendData(kinesisClient, KinesisName.KINESIS_STREAM_3_1.getValue());
            }
        }

        // constructor (we can set thread name via this)
        public MyThread(String name){
            super(name);
        }

    }

    private static List<RawRecord> getRecordList(int count){

        List<RawRecord> records = new ArrayList<>();
        for (int i = 0; i < count; i++){
            RawRecord data = getRecord();
            records.add(data);
        }
        return records;
    }

    private static RawRecord getRecord(){

        String today = DataTimeUtil.getToday();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        RawRecord rawRecord = new RawRecord();
        rawRecord.setId(UUID.randomUUID().toString());
        rawRecord.setTimeStamp(timestamp.getTime());

        Random ran = new Random();
        int n = ran.nextInt(100) + 5;

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

        return rawRecord;
    }

    private static void sendData(AmazonKinesis kinesisClient, String streamName) {

        final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

        List<RawRecord> records = getRecordList(500);
        System.out.println("records size = " + records.size() + " thread = " + Thread.currentThread().getName());

        //byte[] bytes = records.toJsonAsBytes();
        byte[] bytes = EncodeDecodeUtil.toJsonAsBytes(records);
        if (bytes == null) {
            logger.warn("Could not get JSON bytes for record");
            return;
        }

        PutRecordRequest putRecord = new PutRecordRequest();
        putRecord.setStreamName(streamName);
        // We use the ticker symbol as the partition key, explained in the Supplemental Information section below.
        putRecord.setPartitionKey(UUID.randomUUID().toString());
        putRecord.setData(ByteBuffer.wrap(bytes));
        // NOTE : we need to setup partition key here when send to kinesis
        putRecord.setPartitionKey(UUID.randomUUID().toString());

        try {
            PutRecordResult result = kinesisClient.putRecord(putRecord);
            System.out.println(">>> Put record result = " + result);
//            System.out.println(">>> Failed record count = " + result.getFailedRecordCount());
        } catch (AmazonClientException ex) {
            logger.warn("Error sending record to Amazon Kinesis.", ex);
        }
    }

}
