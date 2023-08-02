package com.yen.model;

// https://docs.aws.amazon.com/streams/latest/dev/tutorial-stock-data-kplkcl-producer.html

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class StockTrade {

    private String tickerSymbol;
    private String tradeType;
    private Double price;
    private Integer quantity;
    private Integer id;

    public StockTrade() {
    }
    
    public StockTrade(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // toString


    @Override
    public String toString() {
        return "StockTrade{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", id=" + id +
                '}';
    }

    // https://github.com/aws-samples/amazon-kinesis-learning/blob/master/src/main/java/com/amazonaws/services/kinesis/samples/stocktrades/model/StockTrade.java
    public byte[] toJsonAsBytes() {
        final ObjectMapper JSON = new ObjectMapper();
        try {
            return JSON.writeValueAsBytes(this);
        } catch (IOException e) {
            return null;
        }
    }
}
