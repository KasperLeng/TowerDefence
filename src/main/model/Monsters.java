package model;

public interface Monsters {
    int getHealth();
    void takeDamage(int damage);
    void move(Position newPosition);
    boolean isWithinRange(Position buildingPosition, int range);
    Position getPosition();
}
