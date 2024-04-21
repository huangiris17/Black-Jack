import java.util.*;

public class User {
    protected String name;
    protected int money;
    protected int points;
    
    public User(String name) {
        this.name = name;
        this.points = 0;
    }

    public void refresh() {
        this.points = 0;
    }

    public void drawCardFirst() {
        drawCard();
        drawCard();
    }

    public Card drawCard() {
        Card newCard = new Card();
        setPoints(newCard);
        return newCard;
    }

    public int getMoney() {
        return this.money;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public void showPoints() {
        System.out.println(this.getName() + " has " + this.getPoints() + " points.");
    }

    public boolean bet(int betMade) {
        if (betMade > this.money) return false;
        this.money -= betMade;
        return true;
    }

    public boolean hasMoney() {
        return this.getMoney() > 0;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    protected void setPoints(Card card) {
        int cardPoint = card.getValue();
        if (cardPoint > 10) cardPoint = Math.min(10, cardPoint);
        else if (cardPoint == 1 && this.getPoints() <= 10) cardPoint = 11;
        this.points += cardPoint;
    }
}

/* 
 * player class -> dealer / player 
 * Attributes
 * 1. Name
 * 2. cards: stack of card owned
 * 3. money
 * 4. points
 * Functions
 * 1. showCard(dealer)
 * 2. setPoint
 * 3. getMoney
 * 4. bet
 * 5. getRandomMoney
 * 6. getName
 * 7. drawCards
 */
