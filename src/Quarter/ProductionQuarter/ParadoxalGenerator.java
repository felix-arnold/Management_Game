package Quarter.ProductionQuarter;

import static java.lang.Math.min;


public class ParadoxalGenerator extends ProductionQuarter {

    public ParadoxalGenerator() {

        super();
        name = "ParadoxalGenerator";
        style = "paradoxalGenerator";
        selectedStyle = "selectedParadoxalGenerator";
        productionType="electricity";
    }

    String codeDataCost2=loadValue("ParadoxalGenerator",3,"codeDataCost");

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

    public String getCodeDataCost2() {
        return codeDataCost2;
    }
}
