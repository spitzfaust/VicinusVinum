package vicinusvinum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import vicinusvinum.knn.Classifier;
import vicinusvinum.knn.ClassifierImpl;
import vicinusvinum.knn.DistanceComparator;
import vicinusvinum.knn.EuclideanDistanceCalculator;
import vicinusvinum.knn.Instance;
import vicinusvinum.knn.InstanceImpl;
import vicinusvinum.knn.NearestNeighborFinder;
import vicinusvinum.knn.NearestNeighborFinderSingle;

/**
 * Created by tobias.
 */
public class Main {
    private static List<Instance<Double>> getRedWineInstances() throws IOException {
        File data = new File("./datasets/winequality-red.csv");
        List<Instance<Double>> instances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                } else if (!line.trim().isEmpty()) {
                    final String[] tokens = line.split(";");
                    final Double[] convertedTokens = new Double[tokens.length];
                    for (int i = 0; i < tokens.length; i++) {
                        convertedTokens[i] = Double.parseDouble(tokens[i]);
                    }
                    instances.add(new InstanceImpl<>(convertedTokens[convertedTokens.length - 1], Arrays.copyOfRange(convertedTokens, 0, convertedTokens.length - 1)));
                }
            }
        }
        return instances;
    }

    private static List<Instance<String>> getIrisInstances() throws IOException {
        File data = new File("./datasets/iris.txt");
        List<Instance<String>> instances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    final String[] tokens = line.split(",");
                    final Double[] convertedTokens = new Double[tokens.length - 1];
                    for (int i = 0; i < tokens.length - 1; i++) {
                        convertedTokens[i] = Double.parseDouble(tokens[i]);
                    }
                    instances.add(new InstanceImpl<>(tokens[tokens.length - 1], convertedTokens));
                }

            }
        }
        return instances;
    }

    public static <T extends Comparable> void runNearestNeighborDemo(List<Instance<T>> instances) {
        Map<T, Long> occurrences = instances.stream()
                .collect(Collectors.groupingBy(Instance::getClassification, Collectors.counting()));
        Long min = occurrences.values().stream()
                .min(Comparator.comparingLong(v -> v))
                .get();
        List<Instance<T>> classifiedData = new ArrayList<>();
        System.out.println(occurrences);
        for (T classification : occurrences.keySet()) {
            classifiedData.addAll(
                    instances.stream()
                            .filter(i -> i.getClassification().equals(classification))
                            .limit(min)
                            .collect(Collectors.toList())
            );
        }
        System.out.println(classifiedData.size());
        Random random = new Random();
        final Instance<T> toClassify = classifiedData.get(random.nextInt(classifiedData.size()));
        NearestNeighborFinder nearestNeighborFinder = new NearestNeighborFinderSingle();
        Classifier classifier = new ClassifierImpl();
        final List<DistanceComparator<T>> neighbors = nearestNeighborFinder.find(toClassify, classifiedData, 3, new EuclideanDistanceCalculator());
        final T classification = classifier.classify(neighbors);
        System.out.println(classification);
        System.out.println(toClassify.getClassification());
    }

    public static void main(String[] args) throws IOException {
        List<Instance<Double>> redWineInstances = getRedWineInstances();
        List<Instance<String>> irisInstances = getIrisInstances();
        runNearestNeighborDemo(redWineInstances);
        runNearestNeighborDemo(irisInstances);
    }
}
