package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public class InstanceImpl<LabelType extends Comparable> implements Instance<LabelType> {
    private final LabelType label;
    private final Double[] attributes;

    public InstanceImpl(LabelType label, Double[] attributes) {
        this.label = label;
        this.attributes = attributes;
    }

    @Override
    public LabelType getLabel() {
        return label;
    }

    @Override
    public Double[] getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "label=" + label +
                ", attributes=" + attributes.length +
                '}';
    }
}
