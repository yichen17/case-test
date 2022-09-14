package com.yichen.casetest.config.position;

import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/14 14:08
 * @describe 手动复制逐年的数据，统一分析记录
 *  可用 已处理   同一个code可能有多个不同名字，现在实现取最新的
 */
@Slf4j
public class TotalDataAnalyseStore {

    public static void main(String[] args) {
        Map<String, Map<String, String>> resultMap = new HashMap<>(8192);
        String year = "";
        String code = "";
        String name = "";
        boolean flag = false;
        try {
            FileInputStream fileInputStream = new FileInputStream("G:\\base\\case-test\\src\\main\\resources\\config\\历年省市区code.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String str = null;
            while((str = bufferedReader.readLine()) != null)
            {
                flag = false;
                if (str.indexOf("===") == 0){
                    year = str.substring(3,7);
                    continue;
                }
                String[] items = str.replaceAll("\t", " ").trim().split("\\s+");
                if (items.length == 1){
                    name = items[0].trim();
                    flag = true;
                    log.warn("切分长度为1，级联操作 code {} name {}", code, name);
                }
                else if (items.length != 2){
                    log.warn("长度切分不为2 {} {}", str, year);
                    continue;
                }
                else {
                    code = items[0].trim();
                    name = items[1].trim();
                }
//                log.info("{} {} {}", items[0], items[1], year);
                Map<String, String> codeMap = resultMap.get(code);
                if (Objects.isNull(codeMap)){
                    codeMap = new HashMap<>(8);
                    codeMap.put("value", name);
                    codeMap.put("includeYear", year);
                    codeMap.put("conflictYear", "");
                    codeMap.put("conflictData", "");
                    resultMap.put(code, codeMap);
                }
                else if(codeMap.get("value").contains(name)) {
                    codeMap.put("includeYear", codeMap.get("includeYear") + "," + year);
                }
                else if (flag){
                    log.warn("级联操作忽略冲突 {} {}", code ,year);
                }
                else {
                    log.warn("code {} 地址出现冲突 {}", code, year);
                    codeMap.put("conflictYear", codeMap.get("conflictYear") + "," + year);
                    codeMap.put("conflictData", codeMap.get("conflictData") + "," + name);
                }
            }
            fileInputStream.close();
            bufferedReader.close();
        }
        catch (IOException e){
            log.error("执行出错 {}", e.getMessage(), e);
        }

        try {
            FileWriter fileWriter = new FileWriter("F:\\test_store\\codePosition\\analyseData.txt");
            fileWriter.append(FastJsonUtils.toJson(resultMap));
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            log.error("写入文件出错  {}", e.getMessage(), e);
        }

        StringBuilder keyValueData = new StringBuilder();
        resultMap.forEach((key, value) -> keyValueData.append(key).append("=")
                .append(value.get("value")).append("\r\n"));

        try {
            FileWriter fileWriter = new FileWriter("F:\\test_store\\codePosition\\keyValueData.properties");
            fileWriter.append(keyValueData.toString());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            log.error("写入文件出错  {}", e.getMessage(), e);
        }

    }

}
