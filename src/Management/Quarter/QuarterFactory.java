package Management.Quarter;

import Management.Quarter.ProductionQuarter.*;

/**
 * A factory class to build the desired quarter.
 */
public class QuarterFactory {

    /**
     * Creates easily a new specific quarter without explicitly calling its children class, using only the name and level of the desired quarter.
     * @param name the name of the quarter
     * @param level the level of the quarter
     * @return
     */
    public static Quarter getQuarter(String name, int level) {
        if("Berth".equalsIgnoreCase(name)) return new Berth(level);
            else if("Birdcatcher".equalsIgnoreCase(name)) return new Birdcatcher(level);
            else if("Cryptoinvestor".equalsIgnoreCase(name)) return new Cryptoinvestor(level);
            else if("Cryptomine".equalsIgnoreCase(name)) return new Cryptomine(level);
            else if("DataCenter".equalsIgnoreCase(name)) return new DataCenter(level);
            else if("DimensionlessSpace".equalsIgnoreCase(name)) return new DimensionlessSpace(level);
            else if("Galley".equalsIgnoreCase(name)) return new Galley(level);
            else if("HellishBoss".equalsIgnoreCase(name)) return new HellishBoss(level);
            else if("IASynthesisTank".equalsIgnoreCase(name)) return new IASynthetisTank(level);
            else if("InternetFiberProvider".equalsIgnoreCase(name)) return new InternetFiberProvider(level);
            else if("MadScientist".equalsIgnoreCase(name)) return new MadScientist(level);
            else if("ParadoxalGenerator".equalsIgnoreCase(name)) return new ParadoxalGenerator(level);
            else if("ProgrammersOffice".equalsIgnoreCase(name)) return new ProgrammersOffice(level);
            else if("TemporalCaboose".equalsIgnoreCase(name)) return new TemporalCaboose(level);
            else if("VirtualQuantumComputer".equalsIgnoreCase(name)) return new VirtualQuantumComputer(level);
            else if("DataFundry".equalsIgnoreCase(name)) return new DataFundry(level);

        return null;
    }
}