package vicinusvinum.knn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tobias.
 */
public class NearestNeighborFinderSingle implements NearestNeighborFinder {

    @Override
    public <T extends Comparable> List<DistanceComparator> find(Instance<T> toClassify, List<Instance<T>> classifiedData, long numberOfNeighbors, DistanceCalculator distanceCalculator) {
        if(numberOfNeighbors <= 0) {
            throw new IllegalStateException();
        }
        if(numberOfNeighbors > classifiedData.size()) {
            throw new IllegalStateException();
        }
        if(classifiedData.size() == 0) {
            throw new IllegalStateException();
        }
        final List<DistanceComparator> distances = new ArrayList<>();
        for (Instance<T> datum : classifiedData) {
            distances.add(new DistanceComparator(datum,distanceCalculator.calculate(datum, toClassify)));
        }
        return distances.stream()
                .sorted()
                .limit(numberOfNeighbors)
                .collect(Collectors.toList());
    }
}
