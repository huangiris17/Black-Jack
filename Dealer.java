import java.util.*;

public class Dealer extends User {

    public Dealer() {
        super("Dealer");
        this.money = Integer.MAX_VALUE / 2;
    }

    @Override
    public void drawCardFirst() {
        Card card1 = drawCard();
        drawCard();
        
        card1.showCard();
    }
}
