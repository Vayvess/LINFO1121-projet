package raph;
import java.util.Comparator;

public class QuickSort {

    private static <T> int partition(T[] array, Comparator<? super T> comp, int lo, int hi){
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

    private static <T> void quickSort(T[] array, Comparator<? super T> comp, int lo, int hi){
        if(lo < hi){
            int pos = partition(array, comp, lo, hi);
            quickSort(array, comp, lo, pos - 1);
            quickSort(array, comp, pos + 1, hi);
        }
    }

    public static <T> void sort(T[] array, Comparator<? super T> comp){
        quickSort(array, comp, 0, array.length - 1);
    }
}
