package vicinusvinum.knn;

import java.util.stream.IntStream;

/**
 * Created by tobias.
 */
public class ManhattanDistanceCalculator implements DistanceCalculator {
    @Override
    public Double calculate(final Double[] a, final Double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("The lengths of the a and b are not equal.");
        }
        return IntStream.range(0, a.length)
                .mapToDouble(i -> Math.abs(a[i] - b[i]))
                .sum();
    }
}
