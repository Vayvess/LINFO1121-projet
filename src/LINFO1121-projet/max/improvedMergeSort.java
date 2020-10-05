package max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class improvedMergeSort {
    private static Comparable[] aux;

    public static void merge(Comparable[] arr, int low, int middle, int high) {
        //Fast Merge
        //Copie arr[low..middle, high..middle+1] dans le tableau aux

        //Copie la première moitie du tableau arr dans aux dans l'ordre croissant
        int p = low;
        for (int k = low; k <= middle; k++) {
            aux[p] = arr[k];
            p++;
        }
        //Copie la seconde moitie du tableau arr dans aux dans l'ordre decroissant
        for (int k = high; k > middle; k--) {
            aux[p] = arr[k];
            p++;
        }

        //Il ne faut plus verifier que chacune des moitiés est vide car les deux pointeurs se rejoignent
        //L'un va du debut à la fin, l'autre va de la fin vers le debut
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

            //Skip le merge si la liste est déjà triee sur l'intervalle lo..hi
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

}
