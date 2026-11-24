public abstract class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    // Cada classe filha diz qual e o seu tipo
    public abstract String getTipo();

    public String toString() {
        return "[" + getTipo() + "] " + nome + " - R$ " + String.format("%.2f", preco);
    }
}
