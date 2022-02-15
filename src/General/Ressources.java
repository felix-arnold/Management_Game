package General;

public class Ressources {
    private int amount=0;
    private boolean canBeNegativ;

    public Ressources(boolean canBeNegativ) {
        this.canBeNegativ = canBeNegativ;
    }

    public void addAmount(int production) {
        amount += production;
    }

    public void substractAmount(int consumption) {
        if(amount > consumption) {
            amount -= consumption;
        }
        else if (canBeNegativ && (amount < consumption)) {
            amount -= consumption;
        }
        else {
            System.out.println("You cannot use");
        }
    }

}
