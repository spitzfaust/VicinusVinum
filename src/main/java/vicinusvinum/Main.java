package vicinusvinum;

import java.io.IOException;

import vicinusvinum.knn.EuclideanDistanceCalculator;
import vicinusvinum.knn.NearestNeighborFinderSingle;

/**
 * Created by tobias.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        final Demo redWineDemo = new RedWineDemo();
        final Demo whiteWineDemo = new WhiteWineDemo();
        final Demo irisDemo = new IrisDemo();

        //redWineDemo.start(10000, 3, new EuclideanDistanceCalculator(), new NearestNeighborFinderSingle());
        //whiteWineDemo.start(10000, 3, new EuclideanDistanceCalculator(), new NearestNeighborFinderSingle());
        irisDemo.start(10000, 3, new EuclideanDistanceCalculator(), new NearestNeighborFinderSingle());

    }
}
