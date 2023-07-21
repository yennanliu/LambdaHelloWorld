package com.yen;

import com.yen.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {

        List<String> productList = new ArrayList<String>();
        populateProductList(productList);
        List<Order> orders = getOrderList(500, productList);
        System.out.println("productList size = " + productList.size());
        System.out.println("orders size = " + orders.size());

        // step : send event to kinesis

        // 1. get client

        // 2. PutRecordRequest

        // 3. putRecord or putRecords ( 1 record or 500 records in single API call (batch) )

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

}