package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
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

    private static final Random random = new Random();
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

        public static SkipList constructRandomInsert(int range){
            List<Integer> insertOrders = com.yichen.casetest.utils.StringUtils.randomList(range);
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
        private LinkedList<NodeOptimize> dummys;
        /**
         * 整体层高
         */
        private int level;

        public SkipListOptimize() {
            this.dummys = new LinkedList<>();
            this.level = 0;
        }

        /**
         * 计算真实高度
         *                  节点层数 -  （当前跳表层数 - 层数 - 1 ） - 1
         * @param lv  遍历层数
         * @param height 节点层高
         * @return
         */
        private int getRealPos(int lv, int height){
            int result = lv - this.level + height;
            if (result < 0 || result >= height){
                log.error("getRealPos 计算存在问题 {} {} {}", lv, this.level, height);
            }
            return result;
        }


        /**
         * 插入节点，先计算随机层高，空层高直接映射。剩余的找前节点，然后进行处理
         * @param val 目标值
         * @return
         */
        public NodeOptimize insert(int val){
            // startPos开始位置 当新层高高于当前，则为0，否则为当前层高 - 新层高
            // gap 节点间距 用于新节点的nextItems相对位置计算
            int newLevel = randomLevel(),
                    startPos = this.level > newLevel ? this.level - newLevel : 0,
                    gap = this.level > newLevel ? 0 : newLevel - this.level;
            // 寻找现有层级的插入位置
            List<NodeOptimize> preNodes = this.findPre(val, startPos);
            // 新节点构造
            NodeOptimize newNode = NodeOptimize.builder().val(val)
                    .height(newLevel)
                    .nextItems(new NodeOptimize[newLevel]).build();
            NodeOptimize preNode, next;
            // 处理现有节点， newLevel层级以下的
            for (int i=startPos; i<this.level; i++){
                preNode = preNodes.get(i - startPos);
                int pos;
                // 哨兵特殊处理
                if (preNode.dummy){
                    pos = 0;
                }
                // 相对偏移量
                else {
                    pos = this.getRealPos(i, preNode.height);
                }
                next = preNode.nextItems[pos];
                preNode.nextItems[pos] = newNode;
                newNode.nextItems[i-startPos+gap] = next;
            }
            // 填充空节点  哨兵节点只需要一个后继节点以及记录长度就可以了
            while (newLevel > this.level){
                this.dummys.addFirst(NodeOptimize.builder().height(1).dummy(true)
                        .nextItems(new NodeOptimize[]{newNode}).build());
                this.level++;
            }
            return newNode;
        }

        /**
         * 找到前节点删除
         * 如果触发层级销毁，前提是当前层没有节点，即里面的节点都删除了。
         * @param target
         * @return
         */
        public boolean delete(int target){
            List<NodeOptimize> preNodes = this.findPre(target);
            NodeOptimize preNode, next;
            boolean result = false;
            int pos;
            for (int i=0; i<preNodes.size(); i++){
                preNode = preNodes.get(i);
                if (preNode.dummy){
                    pos = 0;
                }
                else {
                    pos = this.getRealPos(i, preNode.height);
                }
                next = preNode.nextItems[pos];
                // 有后继，改变指针指向， 保存结果，哨兵节点数量减一，到0移除节点
                if (Objects.nonNull(next) && next.val == target) {
                    result = true;
                    preNode.nextItems[pos] =  next.nextItems[this.getRealPos(i, next.height)];
                    // 现有槽位置空
                    next.nextItems[this.getRealPos(i, next.height)] = null;
                }
            }
            // 移除空节点
            this.dummys.removeIf(nodeOptimize -> Objects.isNull(nodeOptimize.nextItems[0]));
            this.level = this.dummys.size();
            return result;
        }



        /**
         * 查找，从顶层开始扫描
         * @param target
         * @return 有找到则返回节点，未找到则返回null
         */
        public NodeOptimize search(int target){
            if (CollectionUtils.isEmpty(this.dummys)){
                return null;
            }
            // 哨兵处理
            int i = 0;
            NodeOptimize node, next;
            while (true){
                if (i == this.dummys.size()){
                    return null;
                }
                node = this.dummys.get(i).nextItems[0];
                // node不可能为空，空的话这一层应该移除
                if (node.val > target){
                    i++;
                }
                else if (node.val == target){
                    return node;
                }
                else {
                    break;
                }
            }
            // 上一步出口结果为 node.val < target
            // 数据节点查询
            while (i < this.dummys.size()){
                next = node.nextItems[this.getRealPos(i, node.height)];
                // 同一层下一个节点为空或者值比target要大
                if (Objects.isNull(next) || next.val > target){
                    i++;
                }
                else if (next.val == target){
                    return next;
                }
                else {
                    node = next;
                }
            }
            return null;
        }

        /**
         * 从指定层数开始找前节点
         * @param target
         * @param lv 从0开始
         * @return
         */
        public List<NodeOptimize> findPre(int target, int lv){
            // 如果寻找的层级大于当前现有层级
            if (CollectionUtils.isEmpty(this.dummys) || this.dummys.size() <= lv){
//                throw new RuntimeException(String.format("查找前驱节点不符合要求，总层数%s, 目标层数%s", this.dummys.size(), lv));
                return new ArrayList<>(0);
            }
            int len = this.dummys.size() - lv;
            List<NodeOptimize> preNodes = new ArrayList<>(len);
            // 起始节点为哨兵，得特殊处理
            NodeOptimize pos, next;
            while (true){
                // 存的全是哨兵节点
                if (preNodes.size() == len){
                    return preNodes;
                }
                pos = this.dummys.get(preNodes.size() + lv);
                next = pos.nextItems[0];
                // 哨兵子节点为空，或者哨兵子节点值大于等于目标值
                if (Objects.isNull(next) || next.val >= target){
                    preNodes.add(pos);
                }
                // pos为非哨兵节点
                else {
                    pos = next;
                    break;
                }
            }
            // 正常数据节点扫描
            while (preNodes.size() < len){
                pos = this.horizontalSearch(pos, target, preNodes.size() + lv);
                preNodes.add(pos);
            }
            return preNodes;
        }

        /**
         * 找到目标节点值得前一个节点，从0层开始
         * @param target 目标节点值
         * @return
         */
        public List<NodeOptimize> findPre(int target){
            return this.findPre(target, 0);
        }

        /**
         * 水平搜索比{@code target}
         * @param node 有可能为根的子节点
         * @param target 目标值
         * @param level 层数
         * @return
         */
        private NodeOptimize horizontalSearch(NodeOptimize node, int target, int level){
            // 理论上不可能存在没有nextItems
            NodeOptimize next;
            // 1、节点为空  2、后续节点为空 3、后续节点值大于等于目标值
            if (Objects.isNull(node)
                    ||  Objects.isNull(next = node.nextItems[this.getRealPos(level, node.height)])
                    || next.val >= target){
                return node;
            }
            // 后面同层的值小于等于目标值，往后找
            return this.horizontalSearch(next, target, level);
        }

        /**
         * 水平打印
         * @param node
         */
        public static void horizontalPrint(SkipListOptimize node){
            if (CollectionUtils.isEmpty(node.dummys)){
                return;
            }
            StringBuilder builder = new StringBuilder();
            int pos;
            for (int i=0; i< node.level; i++){
                boolean first = true;
                NodeOptimize dummy = node.dummys.get(i);
                builder.append("level").append(i);
                while (Objects.nonNull(dummy)){
                    // 第一个哨兵节点处理
                    if (first){
                        first = false;
                        dummy = dummy.nextItems[0];
                    }
                    // 真实数据节点
                    else {
                        builder.append(" => ").append(dummy.val);
                        pos = node.getRealPos(i, dummy.height);
                        dummy = dummy.nextItems[pos];
                    }
                }
                System.out.println(builder);
                builder = new StringBuilder();
            }
            System.out.println("\n");
        }

        /**
         * 水平校验
         * @param root
         * @return
         */
        public static boolean horizontalCheck(SkipListOptimize root){
            if (CollectionUtils.isEmpty(root.dummys)){
                return true;
            }
            int preVal;
            for (int i=0; i< root.level; i++){
                preVal = -1;
                NodeOptimize node = root.dummys.get(i);
                while (Objects.nonNull(node)){
                    if (node.isDummy()){
                        node = node.nextItems[0];
                        continue;
                    }
                    else if (preVal > node.val){
                        horizontalPrint(root);
                        return false;
                    }
                    else {
                        preVal = node.val;
                    }
                    // 往后定位走
                    node = node.nextItems[root.getRealPos(i, node.height)];
                }
            }
            return true;
        }

        public static SkipListOptimize randomInsert(int range){
            SkipListOptimize root = new SkipListOptimize();
            StringUtils.randomList(range).forEach(root::insert);
            return root;
        }




        public static void insertTest(int range, int times){
            for (int i=0; i<times; i++){
                SkipListOptimize.horizontalCheck(SkipListOptimize.randomInsert(range));
            }
        }

        public static void singlePrint(int range){
            SkipListOptimize.horizontalPrint(SkipListOptimize.randomInsert(range));
        }

        public static void searchTest(int range, int times){
            SkipListOptimize skipListOptimize = SkipListOptimize.randomInsert(range);
            for (int i=0; i<times; i++){
                int r = random.nextInt(range*2);
                NodeOptimize node = skipListOptimize.search(r);
                boolean trueResult = r >= 1 && r <= range;
                boolean testResult = Objects.nonNull(node);
                if (trueResult != testResult){
                    SkipListOptimize.horizontalPrint(skipListOptimize);
                    throw new RuntimeException(String.format("%s: %s %s %s", "查询校验异常", r, trueResult, testResult));
                }
            }
        }

        public static void delTest(int range, int times){
            SkipListOptimize root = new SkipListOptimize();
            List<Integer> data = StringUtils.randomList(range);
            data.forEach(root::insert);
            Set<Integer> set = new HashSet<>(data);
            for (int i=0; i<times; i++){
//                System.out.println(i);
                int r = random.nextInt(range*2);
                boolean delResult, trueResult;
                try {
                    delResult = root.delete(r);
                    trueResult = set.remove(r);
                    if (trueResult != delResult){
                        throw new RuntimeException(String.format("%s: %s %s %s", "删除校验异常", r, trueResult, delResult));
                    }
                }
                catch (Exception e){
//                    SkipListOptimize.horizontalPrint(root);
                    log.error("delTest删除节点异常 {}", r);
                    throw e;
                }
            }
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
             * 层高  计算相对位置
             */
            private Integer height;
            /**
             * 存储键值
             */
            private Integer val;
            /**
             * 长度根据初始化时的层数设定   理论上来说不可能为空
             */
            private NodeOptimize[] nextItems;

            private boolean dummy;

            public void printInfo(){
                StringBuilder builder = new StringBuilder();
                builder.append("NodeOptimize printInfo: height:{}, val:{}", height, val);
                System.out.println(builder);
            }


        }
    }



    public static void main(String[] args) {
//        SkipList.caseCheck(100, 10000, 100000);


        for (int i=0; i<10000; i++){
            SkipListOptimize.insertTest(1000, 1000);
            StringUtils.divisionLine("insertTest");
//        SkipListOptimize.singlePrint(1000);
//        StringUtils.divisionLine("singlePrint");
            SkipListOptimize.searchTest(1000, 1000);
            StringUtils.divisionLine("searchTest");
            SkipListOptimize.delTest(1000, 1000);
            StringUtils.divisionLine("delTest");
        }





    }










}
