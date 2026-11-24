
package view;

import Controller.GerenciarEstacionamento;
import Model.TicketEstacionamento;
import exceptions.VagaNaoDispException;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EstacionamentoRotativo {

    static GerenciarEstacionamento estacionamento =
            new GerenciarEstacionamento(20);

    static JTextField campoPlaca = new JTextField(15);
    static JTextField campoModelo = new JTextField(15);

    static JTextArea relatorio = new JTextArea(12, 50);

    static StringBuilder registros = new StringBuilder();

    static DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame janela = new JFrame("Estacionamento Rotativo");

            janela.setSize(750, 580);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null);

            // PAINEL PRINCIPAL
            JPanel painel = new JPanel(new BorderLayout(15, 15));

            painel.setBackground(Color.WHITE);

            // BORDA INTERNA
            painel.setBorder(
                    new CompoundBorder(
                            new LineBorder(new Color(120, 120, 120), 2),
                            new EmptyBorder(20, 20, 20, 20)
                    )
            );

            // TÍTULO
            JLabel titulo = new JLabel(
                    "Sistema de Estacionamento Rotativo",
                    SwingConstants.CENTER
            );

            titulo.setFont(new Font("Arial", Font.BOLD, 22));

            painel.add(titulo, BorderLayout.NORTH);

            // CAMPOS DE PREENCHIMENTO
            JPanel formulario = new JPanel(new GridLayout(2, 2, 10, 10));

            formulario.setBackground(Color.WHITE);
            formulario.setBorder(new EmptyBorder(5, 5, 10, 5));

            formulario.add(new JLabel("Placa do veículo:"));
            formulario.add(campoPlaca);

            formulario.add(new JLabel("Modelo do veículo:"));
            formulario.add(campoModelo);

            // BOTÕES
            JButton botaoEntrada = new JButton("Registrar Entrada");
            JButton botaoSaida = new JButton("Registrar Saída");
            JButton botaoEstacionados = new JButton("Listar Veículos");
            JButton botaoHistorico = new JButton("Histórico");
            JButton botaoVagas = new JButton("Consultar Vagas");
            JButton botaoSair = new JButton("Sair");

            JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));

            botoes.setBackground(Color.WHITE);

            botoes.add(botaoEntrada);
            botoes.add(botaoSaida);
            botoes.add(botaoEstacionados);
            botoes.add(botaoHistorico);
            botoes.add(botaoVagas);
            botoes.add(botaoSair);

            // ÁREA DE RELATÓRIO
            relatorio.setEditable(false);
            relatorio.setLineWrap(true);
            relatorio.setWrapStyleWord(true);
            relatorio.setFont(new Font("Monospaced", Font.PLAIN, 13));
            relatorio.setBackground(new Color(248, 248, 248));

            relatorio.setBorder(new EmptyBorder(10, 10, 10, 10));

            JScrollPane rolagem = new JScrollPane(relatorio);


            // ORGANIZAÇÃO DOS COMPONENTES
            JPanel superior = new JPanel(new BorderLayout(10, 10));

            superior.setBackground(Color.WHITE);

            superior.add(formulario, BorderLayout.NORTH);
            superior.add(botoes, BorderLayout.CENTER);

            JPanel centro = new JPanel(new BorderLayout(10, 10));

            centro.setBackground(Color.WHITE);

            centro.add(superior, BorderLayout.NORTH);
            centro.add(rolagem, BorderLayout.CENTER);

            painel.add(centro, BorderLayout.CENTER);

            janela.setContentPane(painel);

            // AÇÕES DOS BOTÕES
            botaoEntrada.addActionListener(e -> registrarEntrada());

            botaoSaida.addActionListener(e -> registrarSaida());

            botaoEstacionados.addActionListener(e -> consultarEstacionados());

            botaoHistorico.addActionListener(e -> consultarHistorico());

            botaoVagas.addActionListener(e -> consultarVagas());

            botaoSair.addActionListener(e -> janela.dispose());

            // EXIBE O EXTRATO ASSIM QUE A JANELA ABRE
            atualizarExtrato();

            janela.setVisible(true);
        });
    }

    // ATUALIZA O EXTRATO PRINCIPAL
    public static void atualizarExtrato() {

        double totalArrecadado = 0;

        List<TicketEstacionamento> historico =
                estacionamento.getHistorico();

        for (TicketEstacionamento ticket : historico) {

            totalArrecadado += ticket.calcularPrecoEstadia();
        }

        String extrato =
                "=== EXTRATO DO PÁTIO ===\n\n"
                        + "Total de vagas: "
                        + estacionamento.getTotalVagas()
                        + "\nVagas ocupadas: "
                        + estacionamento.getVagasOcupadas()
                        + "\nVagas disponíveis: "
                        + estacionamento.getVagasDisponiveis()
                        + String.format(
                        "\nTotal arrecadado: R$ %.2f",
                        totalArrecadado
                )
                        + "\n\n========================================\n\n";

        relatorio.setText(extrato + registros);

        relatorio.setCaretPosition(0);
    }

    // MÉTODO PARA ADICIONAR LOGS
    public static void adicionarLog(String mensagem) {

        registros.append(mensagem).append("\n\n");

        atualizarExtrato();

        relatorio.setCaretPosition(
                relatorio.getDocument().getLength()
        );
    }

    // REGISTRAR ENTRADA
    public static void registrarEntrada() {

        String placa = campoPlaca.getText().trim();
        String modelo = campoModelo.getText().trim();

        try {

            estacionamento.registrarEntrada(placa, modelo);

            adicionarLog(
                    "ENTRADA REGISTRADA COM SUCESSO!\n"
                            + "Placa: " + placa
                            + "\nModelo: " + modelo
                            + "\nVagas disponíveis: "
                            + estacionamento.getVagasDisponiveis()
                            + "\n-----------------------------"
            );

            campoPlaca.setText("");
            campoModelo.setText("");

        } catch (VagaNaoDispException |
                 IllegalArgumentException e) {

            adicionarLog("ERRO: " + e.getMessage());
        }
    }

    // REGISTRAR SAÍDA
    public static void registrarSaida() {

        String placa = campoPlaca.getText().trim();

        if (placa.isEmpty()) {

            adicionarLog("ERRO: Informe a placa do veículo.");
            return;
        }

        TicketEstacionamento ticket =
                estacionamento.registrarSaida(placa);

        if (ticket == null) {

            adicionarLog("ERRO: Veículo não encontrado!");
            return;
        }

        adicionarLog(
                "SAÍDA REGISTRADA COM SUCESSO!\n"
                        + "Placa: " + ticket.getVeiculo().getPlaca()
                        + "\nModelo: " + ticket.getVeiculo().getModelo()
                        + "\nEntrada: "
                        + ticket.getHorarioEntrada().format(formato)
                        + "\nSaída: "
                        + ticket.getHorarioSaida().format(formato)
                        + "\nHoras cobradas: " + ticket.getHorasCobradas()
                        + String.format(
                        "\nTotal a pagar: R$ %.2f",
                        ticket.calcularPrecoEstadia()
                )
                        + "\n-----------------------------"
        );

        campoPlaca.setText("");
        campoModelo.setText("");
    }

    // CONSULTAR VEÍCULOS ESTACIONADOS
    public static void consultarEstacionados() {

        List<TicketEstacionamento> lista =
                estacionamento.getVeiculosEstacionadosOrdenados();

        adicionarLog("VEÍCULOS ESTACIONADOS:");

        if (lista.isEmpty()) {

            adicionarLog("Não existem veículos estacionados.");

        } else {

            for (TicketEstacionamento ticket : lista) {

                adicionarLog(
                        "Placa: "
                                + ticket.getVeiculo().getPlaca()
                                + "\nModelo: "
                                + ticket.getVeiculo().getModelo()
                                + "\nEntrada: "
                                + ticket.getHorarioEntrada().format(formato)
                                + "\n-----------------------------"
                );
            }
        }
    }

    // CONSULTAR HISTÓRICO
    public static void consultarHistorico() {

        List<TicketEstacionamento> lista =
                estacionamento.getHistorico();

        adicionarLog("HISTÓRICO DE SAÍDAS:");

        if (lista.isEmpty()) {

            adicionarLog("Ainda não existem saídas registradas.");

        } else {

            for (TicketEstacionamento ticket : lista) {

                adicionarLog(
                        "Placa: "
                                + ticket.getVeiculo().getPlaca()
                                + "\nModelo: "
                                + ticket.getVeiculo().getModelo()
                                + String.format(
                                "\nValor pago: R$ %.2f",
                                ticket.calcularPrecoEstadia()
                        )
                                + "\n-----------------------------"
                );
            }
        }
    }

    // CONSULTAR VAGAS
    public static void consultarVagas() {

        adicionarLog(
                "SITUAÇÃO DO ESTACIONAMENTO:\n"
                        + "Total de vagas: "
                        + estacionamento.getTotalVagas()
                        + "\nVagas ocupadas: "
                        + estacionamento.getVagasOcupadas()
                        + "\nVagas disponíveis: "
                        + estacionamento.getVagasDisponiveis()
                        + "\n-----------------------------"
        );
    }
}
