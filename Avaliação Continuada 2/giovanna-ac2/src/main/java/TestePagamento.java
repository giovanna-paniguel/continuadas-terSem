import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TestePagamento {

    public static void gravaLista(ListaObj<Pagamento> lista, String nomeDoArquivo) {
        FileWriter arquivo = null;
        Formatter saida = null;
        boolean deuRuim = false;

        try {
            arquivo = new FileWriter(nomeDoArquivo, true);
            saida = new Formatter(arquivo);
        } catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Pagamento pagamento = lista.getElemento(i);
                saida.format("%d;%s;%s;%.2f;%.2f;%s;%d%n",
                        pagamento.getId(),
                        pagamento.getTipo(),
                        pagamento.getNomeTitular(),
                        pagamento.getValor(),
                        pagamento.getSaldoConta(),
                        pagamento.getNumeroCartao(),
                        pagamento.getCodSeguranca());

            }
        } catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arquivo.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim)
                System.exit(1);
        }
    }

    public static void leExibeArquivo(String nomeDoArquivo) {
        FileReader arquivo = null;
        Scanner entrada = null;
        boolean deuRuim = false;

        try {
            arquivo = new FileReader(nomeDoArquivo);
            entrada = new Scanner(arquivo).useDelimiter(";|\\r\\n|\\n");
        } catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%-8s %-8s %-50s %-20s %-20s %-10s %-10s\n", "ID PAGAMENTO", "TIPO" ,"NOME DO TITULAR", "VALOR", "SALDO DA CONTA", "NÚM CARTÃO", "CÓD SEGURANÇA");
            while (entrada.hasNext()) {
                Integer id = entrada.nextInt();
                String tipo = entrada.next();
                String nomeTitular = entrada.next();
                Double valor = entrada.nextDouble();
                Double saldoConta = entrada.nextDouble();
                String numeroCartao = entrada.next();
                Integer codSeguranca = entrada.nextInt();
                System.out.printf("%8d %-8s %-20s %6.2f %6.2f %-20s %8d\n", id, tipo, nomeTitular, valor, saldoConta, numeroCartao, codSeguranca);
            }
        } catch (NoSuchElementException erro) {
            System.err.println("Arquivo com problemas.");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.err.println("Erro na leitura do arquivo.");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arquivo.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim)
                System.exit(1);
        }
    }

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);   // leitor para uso geral, menos nextLine()
        Scanner leitorNL = new Scanner(System.in); // leitor para usar para ler com nextLine()

        boolean fim = false;

        Integer id;
        String tipo;
        String nomeTitular;
        Double valor;
        Double saldoConta;
        String numeroCartao;
        Integer codSeguranca;

        ListaObj<Pagamento> pagamento = new ListaObj<Pagamento>(10);

        while (!fim) {
            System.out.println("\n" +
                    "1.\tAdicionar um pagamento\n" +
                    "2.\tExibir a lista\n" +
                    "3.\tGravar a lista em arquivo CSV\n" +
                    "4.\tLer e exibir um arquivo CSV\n" +
                    "5.\tGravar em arquivo CSV apenas pagamentos do tipo...\n" +
                    "6.\tSair\n"
            );

            int opcao = leitor.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o id do pagamento");
                    id= leitor.nextInt();
                    System.out.println("Digite o tipo de pagamento");
                    tipo= leitor.next();
                    System.out.println("Digite o nome do titular");
                    nomeTitular= leitor.next();
                    System.out.println("Digite o valor");
                    valor= leitor.nextDouble();
                    System.out.println("Digite o saldo da conta");
                    saldoConta= leitor.nextDouble();
                    System.out.println("Digite o número do cartão");
                    numeroCartao= leitor.next();
                    System.out.println("Digite o cód de segurança");
                    codSeguranca= leitor.nextInt();
                    // Cria um objeto aluno inicializando os atributos com os valores digitados pelo usuário
                    Pagamento pag = new Pagamento(id, tipo, nomeTitular, valor, saldoConta, numeroCartao, codSeguranca);
                    // Adiciona esse objeto à lista de alunos
                    pagamento.adiciona(pag);
                    break;
                case 2:
                    if (pagamento.getTamanho() == 0) {
                        System.out.println("\nLista vazia");
                    } else {
                        pagamento.exibe();
                    }
                    break;
                case 3:
                    if (pagamento.getTamanho() == 0) {
                        System.out.println("\nLista vazia, não há nada para gravar");
                    } else {
                        gravaLista(pagamento, "pagamento.csv");
                        pagamento.limpa();
                        System.out.println("\nArquivo gravado com sucesso");
                    }
                    break;
                case 4:
                    System.out.print("\nDigite o nome do arquivo a ser lido, com extensão csv: ");
                    String nomeDoArquivo = leitor.next();
                    leExibeArquivo(nomeDoArquivo);
                    break;
                case 5:
                    if (pagamento.getTamanho() == 0)
                        System.out.println("\nLista vazia, não há nada para gravar");
                    else {
                        System.out.println("\nDigite o nome do arquivo a ser gravado, com extensão csv: ");
                        String nomeArquivo = leitor.next();
                        System.out.println("\nDigite o tipo de pagamento a ser gravado: ");
                        String generoParaGravar = leitor.next();
                        ListaObj<Pagamento> pagamentoFiltrado = new ListaObj<Pagamento>(10);
                        for (int i = 0; i < pagamento.getTamanho(); i++) {
                            Pagamento filme = pagamento.getElemento(i);
                            if (filme.getTipo().equals(generoParaGravar)) {
                                pagamentoFiltrado.adiciona(filme);
                            }
                        }

                        if (pagamentoFiltrado.getTamanho() == 0) {
                            System.out.println("\nNão há pagamento desse tipo na lista");
                        } else {
                            gravaLista(pagamentoFiltrado, nomeArquivo);
                            System.out.println("\nArquivo gravado com sucesso");
                        }
                    }
                    break;

                case 6:
                    System.out.println("\nPrograma encerrado");
                    fim = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
