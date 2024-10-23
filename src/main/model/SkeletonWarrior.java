package model;

/**
 * Represents a Skeleton Warrior on the game screen. The Skeleton Warrior is a
 * type of monster
 * that can move, take damage, and attack buildings.
 */
public class SkeletonWarrior implements Monsters {
    private int health; // Health of the Skeleton Warrior (200 max)
    private Position position; // Current position of the Skeleton Warrior on the game map
    private final int range = 2; // Attack range of the Skeleton Warrior
    private final int damage = 50; // Damage dealt by the Skeleton Warrior
    // private final int movingSpeed = 20; // Movement speed of the Skeleton Warrior
    private boolean status; // Status of the Skeleton Warrior (true if alive)

    /**
     * Constructs a Skeleton Warrior with a given position on the map.
     * Initializes health to 200, range to 2, damage to 50, and movement speed to
     * 20.
     * 
     * REQUIRES: position != null
     * MODIFIES: this
     * EFFECTS: Initializes the Skeleton Warrior's attributes with default values
     * and sets its initial position.
     */
    public SkeletonWarrior(Position position) {
        this.health = 200;
        this.position = position;
        this.status = true;
    }

    /**
     * Returns the current health of the Skeleton Warrior.
     * 
     * EFFECTS: Returns the health of the Skeleton Warrior.
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the Skeleton Warrior's health by a specified damage amount.
     * If the health drops to 0 or below, the Skeleton Warrior is considered dead.
     * 
     * REQUIRES: damage >= 0
     * MODIFIES: this
     * EFFECTS: Reduces health by the damage amount and sets status to false if the
     * Skeleton Warrior dies.
     */
    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("Skeleton Died");
            health = 0;
            status = false;
        }
    }

    /**
     * Moves the Skeleton Warrior to a new position on the game map.
     * 
     * REQUIRES: newPosition != null
     * MODIFIES: this
     * EFFECTS: Updates the position of the Skeleton Warrior to the specified new
     * position.
     */
    @Override
    public void move(Position newPosition) {
        this.position = newPosition;
    }

    /**
     * Returns true if the building at the given position is within the Skeleton
     * Warrior's attack range.
     * 
     * REQUIRES: towerPosition != null, monsterPosition != null
     * EFFECTS: Returns true if the building's position is within the attack range
     * of the Skeleton Warrior.
     */
    private boolean withinRange(Position towerPosition, Position monsterPosition) {
        return getDistance(towerPosition, monsterPosition) <= range;
    }

    /**
     * Returns the current position of the Skeleton Warrior on the game map.
     * 
     * EFFECTS: Returns the current position of the Skeleton Warrior.
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the current status of the Skeleton Warrior (true if alive, false if
     * dead).
     * 
     * EFFECTS: Returns the status of the Skeleton Warrior.
     */
    @Override
    public boolean getStatus() {
        return status;
    }

    /**
     * Returns the damage dealt by the Skeleton Warrior.
     * 
     * EFFECTS: Returns the attack damage of the Skeleton Warrior.
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * Calculates the distance between two positions on the game map.
     * 
     * REQUIRES: buildingPosition != null, monsterPosition != null
     * EFFECTS: Returns the distance between the two positions using the Euclidean
     * distance formula.
     */
    public double getDistance(Position buildingPosition, Position monsterPosition) {
        int x1 = buildingPosition.getRow();
        int y1 = buildingPosition.getColumn();
        int x2 = monsterPosition.getRow();
        int y2 = monsterPosition.getColumn();

        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * Attacks a building if it is within the Skeleton Warrior's attack range.
     * 
     * REQUIRES: building != null
     * MODIFIES: building
     * EFFECTS: Reduces the building's health by the damage dealt by the Skeleton
     * Warrior, if within range.
     */
    @Override
    public void attack(Buildings building, int damage) {
        if (withinRange(this.position, building.getPosition())) {
            building.takeDamage(this.damage);
        }
    }
}
