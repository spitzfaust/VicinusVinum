package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public class InstanceImpl<ClassificationType extends Comparable> implements Instance<ClassificationType> {
    private final ClassificationType classification;
    private final Double[] attributes;

    public InstanceImpl(ClassificationType classification, Double[] attributes) {
        this.classification = classification;
        this.attributes = attributes;
    }

    @Override
    public ClassificationType getClassification() {
        return classification;
    }

    @Override
    public Double[] getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "classification=" + classification +
                ", attributes=" + attributes.length +
                '}';
    }
}
