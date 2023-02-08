package com.yen;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 *  Main class that we run on the AWS Lambda
 *
 *      - input: String
 *      - output: String
 *
 *      https://youtu.be/kyWllXOGMWQ?t=282
 */
public class SimpleHandler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String input, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("Function = " + context.getFunctionName());
        return input.toUpperCase();
    }

}
