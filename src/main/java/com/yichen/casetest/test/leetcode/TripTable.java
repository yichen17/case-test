package com.yichen.casetest.test.leetcode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Qiuxinchao
 * @date 2023/8/3 14:28
 * @describe 跳表实现
 *      参考文章：https://mp.weixin.qq.com/s/HoEfIy2jZbhNsXY99Bm1LA
 *              https://blog.csdn.net/weixin_45480785/article/details/116293416
 */
@Slf4j
public class TripTable {

    /**
     * 最大层数限制
     */
    private static final  int MAX_LEVEL = 32;
    /**
     * 上层元素数是下层数量的 1/N（相当于 N 叉树）
     */
    private static final int N = 4;

    private static final int RANDOM_RANGE = 10000000;


    private static int randomLevel(){
        int level = 1;
        while (Math.round(Math.random() * RANDOM_RANGE) % N == 0){
            level++;
        }
        int randomLevel = Math.min(level, MAX_LEVEL);
//            System.out.println("randomLevel: " + randomLevel);
        return randomLevel;
    }

    /**
     * todo 存在问题，插入应该只产生一个节点，而不是多个  对跳表的概念理解有问题
     * 最简易的版本，存在对象重复的场景，即一个节点在多个多层反复构建，插入删除的开销比较大，查找应该相差不大
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class SkipList{

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
            int pos = heads.size()-1;
            Node p = heads.get(pos);
            while (p != null && p.getVal() != val){
                // 如果当前节点比val值大，上移一层
                // 如果当前节点小于等于val，且后面的元素后面的也小于等于它  往后移
                // 如果当前节点小于等于val，且后面无节点或者后面节点大于val，前移查找
                if (p.getVal() > val){
                    if (pos == 0){
                        break;
                    }
                    p = heads.get(--pos);
                }
                else if (Objects.nonNull(p.getNext()) && p.getNext().getVal() <= val){
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
            int len = randomLevel(), oldLen = heads.size();
            Node addNode = null;
            while (len > oldLen){
                heads.add(null);
                oldLen++;
            }
            // 插入数据
            for (int i=len-1; i>=0; i--){
                Node node = heads.get(i), p = node;
                addNode = this.constructAndLinkNext(addNode, val);
                if (Objects.isNull(p) || p.getVal() > val){
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
            long average = 0L;
            long searchOrDel = 0L;
            for (int i=0; i<testTimes; i++){
                int limit = random.nextInt(range) + 1;
                long start = System.currentTimeMillis();
                SkipList skipList = constructRandomInsert(limit);
                average += System.currentTimeMillis() - start;
                skipList.check();
                // 删除查找性能测试
                searchOrDel += searchOrDel(searchOrDelTimes, range, skipList);
                // 删除验证测试
//                boolean result = delAndValidate(searchOrDelTimes, limit, skipList, constructCompareSet(limit));
//                if (!result){
//                    return;
//                }
            }
            // 构造均值 25-35ms  查找删除均值 17
            log.info("构造总耗时:{}ms平均耗时:{}ms", average, average / testTimes);
            log.info("{}次查询或删除总耗时：{}ms,平均耗时:{}ms", searchOrDelTimes, searchOrDel, searchOrDel/testTimes);
        }

        public static Set<Integer> constructCompareSet(int range){
            Set<Integer> compareSet = new HashSet<>();
            for (int i=1; i<=range; i++){
                compareSet.add(i);
            }
            return compareSet;
        }

        public static boolean delAndValidate(int times, int limit, SkipList skipList, Set<Integer> compareSet){
            for (int i=0; i<times; i++){
                int val = random.nextInt(limit);
                if (skipList.delete(val) != compareSet.remove(val)){
                    log.info("删除存在问题");
                    return false;
                }
            }
            for (int i=0; i<times; i++){
                int val = random.nextInt(limit);
                if (skipList.search(val) != compareSet.contains(val)){
                    log.info("查找存在问题");
                    return false;
                }
            }
            return true;
        }

        public static Long searchOrDel(int times, int limit, SkipList skipList){
            long start = System.currentTimeMillis();
            doSearchOrDel(times, limit, skipList);
            start = System.currentTimeMillis() - start;
            skipList.check();
            return start;
        }


        public static void doSearchOrDel(int times, int limit, SkipList skipList){
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


    // 整体设计逻辑
    // root中存储哨兵节点，nextItems只有一个节点，即后继节点，整体自上而下节点数逐渐增多
    // 插入时如果随机层高高于当前，则在root头部插入。
    // 删除时从上往下扫描，
    // 查找自上而下扫描，直到没有节点可找或者可找的节点值大于等于它停止
    // 插入删除有一个相对位置定位，我这里设计的非哨兵节点不是全长度铺设的，它的长度是最初插入时的随机长度

    static class SkipListOptimize{

        /**
         * 越往下节点越多，上面的为跳跃节点
         * 空的哨兵节点，使得后续节点等价处理
         */
        private LinkedList<NodeOptimize> root;
        private int level;

        public SkipListOptimize() {
            this.root = new LinkedList<>();
            this.level = 0;
        }

