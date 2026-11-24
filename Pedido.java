import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private List<Produto> itens = new ArrayList<>();

    public Pedido(int id) {
        this.id = id;
    }

    public int getId() { return id; }
    public List<Produto> getItens() { return itens; }

    public void adicionarItem(Produto produto) {
        itens.add(produto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto p : itens) {
            total = total + p.getPreco();
        }
        return total;
    }

    public String toString() {
        String texto = "Pedido #" + id + "\n";
        for (Produto p : itens) {
            texto = texto + "   " + p + "\n";
        }
        texto = texto + "   TOTAL: R$ " + String.format("%.2f", calcularTotal());
        return texto;
    }
}
