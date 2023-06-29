package com.yichen.casetest.test.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/6/27 17:17
 * @describe 数打印
 * 参考文章：
 *      =>   https://www.baeldung.com/java-print-binary-tree-diagram
 *      =>   https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
 *      =>   https://www.techiedelight.com/c-program-print-binary-tree/
 */
@Slf4j
public class BinaryTreePrint {

    private static final String left = "┍";
    private static final String right =  "┑";
    private static final String link = "-";

    public static void main(String[] args) {
        System.out.println(left + " 1 " + right);
        System.out.println("2   3");
    }





















}
