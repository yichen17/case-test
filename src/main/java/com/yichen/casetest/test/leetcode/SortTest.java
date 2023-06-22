package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/22 08:38
 * @describe 排序测试
 */
@Slf4j
public class SortTest {

    public static void main(String[] args) {
        Integer[] array = StringUtils.randomIntArray(999, 0, 100);
        quickSort(array);
        log.info("quick result {} \n {}", StringUtils.printArray(array),
                StringUtils.checkOrder(array, true));
        StringUtils.divisionLine();

        array = StringUtils.randomIntArray(999, 0, 100);
        LinkedListNode root = LinkedListNode.buildListedList(array);
        log.info("linked list node old path {}", LinkedListNode.printPath(root));
        linkedListMergeSort(root);
        log.info("linked list node sort path {}", LinkedListNode.printPath(root));
        StringUtils.divisionLine();


    }


    //=============  归并排序  链表
    private static LinkedListNode linkedListMergeSort(LinkedListNode head){
        LinkedListNode pos = getMid(head);
        if (pos == head){
            return head;
        }
        LinkedListNode next = pos.next;
        pos.next = null;
        LinkedListNode left = linkedListMergeSort(head);
        LinkedListNode right = linkedListMergeSort(next);
        return merge(left, right);
    }


    private static LinkedListNode merge(LinkedListNode left, LinkedListNode right){
        if (left == null)return right;
        if (right == null)return left;
        LinkedListNode head = new LinkedListNode();
        LinkedListNode pos = head;
        LinkedListNode p = left, q = right;
        while(p != null && q != null){
            if (p.val > q.val){
                pos.next = q;
                q = q.next;
            }
            else {
                pos.next = p;
                p = p.next;
            }
            pos = pos.next;
        }
        if (p == null){
            pos.next = q;
        }
        if (q == null){
            pos.next = p;
        }
        return head.next;
    }


    private static LinkedListNode getMid(LinkedListNode root){
        if (root == null || root.next == null){
            return root;
        }
        LinkedListNode p = root, q = root;
        while(q.next != null && q.next.next!= null){
            p = p.next;
            q = q.next.next;
        }
        return p;
    }

    
    //===================快排

    private static Random random = new Random();
    /**
     * 快排实现
     * @param nums 待排序数组
     */
    private static void quickSort(Integer[] nums){
        quickSort(nums, 0, nums.length-1);
    }

    private static void quickSort(Integer[] nums, int left, int right){
        if (nums.length == 0 || left  > right){
            return ;
        }
        int pos = partitionPos(nums, left, right);
        // 左子域处理
        quickSort(nums, left, pos -1);
        // 右子域处理
        quickSort(nums, pos+1, right);
    }

    private static int partitionPos(Integer[] nums, int left, int right){
        left = Math.max(0, left);
        right = Math.min(right, nums.length-1);
        // 随机位置可以避免子域划分不合理问题，即每次都有一个长度为1
        int randomPos = random.nextInt(right-left+1) + left;
        swap(nums, left, randomPos);
        int first = nums[left];
        int i=left, j=right;
        while (i < j){
            while(nums[j] >= first && i<j){
                j--;
            }
            if (i >= j){
                break;
            }
            swap(nums, i, j);
            while (nums[i] < first && i < j){
                i++;
            }
            if (i >= j){
                break;
            }
            swap(nums, i, j);
        }
        nums[j] = first;
        return j;
    }

    private static void swap(Integer[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
