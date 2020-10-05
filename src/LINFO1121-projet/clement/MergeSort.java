

public class MergeSort {
    /**
     * Pre-conditions: a[lo..mid] and a[mid+1..hi] are sorted
     * Post-conditions: a[lo..hi] is sorted
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static void newMerge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        if(aux[hi]!=null) {
            for (int k = lo; k <= hi; k++) {
                if (i > mid) {
                    a[k] = aux[j++];
                } else if (j > hi) {
                    a[k] = aux[i++];
                } else if (less(aux[j],(aux[i]))) {
                    a[k] = aux[j++];
                } else {
                    a[k] = aux[i++];
                }
            }

        }
        else{
            for (int k = lo; k <= hi; k++) {
                if (i > mid) {
                    aux[k] = a[j++];
                } else if (j > hi) {
                    aux[k] = a[i++];
                } else if (a[j].compareTo(a[i]) < 0) {
                    aux[k] = a[j++];
                } else {
                    aux[k] = a[i++];
                }
            }
        }
    }

    // Mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo<hi){
            int mid=((hi-lo)/2 )+lo;
            sort(a,aux,lo,mid);
            sort(a,aux,mid+1,hi);

            if(a[mid+1].compareTo(a[mid]) <= 0) {
                newMerge(a, aux, lo, mid, hi);
            }
        }
    }

    private static void insertionSort(Comparable[] a) {
        int n =a.length;
        for(int i =1 ; i<n ; i++ ) {
            for(int j=i ; j>0 && less(a[j],a[j-1]); j--){
                exchange(a,j,j-1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) <0;
    }
    private static void exchange(Comparable[] a, int i , int j){
        Comparable t =a[i];
        a[i]=a[j];
        a[j]=t;
    }
    /**
     * Rearranges the array in ascending order, using the natural order
     */
    public static void sort(Comparable[] a) {
        if(a.length>15) {
            if (!less(a[0], a[1])) {
                exchange(a, 0, 1);
            }
            Comparable[] aux = new Comparable[a.length];
            sort(a, aux, 0, a.length - 1);
            if(a.length%2 !=0){
                newMerge(a,aux,0,a.length/2,a.length-1);

            }
        }
        else {
            insertionSort(a);
        }
    }
}