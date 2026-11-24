
package Controller;

import java.util.Objects;

public class Veiculo {

    private String placa;
    private String modelo;

    public Veiculo(String placa, String modelo) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("A placa é obrigatória.");
        }

        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo é obrigatório.");
        }

        this.placa = placa.trim().toUpperCase();
        this.modelo = modelo.trim();
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Veiculo outro = (Veiculo) obj;
        return placa.equalsIgnoreCase(outro.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa.toUpperCase());
    }

    @Override
    public String toString() {
        return "Placa: " + placa + " | Modelo: " + modelo;
    }
}
