package br.com.fiap.paquimetro.dominio;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public enum OpcaoEstacionamento {

    FIXO(new BigDecimal(20), Arrays.asList(FormaPagamento.PIX, FormaPagamento.CREDITO, FormaPagamento.DEBITO)),
    P_HORA(new BigDecimal(10), Arrays.asList(FormaPagamento.CREDITO, FormaPagamento.DEBITO));

    private BigDecimal preco;

    private List<FormaPagamento> formaPagamentoPermitido;

    OpcaoEstacionamento(BigDecimal preco, List<FormaPagamento> formaPagamentoPermitido){
        this.preco = preco;
        this.formaPagamentoPermitido = formaPagamentoPermitido;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<FormaPagamento> getFormaPagamentoPermitido() {
        return formaPagamentoPermitido;
    }

}
