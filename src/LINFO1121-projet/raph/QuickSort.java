package raph;

public class QuickSort {

    private static <T extends Comparable<T>> int partition(T[] array, int lo, int hi){
        int i = lo, j = hi + 1;
        T pvt = array[lo];
        while(i < j){

            while(array[++i].compareTo(pvt) < 0 && i < hi);
            while(pvt.compareTo(array[--j]) < 0 && j > lo);
            if(i < j){
                T tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        T tmp = array[lo];
        array[lo] = array[j];
        array[j] = tmp;
        return j;
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int lo, int hi){
        if (hi <= lo) return;
        int pos = partition(array, lo, hi);
        quickSort(array, lo, pos - 1);
        quickSort(array, pos + 1, hi);
    }

    public static <T extends Comparable<T>> void sort(T[] array){
        quickSort(array, 0, array.length - 1);
    }
}
