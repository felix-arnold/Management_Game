package General;

public class Ressources {
    private int amount;
    private boolean beNegativ;
    private String type;

    public Ressources(int amount, boolean beNegativ, String type) {
        this.amount = amount;
        this.beNegativ = beNegativ;
        this.type = type;
    }

    public void addAmount(int production) {
        amount += production;
    }

    public void substractAmount(int consumption) {
        if(amount > consumption) {
            amount -= consumption;
        }
        else if (beNegativ && (amount < consumption)) {
            amount -= consumption;
        }
        else {
            System.out.println("You cannot use");
        }
    }

}
