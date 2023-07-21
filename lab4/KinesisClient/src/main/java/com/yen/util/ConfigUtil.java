package com.yen.util;

// https://stackoverflow.com/questions/16273174/how-to-read-a-configuration-file-in-java

// https://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/
// https://mkyong.com/java/java-read-a-file-from-resources-folder/#:~:text=In%20Java%2C%20we%20can%20use,content%20InputStream%20is%20%3D%20getClass().

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigUtil {

    private Properties loadConfig(String fileName){
        String res = "";
        Properties pros = new Properties();
        try(FileInputStream fis = new FileInputStream(fileName)){
            pros.load(fis);
        }catch (FileNotFoundException fileNotFoundException){
            System.out.println("file not found : " + fileNotFoundException.getStackTrace());
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println("Exception : " + e.getStackTrace());
        }

        return pros;
    }

}
