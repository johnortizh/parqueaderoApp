
package com.mycompany.parqueadero;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Parqueadero parqueadero = new Parqueadero();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            switch (opcion) {
                case 1 -> ingresarVehiculo();
                case 2 -> registrarSalida();
                case 3 -> consultarEstado();
                case 4 -> System.out.println("Saliendo... ¡Gracias!");
                default -> System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 4);
    }

    /* ====== Operaciones ====== */

    private static void ingresarVehiculo() {
        System.out.println("\n--- Ingresar vehículo ---");
        System.out.println("Tipo: 1) Automóvil  2) Motocicleta  3) Camión");
        int tipo = leerEntero("Selecciona: ");
        String placa = leerLinea("Placa: ");
        String marca = leerLinea("Marca: ");
        String modelo = leerLinea("Modelo: ");

        Vehiculo v;
        switch (tipo) {
            case 1 -> {
                TipoCombustible tc = leerTipoCombustible();
                v = new Automovil(placa, marca, modelo, tc);
            }
            case 2 -> {
                int cilindraje = leerEntero("Cilindraje (cc): ");
                v = new Motocicleta(placa, marca, modelo, cilindraje);
            }
            case 3 -> {
                double ton = leerDouble("Capacidad de carga (ton): ");
                v = new Camion(placa, marca, modelo, ton);
            }
            default -> {
                System.out.println("Tipo no válido. Cancelado.");
                return;
            }
        }

        parqueadero.registrarEntrada(v, LocalDateTime.now());
        System.out.println("✔ Ingresado: " + resumenVehiculo(v));
    }

    private static void registrarSalida() {
        System.out.println("\n--- Registrar salida ---");
        String placa = leerLinea("Placa: ");
        try {
            double costo = parqueadero.registrarSalida(placa, LocalDateTime.now());
            System.out.printf("✔ Salida registrada. Total a pagar: $%.2f%n", costo);
        } catch (IllegalArgumentException e) {
            System.out.println("✖ " + e.getMessage());
        }
    }

    private static void consultarEstado() {
        System.out.println("\n--- Vehículos presentes ---");
        List<Vehiculo> lista = parqueadero.consultarEstado();
        if (lista.isEmpty()) {
            System.out.println("(No hay vehículos en el parqueadero)");
            return;
        }
        int i = 1;
        for (Vehiculo v : lista) {
            System.out.printf("%d) %s%n", i++, resumenVehiculo(v));
        }
    }

    /* ====== Entrada segura ====== */

    private static void mostrarMenu() {
        System.out.println("""
            ==============================
            PARQUEADERO - Menú principal
            1. Ingresar un vehículo
            2. Registrar salida de un vehículo
            3. Consultar estado del parqueadero
            4. Salir
            ==============================
            """);
    }

    private static int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) { System.out.println("Ingresa un número válido."); }
        }
    }

    private static double leerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            try { return Double.parseDouble(s); }
            catch (NumberFormatException e) { System.out.println("Ingresa un número decimal válido (ej. 2.5)."); }
        }
    }

    private static String leerLinea(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static TipoCombustible leerTipoCombustible() {
        System.out.println("Tipo de combustible:");
        TipoCombustible[] vals = TipoCombustible.values();
        for (int i = 0; i < vals.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, formatearEnum(vals[i]));
        }
        int idx;
        while (true) {
            idx = leerEntero("Selecciona: ") - 1;
            if (idx >= 0 && idx < vals.length) break;
            System.out.println("Opción no válida.");
        }
        return vals[idx];
    }

    private static String formatearEnum(Enum<?> e) {
        String n = e.name().toLowerCase();
        return Character.toUpperCase(n.charAt(0)) + n.substring(1);
    }

    private static String resumenVehiculo(Vehiculo v) {
        String base = "%s [%s] %s %s".formatted(
                v.getClass().getSimpleName(), v.getPlaca(), v.getMarca(), v.getModelo()
        );
        if (v instanceof Automovil a)  return base + " - " + formatearEnum(a.getTipoCombustible());
        if (v instanceof Motocicleta m) return base + " - " + m.getCilindraje() + "cc";
        if (v instanceof Camion c)     return base + " - " + c.getCapacidadCarga() + " ton";
        return base;
    }
}
