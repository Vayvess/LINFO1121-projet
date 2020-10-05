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
        int i = lo;
        T x = array[hi];
        for (int j = lo; j < hi; j++) {
            if(comp.compare(array[j], x) < 1){
                T tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
            }
        }
        T tmp = array[i];
        array[i] = array[hi];
        array[hi] = tmp;
        return i;
    }

    private int partition(int bas, int haut){
        int i = bas;
        T x = array[haut];
        for (int j = bas; j < haut; j++) {
            if(comp.compare(array[j], x) < 1){
                T tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
            }
        }
        T tmp = array[i];
        array[i] = array[haut];
        array[haut] = tmp;
        return i;
    }

    private void quickSort(int bas, int haut){
        if(bas < haut){
            int pos = partition(bas, haut);
            if(pos < 0) return;
            quickSort(bas, pos - 1);
            quickSort(pos + 1, haut);
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
