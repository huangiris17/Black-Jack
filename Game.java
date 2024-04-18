import java.util.Scanner;
import java.util.Random;

public class Game {
    User player;
    User dealer;
    boolean isEnd;
    boolean playerWin;
    boolean isFirstGame;

    public Game(String pName) {
        this.player = new Player(pName);
        this.dealer = new Dealer();
        this.playerWin = false;
        this.isEnd = false;
        this.isFirstGame = true;
    }

    public void gameStart() {
        while (player.hasMoney()) {
            Scanner in = new Scanner(System.in);
            if (!isFirstGame) {
                System.out.println();
                System.out.println("Keep playing Y/N: ");
                String ans = in.next();
                if (ans.equals("N")) {
                    System.out.println("See ya!");
                    break;
                }
            }

            System.out.println();
            System.out.println("=== New Round Starts ===");
            newGame();
            System.out.println(player.getName() + ": $" + player.getMoney());
            System.out.print("How much money to bet: ");
            int playerBet = Integer.parseInt(in.next());
            player.bet(playerBet);
            Random random = new Random();
            int dealerBet = random.nextInt(playerBet) + 50;
            dealer.bet(dealerBet);
            System.out.println("Dealer bet $" + dealerBet);

            play();
            isFirstGame = false;

            if (playerWin) {
                player.addMoney(playerBet + dealerBet);
            } else {
                if (!player.hasMoney()) {
                    System.out.println("You lost all your money. Dealer won.");
                    break;
                }
                dealer.addMoney(playerBet + dealerBet);
            }
        }
        return;
    }

    private void newGame() {
        player.refresh();
        dealer.refresh();
        isEnd = false;
        playerWin = false;
    }

    public void play() {
        initialCards();
        if (player.isOver21()) {
            System.out.println("😫Dealer won!");
            return;
        }
        if (dealer.isOver21()) {
            System.out.println("😍" + this.player.getName() + " won!");
            playerWin = true;
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
        System.out.println();
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
                    playerWin = true;
                    break;
                } else if (player.isOver21()) {
                    System.out.println("Bomb💥 player lost😫!");
                    isEnd = true;
                    playerWin = false;
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
        if (checkWin(dealer)) {
            playerWin = false;
            return;
        }
        if (dealer.isOver21()) {
            isEnd = true;
            playerWin = true;
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
