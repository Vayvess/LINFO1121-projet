package groupe4;


import francois.ImprovedQuickSort;
import groupe4.part1.Merge;
import groupe4.part1.Quick;
import raph.QuickSort;
import max.improvedMergeSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static boolean isArraySorted(Integer[] array){
        for(int i = 0 ; i < array.length -1; i++){
            if(array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void ImprovedMergeSort(Integer[] array){
        long start = System.nanoTime();
        improvedMergeSort.sort(array, 50);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("ImprovedMergeSort has taken " + elapsedTimeInSecond + " seconds");
    }

    public static void ImprovedQuickSort(Integer[] array){
        long start = System.nanoTime();
        ImprovedQuickSort.sort(array);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("ImprovedQuickSort has taken " + elapsedTimeInSecond + " seconds");
    }

    public static void RaphaelQuickSort(Integer[] array){
        long start = System.nanoTime();
        QuickSort.sort(array);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("RaphaelQuickSort has taken " + elapsedTimeInSecond + " seconds");
    }

    public static void BookQuickSort(Integer[] array){
        long start = System.nanoTime();
        Quick.sort(array);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("BookQuickSort has taken " + elapsedTimeInSecond + " seconds");
    }

    public static void BookMergeSort(Integer[] array){
        long start = System.nanoTime();
        Merge.sort(array);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("BookMergeSort has taken " + elapsedTimeInSecond + " seconds");
    }

    public static void ParallelMergeSort(Integer[] array){
        raph.ParallelMergeSort<Integer> task =
                new raph.ParallelMergeSort<>(array, 0, array.length -1, new Integer[array.length]);

        long start = System.nanoTime();
        new ForkJoinPool().invoke(task);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("ParallelMergeSort has taken " + elapsedTimeInSecond + " seconds");
    }

    public static void ParallelQuickSort(Integer[] array){
        raph.ParallelQuickSort<Integer> task = new raph.ParallelQuickSort<Integer>(array, 0, array.length - 1);
        long start = System.nanoTime();
        new ForkJoinPool().invoke(task);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("ParallelQuickSort has taken " + elapsedTimeInSecond + " seconds");
    }

    public static void main(String[] args){
        int size = 10_000_000; Random rng = new Random();
        Integer[] main = new Integer[size];
        for(int i = 0 ; i < size ; i++){
            main[i] = rng.nextInt(999_999);
        }

        System.out.println("The sample has size " + size);
        Integer[] ImprovedQuickSortTest = new Integer[size];
        System.arraycopy(main, 0, ImprovedQuickSortTest, 0, size);
        ImprovedQuickSort(ImprovedQuickSortTest);

        Integer[] ImprovedMergeSortTest = new Integer[size];
        System.arraycopy(main, 0, ImprovedMergeSortTest, 0, size);
        ImprovedMergeSort(ImprovedMergeSortTest);
        System.out.println(isArraySorted(ImprovedMergeSortTest));

        Integer[] BookMergeSortTest = new Integer[size];
        System.arraycopy(main, 0, BookMergeSortTest, 0, size);
        BookMergeSort(BookMergeSortTest);
        System.out.println(isArraySorted(BookMergeSortTest));


        Integer[] BookQuickSortTest = new Integer[size];
        System.arraycopy(main, 0, BookQuickSortTest, 0, size);
        BookQuickSort(BookQuickSortTest);

        Integer[] RaphaelQuickSortTest = new Integer[size];
        System.arraycopy(main, 0, RaphaelQuickSortTest, 0, size);
        RaphaelQuickSort(RaphaelQuickSortTest);

        Integer[] ParallelQuickSortTest = new Integer[size];
        System.arraycopy(main, 0, ParallelQuickSortTest, 0, size);
        ParallelQuickSort(ParallelQuickSortTest);

        Integer[] ParallelMergeSortTest = new Integer[size];
        System.arraycopy(main, 0, ParallelMergeSortTest, 0, size);
        ParallelMergeSort(ParallelMergeSortTest);
    }
}
