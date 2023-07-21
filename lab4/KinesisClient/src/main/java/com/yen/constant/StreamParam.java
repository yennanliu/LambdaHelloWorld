package com.yen.constant;

public enum StreamParam {

    KINESIS_STREAM_NAME("KINESIS_STREAM_NAME", "my_kinesis_stream_1");

    private String name;
    private String value;

    StreamParam(String name, String value){
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
