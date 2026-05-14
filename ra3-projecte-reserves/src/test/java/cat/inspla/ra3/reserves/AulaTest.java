package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    @Test
    void TODO_calcularCostReservaAmbHoresValides() {
        Aula aula = new Aula("Aula 101", 30);
        int horesReserva = 2;
        double costEsperat = 24.0;

        double costObtingut = aula.calcularCostReserva(horesReserva);

        assertEquals(costEsperat, costObtingut, "El cost per 2 hores hauria de ser 24.0€");
    }

    @Test
    void TODO_crearAulaAmbCapacitatInvalidaLlançaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aula("Aula 77", 0);
        });
    }

    @Test
    void reservarAulaCanviaEstatILlançaExcepcioSiJaEstaReservada() {
        Aula aula = new Aula("Laboratori 1", 20);

        assertTrue(aula.estaDisponible());
        aula.reservar();
        assertFalse(aula.estaDisponible(), "L'aula hauria de passar a no disponible");
        assertThrows(IllegalStateException.class, () -> {
            aula.reservar();
        }, "Hauria de llançar IllegalStateException si ja està reservada");
    }

    @Test
    void crearAulaAmbNomInvalidLlançaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> new Aula("", 25));
        assertThrows(IllegalArgumentException.class, () -> new Aula("   ", 10));
        assertThrows(IllegalArgumentException.class, () -> new Aula(null, 15));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 12.0",
            "3, 36.0",
            "5, 60.0",
            "10, 120.0"
    })
    void calcularCostReservaRetornaPreuCorrecteSegonsHores(int hores, double costEsperat) {
        Aula aula = new Aula("Aula Teoria", 40);
        double costObtingut = aula.calcularCostReserva(hores);

        assertEquals(costEsperat, costObtingut,
                "El cost per " + hores + " hores hauria de ser " + costEsperat);
    }
}
