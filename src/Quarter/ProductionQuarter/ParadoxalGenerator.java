package Quarter.ProductionQuarter;

import static java.lang.Math.min;


public class ParadoxalGenerator extends ProductionQuarter {

    public ParadoxalGenerator() {
        super();

        //Construction cost, consumption, upgrade cost, variable
        maxLevel=4;
        maxCrew= new int[]{4, 5, 6, 8};
        productionPerCrew=new double[]{150,200,250,300};
        bitCost= new int[]{400,800,800,1200};
        codeDataCost=new int[]{0,0,1000,1500};
        cryptomoneyCost=new int[]{500,1000,2000,4000};
        electricityConsumption=new int[]{0,0,0,0};
    }

    @Override
    public void isAdjacent(int quarterAdjacentType, int crewAdjacent, int levelAdjacent, int parameterAdjacent) {
        //0=ParadoxalGenerator
        if (quarterAdjacentType==0) {
            productionBonusConstant+= (float) 15*min(levelAdjacent,level)*min(crewAdjacent,crew);
        }
        //1=DimensionlessSpace
        else if (quarterAdjacentType==1) {
            productionBonusRate+= (float) 10*levelAdjacent*crewAdjacent/(levelAdjacent+1);
        }
    }
}
