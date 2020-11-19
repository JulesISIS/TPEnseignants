package champollion;

import java.util.Date;

/**
 *
 * @author mjule
 */
public class Intervention {

    private final java.util.Date debut;
    private final int duree;
    private final boolean annulee;

    public Intervention (Salle s, UE u, Enseignant e, java.util.Date deb, int duree) {
        this.debut = deb;
        this.duree = duree;
        this.annulee = false;
    }

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public boolean isAnnulee() {
        return annulee;
    }

}
