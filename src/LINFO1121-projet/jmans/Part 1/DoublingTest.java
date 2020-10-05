import java.util.Random;

public class DoublingTest {
    public static double timeTrial(int N) {
        int MAX = 1000000;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = new Random().nextInt(2*MAX)-MAX;
        long start = System.nanoTime();
        Selection.sort(a);
        return (System.nanoTime()-start)/1000000d;
    }
    static double log2(double x) {
        return Math.log(x)/Math.log(2);
    }

    public static void main(String[] args) {
        double prev = timeTrial(125);
        int mesures = 10;
        double[] ratios = new double[mesures], times = new double[mesures];
        int i = 0;
        System.out.print('[');
        for (int N = 250; i < mesures; N += N) {
            double time = timeTrial(N);
            times[i] = time;
            System.out.printf("%4.1f", time/prev);
            ratios[i++] = time/prev;
            if (i < mesures) System.out.print(",");
            else System.out.println(" ]");
            prev = time;
        }
        double sum = 0;
        for (double ratio : ratios) {
            sum += ratio;
        } double mean = sum/mesures;
        double max = 0, min = times[0];
        for (double time : times) {
            if (time > max) max = time;
            else if (time < min) min = time;
        }
        System.out.println("b = " + log2(mean));
        StdDraw.setXscale(7, log2(250*Math.pow(2,ratios.length)));
        StdDraw.setYscale(log2(min),log2(max));
        StdDraw.setPenRadius(.01);
        StdDraw.point(log2(250), log2(times[0]));
        for (int j = 1; j < ratios.length; j++) {
            StdDraw.setPenRadius(.003);
            double logx = log2(250*Math.pow(2,j));
            double logy = log2(times[j]);
            StdDraw.line(log2(250*Math.pow(2,j-1)),log2(times[j-1]),logx, logy);
            StdDraw.setPenRadius(.01);
            StdDraw.point(logx, logy);
        }
        StdDraw.show();
    }
}