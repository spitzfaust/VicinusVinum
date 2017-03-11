package vicinusvinum.knn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tobias.
 */
public class NearestNeighborFinderSingle implements NearestNeighborFinder {

    @Override
    public <T extends Comparable> List<DistanceComparator<T>> find(Instance<T> toClassify, List<Instance<T>> classifiedData, long numberOfNeighbors, DistanceCalculator distanceCalculator) {
        if (numberOfNeighbors <= 0) {
            throw new IllegalStateException("Number of neighbors is 0 or below 0.");
        }
        if (classifiedData.isEmpty()) {
            throw new IllegalStateException("Classified data list is empty.");
        }
        if (numberOfNeighbors > classifiedData.size()) {
            throw new IllegalStateException("Number of neighbors is bigger than the size of classified data.");
        }
        final List<DistanceComparator<T>> distances = new ArrayList<>();
        for (Instance<T> datum : classifiedData) {
            distances.add(new DistanceComparatorImpl<>(
                    datum,
                    distanceCalculator.calculate(toClassify.getAttributes(), datum.getAttributes())
            ));
        }
        return distances.stream()
                .sorted()
                .limit(numberOfNeighbors)
                .collect(Collectors.toList());
    }
}
