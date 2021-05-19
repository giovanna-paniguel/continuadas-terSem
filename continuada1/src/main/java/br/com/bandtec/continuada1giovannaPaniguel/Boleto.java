package br.com.bandtec.continuada1giovannaPaniguel;

public class Boleto implements Pagamento{

    //Atributos
    private String linhaDigitavel;
    private String dataVencimento;
    private Double valor;
    private Double valorFinal;

    //Construtor
    public Boleto(Double valor,
                  String linhaDigitavel,
                  String dataVencimento) {
        this.linhaDigitavel = linhaDigitavel;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
    }


    //Métodos
    public Double valorFinal() {
        return valor * 0.95;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public Double getValor() {
        return valor;
    }

    public Double getValorFinal(){
        return valorFinal();
    }
}
