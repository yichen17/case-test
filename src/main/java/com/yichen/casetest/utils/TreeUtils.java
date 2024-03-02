package com.yichen.casetest.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2024/3/2 10:11
 * @describe 树相关的工具类
 */
@Slf4j
public class TreeUtils {

    private static Random random = new Random();


    /**
     * 构造无向的树,有效数，即每个点都可达
     * @param n 树的节点数量，标识为 [0,n-1]
     * @return
     */
    public static int[][] buildNoDirectTree(int n){
        int[][] result = new int[n-1][2];
        int pos = 0;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> notSelect = new HashSet<>();
        for (int i=0; i<n; i++){
            notSelect.add(i);
        }
        int r = random.nextInt(n);
        notSelect.remove(r);
        queue.offer(r);
        while (n > 1){
            int item = queue.poll();
            int times;
            // 选择子节点数量
            while ((times = random.nextInt(n)) == 0){}
            n -= times;
            while (times > 0){
                times --;
                // 选具体的子节点
                List<Integer> list = new LinkedList<>(notSelect);
                r = list.get(random.nextInt(list.size()));
                queue.offer(r);
                notSelect.remove(r);
                result[pos][0] = item;
                result[pos++][1] = r;
            }
        }
        log.info("buildNoDirectTree {}", JSON.toJSONString(result));
        return result;
    }

    public static void main(String[] args) {

        System.out.println(JSON.toJSONString(buildNoDirectTree(4)));

        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(1);
        set.add(3);
        Iterator<Integer> iterator = set.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }


}
