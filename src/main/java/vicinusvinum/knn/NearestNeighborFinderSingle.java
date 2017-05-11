package vicinusvinum.knn;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Nearest neighbor finder running on a single thread.
 */
public class NearestNeighborFinderSingle implements NearestNeighborFinder {

    @Override
    public <T extends Comparable> List<Pair<T, Double>> find(Instance<T> toClassify, List<Instance<T>> classifiedData, long numberOfNearestNeighbors, DistanceCalculator distanceCalculator) {
        if (numberOfNearestNeighbors <= 0) {
            throw new IllegalStateException("Number of neighbors is 0 or below 0.");
        }
        if (classifiedData.isEmpty()) {
            throw new IllegalStateException("Classified data list is empty.");
        }
        if (numberOfNearestNeighbors > classifiedData.size()) {
            throw new IllegalStateException("Number of neighbors is bigger than the size of classified data.");
        }
        return classifiedData.stream()
                /* map each instance to a pair of the label
                   and the distance to the element to be classified */
                .map(instance -> new PairImpl<>(
                        instance.getLabel(),
                        distanceCalculator.calculate(toClassify.getAttributes(), instance.getAttributes())
                ))
                // sort the pairs ascending by distance
                .sorted(Comparator.comparingDouble(Pair::getSecond))
                .limit(numberOfNearestNeighbors)
                .collect(Collectors.toList());
    }
}
