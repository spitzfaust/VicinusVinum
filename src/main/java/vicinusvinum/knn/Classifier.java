package vicinusvinum.knn;

import java.util.List;

/**
 * Created by tobias.
 */
public interface Classifier {
    <T extends Comparable> T classify(List<Pair<T, Double>> neighbors);
}
