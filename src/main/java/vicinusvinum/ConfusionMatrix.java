package vicinusvinum;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tobias.
 */
public class ConfusionMatrix <T extends Comparable> {
    protected Map<T, Map<T, Integer>> matrix;
    protected int total;
    protected int correct;

    public ConfusionMatrix() {
        matrix = new TreeMap<>();
    }

    public void increaseValue(T actualLabel, T predictedLabel) {

        if (!matrix.containsKey(actualLabel)) {
            matrix.put(actualLabel, new TreeMap<>());
        }

        if (!matrix.get(actualLabel).containsKey(predictedLabel)) {
            matrix.get(actualLabel).put(predictedLabel, 0);
        }

        int currentValue = this.matrix.get(actualLabel).get(predictedLabel);
        this.matrix.get(actualLabel).put(predictedLabel, currentValue + 1);

        total += 1;

        if (actualLabel.equals(predictedLabel)) {
            correct += 1;
        }
    }

    public Map<T, Map<T, Integer>> getMatrix() {
        return matrix;
    }

    public int getTotal() {
        return total;
    }

    public int getCorrect() {
        return correct;
    }

    public double getAccuracy() {
        return (double) correct / (double) total;
    }
}

