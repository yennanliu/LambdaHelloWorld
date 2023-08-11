package com.yen.util;

import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KinesisUtil {

    public static <T> List<PutRecordsRequestEntry> getRecordRequestList(List<T> inputs){

        List<PutRecordsRequestEntry> records = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (T input: inputs){
            //System.out.println(">>> input = " + input);
            PutRecordsRequestEntry responseEntry = new PutRecordsRequestEntry();
            responseEntry.setData(ByteBuffer.wrap(gson.toJson(input).getBytes()));
            responseEntry.setPartitionKey(UUID.randomUUID().toString());
            records.add(responseEntry);
        }
        return records;
    }

}
