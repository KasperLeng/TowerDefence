package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameBoard {
    private GameBoard gameBoard;
    private ArrayList<Buildings> buildings;

    
    @BeforeEach
    void runBefore() {
        gameBoard = new GameBoard();
        buildings = new ArrayList<Buildings>();
    }

    @Test
    void testConstructor() {
        assertEquals(new ArrayList<Buildings>(), gameBoard.getBuildings());
        assertEquals(new ArrayList<Monsters>(), gameBoard.getMonsters());
        assertEquals(500, gameBoard.getMoney());
            
    }

    @Test
    void testBuyBuildings(){
        Buildings aTower = new ArcherTower(1, new Position(1, 3));
        assertEquals(buildings, gameBoard.getBuildings());
        GameBoard.addBuilding(aTower);
        buildings.add(aTower);
        assertEquals(buildings, gameBoard.getBuildings());
        assertEquals(buildings.get(0), gameBoard.getBuildings().get(0));

        
    }

    
}
