package br.com.bandtec.continuada1giovannaPaniguel;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private List<Pagamento> pagamentos = new ArrayList<>();

    public PagamentoController() {
        pagamentos.add(new Debito(125.00,
                "74836262552232",
                "Giovanna Paniguel",
                654,
                678.90));
        pagamentos.add(new Credito(239.90,
                "74836262552298",
                "Robson Carlos",
                936,
                456.60));
        pagamentos.add(new Boleto(99.00,
                "83726166374858947",
                "25/02/2021"));
    }

    @GetMapping
    public List<Pagamento> getPagamento() {
        return pagamentos;
    }

    @DeleteMapping("/{id}")
    public String deletePagamento(@PathVariable int id){
        if (id < pagamentos.size()){
            pagamentos.remove(id);
            return "Pagamento excluido com sucesso!";
        }
        else {
            return "Pagamento não encontrado!";
        }
    }

    @PostMapping
    public String postPagamento(@RequestBody Pagamento novoPagamento){
        pagamentos.add(novoPagamento);
        return "Pagamento criado com sucesso!";
    }

}
