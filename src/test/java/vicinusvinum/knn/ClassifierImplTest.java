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
        when(mostFrequentInstance.getLabel()).thenReturn("A");
        Pair<String> pair1 = (Pair<String>) mock(Pair.class);
        when(pair1.getLabel()).thenReturn(mostFrequentInstance);
        Pair<String> pair2 = (Pair<String>) mock(Pair.class);
        when(pair2.getLabel()).thenReturn(mostFrequentInstance);
        Instance<String> lessFrequentInstance = (Instance<String>) mock(Instance.class);
        when(lessFrequentInstance.getLabel()).thenReturn("B");
        Pair<String> pair3 = (Pair<String>) mock(Pair.class);
        when(pair3.getLabel()).thenReturn(lessFrequentInstance);

        // When
        final String result = sut.classify(Arrays.asList(pair1, pair2, pair3));

        // Then
        verify(pair1).getLabel();
        verify(pair2).getLabel();
        verify(pair3).getLabel();
        assertThat(result).isEqualTo("A");
    }

    @Test
    public void classifyShallReturnTheClassificationWithTheSmallestDistanceIfTheClassificationsAreDistributedEven() throws Exception {
        // Given
        Instance<String> instanceA = (Instance<String>) mock(Instance.class);
        when(instanceA.getLabel()).thenReturn("A");
        Pair<String> pair1 = (Pair<String>) mock(Pair.class);
        when(pair1.getLabel()).thenReturn(instanceA);
        when(pair1.getDistance()).thenReturn(12d);

        Instance<String> instanceB = (Instance<String>) mock(Instance.class);
        when(instanceB.getLabel()).thenReturn("B");
        Pair<String> pair2 = (Pair<String>) mock(Pair.class);
        when(pair2.getLabel()).thenReturn(instanceB);
        when(pair2.getDistance()).thenReturn(33d);

        Instance<String> instanceC = (Instance<String>) mock(Instance.class);
        when(instanceC.getLabel()).thenReturn("C");
        Pair<String> pair3 = (Pair<String>) mock(Pair.class);
        when(pair3.getLabel()).thenReturn(instanceC);
        when(pair3.getDistance()).thenReturn(6d);

        // When
        final String result = sut.classify(Arrays.asList(pair1, pair2, pair3));

        // Then
        verify(pair1).getLabel();
        verify(pair2).getLabel();
        verify(pair3, times(2)).getLabel();

        assertThat(result).isEqualTo("C");
    }



}