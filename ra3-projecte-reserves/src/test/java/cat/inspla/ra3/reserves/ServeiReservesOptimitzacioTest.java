package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServeiReservesOptimitzacioTest {

    @Test
    void recursosOrdenatsPerNomRetornaCopiaOrdenada() {
        ServeiReserves servei = new ServeiReserves();
        Aula aulaZ = new Aula("Zeta", 10);
        Aula aulaA = new Aula("Alfa", 20);
        Aula aulaM = new Aula("Media", 15);

        servei.afegirRecurs(aulaZ);
        servei.afegirRecurs(aulaA);
        servei.afegirRecurs(aulaM);

        List<Reservable> ordenats = servei.obtenirRecursosOrdenatsPerNom();

        assertEquals("Alfa", ordenats.get(0).getNom());
        assertEquals("Media", ordenats.get(1).getNom());
        assertEquals("Zeta", ordenats.get(2).getNom());

        assertEquals("Zeta", servei.getRecursos().get(0).getNom(),
                "La llista original del servei no s'hauria d'haver modificat");
    }

    @Test
    void generarInformeInclouNomTipusIEstat() {
        ServeiReserves servei = new ServeiReserves();

        Aula aula1 = new Aula("Aula 101", 30);
        Aula aula2 = new Aula("Aula 102", 20);
        aula2.reservar();

        servei.afegirRecurs(aula1);
        servei.afegirRecurs(aula2);

        String informe = servei.generarInformeRecursos();

        assertTrue(informe.contains("Aula 101"));
        assertTrue(informe.contains("AULA"));
        assertTrue(informe.contains("Disponible"));

        assertTrue(informe.contains("Aula 102"));
        assertTrue(informe.contains("Reservat"));

        assertTrue(informe.split(System.lineSeparator()).length >= 2);
    }
}
