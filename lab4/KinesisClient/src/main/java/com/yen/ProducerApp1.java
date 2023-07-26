package com.yen;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;

import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.services.kinesis.model.PutRecordsResultEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yen.constant.KinesisName;
import com.yen.producer.ProducerClient;
import com.yen.model.Order;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProducerApp1 {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(">>> ProducerApp1 start");

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
        populateProductList(productList);
        /**
         *  max record for one PutRecordsRequest <= 500,
         *  will face error if > 500
         *  -> error : at 'records' failed to satisfy constraint: Member must have length less than or equal to 500
         */
        List<Order> orders = getOrderList(500, productList);
        System.out.println("productList size = " + productList.size());
        System.out.println("orders size = " + orders.size());

        // 2. PutRecordRequest
        List<PutRecordsRequestEntry> requestEntryList = getRecordRequestList(orders);
        PutRecordsRequest recordRequest = new PutRecordsRequest();
        recordRequest.setStreamName(KinesisName.KINESIS_STREAM_1.getValue());
        recordRequest.setRecords(requestEntryList);

        // 3. putRecord or putRecords ( 1 record or 500 records in single API call (batch) )
        PutRecordsResult results = kinesisClient.putRecords(recordRequest);
        System.out.println(">>> Put record result = " + results);
        System.out.println(">>> Failed record count = " + results.getFailedRecordCount());

        // 4. retry mechanism
        int failedRecords = results.getFailedRecordCount();
        for (PutRecordsResultEntry result : results.getRecords()){
            if (result.getErrorCode() != null){
                // this record failed to send, need to retry
            }
        }

    }

    private static void populateProductList(List<String> productList){
        productList.add("car");
        productList.add("glass");
        productList.add("hat");
    }

    private static List<Order> getOrderList(int count, List<String> productList){
        Random random = new Random();
        List<Order> orders = new ArrayList<Order>();
        for (int i = 0; i < count; i++){
            Order order = new Order();
            order.setOrderId(random.nextInt());
            order.setProduct(productList.get(random.nextInt(productList.size())));
            order.setQuantity(random.nextInt());
            orders.add(order);
        }
        return orders;
    }

    private static List<PutRecordsRequestEntry> getRecordRequestList(List<Order> orders){
        List<PutRecordsRequestEntry> records = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (Order order: orders){
            PutRecordsRequestEntry responseEntry = new PutRecordsRequestEntry();
            responseEntry.setData(ByteBuffer.wrap(gson.toJson(order).getBytes()));
            responseEntry.setPartitionKey(UUID.randomUUID().toString());
            records.add(responseEntry);
        }
        return records;
    }

}