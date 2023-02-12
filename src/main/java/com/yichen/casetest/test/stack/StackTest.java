package com.yichen.casetest.test.stack;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/2/12 16:24
 * @describe
 */
@Slf4j
public class StackTest {

    public static void main(String[] args) {
        getAllData();
        StringUtils.divisionLine();
        reverseString();
    }

    private static void reverseString(){
        Stack<Character> stack = new Stack<>();
        stack.push('a');
        stack.push('b');
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<stack.size(); i++){
            builder.append(stack.get(i));
        }
        log.info("reverse stack string {}", builder);
    }

    private static void getAllData(){
        Stack<Integer> stack = new Stack<>();
        stack.push(11);
        stack.push(22);
        for (Integer integer : stack) {
            log.info("==> {}", integer);
        }
        log.info("peek {}", stack.peek());
    }

}
