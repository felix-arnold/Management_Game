package General;

public class LocalRessourcesManager {

    Ressources elecricityRessource = new Ressources(true);
    Ressources foodRessource = new Ressources(true);
    Ressources crewRessource = new Ressources(false);

    public Ressources getCrewRessource() {
        return crewRessource;
    }
    public Ressources getElecricityRessource() {
        return elecricityRessource;
    }
    public Ressources getFoodRessource() {
        return foodRessource;
    }


    public void localRessourcesUpdate() {

    }
}
