

public class Biblioteca {

    private Livro[] livros;
    private int quantidadeLivros;
    private static final int CAPACIDADE_MAXIMA = 10;

    public Biblioteca() {
        livros = new Livro[CAPACIDADE_MAXIMA];
        quantidadeLivros = 0;
    }

    public boolean adicionarLivro(Livro livro) {
        if (quantidadeLivros < CAPACIDADE_MAXIMA && livro != null) {
            livros[quantidadeLivros] = livro;
            quantidadeLivros++;
            System.out.println("Livro '" + livro.getTitulo() + "' adicionado à biblioteca!");
            return true;
        } else {
            System.out.println("Não foi possível adicionar o livro. Biblioteca cheia ou livro inválido.");
            return false;
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for (int i = 0; i < quantidadeLivros; i++) {
            if (livros[i].getTitulo().equalsIgnoreCase(titulo)) {
                return livros[i];
            }
        }
        return null;
    }

    public void listarLivrosDisponiveis() {
        System.out.println("=== LIVROS DISPONÍVEIS ===");
        boolean encontrou = false;
        for (int i = 0; i < quantidadeLivros; i++) {
            if (livros[i].isDisponivel()) {
                System.out.println("- " + livros[i].getTitulo() +
                        " por " + livros[i].getAutor() +
                        " (R$ " + livros[i].getPreco() + ")");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum livro disponível no momento.");
        }
    }

    public double calcularValorTotal() {
        double total = 0.0;
        for (int i = 0; i < quantidadeLivros; i++) {
            total += livros[i].getPreco();
        }
        return total;
    }

    public int getQuantidadeLivros() {
        return quantidadeLivros;
    }
}