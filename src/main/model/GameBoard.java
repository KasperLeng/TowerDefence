package model;
import java.util.*;

// Represents a gameboard that has coordinates, board sizes, and contains other objects. 
public class GameBoard {
    private final int x_Max = 500; // Length of game board
    private final int y_Max = 400; // Width of game board

    private ArrayList<Buildings> buildings; // Collection of all buildings
    private ArrayList<Monsters> monsters; // Collection of all buildings

    public GameBoard() {
        buildings = new ArrayList<Buildings>();

    }

    public void addBuilding(Buildings building) {
        buildings.add(building);
    }

    public void addMonster(Monsters monster){
        monsters.add(monster);
    }

    public boolean checkWinCondition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkWinCondition'");
    }

    public void updateMonsters() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMonsters'");
    }

    public void updateBuildings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBuildings'");
    }

}