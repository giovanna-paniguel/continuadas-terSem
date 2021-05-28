package br.com.bandtec.continuada3giovannapaniguel.repositorio;

import br.com.bandtec.continuada3giovannapaniguel.dominio.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
