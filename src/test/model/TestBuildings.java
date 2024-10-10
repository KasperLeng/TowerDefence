package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBuildings {
    private Buildings archerTower;
    private Buildings goldMine;

    @BeforeEach
    void runBefore() {
        archerTower = new ArcherTower(1, new Position(5, 4));
        goldMine = new GoldMine(1, new Position(4, 5));
    }

    @Test
    void testConstructor() {
        assertEquals(new Position(5, 4), archerTower.getPosition());
        assertEquals(1, archerTower.getNum());

        assertEquals(new Position(4, 5), goldMine.getPosition());
        assertEquals(1, goldMine.getNum());
    }

    @Test
    void testTakeDamage() {
        archerTower.takeDamage(100);
        assertEquals(100, archerTower.getHealth());
        archerTower.takeDamage(200);
        assertEquals(0, archerTower.getHealth());
        assertFalse(archerTower.getStatus());


        goldMine.takeDamage(399);
        assertEquals(1, goldMine.getHealth());
        goldMine.takeDamage(2);
        assertEquals(0, goldMine.getHealth());
        assertEquals(false, goldMine.getStatus());

    }

}
