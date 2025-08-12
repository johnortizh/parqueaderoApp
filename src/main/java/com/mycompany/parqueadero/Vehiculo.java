
package com.mycompany.parqueadero;


import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Vehiculo {
    private final String placa;
    private final String marca;
    private final String modelo;
    private LocalDateTime horaEntrada;

    protected Vehiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public void registrarEntrada(LocalDateTime hora) {
        this.horaEntrada = hora;
    }

    /** Cada subclase define su costo. */
    public abstract double calcularCosto(LocalDateTime horaSalida);

    /** Horas de estadía redondeadas hacia arriba (p.ej., 2h01m → 3h). */
    protected long horasEstadia(LocalDateTime horaSalida) {
        if (horaEntrada == null) throw new IllegalStateException("El vehículo no tiene hora de entrada.");
        long min = Duration.between(horaEntrada, horaSalida).toMinutes();
        return (min <= 0) ? 0 : (min + 59) / 60;
    }

    // Getters
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
}

