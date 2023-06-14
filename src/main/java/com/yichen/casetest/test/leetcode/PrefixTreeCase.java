package com.yichen.casetest.test.leetcode;

import java.util.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/13 21:09
 * @describe 前缀树实现demo
 */
public class PrefixTreeCase {




    public static void main(String[] args) {


    }
    static class PrefixTreeNode{
        public boolean end;
        public PrefixTreeNode[] path;
        PrefixTreeNode(){
            this.path = new PrefixTreeNode[26];
        }
    }

    static class PrefixTree{
        PrefixTreeNode root;
        PrefixTree(){
            root = new PrefixTreeNode();
        }

        public void forwardInit(String[] dictionary){
            this.forwardInit(Arrays.asList(dictionary));
        }

        public void forwardInit(List<String> dictionary){
            for (String item : dictionary){
                this.forwardInsert(item);
            }
        }

        public void backwardInit(List<String> dictionary){
            for (String item : dictionary){
                this.backwardInsert(item);
            }
        }

        public void backwardInsert(String word){
            int i = word.length() - 1, slot;
            PrefixTreeNode pos = root;
            while (i >= 0){
                slot = word.charAt(i) - 'a';
                i--;
                if (pos.path[slot] == null){
                    pos.path[slot] = new PrefixTreeNode();
                }
                pos = pos.path[slot];
            }
            pos.end = true;
        }

        /**
         * 正向插入
         * @param word
         */
        public void forwardInsert(String word){
            int i = 0, slot;
            PrefixTreeNode pos = root;
            while (i < word.length()){
                slot = word.charAt(i) - 'a';
                i++;
                if (pos.path[slot] == null){
                    pos.path[slot] = new PrefixTreeNode();
                }
                pos = pos.path[slot];
            }
            pos.end = true;
        }


        public String simplify(String sentence){
            StringBuilder builder = new StringBuilder();
            String[] items = sentence.split(" ");
            for (String item : items){
                builder.append(getPrefix(item)).append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }

        public String getPrefix(String word){
            PrefixTreeNode pos = root;
            int i = 0, slot;
            for (; i < word.length(); i++){
                slot = word.charAt(i) - 'a';
                if (pos.path[slot] == null){
                    return word;
                }
                pos = pos.path[slot];
                if (pos.end){
                    return word.substring(0, i+1);
                }
            }
            return word;
        }

        public boolean findWord(String word){
            PrefixTreeNode node = getPrefixNode(word);
            return node != null && node.end;
        }

        public boolean findWord(String word, PrefixTreeNode start){
            PrefixTreeNode node = getPrefixNode(word, start);
            return node != null && node.end;
        }

        public PrefixTreeNode getPrefixNode(String word){
            return this.getPrefixNode(word, this.root);
        }

        public PrefixTreeNode getPrefixNode(String word, PrefixTreeNode start){
            PrefixTreeNode pos = start;
            int i = 0, slot;
            for (; i<word.length(); i++){
                slot = word.charAt(i) - 'a';
                if (pos.path[slot] == null){
                    return null;
                }
                pos = pos.path[slot];
            }
            return pos;
        }



        public boolean oneWordDiff(String word){
            return oneWordDiff(word, 0, this.root, false);
        }

        public boolean oneWordDiff(String word, int p, PrefixTreeNode start, boolean used){
            if (used){
                return this.findWord(p > word.length() ? "" : word.substring(p), start);
            }
            if (p >= word.length()){
                return false;
            }
            int slot = word.charAt(p) - 'a';
            for (int i=0; i<26; i++){
                if (start.path[i] == null){
                    continue;
                }
                if (i == slot && oneWordDiff(word, p+1, start.path[i], false)){
                    return true;
                }
                if (i != slot && oneWordDiff(word, p+1, start.path[i], true)){
                    return true;
                }
            }
            return false;
        }

    }


}
