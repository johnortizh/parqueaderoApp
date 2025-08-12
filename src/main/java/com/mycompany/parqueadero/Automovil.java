
package com.mycompany.parqueadero;

import java.time.LocalDateTime;

public class Automovil extends Vehiculo {
    private final TipoCombustible tipoCombustible;
    private static final double TARIFA_HORA = 3000.0;

    public Automovil(String placa, String marca, String modelo, TipoCombustible tipoCombustible) {
        super(placa, marca, modelo);
        this.tipoCombustible = tipoCombustible;
    }

    @Override
    public double calcularCosto(LocalDateTime horaSalida) {
        long horas = horasEstadia(horaSalida);
        return horas * TARIFA_HORA;
    }

    public TipoCombustible getTipoCombustible() { return tipoCombustible; }
}
