package vicinusvinum.knn;

/**
 * Created by tobias.
 */
public class PairImpl<T1, T2> implements Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public PairImpl(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public T1 getFirst() {
        return first;
    }

    @Override
    public T2 getSecond() {
        return second;
    }

    @Override
    public void setFirst(T1 first) {
        this.first = first;
    }

    @Override
    public void setSecond(T2 second) {
        this.second = second;
    }
}
