package com.yichen.casetest.test.leetcode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.Now;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Qiuxinchao
 * @date 2023/8/3 14:28
 * @describe 跳表实现
 *      参考文章：https://mp.weixin.qq.com/s/HoEfIy2jZbhNsXY99Bm1LA
 */
@Slf4j
public class TripTable {


    /**
     * 最简易的版本，存在对象重复的场景，即一个节点在多个多层反复构建，插入删除的开销比较大，查找应该相差不大
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class SkipList{
        /**
         * 最大层数限制
         */
        private static final  int MAX_LEVEL = 32;
        /**
         * 上层元素数是下层数量的 1/N（相当于 N 叉树）
         */
        private static final int N = 4;
        private LinkedList<Node> heads;

        /**
         * 搜索节点
         * @return
         */
        public boolean search(int val){
            Node node = this.searchNode(val);
            return Objects.nonNull(node);
        }

        private Node searchNode(int val){
            if (CollectionUtils.isEmpty(heads)){
                return null;
            }
            Node p = heads.get(0);
            while (p != null && p.getVal() != val){
                // 如果下一个节点不为空，且下一个节点的值小于val
                if (Objects.nonNull(p.getNext()) && p.getNext().getVal() <= val){
                    p = p.next;
                }
                else {
                    p = p.getDown();
                }
            }
            return Objects.nonNull(p) && p.getVal() == val ? p : null;
        }

        /**
         * 插入 判断是否要新增层数
         * 插入节点
         * @param val
         */
        public void insert(int val){
            // 构造层级
            if (Objects.isNull(heads)){
                heads = new LinkedList<>();
            }
            int len = this.randomLevel(), oldLen = heads.size();
            Node addNode = null;
            while (len > oldLen){
                addNode = this.constructAndLinkNext(addNode, val);
                heads.add(addNode);
                len--;
            }
            // 插入数据
            for (int i=len-1; i>=0; i--){
                Node node = heads.get(i), p = node;
                addNode = this.constructAndLinkNext(addNode, val);
                if (p.getVal() > val){
                    addNode.next = p;
                    heads.set(i, addNode);
                    continue;
                }
                // 找到应该插入的位置
                while (p.getVal() <= val && Objects.nonNull(p.getNext()) && p.getNext().getVal() <= val){
                    p = p.getNext();
                }
                addNode.next = p.next;
                p.next = addNode;
            }
        }

        private Node constructAndLinkNext(Node now, int val){
            if (Objects.nonNull(now)){
                now.setDown(Node.builder().val(val).build());
                now = now.getDown();
            }
            else {
                now = Node.builder().val(val).build();
            }
            return now;
        }


        /**
         * 找到最顶上的，然后开始删除操作
         * 空层删除
         * @param val 待删除值
         * @return
         */
        public boolean delete(int val){
            return doDelete(val);
        }

        private boolean doDelete(int val){
            if (CollectionUtils.isEmpty(heads)){
                return false;
            }
            boolean result = false;
            int pos = 0;
            Iterator<Node> iterator = heads.iterator();
            while (iterator.hasNext()){
                Node next = iterator.next();
                result |= this.deleteNode(val, pos);
                if (Objects.isNull(heads.get(pos))){
                    iterator.remove();
                }
                else {
                    pos ++;
                }
            }
            return result;
        }

        private boolean deleteNode(int val, int pos){
            boolean result = false;
            Node item = heads.get(pos);
            if (Objects.isNull(item)){
                return false;
            }
            while (Objects.nonNull(item) && item.val == val){
                item = item.next;
                result = true;
            }
            heads.set(pos, item);
            if (Objects.isNull(item)){
                return result;
            }
            while (Objects.nonNull(item.next)){
                if (item.next.val < val){
                    item = item.next;
                }
                else if (item.next.val == val){
                    result = true;
                    item.next = item.next.next;
                }
                else {
                    break;
                }
            }
            return result;
        }

        private int randomLevel(){
            int level = 1;
            while (Math.round(Math.random() * 10000000) % N == 0){
                level++;
            }
            int randomLevel = Math.min(level, MAX_LEVEL);
//            System.out.println("randomLevel: " + randomLevel);
            return randomLevel;
        }

        public void print(){
            if (CollectionUtils.isEmpty(heads)){
                return;
            }
            StringBuilder builder =  new StringBuilder();
            for (Node item :heads){
                Node p = item;
                while (Objects.nonNull(p)){
                    builder.append(p.val).append(" => ");
                    p = p.next;
                }
                if (builder.length() > 0){
                    builder.replace(builder.length() - 4, builder.length(), "");
                }
                System.out.println(builder);
                builder = new StringBuilder();
            }
        }

        public boolean check(){
            if (CollectionUtils.isEmpty(heads)){
                return true;
            }
            int pre;
            for (Node node : heads){
                pre = -1;
                Node p = node;
                while (Objects.nonNull(p) && p.getVal() > pre){
                    pre = p.getVal();
                    p = p.getNext();
                }
                if (Objects.nonNull(p)){
                    this.print();
                    return false;
                }
            }
            return true;
        }

        private static final Random random = new Random();
        public static SkipList constructRandomInsert(int len){
            List<Integer> insertOrders = new ArrayList<>();
            for (int i=1; i<=len; i++){
                insertOrders.add(random.nextInt(i), i);
            }
            SkipList skipList = new SkipList();
            for (Integer item : insertOrders){
                skipList.insert(item);
            }
            return skipList;
        }

        public static void caseCheck(int testTimes, int range, int searchOrDelTimes){
            Long average = 0L;
            Long searchOrDel = 0L;
            for (int i=0; i<testTimes; i++){
                Long start = System.currentTimeMillis();
                SkipList skipList = constructRandomInsert(random.nextInt(range));
                average += System.currentTimeMillis() - start;
                skipList.check();
                start = System.currentTimeMillis();
                searchOrDel(searchOrDelTimes, range, skipList);
                searchOrDel += System.currentTimeMillis() - start;
                skipList.check();
            }
            // 构造均值 25-35ms  查找删除均值 17
            log.info("构造平均耗时{}ms", average / testTimes);
            log.info("{}次查询或删除平均耗时{}", searchOrDelTimes, searchOrDel/testTimes);
        }

        public static void searchOrDel(int times, int limit, SkipList skipList){
            for (int i=0; i<times; i++){
                int val = random.nextInt(limit);
                if (val % 2 == 0){
                    skipList.delete(val);
                }
                else {
                    skipList.search(val);
                }
            }
        }


    }

    public static void main(String[] args) {
        SkipList.caseCheck(100, 10000, 1000);
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    static class Node {
        private int val;
        private Node next;
        private Node down;
    }

}
