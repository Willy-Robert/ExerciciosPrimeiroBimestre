import java.util.ArrayList;
class Aluno {
private String nome;
private int matricula;
public Aluno(String nome, int matricula) {
this.nome = nome;
this.matricula = matricula;
}
public String getNome() {
return nome;
}
@Override
public String toString() {
return "Aluno: " + nome + " (matrícula: " + matricula + ")";
}
}
class TurmaArrayList {
private String codigo;
private ArrayList<Aluno> alunos;
public TurmaArrayList(String codigo) {
this.codigo = codigo;
this.alunos = new ArrayList<>(); // Sem preocupação com capacidade
}
public void adicionarAluno(Aluno aluno) {
alunos.add(aluno); // Sempre funciona, cresce automaticamente
}
public void listarAlunos() {
System.out.println("Alunos da turma " + codigo + ":");
for (Aluno aluno : alunos) {
System.out.println(aluno);
}
}
}