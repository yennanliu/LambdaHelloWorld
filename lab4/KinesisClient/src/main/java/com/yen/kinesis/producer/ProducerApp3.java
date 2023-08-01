package com.yen.kinesis.producer;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yen.constant.KinesisName;
import com.yen.kinesis.producer.ProducerClient;
import com.yen.util.DataTimeUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProducerApp3 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(">>> ProducerApp3 start");

        // Step : send event to kinesis

        // 1. get client
        AmazonKinesis kinesisClient = ProducerClient.getKinesisClient();

        // keep sending data
        while (true){
            System.out.println(">>> Send data start");
            sendData(kinesisClient);
            // sleep 3 sec
            System.out.println(">>> Send data end");
            Thread.sleep(3 * 1000);
        }

    }

    private static void sendData(AmazonKinesis kinesisClient){

        List<String> productList = new ArrayList<String>();
        List<String> records = getOrderList(500);
        System.out.println("records size = " + records.size());

        // 2. PutRecordRequest
        List<PutRecordsRequestEntry> requestEntryList = getRecordRequestList(records);
        PutRecordsRequest recordRequest = new PutRecordsRequest();
        recordRequest.setStreamName(KinesisName.KINESIS_STREAM_3.getValue());
        recordRequest.setRecords(requestEntryList);

        // 3. putRecord or putRecords ( 1 record or 500 records in single API call (batch) )
        PutRecordsResult results = kinesisClient.putRecords(recordRequest);
        System.out.println(">>> Put record result = " + results);
        System.out.println(">>> Failed record count = " + results.getFailedRecordCount());

    }

    private static List<String> getOrderList(int count){
        List<String> records = new ArrayList<>();
        for (int i = 0; i < count; i++){
            String data = getLog();
            records.add(data);
        }
        return records;
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


    private static List<PutRecordsRequestEntry> getRecordRequestList(List<String> inputs){
        List<PutRecordsRequestEntry> records = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (String input: inputs){
            System.out.println(">>> input = " + input);
            PutRecordsRequestEntry responseEntry = new PutRecordsRequestEntry();
            responseEntry.setData(ByteBuffer.wrap(gson.toJson(input).getBytes()));
            responseEntry.setPartitionKey(UUID.randomUUID().toString());
            records.add(responseEntry);
        }
        return records;
    }

}

