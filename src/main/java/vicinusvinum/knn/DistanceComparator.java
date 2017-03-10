package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public class DistanceComparator implements Comparable<DistanceComparator> {
    private final Instance<?> instance;
    private final Double distance;

    public DistanceComparator(Instance<?> instance, Double distance) {
        this.instance = instance;
        this.distance = distance;
    }

    public Instance<?> getInstance() {
        return instance;
    }

    public Double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(DistanceComparator distanceComparator) {
        return this.distance.compareTo(distanceComparator.getDistance());
    }
}
