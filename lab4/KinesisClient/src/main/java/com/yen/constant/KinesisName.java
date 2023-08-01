package com.yen.constant;

public enum KinesisName {

    KINESIS_STREAM_1("KINESIS_STREAM_1", "my_kinesis_stream_1"),
    KINESIS_STREAM_2("KINESIS_STREAM_2", "my_kinesis_stream_2"),
    KINESIS_STREAM_3("KINESIS_STREAM_3", "my_kinesis_stream_3");

    private String name;
    private String value;

    KinesisName(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public String getValue(){
        return value;
    }

}
