package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameBoard {
    private GameBoard gameBoard;
    private ArrayList<Buildings> buildings;
    private ArrayList<Monsters> monsters;
    private String[][] board;

    
    @BeforeEach
    void runBefore() {
        this.gameBoard = new GameBoard();
        this.buildings = new ArrayList<Buildings>();
        this.monsters = new ArrayList<Monsters>();
        this.board = new String[20][10];
    }

    @Test
    void testConstructor() {
        assertEquals(buildings, gameBoard.getBuildings());
        assertEquals(monsters, gameBoard.getMonsters());
        assertEquals(500, gameBoard.getMoney());
            
    }

    @Test
    void testBuyBuildings() {
        Buildings archerTower = new ArcherTower(1, new Position(1, 3));
        assertEquals(buildings, gameBoard.getBuildings());
        GameBoard.addBuilding(archerTower);
        gameBoard.placeBuilding(archerTower.getPosition(), "A");
        buildings.add(archerTower);
        assertEquals(buildings, gameBoard.getBuildings());
        assertEquals(buildings.get(0), gameBoard.getBuildings().get(0));


    }

    @Test
    void testAddMonsters() {
        Monsters gob = new ArcherGoblin(new Position(0, 0));
        Monsters ske = new SkeletonWarrior(new Position(1, 0));
        monsters.add(gob);
        monsters.add(ske);
        GameBoard.addMonster(gob);
        GameBoard.addMonster(ske);

        assertEquals(monsters, gameBoard.getMonsters());

    }

    
}
