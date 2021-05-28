package br.com.bandtec.continuada3giovannapaniguel.repositorio;

import br.com.bandtec.continuada3giovannapaniguel.dominio.Cupcake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupcakeRepository extends JpaRepository<Cupcake, Integer> {

    Integer countBySabor(String sabor);

}
