package Bs;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Bs {

    int[] array = new int[10];
    int sunk = 0;
    int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
    int guess = 0;

    public static void main(String[] args) {
        int ct = 0;
        Bs bs = new Bs();
        //battleship placement algorithm
        if (bs.randomNum <= 7) {
            //placement starts at bs.randomNum and ends at bs.randomNum+2
            for (int i = 0; i < 10; i++) {


                if (i == bs.randomNum) {
                    bs.array[i] = 1;
                    bs.array[i + 1] = 1;
                    bs.array[i + 2] = 1;
                    break;
                }
            }
        } else {
            //placement starts at bs.randomNum-2 and ends at bs.randomNum
            for (int i = 10; i > 0; i--) {
                if (i == bs.randomNum) {
                    bs.array[i] = 1;
                    bs.array[i - 1] = 1;
                    bs.array[i - 2] = 1;
                    break;
                }
            }
        }

        //compare usr input
        while (bs.sunk < 3) {
            int usr = userInput();
            if (bs.array[usr] == 1) {
                bs.array[usr] = 0;
                System.out.println("hit ");

                bs.sunk++;
            } else
                System.out.println("miss");
            bs.guess++;
        }
        System.out.println("Battleship sunk after "+bs.guess+" guesses!");
    }

    static int userInput() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter location number (0 - 9): ");
        int n = reader.nextInt();
        return n;

    }
}