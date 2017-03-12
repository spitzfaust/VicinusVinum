package vicinusvinum.knn;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.*;

/**
 * Created by tobias.
 */
public class ManhattanDistanceCalculatorTest {
    private DistanceCalculator sut;

    @Before
    public void setUp() throws Exception {
        // Given
        this.sut = new ManhattanDistanceCalculator();
    }

    @Test
    public void calculateShallReturnZeroIfTheVectorsHaveADistanceOfZero() throws Exception {
        // Given
        Double[] a = {1d, 1d, 1d, 1d};
        Double[] b = {1d, 1d, 1d, 1d};

        // When
        final Double result = sut.calculate(a, b);

        // Then
        assertThat(result).isEqualTo(0d);
    }

    @Test
    public void calculateShallReturnOneIfTheVectorsHaveADistanceOfOne() throws Exception {
        // Given
        Double[] a = {1d, 1d, 1d, 1d};
        Double[] b = {2d, 1d, 1d, 1d};

        // When
        final Double result = sut.calculate(a, b);

        // Then
        assertThat(result).isEqualTo(1d);
    }

    @Test
    public void calculateShallThrowAnExceptionIfTheVectorsHaveDifferentLengths() throws Exception {
        // Given
        Double[] a = {1d, 1d, 1d, 1d, 1d};
        Double[] b = {1d, 1d, 1d, 1d};

        // When
        Throwable thrown = catchThrowable(() -> sut.calculate(a, b));

        // Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The lengths of the a and b are not equal.");
    }

}