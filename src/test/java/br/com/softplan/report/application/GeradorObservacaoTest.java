package br.com.softplan.report.application;

import org.junit.Before;
import org.junit.Test;

import br.com.softplan.report.model.NotaFiscal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeradorObservacaoTest {

    private GeradorObservacao geradorObservacao;

    @Before
    public void setUp() {
        geradorObservacao = new GeradorObservacao();
    }

    @Test
    public void deve_gerar_observacao_sem_nota() {
        List<NotaFiscal> numerosNotaFiscal = new ArrayList<>();

        String observacao = geradorObservacao.geraObservacao(numerosNotaFiscal);

        assertEquals("", observacao);
    }

    @Test
    public void deve_gerar_observacao_com_uma_nota() {
        List<NotaFiscal> numerosNotaFiscal = new ArrayList<>();
        
        numerosNotaFiscal.add(new NotaFiscal(Long.valueOf(1), BigDecimal.valueOf(10)));

        String observacao = geradorObservacao.geraObservacao(numerosNotaFiscal);

        assertEquals("Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00. Total: R$ 10,00.", observacao);
    }

    @Test
    public void deve_gerar_observacao_com_duas_notas() {
        List<NotaFiscal> numerosNotaFiscal = new ArrayList<>();
        numerosNotaFiscal.add(new NotaFiscal(Long.valueOf(1), BigDecimal.valueOf(10)));
        numerosNotaFiscal.add(new NotaFiscal(Long.valueOf(3), BigDecimal.valueOf(35)));

        String observacao = geradorObservacao.geraObservacao(numerosNotaFiscal);

        assertEquals("Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00 e 3 cujo valor é R$ 35,00. Total: R$ 45,00.", observacao);
    }

    @Test
    public void deve_gerar_observacao_com_diversas_notas() {
        List<NotaFiscal> numerosNotaFiscal = new ArrayList<>();

        numerosNotaFiscal.add(new NotaFiscal(Long.valueOf(1), BigDecimal.valueOf(10)));
        numerosNotaFiscal.add(new NotaFiscal(Long.valueOf(2), BigDecimal.valueOf(35)));
        numerosNotaFiscal.add(new NotaFiscal(Long.valueOf(3), BigDecimal.valueOf(5)));
        numerosNotaFiscal.add(new NotaFiscal(Long.valueOf(4), BigDecimal.valueOf(1500)));
        numerosNotaFiscal.add(new NotaFiscal(Long.valueOf(5), BigDecimal.valueOf(0.3)));

        String observacao = geradorObservacao.geraObservacao(numerosNotaFiscal);

        assertEquals("Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00, 2 cujo valor é R$ 35,00, 3 cujo valor é R$ 5,00, 4 cujo valor é R$ 1.500,00 e 5 cujo valor é R$ 0,30. Total: R$ 1.550,30.", observacao);
    }
}
