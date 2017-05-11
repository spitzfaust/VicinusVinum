package vicinusvinum.knn;

import java.util.List;

/**
 * Interface for a nearest neighbor finder.
 */
public interface NearestNeighborFinder {
    /**
     * Find the nearest neighbors.
     * @param toClassify instance we are searching the nearest neighbors for
     * @param classifiedData all neighbors
     * @param numberOfNearestNeighbors how many nearest neighbors are we looking for
     * @param distanceCalculator
     * @param <T> label type of the instances
     * @return list of pairs with label as first and distance as second element
     */
    <T extends Comparable> List<Pair<T, Double>> find(Instance<T> toClassify, List<Instance<T>> classifiedData, long numberOfNearestNeighbors, DistanceCalculator distanceCalculator);
}
