package vicinusvinum.knn;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tobias.
 */
@SuppressWarnings("Duplicates")
public class NearestNeighborFinderSingleTest {
    private NearestNeighborFinder sut;

    @Before
    public void setUp() throws Exception {
        // Given
        sut = new NearestNeighborFinderSingle();
    }

    @Test
    public void findShallReturnTheNearestNeighborsIfTheParametersAreCorrect() throws Exception {
        // Given
        Double[] toClassifyAttributes = new Double[] {1d, 1d, 1d, 1d};
        Instance<String> toClassify = (Instance<String>) mock(Instance.class);
        when(toClassify.getLabel()).thenReturn(null);
        when(toClassify.getAttributes()).thenReturn(toClassifyAttributes);

        Double[] classifiedAAttributes = new Double[] {1d, 1d, 1d, 1d};
        Instance<String> classifiedA = (Instance<String>) mock(Instance.class);
        when(classifiedA.getLabel()).thenReturn("A");
        when(classifiedA.getAttributes()).thenReturn(classifiedAAttributes);

        Double[] classifiedBAttributes = new Double[] {2d, 1d, 1d, 1d};
        Instance<String> classifiedB = (Instance<String>) mock(Instance.class);
        when(classifiedB.getLabel()).thenReturn("B");
        when(classifiedB.getAttributes()).thenReturn(classifiedBAttributes);

        Double[] classifiedCAttributes = new Double[] {3d, 1d, 1d, 1d};
        Instance<String> classifiedC = (Instance<String>) mock(Instance.class);
        when(classifiedC.getLabel()).thenReturn("C");
        when(classifiedC.getAttributes()).thenReturn(classifiedCAttributes);

        Double[] classifiedDAttributes = new Double[] {2d, 2d, 1d, 1d};
        Instance<String> classifiedD = (Instance<String>) mock(Instance.class);
        when(classifiedD.getLabel()).thenReturn("D");
        when(classifiedD.getAttributes()).thenReturn(classifiedDAttributes);

        List<Instance<String>> classifiedInstances = Arrays.asList(classifiedA, classifiedB, classifiedC, classifiedD);

        DistanceCalculator distanceCalculator = mock(DistanceCalculator.class);
        when(distanceCalculator.calculate(toClassifyAttributes, classifiedAAttributes)).thenReturn(0d);
        when(distanceCalculator.calculate(toClassifyAttributes, classifiedBAttributes)).thenReturn(0.5d);
        when(distanceCalculator.calculate(toClassifyAttributes, classifiedCAttributes)).thenReturn(1d);
        when(distanceCalculator.calculate(toClassifyAttributes, classifiedDAttributes)).thenReturn(0.7d);

        // When
        final List<Pair<String>> result = sut.find(toClassify, classifiedInstances, 3, distanceCalculator);

        // Then
        verify(distanceCalculator).calculate(toClassifyAttributes, classifiedAAttributes);
        verify(distanceCalculator).calculate(toClassifyAttributes, classifiedBAttributes);
        verify(distanceCalculator).calculate(toClassifyAttributes, classifiedCAttributes);
        verify(distanceCalculator).calculate(toClassifyAttributes, classifiedDAttributes);

        assertThat(result).hasSize(3);
        assertThat(result).extracting(Pair::getLabel)
                .doesNotContain(classifiedC);
        assertThat(result).extracting(Pair::getLabel)
                .contains(classifiedA, classifiedB, classifiedD);
    }

    @Test
    public void findShallThrowAnExceptionIfTheNumberOfNeighborsToLookForIsLessThanZero() throws Exception {
        // Given
        Instance<String> toClassify = (Instance<String>) mock(Instance.class);
        List<Instance<String>> classified = Arrays.asList((Instance<String>) mock(Instance.class), (Instance<String>) mock(Instance.class));
        DistanceCalculator distanceCalculator = mock(DistanceCalculator.class);

        // When
        Throwable thrown = catchThrowable(() -> sut.find(toClassify, classified, -1, distanceCalculator));

        // Then
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Number of neighbors is 0 or below 0.");

    }

    @Test
    public void findShallThrowAnExceptionIfTheNumberOfNeighborsToLookForIsZero() throws Exception {
        // Given
        Instance<String> toClassify = (Instance<String>) mock(Instance.class);
        List<Instance<String>> classified = Arrays.asList((Instance<String>) mock(Instance.class), (Instance<String>) mock(Instance.class));
        DistanceCalculator distanceCalculator = mock(DistanceCalculator.class);

        // When
        Throwable thrown = catchThrowable(() -> sut.find(toClassify, classified, 0, distanceCalculator));

        // Then
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Number of neighbors is 0 or below 0.");

    }

    @Test
    public void findShallThrowAnExceptionIfTheNumberOfNeighborsIsBiggerThanTheSizeOfClassifiedData() throws Exception {
        // Given
        Instance<String> toClassify = (Instance<String>) mock(Instance.class);
        List<Instance<String>> classified = Arrays.asList((Instance<String>) mock(Instance.class), (Instance<String>) mock(Instance.class));
        DistanceCalculator distanceCalculator = mock(DistanceCalculator.class);

        // When
        Throwable thrown = catchThrowable(() -> sut.find(toClassify, classified, 4, distanceCalculator));

        // Then
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Number of neighbors is bigger than the size of classified data.");

    }

    @Test
    public void findShallThrowAnExceptionIfTheClassifiedDataListIsEmpty() throws Exception {
        // Given
        Instance<String> toClassify = (Instance<String>) mock(Instance.class);
        List<Instance<String>> classified = new ArrayList<>();
        DistanceCalculator distanceCalculator = mock(DistanceCalculator.class);

        // When
        Throwable thrown = catchThrowable(() -> sut.find(toClassify, classified, 3, distanceCalculator));

        // Then
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Classified data list is empty.");

    }
}