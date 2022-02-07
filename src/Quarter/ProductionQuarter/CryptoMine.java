package Quarter.ProductionQuarter;

import static java.lang.Math.min;

public class CryptoMine extends ProductionQuarter {

    public CryptoMine() {
        super();

        //Construction cost, consumption, upgrade cost, variable
        maxLevel = 4;
        maxCrew = new int[]{4, 5, 6, 8};
        productionPerCrew = new double[]{250, 300, 350, 400};
        bitCost = new int[]{300, 500, 0, 0};
        codeDataCost = new int[]{0, 0, 1200, 2000};
        cryptomoneyCost = new int[]{300, 1200, 2500, 5000};
        electricityConsumption = new int[]{150, 300, 450, 600};
        style = "cryptomine";
        selectedStyle = "selectedCryptomine";
        productionType="cryptomoney";
    }

    @Override
    public void isAdjacent(int quarterAdjacentType, int crewAdjacent, int levelAdjacent, int parameterAdjacent) {
        //0=CryptoMine
        if (quarterAdjacentType==0) {
            productionBonusConstant+=10*min(levelAdjacent,level)*min(crewAdjacent,crew);
        }
        //1=Cryptoinvestor
        else if (quarterAdjacentType==1) {
            if (Math.random()>0.1*(levelAdjacent*2-1)*crewAdjacent/(levelAdjacent+1)) {
                productionBonusRate += 40+parameterAdjacent;
            }
        }
    }
}
