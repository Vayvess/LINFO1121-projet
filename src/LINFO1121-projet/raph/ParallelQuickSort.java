package raph;

import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort<T> extends RecursiveAction {

    private final int lo;
    private final int hi;
    public volatile T[] array;
    private final Comparator<? super T> comp;
    private static final int threshold = 1024;

    public ParallelQuickSort(T[] array, int lo, int hi, Comparator<? super T> comp){
        this.lo = lo;
        this.hi = hi;
        this.comp = comp;
        this.array = array;
    }

    private int parallelPartition(){
        int i = lo, j = hi + 1;
        T pvt = array[lo];
        while(i < j){
            while(comp.compare(array[++i], pvt) < 0 && i < hi);
            while(comp.compare(pvt, array[--j]) < 0 && j > lo);
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
            while(comp.compare(array[++i], pvt) < 0 && i < hi);
            while(comp.compare(pvt, array[--j]) < 0 && j > lo);
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
            ParallelQuickSort<T> left = new ParallelQuickSort<>(array, lo, pos - 1, comp);
            ParallelQuickSort<T> right = new ParallelQuickSort<>(array, pos + 1, hi, comp);
            invokeAll(left, right);
        }
    }
}
