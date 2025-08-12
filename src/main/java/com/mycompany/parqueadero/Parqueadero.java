
package com.mycompany.parqueadero;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parqueadero {
    private final List<Vehiculo> listaVehiculos = new ArrayList<>();

    public void registrarEntrada(Vehiculo v, LocalDateTime hora) {
        v.registrarEntrada(hora);
        listaVehiculos.add(v);
    }

    /** Calcula costo usando polimorfismo y remueve el vehículo. */
    public double registrarSalida(String placa, LocalDateTime horaSalida) {
        Vehiculo v = buscarVehiculo(placa);
        if (v == null) throw new IllegalArgumentException("No se encontró vehículo con placa: " + placa);
        double costo = v.calcularCosto(horaSalida);
        listaVehiculos.remove(v);
        return costo;
    }

    public List<Vehiculo> consultarEstado() {
        return Collections.unmodifiableList(listaVehiculos);
    }

    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }
}

