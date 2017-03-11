package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public interface DistanceComparator<T extends Comparable> extends Comparable<DistanceComparator> {

    Instance<T> getInstance();

    Double getDistance();

    int compareTo(DistanceComparator distanceComparator);
}
