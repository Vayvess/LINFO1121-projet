package raph;

public class MergeSort {
    public static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi){
        if (hi + 1 - lo >= 0) System.arraycopy(arr, lo, aux, lo, hi + 1 - lo);

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
    public static void mergeSort(Comparable[] arr, Comparable[] aux, int lo, int hi){
        if(hi - lo > 1024){
            QuickSort.quickSort(arr, lo, hi);
        }
        else{
            int mid = (lo + hi) / 2;
            mergeSort(arr, aux, lo, mid);
            mergeSort(arr, aux, mid + 1, hi);
            merge(arr, aux, lo, mid, hi);
        }
    }
    public static void sort(Comparable[] arr){
        Comparable[] aux = new Comparable[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }
}
