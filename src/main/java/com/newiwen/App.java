package com.newiwen;

import com.newiwen.fileReader.FileUtils;
import org.junit.Test;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Test
    public void fileSearch() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\xinfuli\\vesion-info";

        Map<String,String> map = FileUtils.getMatchStringFileNames(path,"题");

        map.forEach((key,value)-> System.out.println("filePath:"+key+",  line:"+value));
    }
}
