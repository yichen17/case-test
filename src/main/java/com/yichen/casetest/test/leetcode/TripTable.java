package com.yichen.casetest.test.leetcode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @date 2023/8/3 14:28
 * @describe 跳表实现
 *      参考文章：https://mp.weixin.qq.com/s/HoEfIy2jZbhNsXY99Bm1LA
 */
@Slf4j
public class TripTable {

    public static void main(String[] args) {

    }


    static class SkipList{
        /**
         * 最大层数限制
         */
        private static final  int MAX_LEVEL = 32;
        /**
         * 上层元素数是下层数量的 1/N（相当于 N 叉树）
         */
        private static final int N = 4;
        protected Node head;
        /**
         * 跳表总量
         */
        protected int len;
        /**
         * 层级  上面最大，下面最小
         */
        protected int level;

        public SkipList() {
            head = new Node();
            len = 0;
            level = 0;
        }

        public List<Node> preNodeSearch(int val){
            List<Node> list = new ArrayList<>();
            Node node = head;
            // 从最高层开始往下搜索，直到搜到第一层
            for(int i=level; i>=0; i--){
                // 如果当前节点该层存在后继节点，且该后继节点的 key 小于等于目标 key，则跳到该后继节点
                while (Objects.nonNull(node.nexts.get(i)) && node.nexts.get(i).val < val){
                    node = node.nexts.get(i);
                }
                list.add(node);
            }
            if (list.size() == 0){
                list.add(head);
            }
            return list;
        }

        public void insert(int val){
            List<Node> preNodes = this.preNodeSearch(val);
            Node newNode = new Node(val, null);
            int level = this.randomLevel();
            int newLevel = level;
            // 如果层级比当前大，先处理
            while (level > this.level){
                head.nexts.add(newNode);
                level--;
            }
            // 层级一致后 依次处理
            for(int i=0; i<level; i++){
                Node leftNode = preNodes.get(i);
                // 变更next 指针
                newNode.nexts.set(i, leftNode.nexts.get(i));
                leftNode.nexts.set(i, newNode);
            }
            if (newLevel > this.level){
                this.level = newLevel;
            }
            this.len++;
        }

        public void delete(int val){
            List<Node> preNodes = this.preNodeSearch(val);
            Node current = preNodes.get(0).nexts.get(0);
            if (Objects.isNull(current) || current.val != val){
                return;
            }
            for (int i=0; i<current.nexts.size(); i++){
                preNodes.get(i).nexts.set(i, current.nexts.get(i));
                current.nexts.set(i, null);
                if (Objects.isNull(this.head.nexts.get(i))){
                    this.level--;
                }
            }
            this.len--;
        }


        /**
         * 从上到下一次查询
         * @param val 查找节点
         * @return
         */
        public Node search(int val){
            if (this.level == 0){
                return null;
            }
            Node node = head;
            // 从最高层开始往下搜索，直到搜到第一层
            for(int i=this.level; i>=0; i--){
                // 如果当前节点该层存在后继节点，且该后继节点的 key 小于等于目标 key，则跳到该后继节点
                while (Objects.nonNull(node.nexts.get(i)) && node.nexts.get(i).val <= val){
                    node = node.nexts.get(i);
                }
                // 从 node 进入到下一层继续查找
            }
            return node.val == val ? node : null;
        }

        protected int randomLevel(){
            int level = 1;
            while (Math.round(Math.random() * 10000000) % N == 0){
                level++;
            }
            return Math.min(level, MAX_LEVEL);
        }



    }


    @NoArgsConstructor
    @AllArgsConstructor
    static class Node {
        protected int val;
        /**
         * i层的下一个节点，没有放空占位
         */
        protected List<Node> nexts;

    }

}
