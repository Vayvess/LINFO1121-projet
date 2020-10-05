package jmans;

public class MyInsertionSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int min = 0;
        int e;
        for (e = 1; e < a.length; e++) {
            if(Helper.less(a[e],a[min])) min = e;
        }Helper.exch(a,min,0);
        for (int i = 1; i < N; i++) {
            Comparable ith = a[i];
            int j;
            for (j = i; Helper.less(ith, a[j-1]); j--)
                a[j] = a[j-1];
            a[j] = ith;
        }
    }
}