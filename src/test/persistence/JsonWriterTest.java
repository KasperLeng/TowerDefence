package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            GameBoard gb = new GameBoard("Kasper's Game", 0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            GameBoard gb = new GameBoard("Kasper's Game", 0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(gb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            gb = reader.read();
            assertEquals("Kasper's Game", gb.getName());
            assertEquals(0, gb.getRound());
            assertEquals(500, gb.getMoney());
            assertEquals(0, gb.getBuildings().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Buildings archerTower = new ArcherTower(1, new Position(1, 3), 150);
            Buildings goldMine = new GoldMine(1, new Position(4, 5), 100);
            GameBoard gb = new GameBoard("Kasper's Game", 0);
            GameBoard.addBuilding(archerTower);
            GameBoard.addBuilding(goldMine);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameBoard.json");
            writer.open();
            writer.write(gb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGameBoard.json");
            gb = reader.read();
            assertEquals("Kasper's Game", gb.getName());
            List<Buildings> buildings = gb.getBuildings();
            assertEquals(2, buildings.size());
            checkBuilding(150, new Position(1, 3), 1, "Archer Tower", archerTower);
            checkBuilding(100, new Position(4, 5), 1, "Gold Mine", goldMine);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}