package model;

public class GoldMine implements Buildings{
    private int health;
    private Position position;
    private int produceSpeed;
    private int cost;
    private boolean status;
    private int num;
    private String type;

    public GoldMine(int num, Position position){
        this.health = 400;
        this.position = position;
        this.produceSpeed = 10;
        this.cost = 200;
        this.status = true;
        this.num = num;
        this.type = "Gold Mine";
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
            health = 0;
        }
    }


    public Position getPosition() {
        return position;
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
