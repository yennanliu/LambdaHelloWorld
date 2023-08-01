package com.yen.kinesis.consumer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/** consume
 *
 * Lambda func : ConsumerLambda3
 * Kinesis name : my_kinesis_stream_3
 *
 */
public class ConsumerLambda3 {

    String BUCKET_NAME = "kinesis-lambda-bucket-test-1";
    String S3_OBJECT_KEY = "my_kinesis_stream_3";

    public String handleRequest(KinesisEvent event, Context context){

        System.out.println(">>> context = " + context.toString());
        System.out.println(">>> event = " + event.toString());

        AmazonS3 s3Client = AmazonS3Client.builder().build();

        int cnt = 0;

        for (KinesisEvent.KinesisEventRecord record: event.getRecords()){
            // decode data in kinesis stream
            String data = StandardCharsets.UTF_8.decode(record.getKinesis().getData()).toString();
            System.out.println("--> data = " + data);
            cnt += 1;
            //Boolean writeS3Failure = false;

            List<String> data_array = new ArrayList<>();

            try{
                // write to S3
                // https://medium.com/@olayiwolafunsho/serverless-with-aws-lambda-and-java-generate-an-excel-file-and-upload-to-s3-a21005a015a2
                // https://docs.aws.amazon.com/AmazonS3/latest/userguide/olap-writing-lambda.html
                
                long unixtime = System.currentTimeMillis() / 1000L;
                String filename = Long.toString(unixtime) + ".txt";

                System.out.println(">>> write to s3 start");
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(data.toString().length());
                InputStream inputStream = new ByteArrayInputStream(data.toString().getBytes());
                PutObjectRequest putRequest = new PutObjectRequest(BUCKET_NAME, S3_OBJECT_KEY + "/" + filename, inputStream, metadata);
                s3Client.putObject(putRequest);
            }catch (Exception e){
                //writeS3Failure = true;
                System.out.println(">>> write to S3 failed : " + e.toString());
            }


        }

        System.out.println("--> process " + cnt + " kinesis records");

        return "OK";
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() / 1000L);
    }

}
