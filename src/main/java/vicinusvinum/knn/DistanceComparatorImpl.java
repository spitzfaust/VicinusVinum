package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public class DistanceComparatorImpl<T extends Comparable> implements DistanceComparator<T> {
    private final Instance<T> instance;
    private final Double distance;

    public DistanceComparatorImpl(Instance<T> instance, Double distance) {
        this.instance = instance;
        this.distance = distance;
    }

    @Override
    public Instance<T> getInstance() {
        return instance;
    }

    @Override
    public Double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(DistanceComparator distanceComparator) {
        return this.distance.compareTo(distanceComparator.getDistance());
    }
}
