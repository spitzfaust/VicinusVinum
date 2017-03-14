package vicinusvinum.knn;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by tobias.
 */
public class EuclideanDistanceCalculator implements DistanceCalculator {


    @Override
    public Double calculate(final Double[] a, final Double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("The lengths of the a and b are not equal.");
        }
        Double sum = 0d;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }
}
