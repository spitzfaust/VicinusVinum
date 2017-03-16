package vicinusvinum.knn;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tobias.
 */
public class NearestNeighborFinderSingle implements NearestNeighborFinder {

    @Override
    public <T extends Comparable> List<Pair<T, Double>> find(Instance<T> toClassify, List<Instance<T>> classifiedData, long numberOfNeighbors, DistanceCalculator distanceCalculator) {
        if (numberOfNeighbors <= 0) {
            throw new IllegalStateException("Number of neighbors is 0 or below 0.");
        }
        if (classifiedData.isEmpty()) {
            throw new IllegalStateException("Classified data list is empty.");
        }
        if (numberOfNeighbors > classifiedData.size()) {
            throw new IllegalStateException("Number of neighbors is bigger than the size of classified data.");
        }
        return classifiedData.stream()
                .map(instance -> new PairImpl<>(
                        instance.getLabel(),
                        distanceCalculator.calculate(toClassify.getAttributes(), instance.getAttributes())
                ))
                .sorted(Comparator.comparingDouble(Pair::getSecond))
                .limit(numberOfNeighbors)
                .collect(Collectors.toList());
    }
}
