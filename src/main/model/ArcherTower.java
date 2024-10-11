package model;

import java.lang.Math;
// Represents an Archer Tower on the screen.
public class ArcherTower implements Buildings {
    private int health;
    private Position position;
    private int range;
    private int damage;
    private int attackSpeed;
    private int cost;
    private boolean status;
    private int num;
    private String type;

    
    public ArcherTower(int num, Position position) {
        this.health = 150;  // Starting health of Archer Tower
        this.position = position; 
        this.range = 5;     // Attack range of archer tower
        this.damage = 10;   // Example damage
        this.attackSpeed = 2;  // Example attack speed
        this.cost = 100;    // Example cost
        this.status = true;
        this.num = num; // Tower count
        this.type = "Archer Tower";
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            // Handle tower destruction logic here
            System.out.println("Archer Tower destroyed!");
            status = false;
            health = 0;
        }
    }
    
    // Archer Tower specific behavior
    public void attack(Monsters target) {
        if (withinRange(this.position, target.getPosition())) {
            target.takeDamage(this.damage);
        }
    }


    private boolean withinRange(Position towerPosition, Position monsterPosition) {
        return getDistance(towerPosition, monsterPosition) <= range;
    }

    private double getDistance(Position buildingPosition, Position monsterPosition) {
        int x1 = buildingPosition.getRow();
        int y1 = buildingPosition.getColumn();
        int x2 = monsterPosition.getRow();
        int y2 = monsterPosition.getColumn();

        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }


    @Override
    public int getHealth() {
        return this.health;
    }
    
    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    

    
}
