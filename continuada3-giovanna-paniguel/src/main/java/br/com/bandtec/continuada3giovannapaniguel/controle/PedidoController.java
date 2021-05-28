package br.com.bandtec.continuada3giovannapaniguel.controle;

import br.com.bandtec.continuada3giovannapaniguel.dominio.Cupcake;
import br.com.bandtec.continuada3giovannapaniguel.dominio.Pedido;
import br.com.bandtec.continuada3giovannapaniguel.repositorio.PedidoRepository;
import br.com.bandtec.continuada3giovannapaniguel.repositorio.CupcakeRepository;
import br.com.bandtec.continuada3giovannapaniguel.obj.FilaObj;
import br.com.bandtec.continuada3giovannapaniguel.obj.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private CupcakeRepository cupcakeRepository;

    List<Pedido> lista = new ArrayList<Pedido>();
    PilhaObj<Pedido> pilha = new PilhaObj<>(100);
    FilaObj<Pedido>  fila = new FilaObj<>(100);

    @GetMapping
    public ResponseEntity getPedidos() {
        List<Pedido> pedido = repository.findAll();
        return pedido.isEmpty() ? noContent().build() : ok(pedido);
    }

    @PostMapping
    public ResponseEntity postPedido(@RequestBody Pedido novoPedido) {
        repository.save(novoPedido);
        fila.insert(novoPedido);
        pilha.push(novoPedido);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putPedido(@PathVariable Integer id, @RequestBody Pedido atualizaPedido) {
        if (repository.existsById(id)){
            atualizaPedido.setIdPedido(id);
            repository.save(atualizaPedido);
            fila.insert(atualizaPedido);
            pilha.push(atualizaPedido);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/enfileirar")
    public ResponseEntity enfileirarReq(@RequestBody Pedido addPedido){
        Integer protocolo = new Random().nextInt(100) + 1;
        addPedido.setProtocolo(protocolo);
        fila.insert(addPedido);
        pilha.push(addPedido);
        return ResponseEntity.ok().body(protocolo);
    }

    @GetMapping("/consulta/{protocolo}")
    public ResponseEntity consultaReq(@PathVariable Integer protocolo){
        for (Pedido p : lista) {
            if (p.getProtocolo().equals(protocolo)) {
                lista.remove(0);
                return ResponseEntity.ok().body("Pedido adicionado");
            }
        }
        return ResponseEntity.badRequest().body("Aguarde");
    }

    @DeleteMapping("/desfazerReq")
    public ResponseEntity desfazerReq(){
        if(!pilha.isEmpty()) {
            repository.delete(pilha.pop());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/leituraArquivo/{arquivo}")
    public ResponseEntity leituraArquivo(@PathVariable String arquivo) {

        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        Integer idPedido;
        Integer contRegistro = 0;
        String nomeCliente;
        String descricao;
        String sabor;
        LocalDate validade;
        Double preco;

        Cupcake novoCupcake = new Cupcake();

        try {
            entrada = new BufferedReader(new FileReader(arquivo));
        } catch (IOException e) {
            return ResponseEntity.status(400).body("Erro ao abrir o arquivo: %s.\\n\", e.getMessage()");
        }

        try {
            registro = entrada.readLine();

            while (registro != null) {

                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("01")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 20));
                    System.out.println("Data de geração do arquivo: " + registro.substring(20, 30));
                    System.out.println("Versão do layout: " + registro.substring(30, 31));
                } else if (tipoRegistro.equals("01")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(1, 7));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    } else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }
                } else if (tipoRegistro.equals("02")) {
                    sabor = registro.substring(2, 13).trim();
                    validade = LocalDate.parse(registro.substring(14, 24));
                    preco = Double.parseDouble(registro.substring(25,29));

                    novoCupcake.setSabor(sabor);
                    novoCupcake.setPreco(preco);
                    novoCupcake.setValidade(validade);
                    cupcakeRepository.save(novoCupcake);

                    contRegistro++;
                } else if (tipoRegistro.equals("03")) {
                    nomeCliente = registro.substring(3, 27).trim();
                    descricao = registro.substring(28, 47).trim();

                    Pedido novoPedido = new Pedido();
                    novoPedido.setCupcake(novoCupcake);
                    novoPedido.setNomeCliente(nomeCliente);
                    novoPedido.setDescricao(descricao);
                    repository.save(novoPedido);
                    contRegistro++;
                }
                registro = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            return ResponseEntity.status(400).body("Erro ao ler arquivo: %s.\\n\", e.getMessage()");
        }

        return ResponseEntity.status(200).body("Leitura feita com sucesso");
    }


}
