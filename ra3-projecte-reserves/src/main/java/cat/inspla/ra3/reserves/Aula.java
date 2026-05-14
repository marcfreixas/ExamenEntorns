package cat.inspla.ra3.reserves;

/**
 * Aquesta classe implementa la interfície {@link Reservable} i s'encarrega de controlar
 * l'estat d'ocupació, la capacitat d'alumnes i el càlcul del cost del lloguer temporal.
 *
 * @author Marc Freixas
 * @version 1.0
 */
public class Aula implements Reservable {
    private final String nom;
    private final int capacitat;
    private boolean disponible;

    /**
     * Construeix una nova instància d'Aula amb un nom i una capacitat específica.
     *
     * @param nom Nom identificatiu de l'aula (no pot ser nul ni estar buit).
     * @param capacitat Nombre màxim de persones que pot encabir (ha de ser positiu).
     * @throws IllegalArgumentException si el nom és nul/buit o si la capacitat és zero o negativa.
     */
    public Aula(String nom, int capacitat) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de l'aula és obligatori");
        }
        if (capacitat <= 0) {
            throw new IllegalArgumentException("La capacitat ha de ser positiva");
        }
        this.nom = nom;
        this.capacitat = capacitat;
        this.disponible = true;
    }

    @Override
    public String getNom() { return nom; }

    @Override
    public TipusRecurs getTipus() { return TipusRecurs.AULA; }

    @Override
    public int getCapacitat() { return capacitat; }

    @Override
    public boolean estaDisponible() { return disponible; }

    /**
     * Realitza la reserva de l'aula si aquesta es troba lliure.
     * @throws IllegalStateException si l'aula ja ha estat reservada anteriorment i no s'ha alliberat.
     */
    @Override
    public void reservar() {
        if (!disponible) {
            throw new IllegalStateException("El recurs ja està reservat");
        }
        disponible = false;
    }

    /**
     * Restableix l'estat de l'aula a disponible per a futures reserves.
     */
    @Override
    public void alliberar() { disponible = true; }

    /**
     * Calcula l'import total de la reserva basant-se en una tarifa fixa per hora.
     *
     * @param hores Nombre d'hores totals de la reserva.
     * @return El cost final calculat
     * @throws IllegalArgumentException si el nombre d'hores és menor o igual a zero.
     */
    @Override
    public double calcularCostReserva(int hores) {
        validarHores(hores);
        return hores * 12.0;
    }

    /**
     * Mètode auxiliar per garantir que el temps de reserva és coherent.
     *
     * @param hores Valor enter a validar.
     * @throws IllegalArgumentException si el valor no és estrictament positiu.
     */
    protected void validarHores(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
    }
}