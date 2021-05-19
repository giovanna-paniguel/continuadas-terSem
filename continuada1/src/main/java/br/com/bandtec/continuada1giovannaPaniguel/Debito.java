package br.com.bandtec.continuada1giovannaPaniguel;

public class Debito extends Cartao{

    //Atributo
    private Double saldoConta;

    //Construtor
    public Debito(Double valor,
                  String numeroCartao,
                  String nomeTitular,
                  Integer codSeguranca,
                  Double saldoConta) {
        super(valor, numeroCartao, nomeTitular, codSeguranca);
        this.saldoConta = saldoConta;
    }

    //Métodos
    @Override
    public void debitaValor() {
        saldoConta = saldoConta - getValor();
    }

    @Override
    public Double valorFinal() {
        return getValor() * 1.03;
    }

    public Double getSaldoConta() {
        return saldoConta;
    }
}
