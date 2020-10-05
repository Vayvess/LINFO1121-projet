package raph;

import java.util.Comparator;

public class MergeSort {
    private static <T extends Comparable<T>> void merge(T[] arr, T[] aux, int lo, int mid, int hi){
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    private static <T extends Comparable<T>> void mergeSort(T[] arr, T[] aux,int lo, int hi){
        if(hi - lo > 0){
            int mid = (lo + hi) / 2;
            mergeSort(arr, aux, lo, mid);
            mergeSort(arr, aux, mid + 1, hi);
            merge(arr, aux, lo, mid, hi);
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array){
        Object[] aux = new Object[array.length];
    }
}
