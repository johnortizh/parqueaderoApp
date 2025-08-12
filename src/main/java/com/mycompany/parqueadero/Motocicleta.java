
package com.mycompany.parqueadero;

import java.time.LocalDateTime;

public class Motocicleta extends Vehiculo {
    private final int cilindraje;
    private static final double TARIFA_HORA = 1500.0;

    public Motocicleta(String placa, String marca, String modelo, int cilindraje) {
        super(placa, marca, modelo);
        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularCosto(LocalDateTime horaSalida) {
        long horas = horasEstadia(horaSalida);
        return horas * TARIFA_HORA;
    }

    public int getCilindraje() { return cilindraje; }
}
