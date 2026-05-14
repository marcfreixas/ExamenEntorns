package cat.inspla.ra3.reserves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServeiReserves {
    private final List<Reservable> recursos = new ArrayList<>();

    public void afegirRecurs(Reservable recurs) {
        if (recurs == null) {
            throw new IllegalArgumentException("El recurs no pot ser nul");
        }
        recursos.add(recurs);
    }

    public List<Reservable> getRecursos() {
        return Collections.unmodifiableList(recursos);
    }

    public double calcularCostTotal(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
        double total = 0;
        for (Reservable recurs : recursos) {
            total += recurs.calcularCostReserva(hores);
        }
        return total;
    }

    public long comptarDisponibles() {
        return recursos.stream().filter(Reservable::estaDisponible).count();
    }

    public Reservable buscarPerNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de cerca és obligatori");
        }
        return recursos.stream()
                .filter(r -> r.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    /**
     * Utilitzant el metode .sorted es filtra tot amb l'algoritme Timsort
     * Pero mante la logica amb Comparator.compaging neccecitant una variable i fent la comparacio amb
     * aquesta
     */
    public List<Reservable> obtenirRecursosOrdenatsPerNom() {
        return recursos.stream()
                .sorted(Comparator.comparing(Reservable::getNom, String.CASE_INSENSITIVE_ORDER))
                .toList(); // O .collect(Collectors.toList())
    }

    /** Optimitzat utilitzant StringBuilder per evitar la creacio excessiva
     * d'objectes String en memòria durant el bucle.
     *
     * @return Un String amb el detall de cada recurs separat per salts de linia.
    */
    public String generarInformeRecursos() {
        // Inicialitzem el builder mes eficient per a concatenacions en bucles
        StringBuilder sb = new StringBuilder();

        for (Reservable recurs : recursos) {
            sb.append(recurs.getNom())
                    .append(" - ")
                    .append(recurs.getTipus())
                    .append(" - ")
                    .append(recurs.estaDisponible() ? "Disponible" : "Reservat")
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
