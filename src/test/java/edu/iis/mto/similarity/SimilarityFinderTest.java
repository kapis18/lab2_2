package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {

    private int[] emptySeq1 = {};
    private int[] emptySeq2 = {};
    private int[] filledSeq1 = {1, 2, 3, 4, 5};
    private SimilarityFinder finder;

    @BeforeEach void setUp() {
        finder = new SimilarityFinder(new SequenceSearcherMock());
    }

    @Test void checkWhenBothSequencesEmpty() {
        assertEquals(finder.calculateJackardSimilarity(emptySeq1, emptySeq2), 1.0d);
    }

    @Test void checkWhenOneSequenceIsEmptyAndOneIsFilled() {
        assertEquals(finder.calculateJackardSimilarity(filledSeq1, emptySeq2), 0.0d);
    }
}
