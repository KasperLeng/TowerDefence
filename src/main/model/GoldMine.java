package model;

import org.json.JSONObject;

/**
 * Represents a Gold Mine on the screen. A Gold Mine produces gold at a certain speed,
 * has health, a position on the game map, and can be damaged or destroyed.
 */
public class GoldMine implements Buildings {
    private int health;               // Health of the Gold Mine (400 max)
    private Position position;        // Position of the Gold Mine on the game map
    private final int produceSpeed = 10;         // Speed at which the Gold Mine produces gold
    private final int cost = 200;                 // Cost of building or maintaining the Gold Mine
    private boolean status;           // Status of the Gold Mine (true if operational)
    private int num;                  // Unique identifier for the Gold Mine
    private final String type = "Gold Mine";              // Type of building ("Gold Mine")

    /**
     * Constructs a Gold Mine with a specific position and unique identifier.
     * Initializes health to 400, produce speed to 10, and cost to 200.
     * 
     * REQUIRES: position != null, num >= 0
     * MODIFIES: this
     * EFFECTS: Initializes the Gold Mine with the given position and assigns default values for other attributes.
     */
    public GoldMine(int num, Position position, int health) {
        this.health = health;
        this.position = position;
        this.status = true;
        this.num = num;
    }

    /**
     * Returns the current health of the Gold Mine.
     * 
     * EFFECTS: Returns the health of the Gold Mine.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the Gold Mine's health by the specified amount of damage.
     * If the health drops to 0 or below, the Gold Mine is destroyed.
     * 
     * REQUIRES: damage >= 0
     * MODIFIES: this
     * EFFECTS: Reduces health by the damage amount and sets status to false if the Gold Mine is destroyed.
     */
    public void takeDamage(int damage) {
        health = health - damage;
        if (this.health <= 0) {
            System.out.println("Gold Mine destroyed!");
            status = false;
            health = 0;
        }
    }

    /**
     * Returns the position of the Gold Mine on the game map.
     * 
     * EFFECTS: Returns the current position of the Gold Mine.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the unique identifier of the Gold Mine.
     * 
     * EFFECTS: Returns the unique identifier (num) of the Gold Mine.
     */
    @Override
    public int getNum() {
        return num;
    }

    /**
     * Returns the type of the building, which is "Gold Mine".
     * 
     * EFFECTS: Returns the building type as "Gold Mine".
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Returns the cost of building or maintaining the Gold Mine.
     * 
     * EFFECTS: Returns the cost of the Gold Mine.
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * Returns the current operational status of the Gold Mine.
     * 
     * EFFECTS: Returns true if the Gold Mine is operational, false if it has been destroyed.
     */
    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("health", health);
        json.put("row", position.getRow());
        json.put("column", position.getColumn());
        json.put("num", num);
        json.put("type", type);
        return json;
    }
}
