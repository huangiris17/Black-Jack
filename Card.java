import java.util.Random;

public class Card {
    private String suit;
    private int value;
    
    public Card() {
        this.suit = getRandomSuit();
        this.value = getRandomVal();
    }

    public String getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }

    public void showCard() {
        System.out.println("Card: " + this.getSuit() + " " + this.getValue());
    }

    private int getRandomVal() {
        Random random = new Random();
        return random.nextInt(13) + 1;
    }

    private String getRandomSuit() {
        Random random = new Random();
        int idx = random.nextInt(4);
        String suit = "";
        switch(idx) {
            case 0:
                suit = "heart♥";
                break;
            case 1:
                suit = "spade♠";
                break;
            case 2:
                suit = "diamond♦";
                break;
            case 3:
                suit = "club♣";
                break;
        }
        return suit;
    }
    
}

/* card
 * Attributes
 * 1. suit
 * 2. value
 * Functions
 * 1. getSuit
 * 2. getValue
 * 3. showCard
 */ 
