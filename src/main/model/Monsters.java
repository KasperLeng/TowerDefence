package model;

import org.json.JSONObject;

//Represents all types of Monsters.

public interface Monsters {
    int getHealth();

    void takeDamage(int damage);

    void move(Position newPosition);

    Position getPosition();

    boolean getStatus();

    int getDamage();
    
    void attack(Buildings building, int damage);

}
