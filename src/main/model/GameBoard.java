package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/**
 * Represents a gameboard that contains coordinates, defines board dimensions, and manages
 * buildings and monsters placed on it.
 */
public class GameBoard implements Writable {
    private final int xmax = 20;        // Length of game board (number of columns)
    private final int ymax = 10;        // Width of game board (number of rows)
    private String[][] board;           // 2D array representing the game board grid
    private String name;
    private int round;

    private static ArrayList<Buildings> buildings;   // Collection of all buildings on the board
    private static ArrayList<Monsters> monsters;     // Collection of all monsters on the board
    private int money;                               // Player's available money

    /**
     * Constructs a new GameBoard. Initializes an empty board and sets the initial money.
     * Each cell of the board is initialized to a blank space (" ").
     * 
     * MODIFIES: this
     * EFFECTS: Initializes the board to be empty, sets starting money to 500, and 
     *          initializes empty collections for buildings and monsters.
     */
    public GameBoard(String name, int round) {
        this.name = name;
        buildings = new ArrayList<Buildings>();
        monsters = new ArrayList<Monsters>();
        this.money = 500;
        this.round = round;
        this.board = new String[ymax][xmax];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";  // Empty spaces for now
            }
        }
    }

    /**
     * Adds a building to the list of buildings on the game board.
     * 
     * REQUIRES: building != null
     * MODIFIES: buildings
     * EFFECTS: Adds the given building to the buildings collection.
     */
    public static void addBuilding(Buildings building) {
        buildings.add(building);
    }

    /**
     * Adds a monster to the list of monsters on the game board.
     * 
     * REQUIRES: monster != null
     * MODIFIES: monsters
     * EFFECTS: Adds the given monster to the monsters collection.
     */
    public static void addMonster(Monsters monster) {
        monsters.add(monster);
    }

    /**
     * Decreases the player's available money by a given cost.
     * 
     * REQUIRES: cost >= 0
     * MODIFIES: this
     * EFFECTS: Subtracts the cost from the current money.
     */
    public void spendMoney(int cost) {
        money -= cost;
    }

    /**
     * Returns the player's current amount of money.
     * 
     * EFFECTS: Returns the current money amount.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Returns the collection of buildings currently placed on the game board.
     * 
     * EFFECTS: Returns a list of all buildings.
     */
    public ArrayList<Buildings> getBuildings() {
        return buildings;
    }

    /**
     * Starts a new round in the game.
     * 
     * EFFECTS: Prints a message indicating that the round has started.
     */
    public void startRound() {
        round++;
    }

    /**
     * Places a building on the game board at the specified position with a given symbol.
     * 
     * REQUIRES: position != null, symbol != null
     * MODIFIES: board
     * EFFECTS: Places the building symbol on the board at the specified position.
     */
    public void placeBuilding(Position position, String symbol) {
        board[position.getColumn() - 1][position.getRow() - 1] = symbol;
    }

    /**
     * Returns the collection of monsters currently on the game board.
     * 
     * EFFECTS: Returns a list of all monsters.
     */
    public Object getMonsters() {
        return monsters;
    }

    /**
     * Returns the current state of the game board as a 2D array of strings.
     * 
     * EFFECTS: Returns the game board grid.
     */
    public String[][] getBoard() {
        return board;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("buildings", buildingsToJson());
        json.put("round", round);
        json.put("money", money);
        return json;
    }

    // EFFECTS: returns buildings in gameboard as a JSON array
    private JSONArray buildingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Buildings b : buildings) {
            jsonArray.put(b.toJson());
        }
        
        return jsonArray;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Integer getRound() {
        return round;
    }



    
}
