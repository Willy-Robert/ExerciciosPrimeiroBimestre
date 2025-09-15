class Livro {
private String titulo;
private String autor;
private String isbn;
public Livro(String titulo, String autor, String isbn) {
this.titulo = titulo;
this.autor = autor;
this.isbn = isbn;
}
public String getTitulo() {
return titulo;
}
public String getIsbn() {
return isbn;
}
@Override
public String toString() {
return "Livro: " + titulo + " | Autor: " + autor + " | ISBN: " + isbn;
}
}
class Biblioteca {
private String nome;
private Livro[] acervo;
private int quantidadeLivros;
public Biblioteca(String nome, int capacidadeMaxima) {
this.nome = nome;
this.acervo = new Livro[capacidadeMaxima];
this.quantidadeLivros = 0;
}
public boolean adicionarLivro(Livro livro) {
if (quantidadeLivros < acervo.length) {
acervo[quantidadeLivros] = livro;
quantidadeLivros++;
return true;
}
return false;
}
public void listarAcervo() {
System.out.println("Acervo da " + nome + ":");
for (int i = 0; i < quantidadeLivros; i++) {
System.out.println((i+1) + ". " + acervo[i]);
}
}
}