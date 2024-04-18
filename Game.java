import java.util.Scanner;

public class Game {
    User player;
    User dealer;
    boolean isEnd;

    public Game(String pName) {
        this.player = new Player(pName);
        this.dealer = new Dealer();
        isEnd = false;
    }

    public void gameStart() {
        initialCards();
        if (player.isOver21()) {
            System.out.println("😫Dealer won!");
            return;
        }
        if (dealer.isOver21()) {
            System.out.println("😍" + this.player.getName() + " won!");
            return;
        }
        while (!isEnd) {
            round();
        }
        printBreakLine();
        System.out.println("Game Over.");
        return;
    }

    private void initialCards() {
        System.out.println("=== Dealer draw card ===");
        dealer.drawCardFirst();
        checkWin(dealer);
        System.out.println("=== " + this.player.getName() + " draw card ===");
        player.drawCardFirst();
        checkWin(player);
    }

    private void round() {
        playerPlay();
        if (!isEnd) dealerPlay();
    }

    private void playerPlay() {
        printBreakLine();
        System.out.println("=== " + player.getName() + "'s turn ===");
        System.out.println(player.getName() + "has $" + player.getMoney());
        System.out.println(player.getName() + ": " + player.getPoints() + " points/ " + dealer.getName() + ": " + dealer.getPoints() + " points.");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print( "Draw a new card Y/N: ");
            String res = in.next();
            if (res.equals("Y")) {
                player.drawCard();
                System.out.println();
                dealer.showPoints();
                player.showPoints();
                if (checkWin(player)) {
                    break;
                } else if (player.isOver21()) {
                    System.out.println("Bomb💥 player lost😫!");
                    isEnd = true;
                    break;
                }
            } else if (res.equals("N")) {
                printBreakLine();
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
        return;
    }

    private void dealerPlay() {
        System.out.println("=== Dealer's turn ===");
        if (dealer.getPoints() >= player.getPoints()) {
            System.out.println("Dealer has fewer points. Player's turn.");
            return;
        }
        while (dealer.getPoints() < player.getPoints()) {
            System.out.println("...Dealer draw card...");
            dealer.drawCard();
            dealer.showPoints();
            System.out.println();
        }
        if (checkWin(dealer)) return;
        if (dealer.isOver21()) {
            isEnd = true;
            System.out.println("😍" + this.player.getName() + " won!");
            return;
        }
    }

    private void printBreakLine() {
        System.out.println();
        System.out.println("*********************");
    }

    private boolean checkWin(User user) {
        if (user.getPoints() == 21) {
            System.out.println("Black Jack. " + user.getName() + " won❗️");
            isEnd = true;
            return true;
        }
        return false;
    }
}

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
