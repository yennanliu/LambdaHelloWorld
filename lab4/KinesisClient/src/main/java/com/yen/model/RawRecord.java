package com.yen.model;

public class RawRecord {

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

    @Override
    public String toString() {
        return "Record{" +
                "eventType='" + eventType + '\'' +
                ", id='" + id + '\'' +
                ", machine='" + machine + '\'' +
                ", port=" + port +
                ", env='" + env + '\'' +
                '}';
    }

}
