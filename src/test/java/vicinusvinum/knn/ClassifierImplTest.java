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
        String mostFrequentLabel = "A";
        Pair<String, Double> pair1 = (Pair<String, Double>) mock(Pair.class);
        when(pair1.getFirst()).thenReturn(mostFrequentLabel);
        Pair<String, Double> pair2 = (Pair<String, Double>) mock(Pair.class);
        when(pair2.getFirst()).thenReturn(mostFrequentLabel);
        String lessFrequentLabel = "B";
        Pair<String, Double> pair3 = (Pair<String, Double>) mock(Pair.class);
        when(pair3.getFirst()).thenReturn(lessFrequentLabel);

        // When
        final String result = sut.classify(Arrays.asList(pair1, pair2, pair3));

        // Then
        verify(pair1).getFirst();
        verify(pair2).getFirst();
        verify(pair3).getFirst();
        assertThat(result).isEqualTo("A");
    }

    @Test
    public void classifyShallReturnTheClassificationWithTheSmallestDistanceIfTheClassificationsAreDistributedEven() throws Exception {
        // Given
        Pair<String, Double> pair1 = (Pair<String, Double>) mock(Pair.class);
        when(pair1.getFirst()).thenReturn("A");
        when(pair1.getSecond()).thenReturn(12d);

        Pair<String, Double> pair2 = (Pair<String, Double>) mock(Pair.class);
        when(pair2.getFirst()).thenReturn("B");
        when(pair2.getSecond()).thenReturn(33d);

        Pair<String, Double> pair3 = (Pair<String, Double>) mock(Pair.class);
        when(pair3.getFirst()).thenReturn("C");
        when(pair3.getSecond()).thenReturn(6d);

        // When
        final String result = sut.classify(Arrays.asList(pair1, pair2, pair3));

        // Then
        verify(pair1).getFirst();
        verify(pair2).getFirst();
        verify(pair3, times(2)).getFirst();

        assertThat(result).isEqualTo("C");
    }



}