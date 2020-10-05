package raph;

import java.util.Comparator;

public class QuickSort<T> {
    private final int lo;
    private final int hi;
    public volatile T[] array;
    private final Comparator<? super T> comp;

    public QuickSort(T[] array, Comparator<? super T> comp){
        this.array = array;
        this.comp = comp;
        this.lo = 0;
        this.hi = array.length -1;
    }

    public QuickSort(T[] array, Comparator<? super T> comp, int lo, int hi){
        this.array = array;
        this.comp = comp;
        this.lo = lo;
        this.hi = hi;
    }

    private int partition(T[] array, int lo, int hi){
        for (int j = lo; j < hi; j++) {
            if(comp.compare(array[j], array[hi]) < 1){
                T tmp = array[lo];
                array[lo] = array[j];
                array[j] = tmp;
                lo++;
            }
        }

        T tmp = array[lo];
        array[lo] = array[hi];
        array[hi] = tmp;
        return lo;
    }

    private void quickSort(int lo, int hi){
        if(lo < hi){
            int pos = partition(array, lo, hi);
            if(pos < 0) return;
            quickSort(lo, pos - 1);
            quickSort(pos + 1, hi);
        }
    }

    public void sort(){
        quickSort(lo, hi);
    }
}
