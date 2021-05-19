package br.com.bandtec.continuada1giovannaPaniguel;

public class Credito extends Cartao{

    //Atributo
    private Double limiteCartao;

    //Construtor
    public Credito(Double valor,
                   String numeroCartao,
                   String nomeTitular,
                   Integer codSeguranca,
                   Double limiteCartao) {
        super(valor, numeroCartao, nomeTitular, codSeguranca);
        this.limiteCartao = limiteCartao;
    }

    //Métodos

    @Override
    public void debitaValor() {
        limiteCartao = limiteCartao - getValor();
    }

    @Override
    public Double valorFinal() {
        return getValor() * 1.03;
    }

    public Double getLimiteCartao() {
        return limiteCartao;
    }
}
