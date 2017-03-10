package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public interface DistanceCalculator {
    <T extends Comparable> Double calculate(Instance<T> a, Instance<T> b);
}
