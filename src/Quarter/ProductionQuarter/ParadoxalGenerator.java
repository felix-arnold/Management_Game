package Quarter.ProductionQuarter;

import static java.lang.Math.min;


public class ParadoxalGenerator extends ProductionQuarter {

    public ParadoxalGenerator() {
        super();

        maxCrew=4;
        maxLevel=4;
        productionPerCrew=250;

        //Construction costs and consumption
        codeDataCost=0;
        bitCost=250;
        cryptomoneyCost=500;
        electricityConsumption=0;

        //Upgrade to level 2 costs and consumption
        codeDataCost1Upgrade=0;
        bitCost1Upgrade=600;
        cryptomoneyCost1Upgrade=1200;
        electricityConsumption1Upgrade=0;

        // Upgrade to level 3 costs and consumption
        codeDataCost2Upgrade=1000;
        bitCost2Upgrade=0;
        cryptomoneyCost2Upgrade=2000;
        electricityConsumption2Upgrade=0;

        //Upgrade to level 4 costs and consumption
        codeDataCost3Upgrade=1500;
        bitCost3Upgrade=0;
        cryptomoneyCost3Upgrade=3000;
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
        //0=ParadoxalGenerator
        if (quarterAdjacentType==0) {
            productionBonusConstant+=15*min(levelAdjacent,level)*min(crewAdjacent,crew);
        }
        //1=DimensionlessSpace
        else if (quarterAdjacentType==1) {
            if (levelAdjacent==1) {
                productionBonusRate+=10*levelAdjacent*crewAdjacent/2;
            }
            else if (levelAdjacent==2) {
                productionBonusRate+=10*levelAdjacent*crewAdjacent/3;
            }
            else if (levelAdjacent==3) {
                productionBonusRate+=10*levelAdjacent*crewAdjacent/4;
            }
        }
    }

    @Override
    public int getProduction() {
        production = productionPerCrew*crew*productionBonusRate/100+productionBonusConstant;
        return super.getProduction();
    }
}
