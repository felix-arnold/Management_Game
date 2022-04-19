package Quarter;

import Quarter.ProductionQuarter.*;

import javax.xml.crypto.Data;

public class QuarterFactory {

    public static Quarter getQuarter(String type, int level) {
        if("Berth".equalsIgnoreCase(type)) return new Berth(level);
            else if("Birdcatcher".equalsIgnoreCase(type)) return new Birdcatcher(level);
            else if("Cryptoinvestor".equalsIgnoreCase(type)) return new Cryptoinvestor(level);
            else if("Cryptomine".equalsIgnoreCase(type)) return new Cryptomine(level);
            else if("DataCenter".equalsIgnoreCase(type)) return new DataCenter(level);
            else if("DimensionlessSpace".equalsIgnoreCase(type)) return new DimensionlessSpace(level);
            else if("Galley".equalsIgnoreCase(type)) return new Galley(level);
            else if("HellishBoss".equalsIgnoreCase(type)) return new HellishBoss(level);
            else if("IASynthesisTank".equalsIgnoreCase(type)) return new IASynthetisTank(level);
            else if("InternetFiberProvider".equalsIgnoreCase(type)) return new InternetFiberProvider(level);
            else if("MadScientist".equalsIgnoreCase(type)) return new MadScientist(level);
            else if("ParadoxalGenerator".equalsIgnoreCase(type)) return new ParadoxalGenerator(level);
            else if("ProgrammersOffice".equalsIgnoreCase(type)) return new ProgrammersOffice(level);
            else if("TemporalCaboose".equalsIgnoreCase(type)) return new TemporalCaboose(level);
            else if("VirtualQuantumComputer".equalsIgnoreCase(type)) return new VirtualQuantumComputer(level);
            else if("DataFundry".equalsIgnoreCase(type)) return new DataFundry(level);

        return null;
    }
}