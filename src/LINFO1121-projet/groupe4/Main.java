package groupe4;


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

    public static void ParallelMergeSort(Integer[] array){
        raph.ParallelMergeSort<Integer> task =
                new raph.ParallelMergeSort<Integer>(array, 0, array.length -1, new Integer[array.length],
                        Comparator.comparing(Integer::intValue));

        long start = System.nanoTime();
        new ForkJoinPool().invoke(task);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println(elapsedTimeInSecond + " seconds");
    }

    public static void ParallelQuickSort(Integer[] array){
        raph.ParallelQuickSort<Integer> task =
                new raph.ParallelQuickSort<Integer>(array, 0, array.length - 1,
                        Comparator.comparing(Integer::intValue));

        long start = System.nanoTime();
        new ForkJoinPool().invoke(task);
        long end = System.nanoTime();

        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;

        System.out.println(elapsedTimeInSecond + " seconds");
    }

    public static void main(String[] args){
        int size = 10_000_000; Random rng = new Random();
        Integer[] main = new Integer[size];
        for(int i = 0 ; i < size ; i++){
            main[i] = rng.nextInt(100000);
        }

        Integer[] ParallelQuickSortTest = new Integer[size];
        System.arraycopy(main, 0, ParallelQuickSortTest, 0, size);
        ParallelQuickSort(ParallelQuickSortTest);

        Integer[] ParallelMergeSortTest = new Integer[size];
        System.arraycopy(main, 0, ParallelMergeSortTest, 0, size);
        ParallelMergeSort(ParallelMergeSortTest);
    }
}
