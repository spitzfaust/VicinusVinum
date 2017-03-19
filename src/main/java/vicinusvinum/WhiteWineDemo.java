package vicinusvinum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vicinusvinum.knn.Instance;
import vicinusvinum.knn.InstanceImpl;

/**
 * Created by tobias.
 */
public class WhiteWineDemo extends Demo<Double> {
    @Override
    protected List<Instance<Double>> getInstances() throws IOException {
        File data;
        data = new File("./datasets/winequality-white.csv");
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
}
