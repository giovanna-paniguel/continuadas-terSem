package br.com.bandtec.continuada3giovannapaniguel.dominio;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idPedido;

    @NotBlank
    @Size(min = 2, max = 25)
    public String nomeCliente;

    @NotBlank
    public String descricao;

    @Positive
    public Integer protocolo;

    @ManyToOne
    public Cupcake cupcake;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Integer protocolo) {
        this.protocolo = protocolo;
    }

    public Cupcake getCupcake() {
        return cupcake;
    }

    public void setCupcake(Cupcake cupcake) {
        this.cupcake = cupcake;
    }
}
