package raph;

import java.util.Comparator;

public class QuickSort<T>{
    private final int lo;
    private final int hi;
    public volatile T[] array;
    private final Comparator<? super T> comp;

    public QuickSort(int lo, int hi, Comparator<? super T> comp){
        this.comp = comp;
        this.hi = hi;
        this.lo = lo;
    }

    private int partition(Integer[] array, int lo, int hi){
        int i = lo;
        Integer x = array[hi];
        for (int j = lo; j < hi; j++) {
            if(array[j].compareTo(x) < 1){
                Integer tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
            }
        }
        Integer tmp = array[i];
        array[i] = array[hi];
        array[hi] = tmp;
        return i;
    }
}
