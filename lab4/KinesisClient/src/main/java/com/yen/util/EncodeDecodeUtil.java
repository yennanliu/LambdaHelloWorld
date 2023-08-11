package com.yen.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class EncodeDecodeUtil {

    public static <T> byte[] toJsonAsBytes(List<T> jsonArray) {

        final ObjectMapper JSON = new ObjectMapper();
        try {
            return JSON.writeValueAsBytes(jsonArray);
        } catch (IOException e) {
            return null;
        }

    }

}
