package br.com.bandtec.continuada1giovannaPaniguel;

public interface Pagamento {

    /*
    Utilizei interface pois todo pagamento possui o valor final e
    nem todo pagamento possui número do cartão ou linha digitável
     */

    public Double valorFinal();

}
