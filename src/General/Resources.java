package General;

public class Resources {

    //Classe pour simplifier le code

    private long amount=0;
    private final boolean canBeNegativ;

    //Constructor
    public Resources(boolean canBeNegativ) {
        this.canBeNegativ = canBeNegativ;
    }

    public void addAmount(long production) {
        amount += production;
    }

    public void subtractAmount(long consumption) {
        amount -= consumption;
        if(!canBeNegativ && amount < 0) {
            amount = 0;
        }
    }

    public void setAmount(long newAmount) {
        amount = newAmount;
    }

    public long getAmount() {
        return amount;
    }
}