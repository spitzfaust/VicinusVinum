package vicinusvinum.knn;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tobias.
 */
public interface Classifier {
    <T extends Comparable> T classify(List<DistanceComparator<T>> neighbors);
}
