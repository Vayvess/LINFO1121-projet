package com.jetbrains;

import java.util.Arrays;

public class improvedMergeSort {
    private static int[] aux;

    public static void merge(int[] arr, int low, int middle, int high) {
        int i = low;
        int j = middle + 1;
        if (high + 1 - low >= 0) System.arraycopy(arr, low, aux, low, high + 1 - low);

        for (int k = low; k <= high; k++) {
            if (i > middle) arr[k] = aux[j++];
            else if (j > high) arr[k] = aux[i++];
            else if (aux[j] < aux[i]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }

    public static void sort(int[] a, int lo, int hi, int limit) {
        //Sort les petites arrays (dont la longueur <= limit) avec l'Insertion Sort
        if (hi - lo <= limit) {
            insertionSort(a, lo, hi);
        }
        //Sinon utiliser le Merge Sort classique
        else {
            int middle = (lo + hi) / 2;
            sort(a, lo, middle, limit);
            sort(a, middle + 1, hi, limit);

            //Skip le merge si la liste est déjà triée sur l'intervalle lo..hi
            if (a[middle] > a[middle + 1]){
                merge(a, lo, middle, hi);
            }
        }
    }

    public static void insertionSort(int[] a, int hi, int lo) {
        for (int i = hi; i < lo; i++) {
            int temp = a[i + 1];
            int j = i + 1;
            while (j > hi && a[j - 1] > temp) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }

    public static void sort(int[] a, int limit) {
        //Utilise une seule array auxiliaire pour le merge
        aux = new int[a.length];
        sort(a, 0, a.length - 1, limit);
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 7, 6, 9, 8, 4, 1, 3};
        System.out.println(Arrays.toString(arr));
        insertionSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {5, 4, 7, 6, 9, 8, 4, 1, 3};
        sort(arr2, 5);
        System.out.println(Arrays.toString(arr2));
    }
}
