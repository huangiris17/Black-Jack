import java.util.Scanner;
import java.util.Random;

public class Game {
    private User player;
    private User dealer;
    private boolean isEnd;
    private boolean playerWin;
    private int playerBet;
    private int dealerBet;
    private boolean isFirstGame;
    private Scanner in;

    public Game(String pName) {
        this.player = new Player(pName);
        this.dealer = new Dealer();
        this.playerWin = false;
        this.isEnd = false;
        this.isFirstGame = true;
        this.playerBet = 0;
        this.dealerBet = 0;
        this.in = new Scanner(System.in);
    }

    public void gameStart() {
        while (player.hasMoney()) {
            if (!isFirstGame) {
                println();
                System.out.println("Keep playing Y/N: ");
                String ans = in.next();
                if (ans.equals("N")) {
                    System.out.println("See ya!");
                    break;
                }
                newGame();
            }

            printSectorLine("New Round Starts");
            makeBet();
            play();
            updateBet();
            if(isFirstGame) isFirstGame = false;

        }
        System.out.println("You lost all your 💰💰. Dealer won.");
        return;
    }

    private void newGame() {
        player.refresh();
        dealer.refresh();
        isEnd = false;
        playerWin = false;
    }

    private void makeBet() {
        System.out.println(player.getName() + ": $" + player.getMoney());
        System.out.print("How much money to bet: $");
        playerBet = Integer.parseInt(in.next());
        player.bet(playerBet);
        Random random = new Random();
        dealerBet = random.nextInt(playerBet) + random.nextInt(50);
        dealer.bet(dealerBet);
        System.out.println("Dealer bet $" + dealerBet);
    }

    private void updateBet() {
        if (playerWin) {
            player.addMoney(playerBet + dealerBet);
        } else {
            dealer.addMoney(playerBet + dealerBet);
        }
    }

    public void play() {
        initialCards();
        while (!isEnd) {
            round();
        }
        printBreakLine();
        System.out.println("Game Over.");
        return;
    }

    private void initialCards() {
        printSectorLine("Dealer draw card");
        dealer.drawCardFirst();
        if (checkBomb(dealer)) {
            return;
        }
        printSectorLine(this.player.getName() + " draw card");
        player.drawCardFirst();
        if (checkBomb(player)) {
            return;
        }
    }

    private void round() {
        playerPlay();
        if (!isEnd) dealerPlay();
    }

    private void playerPlay() {
        printBreakLine();
        printSectorLine(player.getName() + "'s turn");
        System.out.println(player.getName() + ": " + player.getPoints() + " points/ " + dealer.getName() + ": " + dealer.getPoints() + " points.");
        while (true) {
            println();
            System.out.print( "Draw a new card Y/N: ");
            String res = in.next();
            if (res.equals("Y")) {
                player.drawCard();
                println();
                dealer.showPoints();
                player.showPoints();
                println();
                if (checkBomb(player)) {
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
            println();
            if (checkBomb(dealer)) break;
        }
        return;
    }

    private void printSectorLine(String str) {
        println();
        System.out.println("=== " + str + " ===");
    }

    private void printBreakLine() {
        println();
        System.out.println("*********************");
        println();
    }

    private void println() {
        System.out.println();
    }

    private boolean checkBomb(User user) {
        if (user.getPoints() == 21) {
            System.out.println("Black Jack. " + user.getName() + " won❗️");
            isEnd = true;
            if (user.getName().equals(player.getName())) playerWin = true;
            return true;
        } else if (user.getPoints() > 21) {
            isEnd = true;
            if (user.getName().equals("Dealer")) {
                System.out.println("Bomb💥 dealer lost!");
                playerWin = true;
            } else {
                System.out.println("Bomb💥 " + player.getName()+ " lost😫!");
            }
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
