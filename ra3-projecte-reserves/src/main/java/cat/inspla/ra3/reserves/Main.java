package cat.inspla.ra3.reserves;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Inicialitzem el servei i afegim dades de prova desordenades
        ServeiReserves servei = new ServeiReserves();

        servei.afegirRecurs(new Aula("Zeta", 15));

        servei.afegirRecurs(new Aula("Alfa", 30));

        servei.afegirRecurs(new Aula("Media", 20));

        // 2. Simulem una reserva per veure com canvia l'informe
        Reservable lab = servei.buscarPerNom("Laboratori de Física");
        if (lab != null) {
            lab.reservar();
        }


        List<Reservable> ordenats = servei.obtenirRecursosOrdenatsPerNom();
        
        ordenats.forEach(r -> System.out.println("- " + r.getNom()));


        String informe = servei.generarInformeRecursos();
        System.out.print(informe);

        // 5. Extra: Comprovem el càlcul de cost total
        int hores = 5;
        double costTotal = servei.calcularCostTotal(hores);
        System.out.println("========================================");
        System.out.printf("COST TOTAL DE TOTS ELS RECURSOS (%d h): %.2f€%n", hores, costTotal);
        System.out.println("========================================");
    }
}