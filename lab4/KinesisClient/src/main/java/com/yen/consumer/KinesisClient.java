package com.yen.consumer;

// https://youtu.be/05yauiKMWBM?t=196

import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;

public class KinesisClient {

    // TODO : change below
    public static final String AWS_ACCESS_KEY = "";
    public static final String AWS_SECRET_KEY = "";

    static {
        System.setProperty("AWS_ACCESS_KEY", AWS_ACCESS_KEY);
        System.setProperty("AWS_SECRET_KEY", AWS_SECRET_KEY);
    }

    public static AmazonKinesis getKinesisClient(){

        return AmazonKinesisClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

}
