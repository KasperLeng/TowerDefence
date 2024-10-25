package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Represents the main Tower Defense game logic, handling player interactions,
 * game state,
 * and management of buildings and resources.
 */
public class TowerDefenceGame {
    private static final String JSON_STORE = "./data/gameBoard.json";
    private Scanner input; // User input scanner
    private GameBoard gameBoard; // The game board instance
    private int towerCount; // Count of Archer Towers placed
    private int mineCount; // Count of Gold Mines placed
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /**
     * Constructs a TowerDefenceGame and starts the game by calling the runGame
     * method.
     * 
     * EFFECTS: Initializes and runs the Tower Defense game.
     */
    public TowerDefenceGame() {
        runGame();
    }

    /**
     * Runs the main game loop where the player can buy buildings, start rounds,
     * view stats, and perform other game actions.
     * 
     * MODIFIES: this
     * EFFECTS: Starts the game, initializes necessary components, and processes
     * user commands until "q" is entered.
     */
    public void runGame() {
        // Initialize game board
        gameBoard = new GameBoard("Kasper's Game", 0);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        init();

        String command = null;
        towerCount = 0;
        mineCount = 0;

        while (true) {
            displayChoices();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                break;
            } else {
                processCommand(command);
            }
        }
    }

    /**
     * Initializes the user input scanner.
     * 
     * MODIFIES: this
     * EFFECTS: Sets up the input scanner for reading user commands.
     */
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    /**
     * Processes a given user command and performs the corresponding game action.
     * 
     * MODIFIES: this, gameBoard
     * EFFECTS: Interprets the command and calls the appropriate method (e.g.,
     * buying buildings, starting the game).
     */
    private void processCommand(String command) {
        if (command.equals("b")) {
            choiceB();
        } else if (command.equals("s")) {
            startGame();
        } else if (command.equals("p")) {
            printBoard();
        } else if (command.equals("m")) {
            System.out.println("Money: " + gameBoard.getMoney());
        } else if (command.equals("v")) {
            System.out.println("Buildings:");
            for (Buildings i : gameBoard.getBuildings()) {
                System.out.println(i.getType() + " " + i.getNum());
            }
        } else if (command.equals("sg")) {
            saveGameBoard();
        } else if (command.equals("lg")) {
            loadGameBoard();
        }
    }

    /**
     * Helper choiceB prints the options for buildings.
     * 
     * MODIFIES: this, gameBoard
     * EFFECTS: This helper allows the user to buy buildings.
     */
    private void choiceB() {
        String choice = null;
        System.out.println("\nType of Building:");
        System.out.println("\ta -> archer tower" + "    Cost: 100");
        System.out.println("\tg -> gold mine" + "    Cost: 200");
        choice = input.next();
        choice = choice.toLowerCase();

        if (choice.equals("a")) {
            buyArcherTower();
        } else if (choice.equals("g")) {
            buyGoldMine();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    /**
     * Starts a round in the game.
     * 
     * MODIFIES: gameBoard
     * EFFECTS: Starts a new game round.
     */
    private void startGame() {
        gameBoard.startRound();
    }

    /**
     * Handles the logic for buying and placing an Archer Tower.
     * 
     * MODIFIES: this, gameBoard
     * EFFECTS: Buys an Archer Tower if the player has enough money and places it on
     * the game board.
     */
    private void buyArcherTower() {
        towerCount++;
        Buildings archerTower = new ArcherTower(towerCount, getPosition(), 150);

        if ((archerTower.getPosition().getRow() <= 0) || (archerTower.getPosition().getColumn() <= 0)) {
            System.out.println("Try again...");
        } else {
            if (gameBoard.getMoney() >= archerTower.getCost()) {
                GameBoard.addBuilding(archerTower);
                gameBoard.placeBuilding(archerTower.getPosition(), "A");
                gameBoard.spendMoney(archerTower.getCost());
            } else {
                System.out.println("Not enough money...");
            }
        }
    }

    /**
     * Handles the logic for obtaining the position of a building from user input.
     * 
     * EFFECTS: Returns a Position object based on the user-provided x and y
     * coordinates.
     */
    private Position getPosition() {
        int xpos = 0;
        int ypos = 0;
        try {
            System.out.println("What is the x coordinate of your Building?: ");
            xpos = input.nextInt();
            System.out.println("What is the y coordinate of your Building?: ");
            ypos = input.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Please Enter a valid coordinate...");
            input.nextLine(); // Clear invalid input
        }
        return new Position(xpos, ypos);
    }

    /**
     * Handles the logic for buying and placing a Gold Mine.
     * 
     * MODIFIES: this, gameBoard
     * EFFECTS: Buys a Gold Mine if the player has enough money and places it on the
     * game board.
     */
    private void buyGoldMine() {
        mineCount++;
        Buildings goldMine = new GoldMine(mineCount, getPosition(), 100);

        if (gameBoard.getMoney() >= goldMine.getCost()) {
            GameBoard.addBuilding(goldMine);
            gameBoard.placeBuilding(goldMine.getPosition(), "$");
            gameBoard.spendMoney(goldMine.getCost());
        } else {
            System.out.println("Not enough money...");
        }
    }

    /**
     * Displays the available choices for the player, such as buying buildings or
     * starting a round.
     * 
     * EFFECTS: Prints the available commands and their descriptions to the console.
     */
    private void displayChoices() {
        System.out.println("\nSelect from: ");
        System.out.println("\tb -> Buy buildings");
        System.out.println("\ts -> Start round");
        System.out.println("\tm -> Check money");
        System.out.println("\tv -> View your buildings");
        System.out.println("\tp -> print board");
        System.out.println("\tsg -> save game");
        System.out.println("\tlg -> load game");
        System.out.println("\tq -> quit");
    }

    /**
     * Prints the game board with buildings placed on it and the grid numbers.
     * 
     * EFFECTS: Displays the current state of the game board to the console.
     */
    public void printBoard() {
        String[][] board = gameBoard.getBoard();

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

    // EFFECTS: saves the gameBoard to file
    private void saveGameBoard() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameBoard);
            jsonWriter.close();
            System.out.println("Saved " + gameBoard.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadGameBoard() {
        try {
            gameBoard = jsonReader.read();
            System.out.println("Loaded " + gameBoard.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
