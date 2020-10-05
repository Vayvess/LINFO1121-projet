package max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class improvedMergeSort {
    private static Comparable[] aux;

    public static void merge(Comparable[] arr, int low, int middle, int high) {
        //Fast Merge
        //Copie arr[low..middle, high..middle+1] dans le tableau aux

        //Copie la première moitié du tableau arr dans aux dans l'ordre croissant
        int p = low;
        for (int k = low; k <= middle; k++) {
            aux[p] = arr[k];
            p++;
        }
        //Copie la seconde moitié du tableau arr dans aux dans l'ordre décroissant
        for (int k = high; k > middle; k--) {
            aux[p] = arr[k];
            p++;
        }

        //Il ne faut plus vérifier que chacune des moitiés est vide car les deux pointeurs se rejoignent
        //L'un va du début à la fin, l'autre va de la fin vers le début
        int i = low;
        int j = high;
        int k = low;
        while (k <= high) {
            if (Elementary.less(aux[i], aux[j])) {
                arr[k] = aux[i++];
            }
            else {
                arr[k] = aux[j--];
            }
            k++;
        }
    }

    public static void sort(Comparable[] a, int lo, int hi, int limit) {
        //Trie les petites listes (dont la longueur <= limit) directement avec l'Insertion Sort
        if (hi - lo <= limit) {
            insertionSort(a, lo, hi);
        }
        //Sinon utilise le Merge Sort classique
        else {
            int middle = (lo + hi) / 2;
            sort(a, lo, middle, limit);
            sort(a, middle + 1, hi, limit);

            //Skip le merge si la liste est déjà triée sur l'intervalle lo..hi
            if (Elementary.less(a[middle + 1], a[middle])){
                merge(a, lo, middle, hi);
            }
        }
    }

    public static void insertionSort(Comparable[] a, int hi, int lo) {
        for (int i = hi; i < lo; i++) {
            Comparable temp = a[i + 1];
            int j = i + 1;
            while (j > hi && Elementary.less(temp, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }

    public static void sort(Comparable[] a, int limit) {
        //Utilise une seule liste auxiliaire pour merge
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, limit);
    }

    public static void main(String[] args) {
        Comparable[] arr = {5, 4, 7, 6, 9, 8, 4, 1, 3};
        insertionSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Elementary.isSorted(arr));

        Comparable[] arr2 = {5, 4, 7, 6, 9, 8, 4, 1, 3};
        sort(arr2, 5);
        System.out.println(Arrays.toString(arr2));
        System.out.println(Elementary.isSorted(arr2));

        Comparable[] arr3 = {"test", "uioadh", "pojfhd", "jlfkqds", "bbbbb"};
        sort(arr3, 1);
        System.out.println(Arrays.toString(arr3));
        System.out.println(Elementary.isSorted(arr3));

        Comparable[] arr4 = {1, 10, 7, 6, 9, 8, 4, 1, 3, 1, 10, 7, 6, 9, 8, 4, 1, 3, 1, 10, 7, 6, 9, 8, 4, 1, 3,
                1, 10, 7, 6, 9, 8, 4, 1, 3, 1, 10, 7, 6, 9, 8, 4, 1, 3, 1, 10, 7, 6, 9, 8, 4, 1, 3};
        sort(arr4, 1);
        System.out.println(Arrays.toString(arr4));
        System.out.println(Elementary.isSorted(arr4));
    }
}
