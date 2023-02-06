package com.yen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *  https://youtu.be/MaHxZEBRcT4?t=265
 */

class HelloLambdaTest{

    @Test
    public void shouldReturnHelloMsg(){

        HelloLambda func = new HelloLambda();
        Assertions.assertEquals("hello! AWS Lambda", func.handleRequest());
    }

}