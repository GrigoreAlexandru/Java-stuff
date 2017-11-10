package Battleship;

import java.util.ArrayList;

public class BsMain {
    private BsHelper helper = new BsHelper();
    private ArrayList<Battleship> bsList = new ArrayList<Battleship>(3);
    private int numOfGuesses = 0;
    public static void main(String[] args) {
        BsMain game = new BsMain();
        game.setUpGame();
        game.startPlaying();
    }
    private void setUpGame(){
        Battleship one = new Battleship();
        one.setName("Pets.com");
        Battleship two = new Battleship();
        two.setName("eToys.com");
        Battleship three = new Battleship();
        three.setName("Go2.com");
        bsList.add(one);
        bsList.add(two);
        bsList.add(three);

        for (Battleship bsToSet : bsList) {
            ArrayList<String> newLocation = helper.placeBattleship(3);
            bsToSet.setLocationCells(newLocation);
        }
    }
    private void startPlaying(){
        while(!bsList.isEmpty()) {
            String userGuess = helper.getUserInput("Enter GUIs guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }
    private void checkUserGuess(String userGuess){
        numOfGuesses++;
        String result = "miss";
        for (Battleship dotComToTest : bsList) {
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("hit")) {
                break;
            }
            if (result.equals("kill")) {
                bsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }
    private void finishGame(){
        System.out.println("All Dot Coms are dead! Your stock is now worthless.");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println(" You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. "+ numOfGuesses +  "guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }
    }

