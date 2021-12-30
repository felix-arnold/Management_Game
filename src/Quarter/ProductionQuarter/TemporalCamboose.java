package Quarter.ProductionQuarter;

import static java.lang.Math.min;


public class TemporalCamboose extends ProductionQuarter{

    public TemporalCamboose() {
        super();

        //Construction cost, consumption, upgrade cost, variable
        maxLevel = 4;
        maxCrew = new int[]{4, 5, 6, 8};
        productionPerCrew = new double[]{3, 4, 5, 6};
        bitCost = new int[]{300, 600, 800, 1000};
        codeDataCost = new int[]{0, 0, 600, 1300};
        cryptomoneyCost = new int[]{400, 900, 1800, 3800};
        electricityConsumption = new int[]{100, 200, 300, 400};
    }

    @Override
    public void isAdjacent(int quarterAdjacentType, int crewAdjacent, int levelAdjacent, int parameterAdjacent) {
        //0=CryptoMine
        if (quarterAdjacentType==0) {
            productionBonusConstant+=0;
        }
        //1=Cryptoinvestor
        else if (quarterAdjacentType==1) {
            productionBonusRate += 40+parameterAdjacent;
        }
    }
}
