package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public interface Instance<LabelType extends Comparable> {

    LabelType getLabel();

    Double[] getAttributes();

    @Override
    String toString();
}
