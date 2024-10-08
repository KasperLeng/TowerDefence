package model;
import java.util.*;

// Represents a gameboard that has coordinates, board sizes, and contains other objects. 
public class GameBoard {
    private final int x_Max = 20; // Length of game board
    private final int y_Max = 10; // Width of game board
    private String[][] board;

    private static ArrayList<Buildings> buildings; // Collection of all buildings
    private ArrayList<Monsters> monsters; // Collection of all buildings
    private int money;

    public GameBoard() {
        buildings = new ArrayList<Buildings>();
        money = 500;
        board = new String[y_Max][x_Max];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";  // Empty spaces for now
            }
        }

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

    public void printBoard() {
        // Print the column numbers (top row)
        System.out.print("     ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.printf("%2d  ", i + 1); // Prints column numbers
        }
        System.out.println();

        // Print top border
        System.out.print("   ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print("+---");
        }
        System.out.println("+");

        // Print the rows of the board
        for (int i = 0; i < board.length; i++) {
            // Print the row number
            System.out.printf("%2d ", i + 1);

            // Print the content of each row with vertical borders
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");

            // Print bottom border of each row
            System.out.print("   ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
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

    public void placeBuilding(Position position, String symbol) {
        board[position.getRow()-1][position.getColumn()-1] = symbol;
    }

}