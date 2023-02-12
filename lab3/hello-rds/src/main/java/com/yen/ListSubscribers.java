package com.yen;

//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Main class for AWS Lambda app that query RDS
 *
 *      - https://youtu.be/K1OI-S0ET70?t=307
 *      - RequestHandler<String, List<String>>
 *         - input : String
 *         - output : List<String>
 *
 */
//public class ListSubscribers implements RequestHandler<String, List<String>> {
//
//    @Override
//    public List<String> handleRequest(String s, Context context) {
//        return null;
//    }
//
//}

// a simple version
public class ListSubscribers {

    private List<String> subscribers = new ArrayList<>();

    public List<String> handleRequest() {

        // DB conf
        // TODO : fix it
        String jdbcURL = "jdbc:postgresql://xxx.yyy";
        String userName = "";
        String passWord = "";

        // get DB connection
        try(Connection conn = DriverManager.getConnection(jdbcURL, userName, passWord)){

            if (!conn.isValid(0)){
                System.out.println("Unable to connect to DB : " + jdbcURL);
                System.exit(0);
            }

            System.out.println("DB connection OK");
            String query = "SELECT * FROM subscribers";
            PreparedStatement selectStatement = conn.prepareStatement(query);
            ResultSet rs = selectStatement.executeQuery();

            while(rs.next()){
                String email = rs.getString("email");
                System.out.println("email = " + email);
                subscribers.add(email);
            }

        }catch(SQLException e){
            System.out.println("DB connection failed");
            throw new RuntimeException(e);
        }

        return subscribers;
    }

}

