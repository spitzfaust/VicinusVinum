package vicinusvinum.knn;

import java.util.List;

/**
 * Created by tobias.
 */
public class Instance<ClassificationType extends Comparable> {
    private final ClassificationType classification;
    private final Double[] attributes;

    public Instance(ClassificationType classification, Double[] attributes) {
        this.classification = classification;
        this.attributes = attributes;
    }

    public ClassificationType getClassification() {
        return classification;
    }

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
