package vicinusvinum.knn;

import java.util.List;

/**
 * Created by tobias.
 */
public class EuklidianDistanceCalculator implements DistanceCalculator {


    @Override
    public <T extends Comparable> Double calculate(Instance<T> a, Instance<T> b) {
            final Double[] aAttributes = a.getAttributes();
            final Double[] bAttributes = b.getAttributes();
            Double sum = 0d;

            if(aAttributes.length != bAttributes.length) {
                throw new IllegalStateException();
            }

            for (int i = 0; i < aAttributes.length; i++) {
                sum += Math.pow(aAttributes[i] - bAttributes[i], 2);
            }
            return Math.sqrt(sum);
    }
}
