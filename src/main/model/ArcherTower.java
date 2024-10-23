package model;

import java.lang.Math;

import org.json.JSONObject;

/**
 * Represents an Archer Tower in a game. Archer Towers are defensive buildings
 * that attack nearby monsters, have health, a position on the game map, and
 * defined attributes such as range, damage, attack speed, and cost.
 */
public class ArcherTower implements Buildings {
    private int health; // Tower's health, ranges from 0 to 150
    private Position position; // Tower's current position on the map
    private final int range = 5; // Tower's attack range
    private final int damage = 10; // Damage the tower inflicts per attack
    // private final int attackSpeed = 2; // Attack frequency of the tower
    private final int cost = 100; // Cost to build or maintain the tower
    private boolean status; // True if tower is operational, false if destroyed
    private int num; // Unique identifier of the tower
    private final String type = "Archer Tower"; // Tower type ("Archer Tower")

    /**
     * Constructs an Archer Tower with a specified position and unique identifier.
     * Initializes the health to 150, damage to 10, range to 5, attack speed to 2,
     * and cost to 100. The tower is operational (status = true) upon creation.
     * 
     * REQUIRES: position != null, num >= 0
     * MODIFIES: this
     * EFFECTS: Initializes Archer Tower's attributes with default values.
     */
    public ArcherTower(int num, Position position, int health) {
        this.health = 150; // Starting health
        this.position = position; // Position of building
        this.status = true; // Initial operational status
        this.num = num; // Tower's unique identifier
    }

    /**
     * Reduces the Archer Tower's health by a specified amount of damage.
     * If the health drops to 0 or below, the tower is destroyed and its status is
     * updated.
     * 
     * REQUIRES: damage >= 0
     * MODIFIES: this
     * EFFECTS: Decreases the health by the damage amount and updates the status if
     * the health reaches 0.
     */
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("Archer Tower destroyed!");
            status = false;
            health = 0;
        }
    }

    /**
     * Attacks a target monster if it is within the tower's range.
     * 
     * REQUIRES: target != null
     * MODIFIES: target
     * EFFECTS: Inflicts damage on the target if it is within the tower's range.
     */
    public void attack(Monsters target) {
        if (withinRange(this.position, target.getPosition())) {
            target.takeDamage(this.damage);
        }
    }

    /**
     * Checks if a monster is within the Archer Tower's attack range.
     * 
     * REQUIRES: towerPosition != null, monsterPosition != null
     * EFFECTS: Returns true if the distance between the tower and the monster is
     * within the tower's range.
     */
    private boolean withinRange(Position towerPosition, Position monsterPosition) {
        return getDistance(towerPosition, monsterPosition) <= range;
    }

    /**
     * Calculates the distance between two positions on the map.
     * 
     * REQUIRES: buildingPosition != null, monsterPosition != null
     * EFFECTS: Returns the Euclidean distance between the two positions.
     */
    private double getDistance(Position buildingPosition, Position monsterPosition) {
        int x1 = buildingPosition.getRow();
        int y1 = buildingPosition.getColumn();
        int x2 = monsterPosition.getRow();
        int y2 = monsterPosition.getColumn();

        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * Gets the current health of the Archer Tower.
     * 
     * EFFECTS: Returns the current health value of the tower.
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * Gets the current position of the Archer Tower.
     * 
     * EFFECTS: Returns the tower's current position on the map.
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * Gets the unique identifier (number) of the Archer Tower.
     * 
     * EFFECTS: Returns the tower's unique number.
     */
    @Override
    public int getNum() {
        return num;
    }

    /**
     * Gets the type of the building, which is "Archer Tower".
     * 
     * EFFECTS: Returns the type of the building.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Gets the cost of the Archer Tower.
     * 
     * EFFECTS: Returns the cost to build or maintain the tower.
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * Gets the current operational status of the Archer Tower.
     * 
     * EFFECTS: Returns true if the tower is operational, false if it has been
     * destroyed.
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
