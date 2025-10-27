package com.BrigadeirosTK.domain;

import br.com.brigadeirostk.util.CalculadoraFinanceira;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários da classe CalculadoraFinanceira.
 */
public class CalculadoraFinanceiraTest {

    private final CalculadoraFinanceira calc = new CalculadoraFinanceira();

    @Test
    public void deveCalcularLucroCorretamente() {
        double lucro = calc.calcularLucro(500.0, 200.0);
        Assertions.assertEquals(300.0, lucro, 0.001);
    }

    @Test
    public void deveCalcularMargemLucro() {
        double margem = calc.calcularMargemLucro(1000.0, 400.0);
        Assertions.assertEquals(60.0, margem, 0.001);
    }

    @Test
    public void deveCalcularValorTotalEstoque() {
        double valor = calc.calcularValorEstoque(3.5, 100);
        Assertions.assertEquals(350.0, valor, 0.001);
    }
}
