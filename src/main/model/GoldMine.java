package model;

public class GoldMine implements Buildings{
    private int health;
    private Position position;
    private int produceSpeed;
    private int cost;
    private boolean status;

    public GoldMine(Position position){
        this.health = 200;
        this.position = position;
        this.produceSpeed = 10;
        this.cost = 300;
        this.status = true;
    }

    public int getHealth() {
        return health;
    }


    public void takeDamage(int damage) {
        health = health - damage;
        if (this.health <= 0) {
            // Handle tower destruction logic here
            System.out.println("Gold Mine destroyed!");
            status = false;
        }
    }


    public Position getPosition() {
        return position;
    }

}
