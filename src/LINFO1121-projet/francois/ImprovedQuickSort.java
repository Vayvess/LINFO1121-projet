import java.util.Collections;
import java.util.Random;
import java.util.Random.*;

public class ImprovedQuickSort{

    public static void sort(Comparable[] a)
    {
        shuffle(a); // Eliminate dependence on input
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        int cutOff = 10;
        if (hi <= lo + cutOff){
            insertionSort(a, lo, hi);
            return;
        }

        int medOf3 = medianOfThree(a,lo, lo+(hi-lo+1)/2, hi);
        Elementary.exch(a,medOf3, lo); //on choisi la medianne comme valeur de partition

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        Comparable v = a[lo];
        while (true)
        {
            while (Elementary.less(a[++i], v)) if (i == hi) break;
            while (Elementary.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            Elementary.exch(a, i, j);
        }
        Elementary.exch(a, lo, j);
        return j;
    }

    static void shuffle(Comparable[] arr){
        for(int i=0; i< arr.length;i++){
            int index = (int) (Math.random() * arr.length);
            Comparable temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && Elementary.less(a[j], a[j-1]); j--)
                Elementary.exch(a, j, j-1);
    }

    private static int medianOfThree(Comparable[] a, int i, int j, int k) {
        if(Elementary.less(a[j], a[i]) != Elementary.less(a[k], a[i])){
            return i;
        }
        else if(Elementary.less(a[i], a[j]) != Elementary.less(a[k], a[j])){
            return j;
        }
        else{
            return k;
        }
    }
}
