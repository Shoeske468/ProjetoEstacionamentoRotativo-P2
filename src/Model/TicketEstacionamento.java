
package Model;

import Controller.Veiculo;
import interfaces.Tarifavel;

import java.time.Duration;
import java.time.LocalDateTime;

public class TicketEstacionamento implements Comparable<TicketEstacionamento>, Tarifavel {

    private static final double VALOR_POR_HORA = 5.00;

    private Veiculo veiculo;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;

    public TicketEstacionamento(Veiculo veiculo, LocalDateTime horarioEntrada) {
        this.veiculo = veiculo;
        this.horarioEntrada = horarioEntrada;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public void registrarSaida(LocalDateTime horarioSaida) {
        if (horarioSaida == null || horarioSaida.isBefore(horarioEntrada)) {
            throw new IllegalArgumentException("Horário de saída inválido.");
        }

        this.horarioSaida = horarioSaida;
    }

    public long getHorasCobradas() {

        LocalDateTime fim = (horarioSaida == null)
                ? LocalDateTime.now() : horarioSaida;

        long segundos = Duration.between(horarioEntrada, fim).getSeconds();

        return Math.max(1, (long) Math.ceil(segundos / 3600.0));
    }

    @Override
    public double calcularPrecoEstadia() {
        return getHorasCobradas() * VALOR_POR_HORA;
    }

    @Override
    public int compareTo(TicketEstacionamento outro) {
        return horarioEntrada.compareTo(outro.horarioEntrada);
    }
}
