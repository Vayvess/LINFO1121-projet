package raph;

import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort<E> extends RecursiveAction {
    private final int lo, hi;
    private volatile E[] array, aux;
    private Comparator<? super E> comp;
    private static final int threshold = 256;

    public ParallelMergeSort(E[] a, int lo, int hi, E[] aux, Comparator<? super E> comp) {
        array = a; this.lo = lo; this.hi = hi; this.aux = aux; this.comp = comp;
    }

    private void sort(int lo, int hi){
        if (hi <= lo) return;
        int mid = (lo + hi) / 2;
        sort(lo, mid); sort(mid + 1, hi);
        merge(lo, mid, hi);
    }

    //merge two subarray and keep them sorted
    private void merge(int lo, int mid, int hi){
        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if(i > mid) {
                array[k] = aux[j++];
            }else if (j > hi){
                array[k] = aux[i++];
            }else if (comp.compare(aux[j], aux[i]) < 0){
                array[k] = aux[j++];
            }else{
                array[k] = aux[i++];
            }
        }
    }

    /*
     * Run a normal sort when the difference between hi and lo is under the threshold
     * Otherwise : Split the sub array in two and start the sort on each part of the array simultaneously
     */

    @Override
    protected void compute() {
        if (hi - lo < threshold){
            sort(lo, hi);
        }
        else{
            int mid = (lo + hi) / 2;
            ParallelMergeSort<E> left = new ParallelMergeSort<>(array, lo, mid, aux, comp);
            ParallelMergeSort<E> right = new ParallelMergeSort<>(array, mid + 1, hi, aux, comp);
            invokeAll(left, right);
            merge(lo, mid, hi);
        }
    }
}