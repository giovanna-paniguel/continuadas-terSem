package br.com.bandtec.continuada3giovannapaniguel.dominio;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Cupcake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idCupcake;

    @NotBlank
    @Size(min = 2, max = 25)
    public String sabor;

    public LocalDate validade;

    public Double preco;

    public Integer getIdCupcake() {
        return idCupcake;
    }

    public void setIdCupcake(Integer idCupcake) {
        this.idCupcake = idCupcake;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
