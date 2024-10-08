package ui;

import model.*;
import java.util.*;

public class TowerDefenceGame {
    private Scanner input;
    private GameBoard gameBoard;
    private int towerCount;
    private int mineCount;

    
    public TowerDefenceGame() {
        runGame();
    }

    public void runGame(){
        // Initialize game board
        gameBoard = new GameBoard();

        init();

        String command = null;
        towerCount = 0;
        mineCount = 0;

        while (true) {
            displayChoices();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                break;
            } else {
                processCommand(command);
            }
        }
        
    }

    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    private void processCommand(String command) {
        String choice = null;
        if(command.equals("b")){
            System.out.println("\nType of Building:");
            System.out.println("\ta -> archer tower" + "    Cost: 200");
            System.out.println("\tg -> gold mine" + "    Cost: 300");
            choice = input.next();
            choice = choice.toLowerCase();
            
            if(choice.equals("a")){
                buyArcherTower();
            }
            else if(choice.equals("g")){
                buyGoldMine();
            }
            else {
                System.out.println("Selection not valid...");
            }
        }

        else if(command.equals("s")){
            startGame();
        }

        else if(command.equals("p")){
           gameBoard.printBoard();
        }

        else if(command.equals("m")){
            System.out.println("Money: " + gameBoard.getMoney());
        }

        else if(command.equals("g")){
            System.out.println("Buildings:");
            for(Buildings i:gameBoard.getBuildings()){
                System.out.println(i.getType() + " " + i.getNum());
            }
        }

    }

    private void startGame(){
        gameBoard.startRound();
    }   

    private void buyArcherTower() {
        towerCount++;
        Buildings archerTower = new ArcherTower(towerCount, getPosition());

        if(gameBoard.getMoney() >= archerTower.getCost()){
            GameBoard.addBuilding(archerTower);
            gameBoard.placeBuilding(archerTower.getPosition(), "A");
            gameBoard.spendMoney(archerTower.getCost());
        }
        else{
            System.out.println("Not enough money...");
        }
    }

    private Position getPosition() {
        int x_pos = 0;
        int y_pos = 0;
        System.out.println("What is the x coordinate of your Building?: ");
        x_pos = input.nextInt();
        System.out.println("What is the y coordinate of your Building?: ");
        y_pos = input.nextInt();

        Position position = new Position(x_pos, y_pos);
        return position;

    }

    private void buyGoldMine() {
        mineCount++;
        Buildings goldMine = new GoldMine(mineCount, getPosition());

        if(gameBoard.getMoney() >= goldMine.getCost()){
            GameBoard.addBuilding(goldMine);
            gameBoard.placeBuilding(goldMine.getPosition(), "$");
            gameBoard.spendMoney(goldMine.getCost());
        }
        else{
            System.out.println("Not enough money...");
        }

    }

    private void displayChoices() {
        System.out.println("\nSelect from: ");
        System.out.println("\tb -> buy buildings");
        System.out.println("\ts -> start round");
        System.out.println("\tm -> check money");
        System.out.println("\tg -> get buildings");
        System.out.println("\tp -> print board");
        System.out.println("\tq -> quit");
    }        
    
}