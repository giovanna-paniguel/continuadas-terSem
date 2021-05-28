package br.com.bandtec.continuada3giovannapaniguel.controle;

import br.com.bandtec.continuada3giovannapaniguel.repositorio.CupcakeRepository;
import br.com.bandtec.continuada3giovannapaniguel.dominio.Cupcake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/cupcakes")
public class CupcakeController {

    @Autowired
    private CupcakeRepository cupcakeRepository;

    @Autowired
    private PedidoController repository;

    @GetMapping
    public ResponseEntity getCupcakes() {
        List<Cupcake> cupcake = cupcakeRepository.findAll();
        return cupcake.isEmpty() ? noContent().build() : ok(cupcake);
    }

    @PostMapping
    public ResponseEntity postCupcake(@RequestBody @Valid Cupcake novoSabor) {
        if (cupcakeRepository.countBySabor(novoSabor.getSabor()) == 1) {
            return ResponseEntity.status(400).body("Esse sabor ja está cadastrado!");
        } else {
            cupcakeRepository.save(novoSabor);
            return ResponseEntity.status(201).build();
        }
    }

    @GetMapping("/validade/{id}/{validade}")
    public ResponseEntity consultaValidade(@PathVariable Integer id, @PathVariable("validade") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate validade) {
        Cupcake cupcake = cupcakeRepository.findById(id).get();

        if (validade.isAfter(cupcake.getValidade())) {
            return ResponseEntity.status(200).body("Cupcake está apto para consumo!");
        } else {
            return ResponseEntity.status(404).body("Cupcake fora da data de validade!");
        }
    }

    @GetMapping("/preco/{id}/{preco}")
    public ResponseEntity consultaPreco(@PathVariable Integer id, @PathVariable Double preco) {
        Cupcake cupcake = cupcakeRepository.findById(id).get();

        if (preco > 10.0) {
            return ResponseEntity.status(200).body("Cupcake está gerando lucro!");
        } else {
            return ResponseEntity.status(404).body("Redefinir preço!");
        }
    }

}