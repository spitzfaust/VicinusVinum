package vicinusvinum.knn;

import java.util.List;

/**
 * Created by tobias.
 */
public interface NearestNeighborFinder {
    <T extends Comparable> List<Pair<T, Double>> find(Instance<T> toClassify, List<Instance<T>> classifiedData, long numberOfNeighbors, DistanceCalculator distanceCalculator);
}
