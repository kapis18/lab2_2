package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {

    private int[] emptySeq = {};
    private int[] eightElemSeq = {1, 2, 3, 4, 5, 6, 7, 8};
    private int[] twoElemSeq = {1, 2};
    private int[] fourElemSeq = {1, 3, 5, 7};
    private int[] sixElemSeq = {1, 2, 3, 4, 5, 6};
    private SimilarityFinder finder;
    private SimilarityFinder behaviourFinder;
    private SequenceSearcherMock mock;

    @BeforeEach void setUp() {
        mock = new SequenceSearcherMock();
        mock.clearCounter();
        finder = new SimilarityFinder(testSearcher);
        behaviourFinder = new SimilarityFinder(mock);
    }

    //State tests
    @Test void checkWhenBothSequencesEmpty() {
        assertEquals(1.0d, finder.calculateJackardSimilarity(emptySeq, emptySeq));
    }

    @Test void checkWhenOneSequenceIsEmptyAndOneIsFilled() {
        assertEquals(0.0d, finder.calculateJackardSimilarity(eightElemSeq, emptySeq));
    }

    @Test void checkWhenBothSequencesAreTheSame() {
        assertEquals(1.0d, finder.calculateJackardSimilarity(eightElemSeq, eightElemSeq));
    }

    @Test void checkWhenBothSequencesAreDifferent_expected_0_25() {
        assertEquals(0.25d, finder.calculateJackardSimilarity(eightElemSeq, twoElemSeq));
    }

    @Test void checkWhenBothSequencesAreDifferent_expected_0_5() {
        assertEquals(0.5d, finder.calculateJackardSimilarity(eightElemSeq, fourElemSeq));
    }

    @Test void checkWhenBothSequencesAreDifferent_expected_0_75() {
        assertEquals(0.75d, finder.calculateJackardSimilarity(eightElemSeq, sixElemSeq));
    }

    //Behaviour tests
    @Test void testSeqLengthBothZero_ExpectZeroInvocations() {
        behaviourFinder.calculateJackardSimilarity(emptySeq, emptySeq);
        assertEquals(0, mock.getInvocationsCounter());
    }

    @Test void testSeqLengthEightAndZero_ExpectEightInvocations() {
        behaviourFinder.calculateJackardSimilarity(eightElemSeq, emptySeq);
        assertEquals(8, mock.getInvocationsCounter());
    }

    @Test void testSeqLengthTwoAndFour_ExpectTwoInvocations() {
        behaviourFinder.calculateJackardSimilarity(twoElemSeq, fourElemSeq);
        assertEquals(2, mock.getInvocationsCounter());
    }

    @Test void testSeqLengthFourAndSix_ExpectFourInvocations() {
        behaviourFinder.calculateJackardSimilarity(fourElemSeq, sixElemSeq);
        assertEquals(4, mock.getInvocationsCounter());
    }

    private SequenceSearcher testSearcher = (elem, seq) -> {
        int index = IntStream.range(0, seq.length).filter(i -> seq[i] == elem).findFirst().orElse(-1);
        if (index != -1) {
            return SearchResult.builder().withFound(true).build();
        } else {
            return SearchResult.builder().withFound(false).build();
        }
    };

}
