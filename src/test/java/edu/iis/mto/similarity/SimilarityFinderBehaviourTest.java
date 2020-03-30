package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderBehaviourTest {

    private int[] emptySeq = {};
    private int[] eightElemSeq = {1, 2, 3, 4, 5, 6, 7, 8};
    private int[] twoElemSeq = {1, 2};
    private int[] fourElemSeq = {1, 3, 5, 7};
    private int[] sixElemSeq = {1, 2, 3, 4, 5, 6};
    private SimilarityFinder finder;
    private SequenceSearcherMock mock;

    @BeforeEach void setUp() {
        mock = new SequenceSearcherMock();
        mock.clearCounter();
        finder = new SimilarityFinder(mock);
    }

    @Test void testSeqLengthBothZero_ExpectZeroInvocations() {
        finder.calculateJackardSimilarity(emptySeq, emptySeq);
        assertEquals(0, mock.getInvocationsCounter());
    }

    @Test void testSeqLengthEightAndZero_ExpectEightInvocations() {
        finder.calculateJackardSimilarity(eightElemSeq, emptySeq);
        assertEquals(8, mock.getInvocationsCounter());
    }

    @Test void testSeqLengthTwoAndFour_ExpectTwoInvocations() {
        finder.calculateJackardSimilarity(twoElemSeq, fourElemSeq);
        assertEquals(2, mock.getInvocationsCounter());
    }

    @Test void testSeqLengthFourAndSix_ExpectFourInvocations() {
        finder.calculateJackardSimilarity(fourElemSeq, sixElemSeq);
        assertEquals(4, mock.getInvocationsCounter());
    }
}
