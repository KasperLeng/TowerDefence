package model;

// Represents the general type building having hp, cost.

public interface Buildings {
    int getHealth();

    void takeDamage(int damage);

    Position getPosition();

    int getNum();

    String getType();

    int getCost();
    
    boolean getStatus();
}