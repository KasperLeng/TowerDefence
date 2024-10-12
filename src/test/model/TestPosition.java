package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPosition {
    private Position position;

    @BeforeEach
    void runBefore() {
        position = new Position(1, 1);
    }
    
    @Test
    void testPositionEquals() {
        assertEquals(position, new Position(1, 1));
    }
}
