
public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Livro livro1 = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943, 39.90, true, 10);
        Livro livro2 = new Livro("Dom Casmurro", "Machado de Assis", 1899, 29.90, true, 15);
        Livro livro3 = new Livro("Java para Iniciantes", "Herbert Schildt", 2020, 89.90, false, 20);

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);

        System.out.println("\nBusca por título:");
        Livro resultado = biblioteca.buscarLivroPorTitulo("Dom Casmurro");
        if (resultado != null) {
            System.out.println("Livro encontrado: " + resultado.getTitulo());
        } else {
            System.out.println("Livro não encontrado.");
        }

        System.out.println("\nListagem de livros disponíveis:");
        biblioteca.listarLivrosDisponiveis();

        System.out.println("\nValor total dos livros: R$" + biblioteca.calcularValorTotal());
    }
}