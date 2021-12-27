package Quarter.ProductionQuarter;

import static java.lang.Math.min;

public class CryptoMine extends ProductionQuarter {

    public CryptoMine() {
        super();

        maxCrew=4;
        maxLevel=4;
        productionPerCrew=100;

        //Construction costs and consumption
        codeDataCost=0;
        bitCost=400;
        cryptomoneyCost=200;
        electricityConsumption=0;

        //Upgrade to level 2 costs and consumption
        codeDataCost1Upgrade=0;
        bitCost1Upgrade=600;
        cryptomoneyCost1Upgrade=1200;
        electricityConsumption1Upgrade=0;

        // Upgrade to level 3 costs and consumption
        codeDataCost2Upgrade=300;
        bitCost2Upgrade=800;
        cryptomoneyCost2Upgrade=2500;
        electricityConsumption2Upgrade=0;

        //Upgrade to level 4 costs and consumption
        codeDataCost3Upgrade=600;
        bitCost3Upgrade=1000;
        cryptomoneyCost3Upgrade=4000;
        electricityConsumption3Upgrade=0;
    }

    @Override
    public void upgrade() {
        if (level < maxLevel) {
            super.upgrade();
            maxCrew++;
            if (level==4) {
                maxCrew++;
            }
            productionPerCrew += 50;
        }
    }

    @Override
    public void isAdjacent(int quarterAdjacentType, int crewAdjacent, int levelAdjacent, int parameterAdjacent) {
        //0=CryptoMine
        if (quarterAdjacentType==0) {
            productionBonusConstant+=10*min(levelAdjacent,level)*min(crewAdjacent,crew);
        }
        //1=Cryptoinvestor
        else if (quarterAdjacentType==1) {
            if (levelAdjacent==1) {
                if (Math.random()>0.1*crewAdjacent/2) {
                    productionBonusRate += 40+parameterAdjacent;
                }
            }
            else if (levelAdjacent==2) {
                if (Math.random()>0.3*crewAdjacent/3) {
                    productionBonusRate += 40+parameterAdjacent;
                }
            }
            else if (levelAdjacent==3) {
                if (Math.random()>0.5*crewAdjacent/4) {
                    productionBonusRate += 40+parameterAdjacent;
                }
            }
        }
    }

    @Override
    public int getProduction() {
        production = productionPerCrew*crew*productionBonusRate/100+productionBonusConstant;
        return super.getProduction();
    }
}
