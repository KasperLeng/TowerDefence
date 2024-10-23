package model;

/**
 * Represents an Archer Goblin in a game. Archer Goblins are enemies that have
 * health,
 * a position on the game map, a certain attack range, and damage-dealing
 * capability.
 * They can move across the game field, take damage, and attack nearby buildings
 * if
 * within range.
 */
public class ArcherGoblin implements Monsters {
    private int health; // Goblin's health, ranges from 0 to 100
    private Position position; // Goblin's current position on the map
    private final int range = 5; // Goblin's attack range
    private final int damage = 20; // Damage the goblin inflicts
    // private final int movingSpeed = 15; // Goblin's movement speed
    private boolean status; // True if goblin is alive, false if dead

    /**
     * Constructs an Archer Goblin with a specified starting position.
     * Initializes the health to 100, damage to 20, range to 5, and moving speed to
     * 15.
     * The goblin is alive (status = true) upon creation.
     * 
     * Requires: position != null
     * Modifies: this
     * Effects: Initializes Archer Goblin's attributes with default values.
     */
    public ArcherGoblin(Position position) {
        this.health = 100;
        this.position = position;
        this.status = true;
    }

    /**
     * Gets the current health of the Archer Goblin.
     * Effects: Returns the current health value.
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the Archer Goblin's health by a specified amount of damage.
     * If the health drops to 0 or below, the goblin dies and its status is updated.
     * 
     * REQUIRES: damage >= 0
     * MODIFIES: this
     * EFFECTS: Decreases the health by the damage amount and updates the status if
     * the health reaches 0.
     */
    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("Goblin Died");
            status = false;
            health = 0;
        }
    }

    /**
     * Moves the Archer Goblin to a new position on the map.
     * Requires: newPosition != null
     * Modifies: this
     * Effects: Updates the position of the goblin.
     */
    @Override
    public void move(Position newPosition) {
        position = newPosition;
    }

    /**
     * Checks if the goblin is within attacking range of a specified tower position.
     * Requires: towerPosition != null, monsterPosition != null
     * Effects: Returns true if the distance between the goblin and the tower is
     * within the goblin's range.
     */
    private boolean withinRange(Position towerPosition, Position monsterPosition) {
        return getDistance(towerPosition, monsterPosition) <= range;
    }

    /**
     * Calculates the distance between two positions on the map.
     * Requires: buildingPosition != null, monsterPosition != null
     * Effects: Returns the Euclidean distance between the two positions.
     */
    private double getDistance(Position buildingPosition, Position monsterPosition) {
        int x1 = buildingPosition.getRow();
        int y1 = buildingPosition.getColumn();
        int x2 = monsterPosition.getRow();
        int y2 = monsterPosition.getColumn();

        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * Gets the current position of the Archer Goblin.
     * Effects: Returns the goblin's current position.
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Gets the current status of the Archer Goblin.
     * Effects: Returns the goblin's current status.
     */
    @Override
    public boolean getStatus() {
        return status;
    }

    /**
     * Gets the damage value that the Archer Goblin can inflict.
     * Effects: Returns the damage value of the goblin.
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * Attacks a building if the building is within the Archer Goblin's range.
     * The building takes damage if within range.
     * 
     * Requires: building != null, damage >= 0
     * Modifies: building
     * Effects: Inflicts damage to the building if the goblin is within range.
     */
    @Override
    public void attack(Buildings building, int damage) {
        if (withinRange(this.position, building.getPosition())) {
            building.takeDamage(this.damage);
        }
    }
}
