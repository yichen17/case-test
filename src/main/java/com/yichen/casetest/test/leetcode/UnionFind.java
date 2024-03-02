package com.yichen.casetest.test.leetcode;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2024/3/2 11:07
 * @describe 并查集实现
 */
public class UnionFind {

    private int[] f;
    private int[] rank;

    public UnionFind(int n) {
        f = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
    }

    public void merge(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx != ry) {
            if (rank[rx] > rank[ry]) {
                f[ry] = rx;
            } else if (rank[rx] < rank[ry]) {
                f[rx] = ry;
            } else {
                f[ry] = rx;
                rank[rx]++;
            }
        }
    }

    public int find(int x) {
        if (x != f[x]) {
            x = find(f[x]);
        }
        return f[x];
    }

    public int count() {
        int cnt = 0;
        int rt = find(0);
        for (int i = 0; i < f.length; i++) {
            if (rt == find(i)) {
                cnt++;
            }
        }
        return cnt;
    }

}
