package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads gameboard from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads gameBoard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameBoard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameBoard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses gameBoard from JSON object and returns it
    private GameBoard parseGameBoard(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int round = jsonObject.getInt("round");
        int money = jsonObject.getInt("money"); // Get money
        GameBoard gb = new GameBoard(name, round);
        gb.setMoney(money);
        addBuildings(gb, jsonObject);
        return gb;
    }

    // MODIFIES: gb
    // EFFECTS: parses buildings from JSON object and adds them to the gameboard
    private void addBuildings(GameBoard gb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("buildings");
        for (Object json : jsonArray) {
            JSONObject nextBuilding = (JSONObject) json;
            addBuilding(gb, nextBuilding); // Parse each building individually
        }
    }

    // MODIFIES: gb
    // EFFECTS: parses a building from JSON object and adds it to the gameboard
    private void addBuilding(GameBoard gb, JSONObject jsonObject) {
        String type = jsonObject.getString("type"); // Get the building type
        // Parse position from JSON
        int row = jsonObject.getInt("row");
        int column = jsonObject.getInt("column");
        Position position = new Position(row, column); // Create a Position object

        // Check the building type and instantiate the appropriate building
        if (type.equals("Archer Tower")) {
            int num = jsonObject.getInt("num");
            int health = jsonObject.getInt("health");
            ArcherTower tower = new ArcherTower(num, position, health); // Create ArcherTower
            GameBoard.addBuilding(tower); // Add to GameBoard

        } else {
            int num = jsonObject.getInt("num");
            int health = jsonObject.getInt("health");
            GoldMine mine = new GoldMine(num, position, health); // Create GoldMine
            GameBoard.addBuilding(mine); // Add to GameBoard
        }
    }
}
