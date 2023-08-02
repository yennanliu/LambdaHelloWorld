package com.yen.kinesis.producer;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.yen.constant.KinesisName;
import com.yen.model.StockTrade;
import jdk.nashorn.internal.codegen.ApplySpecialization;

import java.lang.invoke.MethodHandles;
import java.nio.ByteBuffer;
import java.util.UUID;


// https://docs.aws.amazon.com/streams/latest/dev/tutorial-stock-data-kplkcl-producer.html
public class ProducerApp4 {

    public static void main(String[] args) {

        while (true){

            StockTrade trade = new StockTrade();
            trade.setId(123);
            trade.setQuantity(100);
            trade.setTradeType("XYZ");
            trade.setPrice(999.34);

            System.out.println(">>> record send to kinesis : " + trade.toString());

            AmazonKinesis kinesisClient = ProducerClient.getKinesisClient();
            sendStockTrade(trade, kinesisClient, KinesisName.KINESIS_STREAM_3.getValue());
        }

    }

    private static void sendStockTrade(StockTrade trade, AmazonKinesis kinesisClient, String streamName) {

        final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
        
        byte[] bytes = trade.toJsonAsBytes();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        byte[] bytes = ByteBuffer.wrap(gson.toJson(trade).getBytes()).array();
        // The bytes could be null if there is an issue with the JSON serialization by the Jackson JSON library.
        if (bytes == null) {
            logger.warn("Could not get JSON bytes for stock trade");
            return;
        }

        logger.debug("Putting trade: " + trade.toString());
        PutRecordRequest putRecord = new PutRecordRequest();
        putRecord.setStreamName(streamName);
        // We use the ticker symbol as the partition key, explained in the Supplemental Information section below.
        putRecord.setPartitionKey(trade.getTickerSymbol());
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
