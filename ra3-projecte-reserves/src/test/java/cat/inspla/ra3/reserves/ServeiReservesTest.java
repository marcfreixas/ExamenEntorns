package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServeiReservesTest {

    @Test
    void TODO_afegirRecursIncrementaLlista() {
        ServeiReserves servei = new ServeiReserves();
        Aula aula = new Aula("Aula 101", 25);
        servei.afegirRecurs(aula);

        assertEquals(1, servei.getRecursos().size(), "La llista hauria de tenir exactament un recurs");
        assertEquals("Aula 101", servei.getRecursos().get(0).getNom());
    }

    @Test
    void afegirRecursNulLlançaExcepcio() {
        ServeiReserves servei = new ServeiReserves();

        assertThrows(IllegalArgumentException.class, () -> {
            servei.afegirRecurs(null);
        }, "Hauria de llançar una excepció en afegir un recurs nul");
    }
}
