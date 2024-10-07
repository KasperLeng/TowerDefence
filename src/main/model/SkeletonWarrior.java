package model;

public class SkeletonWarrior implements Monsters{
    private int health;
    private Position position;
    private int range;
    private int damage;
    private int movingSpeed;
    private boolean status;

    public SkeletonWarrior(Position position){
        this.health = 200;
        this.range = 5;
        this.damage = 10;
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
        if (health <= 0){
            System.out.println("Skeleton Died");
            status = false;
        }
    }

    @Override
    public void move(Position newPosition) {
        this.position = newPosition;
    }

    @Override
    public boolean isWithinRange(Position buildingPosition, int range) {
        return calculateDistance(buildingPosition, this.position) <= range;
    }

    private int calculateDistance(Position buildingPosition, Position position2) {
        return 0;
    }

    @Override
    public Position getPosition() {
        return position;
    }


}
