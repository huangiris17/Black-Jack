import java.util.*;

public class User {
    protected String name;
    protected List<Card> cards;
    protected int money;
    protected int points;
    
    public User(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.points = 0;
    }

    public int getMoney() {
        return this.money;
    }

    public boolean bet(int betMade) {
        if (betMade > this.money) return false;
        this.money -= betMade;
        return true;
    }

    public String getName() {
        return this.name;
    }

    public void drawCardFirst() {
        drawCard();
        drawCard();
    }

    public Card drawCard() {
        Card newCard = new Card();
        cards.add(newCard);
        setPoints(newCard);
        return newCard;
    }

    public int getPoints() {
        return this.points;
    }

    protected void setPoints(Card card) {
        int cardPoint = card.getValue();
        if (cardPoint == 11 && cardPoint + this.getPoints() != 21) cardPoint = 1;
        if (cardPoint != 11) cardPoint = Math.min(10, cardPoint);
        this.points += cardPoint;
    }

    public void showPoints() {
        System.out.println(this.getName() + " has " + this.getPoints() + " points.");
    }

    public boolean isOver21() {
        return this.getPoints() > 21;
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
