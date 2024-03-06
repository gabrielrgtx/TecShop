import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TecShopChatbot {
    private Scanner scanner;
    private List<Produto> pecasComputador;
    private List<Produto> pecasNotebook;
    private List<Produto> perifericos;
    private List<Produto> memorias;
    private List<Produto> carrinho;

    public TecShopChatbot() {
        scanner = new Scanner(System.in);
        // Inicialize as listas de produtos para cada categoria
        pecasComputador = new ArrayList<>();
        pecasNotebook = new ArrayList<>();
        perifericos = new ArrayList<>();
        memorias = new ArrayList<>();
        carrinho = new ArrayList<>();

        // Adicione alguns produtos às listas (você pode adicionar mais conforme necessário)
        pecasComputador.add(new Produto("Placa-mãe Aorus", "PLACA MAE (AMD) GIGABYTE B550 AORUS ELITE AX V2 DDR4 AM4", 1000.0));
        pecasComputador.add(new Produto("Processador", "Intel core i9 13900KF", 3000.0));

        pecasNotebook.add(new Produto("Teclado para notebook", "Descrição do teclado", 50.0));
        pecasNotebook.add(new Produto("Bateria para notebook", "Descrição da bateria", 80.0));

        perifericos.add(new Produto("Mouse", "Descrição do mouse", 20.0));
        perifericos.add(new Produto("Monitor", "Descrição do monitor", 150.0));

        memorias.add(new Produto("Memória RAM", "Descrição da memória RAM", 80.0));
        memorias.add(new Produto("HD", "Descrição do HD", 100.0));
    }

    public void iniciarConversa() {
        boolean continuarComprando = true;

        while (continuarComprando) {
            System.out.println("Bem-vindo à TecShop! Como posso ajudá-lo hoje?");
            System.out.println("1. Peças de computador");
            System.out.println("2. Peças de notebook");
            System.out.println("3. Periféricos");
            System.out.println("4. Memórias");

            int escolhaDepartamento = obterEscolhaUsuario(4);

            List<Produto> produtos = null;
            switch (escolhaDepartamento) {
                case 1:
                    produtos = pecasComputador;
                    break;
                case 2:
                    produtos = pecasNotebook;
                    break;
                case 3:
                    produtos = perifericos;
                    break;
                case 4:
                    produtos = memorias;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }

            System.out.println("Produtos disponíveis:");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println((i + 1) + ". " + produtos.get(i).getNome() + " - R$" + produtos.get(i).getPreco());
            }

            // Aguarda a escolha do produto após exibir os disponíveis
            System.out.println("Escolha o número do produto desejado:");
            int escolhaProduto = obterEscolhaProduto(produtos.size());
            Produto produtoEscolhido = produtos.get(escolhaProduto - 1);

            carrinho.add(produtoEscolhido);

            // Pergunta se o usuário deseja continuar comprando
            System.out.println("Deseja comprar mais alguma coisa? (Sim/Não)");
            String continuar = scanner.next();
            continuarComprando = continuar.equalsIgnoreCase("Sim");
        }

        // Continuação para o pagamento
        System.out.println("Seu carrinho de compras:");
        double total = 0;
        for (Produto produto : carrinho) {
            System.out.println(produto.getNome() + " - R$" + produto.getPreco());
            total += produto.getPreco();
        }
        System.out.println("Total: R$" + total);

        // Pergunta a forma de pagamento
        System.out.println("Como você gostaria de pagar? (PIX, Cartão de Crédito, Cartão de Débito, Boleto Bancário)");
        String formaPagamento = scanner.next();
        System.out.println("Pagamento realizado com " + formaPagamento + ".");

        // Mensagem final
        System.out.println("Obrigado por comprar com a TecShop, volte sempre!");
    }

    private int obterEscolhaUsuario(int opcoes) {
        int escolha = 0;
        while (escolha < 1 || escolha > opcoes) {
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            if (escolha < 1 || escolha > opcoes) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        return escolha;
    }

    private int obterEscolhaProduto(int quantidadeProdutos) {
        int escolha = 0;
        while (escolha < 1 || escolha > quantidadeProdutos) {
            System.out.print("Escolha o número do produto: ");
            escolha = scanner.nextInt();
            if (escolha < 1 || escolha > quantidadeProdutos) {
                System.out.println("Número do produto inválido. Tente novamente.");
            }
        }
        return escolha;
    }

    public static void main(String[] args) {
        TecShopChatbot chatbot = new TecShopChatbot();
        chatbot.iniciarConversa();
    }
}

class Produto {
    private String nome;
    private String descricao;
    private double preco;

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }
}
