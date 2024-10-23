package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameBoard gb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGameBoard() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameBoard.json");
        try {
            GameBoard gb = reader.read();
            assertEquals(500, gb.getMoney());
            assertEquals(0, gb.getBuildings().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}