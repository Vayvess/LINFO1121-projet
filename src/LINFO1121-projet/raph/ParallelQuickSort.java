package raph;

import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort<T extends Comparable<T>> extends RecursiveAction {

    private final int lo;
    private final int hi;
    private final T[] array;
    private static final int threshold = 1024;

    public ParallelQuickSort(T[] array, int lo, int hi){
        this.lo = lo;
        this.hi = hi;
        this.array = array;
    }

    private int parallelPartition(){
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

    private int partition(int lo, int hi){
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

    private void quickSort(int lo, int hi){
        if(lo < hi){
            int pos = partition(lo, hi);
            if(pos < 0) return;
            quickSort(lo, pos - 1);
            quickSort(pos + 1, hi);
        }
    }

    @Override
    protected void compute() {
        if(hi - lo < threshold){
            quickSort(lo, hi);
        }
        else {
            int pos = parallelPartition();
            if(pos < 0) return;
            ParallelQuickSort<T> left = new ParallelQuickSort<>(array, lo, pos - 1);
            ParallelQuickSort<T> right = new ParallelQuickSort<>(array, pos + 1, hi);
            invokeAll(left, right);
        }
    }
}
