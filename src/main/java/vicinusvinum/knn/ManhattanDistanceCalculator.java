package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public class ManhattanDistanceCalculator implements DistanceCalculator {
    @Override
    public <T extends Comparable> Double calculate(Instance<T> a, Instance<T> b) {
        final Double[] aAttributes = a.getAttributes();
        final Double[] bAttributes = b.getAttributes();
        Double sum = 0d;

        if(aAttributes.length != bAttributes.length) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < aAttributes.length; i++) {
            sum += Math.abs(aAttributes[i] - bAttributes[i]);
        }
        return sum;
    }
}
