package com.yichen.casetest.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/10/9 07:11
 * @describe 文件操作工具类
 */
@Slf4j
public class FileUtils {

    /**
     * 同类功能文件合并，例如两个账户文件合并
     * 1、文件编码格式转换
     * 2、空行剔除跳过
     * 3、去除重复的，有差异的放到最后，特殊表示出来
     * @param filePath1 第一个文件路径
     * @param filePath2 第二个文件路径
     * @return 是否合并成功
     */
    public static boolean mergeFile(String filePath1, String filePath2, String mergeFilePath){
        try {
            FileReader fr1 = new FileReader(filePath1);
            String fr1Encoding = fr1.getEncoding();

        }
        catch (Exception e){
            log.error("文件合并异常{}", e.getMessage(), e);
            return false;
        }
        return true;
    }



    /**
     * 编码格式转换
     * @param originStr 原始字符串
     * @param fromEncoding 原始编码格式
     * @param toEncoding 目标编码格式
     * @return 转换后的字符串
     * @throws Exception
     */
    public static String convertEncoding(String originStr, String fromEncoding, String toEncoding) throws Exception{
        return new String(originStr.getBytes(fromEncoding), toEncoding);
    }


    public static void main(String[] args) throws Exception {
        String s = "/opt/homebrew/var/db/redis/a.txt";
        fileWriteByBinary(s);
//        fileWriteByEncoding(s);

        System.out.println(convertEncoding("狂杀一条街", "gbk", "utf8"));
        System.out.println(convertEncoding("¿ñÉ±Ò»Ìõ½Ö", "utf8", "gbk"));

        
    }

    private static void fileWriteByBinary(String s) throws Exception{
        File file = new File(s);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] data = "狂杀一条街".getBytes();
        fileOutputStream.write(data);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static void fileWriteByEncoding(String s)throws Exception{
        File file = new File(s);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, "gbk");
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
        String content = "狂杀一条街";
        bw.write(content);
        bw.flush();
        bw.close();
        outputStreamWriter.close();
        fos.close();
    }

    private static void fileRead(String s) throws Exception{
        FileReader fr = new FileReader(s);
        System.out.println(fr.getEncoding());
        fr.close();
    }



}
