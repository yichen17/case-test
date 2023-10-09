package com.yichen.casetest.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;

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
     * 损坏型编码转换
     * 各个编码之间的关系理解   https://senitco.github.io/2017/06/06/character-encoding/
     * @throws Exception
     */
    private static void damageEncoding() throws Exception{
        String p = FileUtils.convertEncoding("替换结果", "gbk", "utf8");
        System.out.println(p);
        String q = FileUtils.convertEncoding(p, "utf8", "gbk");
        System.out.println(q);
    }

    /**
     * 编码格式转换
     * @param originStr 原始字符串
     * @param fromEncoding 原始编码格式
     * @param toEncoding 目标编码格式
     * @return 转换后的字符串
     * @throws Exception
     */
    private static String convertEncoding(String originStr, String fromEncoding, String toEncoding) throws Exception{
        return new String(originStr.getBytes(fromEncoding), toEncoding);
    }


    public static void main(String[] args) throws Exception {
        String s = "/opt/homebrew/var/db/redis/a.txt";
        FileReader fr = new FileReader(s);
        System.out.println(fr.getEncoding());
        fr.close();
        
    }



}