        /**
         * 插入节点，先计算随机层高，空层高直接映射。剩余的找前节点，然后进行处理
         * @param val 目标值
         * @return
         */
        public NodeOptimize insert(int val){
            int newLevel = randomLevel(), gap = Math.max(0, newLevel-this.level), startPos = this.level - newLevel;
            List<NodeOptimize> preNodes = this.findPre(val, startPos);
            NodeOptimize newNode = NodeOptimize.builder().val(val)
                    .height(newLevel).nextItems(new ArrayList<>(newLevel)).build();
            NodeOptimize node, next;
            // 处理现有节点， newLevel层级以下的
            for (int i=startPos; i<this.level; i++){
                // 相对偏移量
                node = preNodes.get(i - startPos + gap);
                next = node.nextItems.get(this.getRealPos(i, node.height));
                node.nextItems.set(this.getRealPos(i, node.height), newNode);
                newNode.nextItems.set(i-startPos, next);
                this.root.get(i).len++;
            }
            // 填充空节点  哨兵节点只需要一个后继节点以及记录长度就可以了
            while (newLevel > this.level){
                List<NodeOptimize> list = new ArrayList<>(2);
                list.add(newNode);
                this.root.addFirst(NodeOptimize.builder().len(1).nextItems(list).build());
                this.level++;
            }
            return newNode;
        }

        /**
         * 找到前节点删除
         * 如果触发层级销毁，前提是当前层没有节点，即里面的节点都删除了。
         * @param val
         * @return
         */
        public boolean delete(int val){
            List<NodeOptimize> preNodes = this.findPre(val);
            NodeOptimize node, next, dummy;
            boolean result = false;
            int i=0;
            Iterator<NodeOptimize> iterator = this.root.iterator();
            while (iterator.hasNext()){
                dummy = iterator.next();
                node = preNodes.get(i++);
                next = node.nextItems.get(this.getRealPos(i, node.height));
                // 有后继，改变指针指向， 保存结果，哨兵节点数量减一，到0移除节点
                if (Objects.nonNull(next)) {
                    result = true;
                    dummy.len--;
                    node.nextItems.set(this.getRealPos(i, node.height),
                            next.nextItems.get(this.getRealPos(i, next.height)));
                }
                // 整个空了，移除
                if (dummy.len == 0){
                    iterator.remove();
                }
            }
            return result;
        }

        private NodeOptimize search(NodeOptimize startNode, int val){
            if (Objects.isNull(startNode)){
                return null;
            }
            NodeOptimize node = startNode;
            while (Objects.nonNull(node)){
                // 跳出循环逻辑
                if (node.val == val){
                    return node;
                }
                else if (node.val > val){
                    return null;
                }
                // 从上往下扫
                for (int i=0; i<node.nextItems.size(); i++){
                    if (Objects.nonNull(node.nextItems.get(i))){
                        node = node.nextItems.get(i);
                        break;
                    }
                }
            }
            return null;
        }

        /**
         * 查找，自顶向下
         * @param val
         * @return 有找到则返回节点，未找到则返回null
         */
        public NodeOptimize search(int val){
            if (CollectionUtils.isEmpty(this.root)){
                return null;
            }
            return this.search(this.root.get(0), val);
        }

        /**
         * 从指定层数开始找前节点
         * @param val
         * @param lv
         * @return
         */
        public List<NodeOptimize> findPre(int val, int lv){
            if (CollectionUtils.isEmpty(this.root) || this.root.size() < lv){
                return new ArrayList<>(0);
            }
            List<NodeOptimize> preNodes = new ArrayList<>(this.root.size() - lv);
            NodeOptimize pos = this.root.get(lv);
            // 从哨兵开始扫描
            while (preNodes.size() < this.level){
                pos = this.horizontalSearch(pos, val, preNodes.size() + lv);
                preNodes.add(pos);
            }
            return preNodes;
        }

        /**
         * 找到前一个节点
         * @param val
         * @return
         */
        public List<NodeOptimize> findPre(int val){

            return this.findPre(val, 0);

//            if (CollectionUtils.isEmpty(this.root)){
//                return new ArrayList<>(0);
//            }
//            List<NodeOptimize> preNodes = new ArrayList<>(this.root.size());
//            NodeOptimize pos = this.root.get(0), next;
//            int c = 0;
//            // 从哨兵开始扫描
//            while (preNodes.size() < this.level){
//                pos = this.horizontalSearch(pos, val, preNodes.size());
//                preNodes.add(pos);
//            }
//            return preNodes;
        }

        /**
         * 水平定位
         * @param node
         * @param val
         * @param level
         * @return
         */
        private NodeOptimize horizontalSearch(NodeOptimize node, int val, int level){
            // 没有后续指针，我即是前驱
//            if (CollectionUtils.isEmpty(node.nextItems)){
//                return node;
//            }
            NodeOptimize next;
            // 后面同层的为空，或者值比目标值大
            if (Objects.isNull(next = node.nextItems.get(this.getRealPos(level, node.height))) || next.val > val){
                return node;
            }
            // 后面同层的值小于等于目标值，往后找
            return this.horizontalSearch(next, val, level);
        }

        private int getRealPos(int lv, int height){
            return lv - this.level + height + 1;
        }


        /**
         * 用nextItems替代原先的next、down指针
         * 引用代替实际创建对象
         *
         * 如果跳表的整体层数为q， 当前节点n的最大层数为p(p<=q)，此时n的后继层数标识逻辑为 [0, q-p)
         */
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        @Builder
        static class NodeOptimize{
            /**
             * 层高
             */
            private int height;
            /**
             * 存储键值
             */
            private int val;
            /**
             * 层节点数量
             */
            private int len;
            /**
             * 长度根据初始化时的层数设定   理论上来说不可能为空
             */
            private List<NodeOptimize> nextItems;
        }




    }



    public static void main(String[] args) {
        SkipList.caseCheck(100, 10000, 100000);
    }







}
