package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBuilding(int health, Position position, int num, String type, Buildings building) {
        assertEquals(health, building.getHealth());
        assertEquals(position.getRow(), building.getPosition().getRow());
        assertEquals(position.getColumn(), building.getPosition().getColumn());
        assertEquals(type, building.getType());
        assertEquals(num, building.getNum());
    }
}
