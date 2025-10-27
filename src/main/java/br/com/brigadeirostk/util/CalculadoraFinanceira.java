package br.com.brigadeirostk.util;

/**
 * Classe responsável por cálculos financeiros simples.
 * Aplicando o princípio da responsabilidade única (SRP).
 */
public class CalculadoraFinanceira {

    public double calcularLucro(double receita, double despesas) {
        return receita - despesas;
    }

    public double calcularMargemLucro(double receita, double despesas) {
        if (receita == 0) {
            return 0;
        }
        return ((receita - despesas) / receita) * 100;
    }

    public double calcularValorEstoque(double precoUnitario, int quantidade) {
        return precoUnitario * quantidade;
    }
}
