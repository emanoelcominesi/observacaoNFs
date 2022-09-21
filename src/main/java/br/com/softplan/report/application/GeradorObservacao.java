package br.com.softplan.report.application;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import br.com.softplan.report.model.NotaFiscal;

public class GeradorObservacao { 

	//Textos pré-definidos
	static final String umaNota     = "Fatura da nota fiscal de simples remessa: ";
	static final String variasNotas = "Fatura das notas fiscais de simples remessa: ";
	static final String valorNota   = " cujo valor é R$ ";
	static final String totalNotas  = ". Total: R$ ";
		
	//Gera observações, com texto pre-definido, incluindo os números e valores, das notas fiscais, recebidos no parâmetro
	public String geraObservacao(List<NotaFiscal> notas) {
		if (notas.isEmpty()) {
			return "";
		} else {
			return (notas.size() == 1 ? umaNota : variasNotas) + retornaDetalhesNota(notas) + ".";
		}
	}

	//Retorna os numeros e valores das notas da lista com os separadores
	private String retornaDetalhesNota(List<NotaFiscal> notas) {
		DecimalFormat formatoMonetario = new DecimalFormat("#,##0.00");
		StringBuilder retorno = new StringBuilder();
		String separador;
		
		Integer tamanho = 1;
		BigDecimal valorTotalNotas = BigDecimal.ZERO;
		
		for (NotaFiscal nota  : notas) {
			valorTotalNotas = valorTotalNotas.add(nota.getValor());

			if (tamanho == 1) {
				separador = "";
			} else if (tamanho < (notas.size())) {
				separador =  ", ";
			} else {
				separador =  " e ";
			}
			
			retorno.append(separador + nota.getNumero() + valorNota + formatoMonetario.format(nota.getValor()));

			tamanho++;
		}
		
		return retorno.toString() + totalNotas + formatoMonetario.format(valorTotalNotas);
	}
}