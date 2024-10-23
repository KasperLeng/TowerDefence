package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBuildings {
    private ArcherTower archerTower;
    private Buildings goldMine;
    private GameBoard gameBoard;

    @BeforeEach
    void runBefore() {
        archerTower = new ArcherTower(1, new Position(5, 4), 150);
        goldMine = new GoldMine(1, new Position(4, 5), 100);
        gameBoard = new GameBoard("Kasper's Game", 0);
    }

    @Test
    void testConstructor() {
        assertEquals(5, archerTower.getPosition().getRow());
        assertEquals(4, archerTower.getPosition().getColumn());

        assertEquals(1, archerTower.getNum());

        assertEquals(4, goldMine.getPosition().getRow());
        assertEquals(5, goldMine.getPosition().getColumn());
        assertEquals(1, goldMine.getNum());

        assertEquals(100, archerTower.getCost());
        assertEquals(200, goldMine.getCost());

        assertEquals("Archer Tower", archerTower.getType());
        assertEquals("Gold Mine", goldMine.getType());
    }

    @Test
    void testTakeDamage() {
        archerTower.takeDamage(100);
        assertEquals(50, archerTower.getHealth());
        archerTower.takeDamage(200);
        assertEquals(0, archerTower.getHealth());
        assertFalse(archerTower.getStatus());


        goldMine.takeDamage(99);
        assertEquals(1, goldMine.getHealth());
        goldMine.takeDamage(2);
        assertEquals(0, goldMine.getHealth());
        assertEquals(false, goldMine.getStatus());

    }

    @Test
    void testAttack() {
        Monsters archerGoblin1 = new ArcherGoblin(new Position(4, 4));
        Monsters archerGoblin2 = new ArcherGoblin(new Position(10, 10));
        gameBoard.placeBuilding(new Position(2, 2), "A");
        archerTower.attack(archerGoblin1);
        archerTower.attack(archerGoblin2);
        assertEquals(90, archerGoblin1.getHealth());
        assertEquals(100, archerGoblin2.getHealth());

    }

}
