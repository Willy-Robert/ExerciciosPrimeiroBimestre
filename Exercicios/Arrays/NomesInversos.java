import java.util.Scanner;
public class NomesInversos {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
String[] nomes = new String[5];
// Leitura dos nomes
System.out.println("Digite 5 nomes:");
for (int i = 0; i < nomes.length; i++) {
System.out.print("Nome " + (i+1) + ": ");
nomes[i] = scanner.nextLine();
}
// Impressão em ordem inversa
System.out.println("\nNomes em ordem inversa:");
for (int i = nomes.length - 1; i >= 0; i--) {
System.out.println((i+1) + ": " + nomes[i]);
}
scanner.close();
}
}