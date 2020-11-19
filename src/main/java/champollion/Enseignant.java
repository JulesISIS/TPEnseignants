package champollion;

import static java.lang.Math.round;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Enseignant extends Personne {

    private final Set<Intervention> myInterventions;
    private final Map<UE, Map<TypeIntervention, Integer>> monHashMap;
    private final Map<TypeIntervention, Integer> mesHeures;
    private float nbHeuresTotales = 0;
    private float nbHeuresPlanTotales = 0;
    private final ServicePrevu myService = new ServicePrevu(0, 0, 0);

    public Enseignant(String nom, String email) {
        super(nom, email);
        this.myInterventions = new HashSet<>();
        this.mesHeures = new HashMap<>();
        this.monHashMap = new HashMap<>();
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures
     * équivalent TD" Pour le calcul : 1 heure de cours magistral vaut 1,5 h
     * "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut
     * 0,75h "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        for (UE mapkey : monHashMap.keySet()) {
            nbHeuresTotales += heuresPrevuesPourUE(mapkey);
        }
        return (int) nbHeuresTotales;
    }

    public int heuresPlanifiees() {
        for (Intervention e : myInterventions) {
            nbHeuresPlanTotales += e.getDuree();
        }
        return (int) nbHeuresPlanTotales;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE
     * spécifiée en "heures équivalent TD" Pour le calcul : 1 heure de cours
     * magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent
     * TD" 1 heure de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        float heuresCM = (float) (monHashMap.get(ue).get(TypeIntervention.CM) * 1.5);
        float heuresTD = (float) (monHashMap.get(ue).get(TypeIntervention.TD));
        float heuresTP = (float) (monHashMap.get(ue).get(TypeIntervention.TP) * 0.75);
        float nbHeures = round(heuresCM + heuresTD + heuresTP);
        return (int) nbHeures;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        myService.setVolumeCM(myService.getVolumeCM() + volumeTP);
        myService.setVolumeTD(myService.getVolumeTP() + volumeTD);
        myService.setVolumeTP(myService.getVolumeTP() + volumeTD);

        if (monHashMap.get(ue) == null) {
            mesHeures.put(TypeIntervention.CM, volumeCM);
            mesHeures.put(TypeIntervention.TD, volumeTD);
            mesHeures.put(TypeIntervention.TP, volumeTP);
            monHashMap.put(ue, mesHeures);
        } else {
            Map<TypeIntervention, Integer> mesHeures = monHashMap.get(ue);
            mesHeures.put(TypeIntervention.CM, mesHeures.get(TypeIntervention.CM) + volumeCM);
            mesHeures.put(TypeIntervention.TD, mesHeures.get(TypeIntervention.TD) + volumeTD);
            mesHeures.put(TypeIntervention.TP, mesHeures.get(TypeIntervention.TP) + volumeTP);
            monHashMap.put(ue, mesHeures);
        }

    }

    public void ajouteIntervention(Intervention e) {
        myInterventions.add(e);
    }

    public boolean enSousService() {
        if (nbHeuresTotales < nbHeuresPlanTotales) {
            return true;
        }
        return false;
    }
}
