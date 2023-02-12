package com.yen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Unit test
 *      - https://youtu.be/K1OI-S0ET70?t=792
 */
class ListSubscribersTest {

    @Test
    public void shouldListAllSubscribers(){

        ListSubscribers listSubscribers = new ListSubscribers();
        List<String> res = listSubscribers.handleRequest();
        System.out.println("res = " + res);
        Assertions.assertEquals(5, res.size());
    }

    @Test
    public void test1(){

    }

}