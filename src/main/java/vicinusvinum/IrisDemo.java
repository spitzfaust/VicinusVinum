package vicinusvinum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vicinusvinum.knn.Instance;
import vicinusvinum.knn.InstanceImpl;

/**
 * Created by tobias.
 */
public class IrisDemo extends Demo {

    @Override
    protected List<Instance<String>> getInstances() throws IOException {
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

    @Override
    protected <T extends Comparable> List<Instance<T>> prepareClassifiedData(List<Instance<T>> dataToPrepare) {
        List<T> labels = dataToPrepare.stream()
                .map(Instance::getLabel)
                .distinct()
                .collect(Collectors.toList());
        List<Instance<T>> classifiedData = new ArrayList<>();
        System.out.println(labels);
        for (T label : labels) {
            classifiedData.addAll(
                    dataToPrepare.stream()
                            .filter(i -> i.getLabel().equals(label))
                            .limit(35)
                            .collect(Collectors.toList())
            );
        }
        return classifiedData;
    }
}
