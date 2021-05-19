package br.com.bandtec.continuada1giovannaPaniguel;

public abstract class Cartao implements Pagamento{

    /*
    Utilizei classe abstrata pois cada cartão
    processa o pagamento de uma forma diferente
     */

    //Atributos
    private String numeroCartao;
    private String nomeTitular;
    private Integer codSeguranca;
    private Double valor;
    private Double valorFinal;

    //Construtor
    public Cartao(Double valorPagto,
                  String numeroCartao,
                  String nomeTitular,
                  Integer codSeguranca) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.codSeguranca = codSeguranca;
        this.valor = valorPagto;
    }

    //Métodos

    public abstract void debitaValor();

    public Double valorFinal() {
        return valor * 1.03;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public Integer getCodSeguranca() {
        return codSeguranca;
    }

    public Double getValor() {
        return valor;
    }

    public Double getValorFinal(){
        return valorFinal();
    }
}