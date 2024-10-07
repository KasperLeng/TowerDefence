package ui;

import model.GameBoard;
import model.Position;
import model.ArcherTower;
import model.ArcherGoblin;

public class Main {
    public static void main(String[] args) {
        // Initialize game board
        GameBoard gameBoard = new GameBoard();

        // Example of placing buildings
        ArcherTower archerTower = new ArcherTower(new Position(3, 5));
        gameBoard.addBuilding(archerTower);

        // Example of adding monsters
        ArcherGoblin goblin = new ArcherGoblin(new Position(6, 7));
        gameBoard.addMonster(goblin);

        // Game loop (a simplified version)
        while (true) {
            // Update buildings (for example, making them attack)
            gameBoard.updateBuildings();

            // Update monsters (for example, moving them)
            gameBoard.updateMonsters();

            // Check game conditions (e.g., win/loss conditions)
            if (gameBoard.checkWinCondition()) {
                System.out.println("You win!");
                break;
            } else if (gameBoard.checkWinCondition()) {
                System.out.println("You lose!");
                break;
            }
        }
    }
}