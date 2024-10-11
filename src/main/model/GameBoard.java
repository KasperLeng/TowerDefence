package model;

import java.util.*;

// Represents a gameboard that has coordinates, board sizes, and contains other objects. 
public class GameBoard {
    private final int xmax = 20; // Length of game board
    private final int ymax = 10; // Width of game board
    private String[][] board;

    private static ArrayList<Buildings> buildings; // Collection of all buildings
    private static ArrayList<Monsters> monsters; // Collection of all buildings
    private int money;

    public GameBoard() {
        buildings = new ArrayList<Buildings>();
        monsters = new ArrayList<Monsters>();
        this.money = 500;
        this.board = new String[ymax][xmax];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";  // Empty spaces for now
            }
        }

    }

    public static void addBuilding(Buildings building) {
        buildings.add(building);
    }

    public static void addMonster(Monsters monster) {
        monsters.add(monster);
    }

    // public boolean checkWinCondition() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'checkWinCondition'");
    // }

    // public void updateMonsters() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'updateMonsters'");
    // }

    // public void updateBuildings() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'updateBuildings'");
    // }


    public void spendMoney(int cost) {
        money -= cost;
    }

    public int getMoney() {
        return money;
    }

    public ArrayList<Buildings> getBuildings() {
        return buildings;
    }

    public void startRound() {
        System.out.println("Round Started");
    }

    public void placeBuilding(Position position, String symbol) {
        board[position.getColumn() - 1][position.getRow() - 1] = symbol;
    }

    public Object getMonsters() {
        return monsters;
    }

    public String[][] getBoard() {
        return board;
    }

}