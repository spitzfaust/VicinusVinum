package vicinusvinum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vicinusvinum.knn.Instance;

/**
 * Created by tobias.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File data = new File("./datasets/winequality-red.csv");
        List<Instance<Double>> instances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                } else {
                    final String[] tokens = line.split(";");
                    final Double[] convertedTokens = new Double[tokens.length];
                    for (int i = 0; i < tokens.length; i++) {
                        convertedTokens[i] = Double.parseDouble(tokens[i]);
                    }
                    instances.add(new Instance<>(convertedTokens[convertedTokens.length - 1], Arrays.copyOfRange(convertedTokens, 0, convertedTokens.length - 1)));
                }
            }
        }
        for (Instance instance : instances) {
            System.out.println(instance);
        }
    }
}
