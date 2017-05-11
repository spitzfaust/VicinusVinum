package vicinusvinum;

import java.io.IOException;

import vicinusvinum.knn.Classifier;
import vicinusvinum.knn.ClassifierImpl;
import vicinusvinum.knn.DistanceCalculator;
import vicinusvinum.knn.EuclideanDistanceCalculator;
import vicinusvinum.knn.NearestNeighborFinder;
import vicinusvinum.knn.NearestNeighborFinderParallel;
import vicinusvinum.knn.NearestNeighborFinderSingle;

/**
 * Created by tobias.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        final Demo redWineDemo = new RedWineDemo();
        final Demo whiteWineDemo = new WhiteWineDemo();
        final Demo irisDemo = new IrisDemo();
        final DistanceCalculator distanceCalculator = new EuclideanDistanceCalculator();
        final NearestNeighborFinder nearestNeighborFinder = new NearestNeighborFinderParallel();
        final Classifier classifier = new ClassifierImpl();
        new NearestNeighborFinderParallel();
        System.out.println("===RED WINE DEMO===");
        redWineDemo.runConfusionMatrix(10, classifier, nearestNeighborFinder, distanceCalculator, 3);
        System.out.println("-");
        //redWineDemo.start(1000, 3, distanceCalculator, nearestNeighborFinder);
        //redWineDemo.start(10000, 3, distanceCalculator, nearestNeighborFinder);
        //redWineDemo.start(100000, 3, distanceCalculator, nearestNeighborFinder);

        System.out.println("===WHITE WINE DEMO===");
        whiteWineDemo.runConfusionMatrix(10, classifier, nearestNeighborFinder, distanceCalculator, 3);
        System.out.println("-");
        //whiteWineDemo.start(1000, 3, distanceCalculator, nearestNeighborFinder);
        //whiteWineDemo.start(10000, 3, distanceCalculator, nearestNeighborFinder);
        //whiteWineDemo.start(100000, 3, distanceCalculator, nearestNeighborFinder);

        System.out.println("===IRIS DEMO===");
        irisDemo.runConfusionMatrix(10, classifier, nearestNeighborFinder, distanceCalculator, 3);
        System.out.println("-");
        //irisDemo.start(1000, 3, distanceCalculator, nearestNeighborFinder);
        //irisDemo.start(10000, 3, distanceCalculator, nearestNeighborFinder);
        //irisDemo.start(100000, 3, distanceCalculator, nearestNeighborFinder);

    }
}
