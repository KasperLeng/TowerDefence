package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Basic definition of a game class, provides required methods to create a game
 */
public abstract class GamePanel extends JPanel {
    private static final String JSON_STORE = "./data/gameBoard.json";
    protected GameBoard gameBoard; // The game board instance
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: initializes a Game Object and starts the game
    public GamePanel(GameBoard gb) throws FileNotFoundException {
        super();
        gameBoard = gb;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: processes user command
    protected abstract void processCommand(String command) throws FileNotFoundException;

    // EFFECTS: saves the gameBoard to file
    protected void saveGameBoard() {
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
    protected void loadGameBoard() {
        try {
            gameBoard = jsonReader.read();
            System.out.println("Loaded " + gameBoard.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
