package model;

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
        this.health = 100;  // Starting health of Archer Tower
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

    @Override
    public int getNum(){
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

    
}
