package edu.iis.mto.search;

import java.util.stream.IntStream;

public class SequenceSearcherMock implements SequenceSearcher {

    private static int invocationsCounter = 0;

    public SequenceSearcherMock(){
    }
    @Override public SearchResult search(int elem, int[] seq) {
        SearchResult.Builder builder = SearchResult.builder();
        int index = IntStream.range(0, seq.length).filter(i -> seq[i] == elem).findFirst().orElse(-1);
        if (index != -1) {
            builder.withFound(true);
        } else {
            builder.withFound(false);
        }
        builder.withPosition(index);

        invocationsCounter += 1;
        return builder.build();
    }

    public int getInvocationsCounter() {
        return invocationsCounter;
    }
    public void clearCounter(){
        invocationsCounter = 0;
    }
}
