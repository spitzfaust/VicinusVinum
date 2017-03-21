package vicinusvinum;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import vicinusvinum.knn.Classifier;
import vicinusvinum.knn.ClassifierImpl;
import vicinusvinum.knn.DistanceCalculator;
import vicinusvinum.knn.Instance;
import vicinusvinum.knn.NearestNeighborFinder;
import vicinusvinum.knn.Pair;
import vicinusvinum.knn.PairImpl;

/**
 * Created by tobias.
 */
abstract class Demo<T extends Comparable> {
    protected List<Instance<T>> instances;

    public Demo() {
        try {
            instances = getInstances();
            System.out.println(instances.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(int numberOfTests, int numberOfNeighbors, DistanceCalculator distanceCalculator, NearestNeighborFinder nearestNeighborFinder) throws IOException {
        System.out.println("Number of tests: " + numberOfTests);
        System.out.println("Number of neighbors: " + numberOfNeighbors);
        System.out.println("Distance calculator: " + distanceCalculator.getClass());
        System.out.println("Nearest neighbor finder: " + nearestNeighborFinder.getClass());
        final Classifier classifier = new ClassifierImpl();
        // Time test
        final Instant startTime = Instant.now();
        for (int i = 0; i < numberOfTests; ++i) {
            final List<Pair<T, Double>> neighbors = nearestNeighborFinder.find(instances.get(0), instances, numberOfNeighbors, distanceCalculator);
            classifier.classify(neighbors);
        }
        final Instant endTime = Instant.now();

        System.out.println("Duration: ");
        System.out.println(Duration.between(startTime, endTime));
    }


    public void runConfusionMatrix(int numberOfFolds, Classifier classifier, NearestNeighborFinder nearestNeighborFinder, DistanceCalculator distanceCalculator, int numberOfNeighbors) {
        final List<Pair<T, T>> testResults = new LinkedList<>();
        List<Pair<List<Instance<T>>, List<Instance<T>>>> folds = new ArrayList<>();
        Collections.shuffle(instances, new Random(System.nanoTime()));
        int foldSize = (int) Math.ceil((double) instances.size() / numberOfFolds);
        for (int i = 0; i < instances.size(); i += foldSize) {
            int end = Math.min(instances.size(), i + foldSize);
            final List<Instance<T>> train = new ArrayList<>(instances.size() - (end - i));
            if (i > 0 && end < instances.size()) {
                train.addAll(instances.subList(0, i));
                train.addAll(instances.subList(end, instances.size()));
            } else if (i > 0 && end == instances.size()) {
                train.addAll(instances.subList(0, i));
            } else if (i == 0 && end < instances.size()) {
                train.addAll(instances.subList(end, instances.size()));
            }
            folds.add(new PairImpl<>(train, instances.subList(i, end)));
        }
        for (Pair<List<Instance<T>>, List<Instance<T>>> fold : folds) {
            for (Instance<T> testDatum : fold.getSecond()) {
                final List<Pair<T, Double>> neighbors = nearestNeighborFinder.find(testDatum, fold.getFirst(), numberOfNeighbors, distanceCalculator);
                final T predicted = classifier.classify(neighbors);
                testResults.add(new PairImpl<>(testDatum.getLabel(), predicted));
            }
        }
        final ConfusionMatrix<T> confusionMatrix = new ConfusionMatrix<>();
        for (Pair<T, T> testResult : testResults) {
            confusionMatrix.increaseValue(testResult.getFirst(), testResult.getSecond());
        }
        System.out.println("Confusion Matrix: ");
        System.out.println(confusionMatrix.getMatrix());
        System.out.println("Accuracy: " + confusionMatrix.getAccuracy());
    }

    protected abstract List<Instance<T>> getInstances() throws IOException;
}
