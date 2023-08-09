package com.yen.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigInteger;

public class RawRecord {

    private Long timeStamp;

    private String eventType;
    private String id;
    private String machine;
    private Integer port;
    private String env;

    public RawRecord() {
    }

    public RawRecord(String eventType, String id, String machine, Integer port, String env) {
        this.eventType = eventType;
        this.id = id;
        this.machine = machine;
        this.port = port;
        this.env = env;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "RawRecord{" +
                "timeStamp=" + timeStamp +
                ", eventType='" + eventType + '\'' +
                ", id='" + id + '\'' +
                ", machine='" + machine + '\'' +
                ", port=" + port +
                ", env='" + env + '\'' +
                '}';
    }

    public byte[] toJsonAsBytes() {
        final ObjectMapper JSON = new ObjectMapper();
        try {
            return JSON.writeValueAsBytes(this);
        } catch (IOException e) {
            return null;
        }
    }

}
