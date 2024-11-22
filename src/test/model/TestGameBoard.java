package model;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameBoard {
    private GameBoard gameBoard;
    private ArrayList<Buildings> buildings;
    private ArrayList<Monsters> monsters;

    
    @BeforeEach
    void runBefore() {
        this.gameBoard = new GameBoard("Kasper's game", 0);
        this.buildings = new ArrayList<Buildings>();
        this.monsters = new ArrayList<Monsters>();
    }

    @Test
    void testConstructor() {
        assertEquals(buildings, gameBoard.getBuildings());
        assertEquals(monsters, gameBoard.getMonsters());
        assertEquals(500, gameBoard.getMoney());
        gameBoard.startRound();
        assertEquals(1, gameBoard.getRound());
        

    }

    @Test
    void testBuyBuildings() {
        Buildings archerTower = new ArcherTower(1, new Position(1, 3), 150);
        assertEquals(buildings, gameBoard.getBuildings());
        GameBoard.addBuilding(archerTower);
        gameBoard.spendMoney(archerTower.getCost());
        assertEquals(500-archerTower.getCost(), gameBoard.getMoney());
        buildings.add(archerTower);
        assertEquals(buildings, gameBoard.getBuildings());
        assertEquals(buildings.get(0), gameBoard.getBuildings().get(0));

        gameBoard.addMoney(100);
        assertEquals(gameBoard.getMoney(), 500);

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

    @Test
    void testPositionEqual() {
        Position testPosition1 = new Position(4, 5);
        Position testPosition2 = new Position(4, 5);
        assertEquals(testPosition1, testPosition2);

    }

    
}
