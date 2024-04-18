import java.util.Random;

public class Player extends User{

    public Player(String name) {
        super(name);
        this.money = getRandomInitialMoney();
    }
    
    private int getRandomInitialMoney() {
        Random random = new Random();
        return random.nextInt(300) + 100;
    }

    @Override
    public Card drawCard() {
        Card newCard = new Card();
        setPoints(newCard);
        newCard.showCard();
        return newCard;
    }

}
