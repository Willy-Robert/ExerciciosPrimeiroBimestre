

import java.time.LocalDate;

public class Livro {
    
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private double preco;
    private boolean disponivel;
    private int percentual;
    
    public Livro(String titulo, String autor, int anoPublicacao, double preco, boolean disponivel, int percentual) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.percentual = percentual;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public double getPreco() { return preco; }
    public boolean isDisponivel() { return disponivel; }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo;
        }
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        }
    }

    public void emprestar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("Livro '" + titulo + "' emprestado com sucesso!");
        } else {
            System.out.println("Livro '" + titulo + "' não está disponível!");
        }
    }

    public void devolver() {
        disponivel = true;
        System.out.println("Livro '" + titulo + "' devolvido com sucesso!");
    }

    public int obterIdade() {
        int anoAtual = LocalDate.now().getYear();
        return anoAtual - anoPublicacao;
    }

    public void aplicarDesconto() {
        if (percentual > 0 && percentual <= 100) {
            preco = preco * (1 - (percentual / 100.0));
            System.out.println("Desconto aplicado! Novo preço: R$" + preco);
        }
    }
}