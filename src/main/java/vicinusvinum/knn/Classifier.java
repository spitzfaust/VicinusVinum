package vicinusvinum.knn;

import java.util.List;

/**
 * Interface for classifier
 */
public interface Classifier {
    /**
     * Find the label that occurs the most or the one with the smallest distance.
     * @param neighbors list of labels and distances
     * @param <T> type of the label
     * @return label
     */
    <T extends Comparable> T classify(List<Pair<T, Double>> neighbors);
}
