package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {

    private int[] emptySeq = {};
    private int[] eightElemSeq = {1, 2, 3, 4, 5, 6, 7, 8};
    private int[] twoElemSeq = {1, 2};
    private int[] fourElemSeq = {1, 3, 5, 7};
    private int[] sixElemSeq = {1, 2, 3, 4, 5, 6};
    private SimilarityFinder finder;

    @BeforeEach void setUp() {
        finder = new SimilarityFinder(new SequenceSearcherMock());
    }

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
}
