package champollion;

import java.util.Date;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {

    Enseignant untel;
    UE uml, java;
    Salle s02;
    Intervention i1;
    Date premierMai = new Date();

    @BeforeEach
    public void setUp() {
        untel = new Enseignant("untel", "untel@gmail.com");
        uml = new UE("UML");
        java = new UE("Programmation en java");
        s02 = new Salle("s02", 30);
        i1 = new Intervention(s02, uml, untel, premierMai, 2);
    }

    @Test
    public void testNouvelEnseignantSansService() {
        assertEquals(0, untel.heuresPrevues(),
                "Un nouvel enseignant doit avoir 0 heures prévues");
    }

    @Test
    public void testAjouteInter() {
        // 60h d'intervention
        untel.ajouteIntervention(i1);

        assertEquals(60, untel.heuresPlanifiees(),
                "L'enseignant doit maintenant avoir 30heures prévues dans l'intervation");
    }

    @Test
    public void testAjouteHeures() {
        // 10h TD pour UML
        untel.ajouteEnseignement(uml, 0, 10, 0);

        assertEquals(10, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

        // 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);

        assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");

    }

    @Test
    public void testEnseignantSousService() {
        assertTrue(untel.enSousService(),
        "L'enseignant untel est en sous service");
    }

}
