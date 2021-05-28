public class Pagamento {

    private Integer id;
    private String tipo;
    private String nomeTitular;
    private Double valor;
    private Double saldoConta;
    private String numeroCartao;
    private Integer codSeguranca;

    public Pagamento(Integer id, String tipo, String nomeTitular, Double valor, Double saldoConta, String numeroCartao, Integer codSeguranca) {
        this.id = id;
        this.tipo = tipo;
        this.nomeTitular = nomeTitular;
        this.valor = valor;
        this.saldoConta = saldoConta;
        this.numeroCartao = numeroCartao;
        this.codSeguranca = codSeguranca;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public Double getValor() {
        return valor;
    }

    public Double getSaldoConta() {
        return saldoConta;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Integer getCodSeguranca() {
        return codSeguranca;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setSaldoConta(Double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setCodSeguranca(Integer codSeguranca) {
        this.codSeguranca = codSeguranca;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", valor=" + valor +
                ", saldoConta=" + saldoConta +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", codSeguranca=" + codSeguranca +
                '}';
    }
}
