package com.yichen.casetest.test.leetcode;

import java.util.List;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/2/18 07:16
 * @describe
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
