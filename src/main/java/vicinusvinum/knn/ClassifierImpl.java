package vicinusvinum.knn;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tobias.
 */
public class ClassifierImpl implements Classifier {

    public <T extends Comparable> T classify(List<DistanceComparator<T>> neighbors) {
        Map<T, Long> occurrences = neighbors.stream()
                .collect(Collectors.groupingBy(dc ->  dc.getInstance().getClassification(), Collectors.counting()));
        if (new HashSet<>(occurrences.values()).size() == 1) {
            return neighbors.stream()
                    .min(Comparator.comparingDouble(DistanceComparator::getDistance))
                    .get()
                    .getInstance()
                    .getClassification();
        }
        return occurrences.entrySet().stream()
                .max((o1, o2) -> o1.getValue() > o2.getValue() ? 1 : -1)
                .get()
                .getKey();
    }
}
