package vicinusvinum;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import vicinusvinum.knn.Classifier;
import vicinusvinum.knn.ClassifierImpl;
import vicinusvinum.knn.DistanceCalculator;
import vicinusvinum.knn.Pair;
import vicinusvinum.knn.Instance;
import vicinusvinum.knn.NearestNeighborFinder;

/**
 * Created by tobias.
 */
abstract class Demo {



    public <T extends Comparable> void start(int numberOfTests, int numberOfNeighbors, DistanceCalculator distanceCalculator, NearestNeighborFinder nearestNeighborFinder) throws IOException {
        List<Instance<T>> instances = getInstances();
        List<Instance<T>> classifiedData = prepareClassifiedData(instances);
        ConfusionMatrix<T> confusionMatrix = new ConfusionMatrix<>();
        Classifier classifier = new ClassifierImpl();
        int index = 0;
        Instant startTime = Instant.now();
        while(index < numberOfTests) {
            for (Instance<T> toClassify : instances) {
                if (index < numberOfTests) {
                    final List<Pair<T, Double>> neighbors = nearestNeighborFinder.find(toClassify, classifiedData, numberOfNeighbors, distanceCalculator);
                    final T predictedLabel = classifier.classify(neighbors);
                    confusionMatrix.increaseValue(toClassify.getLabel(), predictedLabel);
                    ++index;
                } else {
                    break;
                }
            }
        }
        Instant endTime = Instant.now();
        System.out.println(confusionMatrix.getMatrix());
        System.out.println(Duration.between(startTime, endTime));
    }

    protected  <T extends Comparable> List<Instance<T>> prepareClassifiedData(List<Instance<T>> dataToPrepare) {
        Map<T, Long> occurrences = dataToPrepare.stream()
                .collect(Collectors.groupingBy(Instance::getLabel, Collectors.counting()));
        Long min = occurrences.values().stream()
                .min(Comparator.comparingLong(v -> v))
                .get();
        List<Instance<T>> classifiedData = new ArrayList<>();
        System.out.println(occurrences);
        for (T classification : occurrences.keySet()) {
            classifiedData.addAll(
                    dataToPrepare.stream()
                            .filter(i -> i.getLabel().equals(classification))
                            .limit(min)
                            .collect(Collectors.toList())
            );
        }
        return classifiedData;
    }

    protected abstract <T extends Comparable> List<Instance<T>> getInstances() throws IOException;
}
