package com.yen;

import com.yen.config.DataSourceProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  Unit test
 *      - https://youtu.be/K1OI-S0ET70?t=792
 */
class ListSubscribersTest {

    private ListSubscribers listSubscribers;
    private DataSourceProperties db;

    @BeforeEach
    void setup(){
        String host = "";
        int port = 5432;
        String dbName = "";
        String userName = "";
        String passWord = "";

        db = new DataSourceProperties(host, port, dbName, userName, passWord);
        listSubscribers = new ListSubscribers(db);
    }

    @Test
    public void shouldListAllSubscribers(){

        //ListSubscribers listSubscribers = new ListSubscribers();
        List<String> res = listSubscribers.handleRequest();
        System.out.println("res = " + res);
        Assertions.assertEquals(5, res.size());
    }

    @Test
    public void test1(){

    }

}