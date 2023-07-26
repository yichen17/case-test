package com.yichen.casetest.test.fastjon.genericTrans;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/7/26 16:24
 * @describe
 *    类型擦除 =>  桥接方法  =>  https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html
 */
@Slf4j
public class TypeErasureTest {
    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        Node n = mn;            // A raw type - compiler throws an unchecked warning
//        n.setData("Hello");     // Causes a ClassCastException to be thrown.
        Integer x = mn.data;
    }

    static public class Node<T> {

        public T data;

        public Node(T data) { this.data = data; }

        public void setData(T data) {
            System.out.println("Node.setData");
            this.data = data;
        }
    }

    static public class MyNode extends Node<Integer> {
        public MyNode(Integer data) { super(data); }

        @Override
        public void setData(Integer data) {
            System.out.println("MyNode.setData");
            super.setData(data);
        }
    }
}
