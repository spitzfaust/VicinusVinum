package vicinusvinum.knn;

/**
 * Interface for distance calculator.
 */
@FunctionalInterface
public interface DistanceCalculator {
    /**
     * Calculate the distance between two vectors.
     * @param a first vector
     * @param b second vector
     * @return distance
     */
    Double calculate(final Double[] a, final Double[] b);
}
