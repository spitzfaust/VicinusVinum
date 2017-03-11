package vicinusvinum.knn;

import java.util.List;

/**
 * Created by tobias.
 */
public class EuclideanDistanceCalculator implements DistanceCalculator {


    @Override
    public Double calculate(final Double[] a, final Double[] b) {
        Double sum = 0d;

        if (a.length != b.length) {
            throw new IllegalStateException("The lengths of the a and b are not equal.");
        }

        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }
}
