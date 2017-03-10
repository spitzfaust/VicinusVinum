package vicinusvinum.knn;

import java.util.List;

/**
 * Created by tobias.
 */
public interface NearestNeighborFinder {
    <T extends Comparable> List<DistanceComparator> find(Instance<T> toClassify, List<Instance<T>> classifiedData, long numberOfNeighbors, DistanceCalculator distanceCalculator);
}
