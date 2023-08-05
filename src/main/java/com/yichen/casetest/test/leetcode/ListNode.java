package com.yichen.casetest.test.leetcode;

import java.util.Objects;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/22 11:07
 * @describe
 */
 class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    protected static ListNode preLink(int val, ListNode pre) {
        ListNode now = new ListNode(val);
        pre.next = now;
        return now;
    }

    protected static ListNode buildListedList(Integer[] params){
        if (Objects.isNull(params) || params.length == 0){
            return null;
        }
        ListNode root = new ListNode(params[0]);
        ListNode pos = root;
        for (int i=1; i<params.length; i++){
            pos = preLink(params[i], pos);
        }
        return root;
    }

    protected static String printPath(ListNode root){
        if (Objects.isNull(root)){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        ListNode pos = root;
        while (pos != null){
            builder.append(pos.val).append(" => ");
            pos = pos.next;
        }
        builder.delete(builder.length()-4, builder.length());
        return builder.toString();
    }

    protected static boolean checkOrder(ListNode root, boolean ascend){
        if (ascend){
            int pre = Integer.MIN_VALUE;
            while (root != null){
                if (pre > root.val){
                    return false;
                }
                pre = root.val;
                root = root.next;
            }
            return true;
        }
        int pre = Integer.MAX_VALUE;
        while (root != null){
            if (pre < root.val){
                return false;
            }
            pre = root.val;
            root = root.next;
        }
        return true;
    }

}
