package vicinusvinum.knn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tobias.
 */
public class NearestNeighborFinderParallel implements NearestNeighborFinder {

    @Override
    public <T extends Comparable> List<DistanceComparator<T>> find(final Instance<T> toClassify, final List<Instance<T>> classifiedData, final long numberOfNeighbors, final DistanceCalculator distanceCalculator) {
        if (numberOfNeighbors <= 0) {
            throw new IllegalStateException("Number of neighbors is 0 or below 0.");
        }
        if (classifiedData.isEmpty()) {
            throw new IllegalStateException("Classified data list is empty.");
        }
        if (numberOfNeighbors > classifiedData.size()) {
            throw new IllegalStateException("Number of neighbors is bigger than the size of classified data.");
        }
        return classifiedData.parallelStream()
                .map(instance -> new DistanceComparatorImpl<>(
                        instance,
                        distanceCalculator.calculate(toClassify.getAttributes(), instance.getAttributes())
                    ))
                .sorted()
                .limit(numberOfNeighbors)
                .collect(Collectors.toList());

    }
}
