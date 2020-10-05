import java.util.Random;

public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        long start = System.nanoTime();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        return (System.nanoTime()-start);
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
    public static void main(String[] args) {
        String alg1 = "Shell";
        String alg2 = "Merge";
        int N = 800000;
        int T = 10;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        System.out.printf("For %d random Doubles\n %s is", N, alg1);
        System.out.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}