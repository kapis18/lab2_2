package edu.iis.mto.search;

public class SequenceSearcherMock implements SequenceSearcher {

    private static int invocationsCounter = 0;

    public SequenceSearcherMock() {
    }

    @Override public SearchResult search(int elem, int[] seq) {
        invocationsCounter += 1;
        return SearchResult.builder().build();
    }

    public int getInvocationsCounter() {
        return invocationsCounter;
    }

    public void clearCounter() {
        invocationsCounter = 0;
    }
}
