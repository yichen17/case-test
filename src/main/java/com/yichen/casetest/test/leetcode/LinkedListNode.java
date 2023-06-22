package com.yichen.casetest.test.leetcode;

import java.util.List;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/22 11:07
 * @describe
 */
 class LinkedListNode {
    int val;
    LinkedListNode next;
    LinkedListNode() {}
    LinkedListNode(int val) { this.val = val; }
    protected static LinkedListNode preLink(int val, LinkedListNode pre) {
        LinkedListNode now = new LinkedListNode(val);
        pre.next = now;
        return now;
    }

    protected static LinkedListNode buildListedList(Integer[] params){
        if (Objects.isNull(params) || params.length == 0){
            return null;
        }
        LinkedListNode root = new LinkedListNode(params[0]);
        LinkedListNode pos = root;
        for (int i=1; i<params.length; i++){
            pos = preLink(params[i], pos);
        }
        return root;
    }

    protected static String printPath(LinkedListNode root){
        StringBuilder builder = new StringBuilder();
        LinkedListNode pos = root;
        while (pos != null){
            builder.append(pos.val).append(" => ");
            pos = pos.next;
        }
        builder.delete(builder.length()-4, builder.length());
        return builder.toString();
    }

}
