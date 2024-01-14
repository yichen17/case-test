package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/22 11:07
 * @describe
 */
@Slf4j
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
        return buildSortedListedList(params, null);
    }

    protected static ListNode buildListedList(Integer[] params, Boolean descend){
        return buildSortedListedList(params, descend);
    }

    protected static ListNode buildSortedListedList(Integer[] params, Boolean descend){
        if (Objects.isNull(params) || params.length == 0){
            return null;
        }
        if (Boolean.TRUE.equals(descend)){
            Arrays.sort(params, (p,q)->(q-p));
        }
        else if (Boolean.FALSE.equals(descend)){
            Arrays.sort(params, (p,q)->(p-q));
        }
        if (descend != null){
            log.debug("buildSortedListedList处理后数据：{}", com.yichen.casetest.utils.StringUtils.printArray(params));
        }
        ListNode root = new ListNode(params[0]);
        ListNode pos = root;
        for (int i=1; i<params.length; i++){
            pos = preLink(params[i], pos);
        }
        return root;
    }


    protected static ListNode buildListedList(String s){
        return buildSortedList(s, null);
    }

    /**
     * 构造有序列表
     * @param s
     * @param descend true-降序  false-升序  null无序
     * @return
     */
    protected static ListNode buildSortedList(String s, Boolean descend){
        if (StringUtils.isEmpty(s)){
            return null;
        }
        String[] items = s.split(CommonConstants.COMMA);
        Integer[] params = new Integer[items.length];
        for (int i=0; i< items.length; i++){
            params[i] = Integer.parseInt(items[i]);
        }
        return buildListedList(params, descend);
    }

    /**
     * 切分正则表达式   [[1,4,5],[1,3,4],[2,6]]
     */
    private static final Pattern pattern= Pattern.compile("([0-9,]{2,})");
    protected static ListNode[] buildArray(String s){
        List<ListNode> list = new ArrayList<>();
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            list.add(buildListedList(matcher.group()));
        }
        return list.toArray(new ListNode[0]);
    }

    public static void main(String[] args) {
        String s = "[[1,4,5],[1,3,4],[2,6]]";
//        String s = "[1,4,5]";
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
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
        log.debug(builder.toString());
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
