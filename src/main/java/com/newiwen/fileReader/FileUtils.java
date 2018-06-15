package com.newiwen.fileReader;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by newiwen 2018/6/15
 */
public class FileUtils {

    public static Map<String, String> getMatchStringFileNames(String filePath, String matchStr) throws Exception {

        Map<String, String> resultMap = new LinkedHashMap<>();
        getMatchFileContentFileNames(resultMap, filePath, matchStr);
        return resultMap;
    }

    private static void getMatchFileContentFileNames(Map<String, String> resultMap, String filePath, String matchStr) throws Exception {
        Optional<String> fileDirOption = Optional.of(filePath);
        if (!fileDirOption.isPresent())
            throw new FileNotFoundException("找不到目录或文件!");

        File nowFile = new File(filePath);
        File[] files = null;

        boolean isDir = nowFile.isDirectory();//判断是否是目录

        if (isDir) {
            files = nowFile.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    getMatchFileContentFileNames(resultMap, file.getAbsolutePath(), matchStr);
                } else {
                    if(file.getName().indexOf("txt") != -1){
                        fileMacth(resultMap, file, matchStr);
                    }else{
                        System.out.println(file.getName());
                    }
                }
            }
        } else {
            if(nowFile.getName().indexOf("txt") != -1){
                fileMacth(resultMap, nowFile, matchStr);
            }else{
                System.out.println(nowFile.getName());
            }

        }
    }

    private static void fileMacth(Map<String, String> returnMap, File file, String matchStr) throws Exception {

        InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(inputStream);
        String lineText = null;
        int lineNum = 1;
        while ((lineText = reader.readLine()) != null) {
            if (lineText.indexOf(matchStr) != -1) {
                String desc = "第" + lineNum + "行出现！";
                returnMap.put(lineNum+":"+file.getAbsolutePath(), lineText);
            }
            lineNum++;
        }
    }
}
