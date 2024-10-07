package model;

public class ArcherTower implements Buildings {
    private int health;
    private Position position;
    private int range;
    private int damage;
    private int attackSpeed;
    private int cost;
    private boolean status;
    
    public ArcherTower(Position position) {
        this.health = 100;  // Starting health of Archer Tower
        this.position = position; 
        this.range = 5;     // Attack range of archer tower
        this.damage = 10;   // Example damage
        this.attackSpeed = 2;  // Example attack speed
        this.cost = 200;    // Example cost
        this.status = true;
    }
    
    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            // Handle tower destruction logic here
            System.out.println("Archer Tower destroyed!");
            status = false;
        }
    }

    @Override
    public Position getPosition() {
        return this.position;
    }
    
    // Archer Tower specific behavior
    public void attack(Monsters target) {
        if (target.isWithinRange(this.position, this.range)) {
            target.takeDamage(this.damage);
        }
    }
}
