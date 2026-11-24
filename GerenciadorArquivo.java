import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorArquivo {
    private static final String ARQ_CARDAPIO = "cardapio.txt";
    private static final String ARQ_PEDIDOS = "pedidos.txt";

    // Cada linha do cardapio.txt: TIPO;nome;preco
    public void salvarCardapio(List<Produto> cardapio) {
        try {
            FileWriter escritor = new FileWriter(ARQ_CARDAPIO);
            for (Produto p : cardapio) {
                escritor.write(p.getTipo() + ";" + p.getNome() + ";" + p.getPreco() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o cardapio.");
        }
    }

    public List<Produto> carregarCardapio() {
        List<Produto> cardapio = new ArrayList<>();
        File arquivo = new File(ARQ_CARDAPIO);
        if (!arquivo.exists()) {
            return cardapio;
        }
        try {
            Scanner leitor = new Scanner(arquivo);
            while (leitor.hasNextLine()) {
                String[] partes = leitor.nextLine().split(";");
                double preco = Double.parseDouble(partes[2]);
                cardapio.add(criarProduto(partes[0], partes[1], preco));
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o cardapio.");
        }
        return cardapio;
    }

    // Cada linha do pedidos.txt: id;TIPO;nome;preco;TIPO;nome;preco ...
    public void salvarPedidos(List<Pedido> pedidos) {
        try {
            FileWriter escritor = new FileWriter(ARQ_PEDIDOS);
            for (Pedido ped : pedidos) {
                String linha = "" + ped.getId();
                for (Produto p : ped.getItens()) {
                    linha = linha + ";" + p.getTipo() + ";" + p.getNome() + ";" + p.getPreco();
                }
                escritor.write(linha + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar os pedidos.");
        }
    }

    public List<Pedido> carregarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        File arquivo = new File(ARQ_PEDIDOS);
        if (!arquivo.exists()) {
            return pedidos;
        }
        try {
            Scanner leitor = new Scanner(arquivo);
            while (leitor.hasNextLine()) {
                String[] partes = leitor.nextLine().split(";");
                Pedido ped = new Pedido(Integer.parseInt(partes[0]));
                for (int i = 1; i + 2 < partes.length; i = i + 3) {
                    double preco = Double.parseDouble(partes[i + 2]);
                    ped.adicionarItem(criarProduto(partes[i], partes[i + 1], preco));
                }
                pedidos.add(ped);
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler os pedidos.");
        }
        return pedidos;
    }

    private Produto criarProduto(String tipo, String nome, double preco) {
        if (tipo.equals("LANCHE")) {
            return new Lanche(nome, preco);
        } else {
            return new Bebida(nome, preco);
        }
    }
}
