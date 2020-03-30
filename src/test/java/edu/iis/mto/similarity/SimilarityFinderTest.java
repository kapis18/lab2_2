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
        assertEquals(finder.calculateJackardSimilarity(emptySeq, emptySeq), 1.0d);
    }

    @Test void checkWhenOneSequenceIsEmptyAndOneIsFilled() {
        assertEquals(finder.calculateJackardSimilarity(eightElemSeq, emptySeq), 0.0d);
    }

    @Test void checkWhenBothSequencesAreTheSame() {
        assertEquals(finder.calculateJackardSimilarity(eightElemSeq, eightElemSeq), 1.0d);
    }

    @Test void checkWhenBothSequencesAreDifferent_expected_0_25() {
        assertEquals(finder.calculateJackardSimilarity(eightElemSeq, twoElemSeq), 0.25d);
    }

    @Test void checkWhenBothSequencesAreDifferent_expected_0_5() {
        assertEquals(finder.calculateJackardSimilarity(eightElemSeq, fourElemSeq), 0.5d);
    }

    @Test void checkWhenBothSequencesAreDifferent_expected_0_75() {
        assertEquals(finder.calculateJackardSimilarity(eightElemSeq, sixElemSeq), 0.75d);
    }
}
