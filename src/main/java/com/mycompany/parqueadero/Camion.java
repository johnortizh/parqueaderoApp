
package com.mycompany.parqueadero;

import java.time.LocalDateTime;

public class Camion extends Vehiculo {
    private final double capacidadCarga; // toneladas
    private static final double TARIFA_HORA_BASE = 6000.0;
    private static final double RECARGO_POR_TON = 500.0;

    public Camion(String placa, String marca, String modelo, double capacidadCarga) {
        super(placa, marca, modelo);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public double calcularCosto(LocalDateTime horaSalida) {
        long horas = horasEstadia(horaSalida);
        double tarifa = TARIFA_HORA_BASE + (capacidadCarga * RECARGO_POR_TON);
        return horas * tarifa;
    }

    public double getCapacidadCarga() { return capacidadCarga; }
}
