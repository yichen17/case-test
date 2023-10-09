package com.yichen.casetest.test.endecrypt;

import com.yichen.casetest.utils.StringUtils;

import java.io.FileWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/8 14:02
 * @describe 编码验证
 */
class CaseVerity {

    public static void main(String[] args) throws Exception {
        char1Show();
        StringUtils.divisionLine();
//        transfer();
    }

    /**
     * 编码转换  latin1 到 gbk
     * @throws UnsupportedEncodingException
     */
    private static void transfer() throws UnsupportedEncodingException {
        String test = "AteyeBeanPostProcessor³õÊ¼»¯Íê³É£¬appname=welkin,Ó¦ÓÃÓÐ³Ö¾Ã»¯¿ª¹ØÐÅÏ¢^R";
        System.out.println(new String(test.getBytes("latin1"), "gbk"));

        test = "ÓÐÓ¦ÓÃ¼¶³Ö¾Ã»¯¿ª¹Ø";
        System.out.println(new String(test.getBytes("latin1"), "gb18030"));
        
        String s = "成功设置了";
        System.out.println(new String(s.getBytes("gbk"), "latin1"));

        String r = "³É¹¦ÉèÖÃÁË";
        String p = "³É¹¦ÉèÖÃÁË";
        System.out.println(r.equals(p));

        System.out.println(new String(r.getBytes("latin1"), "gbk"));


    }


    /**
     * char(1) 验证
     * 直接打印和A好像。。。
     * work machine  cat不显示，vi会显示 ^A
     * local   cat末尾会多一个 %   vi显示同上
     * @throws Exception
     */
    private static void char1Show() throws Exception{

//        System.out.println("char 1 =>" + (char)1);
//        System.out.println("char 65 =>" + (char)65);
//        System.out.println((int)'A');
//        char p = (char)1;
//        char q = (char)65;
//        System.out.printf("%s - %s%n", p, q);
//        System.out.println(p == q);



        FileWriter file = new FileWriter("/opt/homebrew/var/db/redis/a.txt");
//        file.write("唯我独狂");

        file.write("shanliang");
        file.write((char)1);
        file.write("唯我独狂");
        file.write((char)1);
        file.write("6666");

        file.flush();
        file.close();
    }


}
