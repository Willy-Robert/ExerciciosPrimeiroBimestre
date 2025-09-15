public class Game {
    // 🔒 Atributos privados (encapsulados)
    private String nome;
    private double preco;
    private String categoria;
    private int classificacaoEtaria;

    public Game(){
        this.nome = "Desconhecido";
        this.preco = 0.0;
        this.categoria = "Indefinida";
        this.classificacaoEtaria = 0;
    }
    public Game(String nome, double preco){
        setNome(nome);
        setPreco(preco);
        this.categoria = "Indefinida";
        this.classificacaoEtaria = 0;
    }
    public Game(String nome, double preco, String categoria, int classificacao){
        setNome(nome);
        setPreco(preco);
        setCategoria(categoria);
        setClassificacaoEtaria(classificacao);
    }

    // 📥 Getters
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    // 📤 Setters com validação
    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public void setCategoria(String categoria) {
        if (categoria != null && !categoria.isEmpty()) {
            this.categoria = categoria;
        }
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        }
    }

    public void setClassificacaoEtaria(int idade) {
        if (idade >= 0 && idade <= 18) {
            this.classificacaoEtaria = idade;
        }
    }

    // 🖨️ Método para exibir informações do jogo
    public static void exibirInformacoes(Game jogo) {
        System.out.println("Nome do jogo: " + jogo.getNome());
        System.out.println("Categoria: " + jogo.getCategoria());
        System.out.println("Preço do jogo: R$" + jogo.getPreco());
        System.out.println("Classificação etária: " + jogo.getClassificacaoEtaria() + " anos");
        System.out.println("-----------------------------------");
    }

    // 🚀 Método principal para testar a classe
    public static void main(String[] args) {
        Game jogoPadrao = new Game();
        Game JogoBasico = new Game("AmongUS", 15.00);
        Game JogoCompleto = new Game("GTA V", 150.00, "Ação/Aventura", 18);
        
        exibirInformacoes(jogoPadrao);
        exibirInformacoes(JogoBasico);
        exibirInformacoes(JogoCompleto);

        JogoBasico.setPreco(-50); // inválido, não altera
        JogoBasico.setPreco(30); // válido
        JogoBasico.setNome(""); // inválido, não altera
        JogoBasico.setNome("Among Us Deluxe"); // válido
        JogoBasico.setCategoria(""); // inválido, não altera
        JogoBasico.setCategoria("Party"); // válido
        JogoBasico.setClassificacaoEtaria(25); // inválido, não altera
        JogoBasico.setClassificacaoEtaria(10); // válido

        System.out.println("Após alterações nos atributos do jogoBasico:");
        exibirInformacoes(JogoBasico);
    }
}