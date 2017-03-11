package vicinusvinum.knn;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tobias.
 */
public class ClassifierImplTest {

    private Classifier sut;

    @Before
    public void setUp() throws Exception {
        // Given
        sut = new ClassifierImpl();
    }

    @Test
    public void classifyShallReturnTheClassificationThatOccurredMostFrequent() throws Exception {
        // Given
        Instance<String> mostFrequentInstance = (Instance<String>) mock(Instance.class);
        when(mostFrequentInstance.getClassification()).thenReturn("A");
        DistanceComparator<String> distanceComparator1 = (DistanceComparator<String>) mock(DistanceComparator.class);
        when(distanceComparator1.getInstance()).thenReturn(mostFrequentInstance);
        DistanceComparator<String> distanceComparator2 = (DistanceComparator<String>) mock(DistanceComparator.class);
        when(distanceComparator2.getInstance()).thenReturn(mostFrequentInstance);
        Instance<String> lessFrequentInstance = (Instance<String>) mock(Instance.class);
        when(lessFrequentInstance.getClassification()).thenReturn("B");
        DistanceComparator<String> distanceComparator3 = (DistanceComparator<String>) mock(DistanceComparator.class);
        when(distanceComparator3.getInstance()).thenReturn(lessFrequentInstance);

        // When
        final String result = sut.classify(Arrays.asList(distanceComparator1, distanceComparator2, distanceComparator3));

        // Then
        verify(distanceComparator1).getInstance();
        verify(distanceComparator2).getInstance();
        verify(distanceComparator3).getInstance();
        assertThat(result).isEqualTo("A");
    }

    @Test
    public void classifyShallReturnTheClassificationWithTheSmallestDistanceIfTheClassificationsAreDistributedEven() throws Exception {
        // Given
        Instance<String> instanceA = (Instance<String>) mock(Instance.class);
        when(instanceA.getClassification()).thenReturn("A");
        DistanceComparator<String> distanceComparator1 = (DistanceComparator<String>) mock(DistanceComparator.class);
        when(distanceComparator1.getInstance()).thenReturn(instanceA);
        when(distanceComparator1.getDistance()).thenReturn(12d);

        Instance<String> instanceB = (Instance<String>) mock(Instance.class);
        when(instanceB.getClassification()).thenReturn("B");
        DistanceComparator<String> distanceComparator2 = (DistanceComparator<String>) mock(DistanceComparator.class);
        when(distanceComparator2.getInstance()).thenReturn(instanceB);
        when(distanceComparator2.getDistance()).thenReturn(33d);

        Instance<String> instanceC = (Instance<String>) mock(Instance.class);
        when(instanceC.getClassification()).thenReturn("C");
        DistanceComparator<String> distanceComparator3 = (DistanceComparator<String>) mock(DistanceComparator.class);
        when(distanceComparator3.getInstance()).thenReturn(instanceC);
        when(distanceComparator3.getDistance()).thenReturn(6d);

        // When
        final String result = sut.classify(Arrays.asList(distanceComparator1, distanceComparator2, distanceComparator3));

        // Then
        verify(distanceComparator1).getInstance();
        verify(distanceComparator2).getInstance();
        verify(distanceComparator3, times(2)).getInstance();

        assertThat(result).isEqualTo("C");
    }



}