package vicinusvinum.knn;

import java.util.List;

/**
 * Created by tobias.
 */
public interface DistanceCalculator {
    Double calculate(final Double[] a, final Double[] b);
}
