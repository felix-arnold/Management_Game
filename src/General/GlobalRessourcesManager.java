package General;

public class GlobalRessourcesManager {

    private Ressources scienceRessource = new Ressources(false);
    private Ressources bitsRessource = new Ressources(false);
    private Ressources codeDataRessource = new Ressources(false);
    private Ressources cryptoMoneyRessource = new Ressources(true);

    public Ressources getBitsRessource() {
        return bitsRessource;
    }
    public Ressources getCodeDataRessource() {
        return codeDataRessource;
    }
    public Ressources getCryptoMoneyRessource() {
        return cryptoMoneyRessource;
    }
    public Ressources getScienceRessource() {
        return scienceRessource;
    }


    //We use a singleton
    private GlobalRessourcesManager () {}

    private static GlobalRessourcesManager INSTANCE = new GlobalRessourcesManager();

    public static GlobalRessourcesManager getInstance() {
        return INSTANCE;
    }


    public void globalRessourcesUpdate() {

    }
}
