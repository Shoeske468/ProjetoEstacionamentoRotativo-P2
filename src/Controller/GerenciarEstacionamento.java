
package Controller;

import Model.TicketEstacionamento;
import exceptions.VagaNaoDispException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenciarEstacionamento {

    private final int totalVagas;

    private List<TicketEstacionamento> veiculosEstacionados;
    private List<TicketEstacionamento> historico;

    public GerenciarEstacionamento(int totalVagas) {

        if (totalVagas <= 0) {
            throw new IllegalArgumentException("Total de vagas inválido.");
        }

        this.totalVagas = totalVagas;

        veiculosEstacionados = new ArrayList<>();
        historico = new ArrayList<>();
    }

    public void registrarEntrada(String placa, String modelo)
            throws VagaNaoDispException {

        if (veiculosEstacionados.size() >= totalVagas) {
            throw new VagaNaoDispException(
                    "Não há vagas disponíveis.");
        }

        Veiculo veiculo = new Veiculo(placa, modelo);

        for (TicketEstacionamento ticket : veiculosEstacionados) {

            if (ticket.getVeiculo().equals(veiculo)) {
                throw new IllegalArgumentException(
                        "Este veículo já está estacionado.");
            }
        }

        TicketEstacionamento ticket =
                new TicketEstacionamento(veiculo, LocalDateTime.now());

        veiculosEstacionados.add(ticket);
    }

    public TicketEstacionamento registrarSaida(String placa) {

        TicketEstacionamento ticket = buscarPorPlaca(placa);

        if (ticket == null) {
            return null;
        }

        ticket.registrarSaida(LocalDateTime.now());

        veiculosEstacionados.remove(ticket);
        historico.add(ticket);

        return ticket;
    }

    public TicketEstacionamento buscarPorPlaca(String placa) {

        if (placa == null) {
            return null;
        }

        for (TicketEstacionamento ticket : veiculosEstacionados) {

            if (ticket.getVeiculo().getPlaca()
                    .equalsIgnoreCase(placa.trim())) {
                return ticket;
            }
        }

        return null;
    }

    public List<TicketEstacionamento> getVeiculosEstacionadosOrdenados() {

        List<TicketEstacionamento> copia =
                new ArrayList<>(veiculosEstacionados);

        Collections.sort(copia);

        return copia;
    }

    public List<TicketEstacionamento> getHistorico() {
        return new ArrayList<>(historico);
    }

    public int getTotalVagas() {
        return totalVagas;
    }

    public int getVagasOcupadas() {
        return veiculosEstacionados.size();
    }

    public int getVagasDisponiveis() {
        return totalVagas - veiculosEstacionados.size();
    }
}
