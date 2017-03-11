package vicinusvinum.knn;

import java.util.List;

/**
 * Created by tobias.
 */
public interface Instance<ClassificationType extends Comparable> {

    ClassificationType getClassification();

    Double[] getAttributes();

    @Override
    String toString();
}
