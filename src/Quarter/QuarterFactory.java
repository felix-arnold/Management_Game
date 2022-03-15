package Quarter;

import Quarter.ProductionQuarter.*;

public class QuarterFactory {

    public static Quarter getQuarter(String type) {
        if("Berth".equalsIgnoreCase(type)) return new Berth();
            else if("Birdcatcher".equalsIgnoreCase(type)) return new Birdcatcher();
            else if("Cryptoinvestor".equalsIgnoreCase(type)) return new Cryptoinvestor();
            else if("Cryptomine".equalsIgnoreCase(type)) return new Cryptomine();
            else if("DataCenter".equalsIgnoreCase(type)) return new DataCenter();
            else if("DimensionlessSpace".equalsIgnoreCase(type)) return new DimensionlessSpace();
            else if("Galley".equalsIgnoreCase(type)) return new Galley();
            else if("HellishBoss".equalsIgnoreCase(type)) return new HellishBoss();
            else if("IASynthesisTank".equalsIgnoreCase(type)) return new IASynthetisTank();
            else if("InternetFiberProvider".equalsIgnoreCase(type)) return new InternetFiberProvider();
            else if("MadScientist".equalsIgnoreCase(type)) return new MadScientist();
            else if("ParadoxalGenerator".equalsIgnoreCase(type)) return new ParadoxalGenerator();
            else if("ProgrammersOffice".equalsIgnoreCase(type)) return new ProgrammersOffice();
            else if("Restroom".equalsIgnoreCase(type)) return new Restroom();
            else if("TemporalCaboose".equalsIgnoreCase(type)) return new TemporalCaboose();
            else if("VirtualQuantumComputer".equalsIgnoreCase(type)) return new VirtualQuantumComputer();
        return null;
    }
}