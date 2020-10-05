package groupe4;

import groupe4.part1.*;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static <T extends Comparable<T>> double time(String alg, T[] a) {

        if (alg.equals("jmansInsertionSort")){
            long start = System.nanoTime();
            jmans.MyInsertionSort.sort(a);
            return (System.nanoTime()-start);
        }

        if (alg.equals("maxMergeSort")){
            long start = System.nanoTime();
            max.improvedMergeSort.sort(a, 50);
            return (System.nanoTime()-start);
        }

        if (alg.equals("franQuickSort")){
            long start = System.nanoTime();
            francois.ImprovedQuickSort.sort(a);
            return (System.nanoTime()-start);
        }

        if (alg.equals("raphQuickSort")){
            long start = System.nanoTime();
            raph.QuickSort.sort(a);
            return (System.nanoTime()-start);
        }

        if (alg.equals("raphParallelQuickSort")){
            long start = System.nanoTime();
            raph.ParallelQuickSort<T> task = new raph.ParallelQuickSort<>(a, 0, a.length - 1);
            new ForkJoinPool().invoke(task);
            return (System.nanoTime()-start);
        }

        if (alg.equals("Insertion")){
            long start = System.nanoTime();
            Insertion.sort(a);
            return (System.nanoTime()-start);
        }

        if (alg.equals("Selection")){
            long start = System.nanoTime();
            Selection.sort(a);
            return (System.nanoTime()-start);
        }

        if (alg.equals("Shell")){
            long start = System.nanoTime();
            Shell.sort(a);
            return (System.nanoTime()-start);
        }
        if (alg.equals("Merge")){
            long start = System.nanoTime();
            Merge.sort(a);
            return (System.nanoTime()-start);
        };

        if (alg.equals("Quick")){
            long start = System.nanoTime();
            Quick.sort(a);
            return (System.nanoTime()-start);
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
        String alg1 = "raphParallelQuickSort";
        String alg2 = "Quick";
        int N = 100_000;
        int T = 100;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        System.out.printf("For %d random Doubles\n %s is", N, alg1);
        System.out.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}
