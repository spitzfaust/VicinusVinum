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
        return Math.sqrt(
                IntStream.range(0, a.length)
                        .mapToDouble(i -> Math.pow(a[i] - b[i], 2))
                        .sum()
        );
    }
}
