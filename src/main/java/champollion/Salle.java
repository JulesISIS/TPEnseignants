package champollion;

/**
 *
 * @author mjule
 */
public class Salle {

    private final String intitule;
    private final int capacite;

    public Salle(String intitule, int capacite) {
        this.intitule = intitule;
        this.capacite = capacite;
    }

    public String getIntitule() {
        return intitule;
    }

    public int getCapacite() {
        return capacite;
    }

}
