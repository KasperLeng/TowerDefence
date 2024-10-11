package model;

public class SkeletonWarrior implements Monsters {
    private int health;
    private Position position;
    private int range;
    private int damage;
    private int movingSpeed;
    private boolean status;

    public SkeletonWarrior(Position position) {
        this.health = 200;
        this.range = 2;
        this.damage = 50;
        this.position = position;
        this.movingSpeed = 20;
        this.status = true;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("Skeleton Died");
            health = 0;
            status = false;
        }
    }

    @Override
    public void move(Position newPosition) {
        this.position = newPosition;
    }


    private boolean withinRange(Position towerPosition, Position monsterPosition) {
        return getDistance(towerPosition, monsterPosition) <= range;
    }


    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public double getDistance(Position buildingPosition, Position monsterPosition) {
        int x1 = buildingPosition.getRow();
        int y1 = buildingPosition.getColumn();
        int x2 = monsterPosition.getRow();
        int y2 = monsterPosition.getColumn();
    
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    @Override
    public void attack(Buildings building, int damage) {
        if (withinRange(this.position, building.getPosition())) {
            building.takeDamage(this.damage);
        }
    }
}
