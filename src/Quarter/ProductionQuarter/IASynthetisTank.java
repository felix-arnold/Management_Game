package Quarter.ProductionQuarter;

import static java.lang.Math.min;

public class IASynthetisTank extends ProductionQuarter {

    public IASynthetisTank() {
        super();

        //Construction cost, consumption, upgrade cost, variable
        maxLevel=4;
        maxCrew= new int[]{4, 5, 6, 8};
        productionPerCrew=new double[]{0.5,0.6,2.0/3.0,0.75};
        bitCost= new int[]{100,100,150,200};
        codeDataCost=new int[]{0,0,300,600};
        cryptomoneyCost=new int[]{700,2000,3500,6000};
        electricityConsumption=new int[]{200,400,600,1000};
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
