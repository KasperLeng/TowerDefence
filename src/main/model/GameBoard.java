package model;
import java.util.*;

// Represents a gameboard that has coordinates, board sizes, and contains other objects. 
public class GameBoard {
    private final int x_Max = 60; // Length of game board
    private final int y_Max = 40; // Width of game board
    private String[][] board;

    private static ArrayList<Buildings> buildings; // Collection of all buildings
    private ArrayList<Monsters> monsters; // Collection of all buildings
    private int money;

    public GameBoard() {
        buildings = new ArrayList<Buildings>();
        money = 500;
        board = new String[x_Max][y_Max];

    }

    public static void addBuilding(Buildings building) {
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

    public void spendMoney(int cost){
        money -= cost;
    }

    public int getMoney(){
        return money;
    }

    public ArrayList<Buildings> getBuildings(){
        return buildings;
    }

    public void startRound() {
        
    }

}