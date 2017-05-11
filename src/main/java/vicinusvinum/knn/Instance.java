package vicinusvinum.knn;

/**
 * Interface for an Instance.
 */
public interface Instance<LabelType extends Comparable> {

    /**
     * Get the label of the instance.
     * @return label
     */
    LabelType getLabel();

    /**
     * Get the attributes of the instance.
     * @return attributes
     */
    Double[] getAttributes();

    @Override
    String toString();
}
