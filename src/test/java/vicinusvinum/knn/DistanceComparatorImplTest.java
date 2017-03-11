package vicinusvinum.knn;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by tobias.
 */
public class DistanceComparatorImplTest {

    private Instance<String> instance;
    private Double distance;
    private DistanceComparator<String> sut;

    @Before
    public void setUp() throws Exception {
        // Given
        instance = (Instance<String>) mock(Instance.class);
        distance = 1d;
        sut = new DistanceComparatorImpl<>(instance, distance);

    }

    @Test
    public void getInstanceShallReturnTheInstance() throws Exception {

        // When
        Instance<String> result = sut.getInstance();

        // Then
        assertThat(result).isEqualTo(instance);
    }

    @Test
    public void getDistanceShallReturnTheDistance() throws Exception {

        // When
        Double result = sut.getDistance();

        // Then
        assertThat(result).isEqualTo(distance);
    }

    @Test
    public void compareToShallReturnAPositiveIntegerIfTheComparedDistanceIsLess() throws Exception {
        // Given
        DistanceComparator<String> comp = new DistanceComparatorImpl<>((Instance<String>) mock(Instance.class), 0d);

        // When
        int result = sut.compareTo(comp);

        // Then
        assertThat(result).isGreaterThan(0);
    }

    @Test
    public void compareToShallReturnANegativeIntegerIfTheComparedDistanceIsGreater() throws Exception {
        // Given
        DistanceComparator<String> comp = new DistanceComparatorImpl<>((Instance<String>) mock(Instance.class), 2d);

        // When
        int result = sut.compareTo(comp);

        // Then
        assertThat(result).isLessThan(0);
    }

    @Test
    public void compareToShallReturnZeroIfTheComparedDistanceIsEqual() throws Exception {
        // Given
        DistanceComparator<String> comp = new DistanceComparatorImpl<>((Instance<String>) mock(Instance.class), 1d);

        // When
        int result = sut.compareTo(comp);

        // Then
        assertThat(result).isEqualTo(0);
    }

}