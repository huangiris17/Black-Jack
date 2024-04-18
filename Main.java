import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to BlackJack! Please insert your name.");

        System.out.print("Player Name: ");
        String name1 = in.next();

        System.out.println("⭐️Welcome to the game " + name1 + "⭐️");
        System.out.println();

        Game curGame = new Game(name1);
        curGame.gameStart();
        in.close();
    }
}

/* 
 * User class -> dealer / player 
 * Attributes
 * 1. Name
 * 2. stack of card owned
 * 3. money
 * 4. total points
 * Functions
 * 1. showCard(dealer)
 * 2. addPoint
 */

/* card
 * Attributes
 * 1. suit
 * 2. value
 * Functions
 * 1. getSuit
 * 2. getValue
 * 3. showCard
 */ 

/* Game
 * Attributes
 * 1. isEnd
 * 2. PlayerList
 * 3. cardStack
 * Functions
 * 1. Check wins
 * 2. newGame
 * 3. gameStart
 */