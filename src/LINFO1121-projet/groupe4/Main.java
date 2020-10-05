package groupe4;

import groupe4.part1.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static <T extends Comparable<T>> double time(String alg, T[] a) {

        if (alg.equals("Java")){
            long start = System.nanoTime();
            Arrays.sort(a);
            return (System.nanoTime() - start);
        }

        if (alg.equals("clemMergeSort")){
            long start = System.nanoTime();
            clement.MergeSort.sort(a);
            return (System.nanoTime() - start);
        }

        if (alg.equals("jmansInsertionSort")){
            long start = System.nanoTime();
            jmans.MyInsertionSort.sort(a);
            return (System.nanoTime() - start);
        }

        if (alg.equals("maxMergeSort")){
            long start = System.nanoTime();
            max.improvedMergeSort.sort(a, 15);
            return (System.nanoTime() - start);
        }

        if (alg.equals("franQuickSort")){
            long start = System.nanoTime();
            francois.ImprovedQuickSort.sort(a);
            return (System.nanoTime() - start);
        }

        if(alg.equals("raphMergeSort")){
            long start = System.nanoTime();
            raph.MergeSort.sort(a);
            return (System.nanoTime() - start);
        }

        if (alg.equals("raphQuickSort")){
            long start = System.nanoTime();
            raph.QuickSort.sort(a);

            return (System.nanoTime() - start);
        }

        if (alg.equals("raphParallelQuickSort")){
            long start = System.nanoTime();
            raph.ParallelQuickSort<T> task = new raph.ParallelQuickSort<>(a, 0, a.length - 1);
            new ForkJoinPool().invoke(task);
            return (System.nanoTime() - start);
        }

        if (alg.equals("Insertion")){
            long start = System.nanoTime();
            Insertion.sort(a);
            return (System.nanoTime() - start);
        }

        if (alg.equals("Selection")){
            long start = System.nanoTime();
            Selection.sort(a);
            return (System.nanoTime() - start);
        }

        if (alg.equals("Shell")){
            long start = System.nanoTime();
            Shell.sort(a);
            return (System.nanoTime() - start);
        }
        if (alg.equals("Merge")){
            long start = System.nanoTime();
            Merge.sort(a);
            return (System.nanoTime() - start);
        };

        if (alg.equals("Quick")){
            long start = System.nanoTime();
            Quick.sort(a);
            return (System.nanoTime() - start);
        }
        return 0;
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = new Random().nextDouble();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args){
        String alg2 = "maxMergeSort";
        String alg1 = "raphMergeSort";
        int N = 1_000_000;
        int T = 10;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        System.out.printf("For %d random Doubles\n %s is", N, alg1);
        System.out.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}
