package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestMonsters {
    private Monsters archerGoblin;
    private Monsters skeletonWarrior;


    @BeforeEach
    void runBefore(){
        archerGoblin = new ArcherGoblin(new Position(4, 4));
        skeletonWarrior = new SkeletonWarrior(new Position(5, 6));
    
    }

    @Test
    void testConstructor(){
        assertEquals(new Position(4, 4), archerGoblin.getPosition());
        assertEquals(new Position(5, 6), skeletonWarrior.getPosition());
    }

    @Test
    void testTakeDamage(){
        archerGoblin.takeDamage(10);
        skeletonWarrior.takeDamage(20);
        assertEquals(90, archerGoblin.getHealth());
        assertEquals(180, skeletonWarrior.getHealth());

        archerGoblin.takeDamage(100);
        skeletonWarrior.takeDamage(200);

        assertFalse(archerGoblin.getStatus());
        assertFalse(skeletonWarrior.getStatus());

        assertEquals(0, archerGoblin.getHealth());
        assertEquals(0, skeletonWarrior.getHealth());

    }

    @Test
    void testAttack(){
        Buildings archerTower = new ArcherTower(1, new Position(0, 0));

        archerGoblin.attack(archerTower, archerGoblin.getDamage());
        assertEquals(150, archerTower.getHealth());
        archerGoblin.move(new Position(3, 4));
        archerGoblin.attack(archerTower, archerGoblin.getDamage());
        assertEquals(130, archerTower.getHealth());

        skeletonWarrior.attack(archerTower, skeletonWarrior.getDamage());
        assertEquals(130, archerTower.getHealth());
        skeletonWarrior.move(new Position(1, 1));
        skeletonWarrior.attack(archerTower, skeletonWarrior.getDamage());
        assertEquals(80, archerTower.getHealth());

    }


}
