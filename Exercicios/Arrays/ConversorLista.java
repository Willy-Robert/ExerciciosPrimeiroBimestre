import java.util.ArrayList;
import java.util.Scanner;
public class ConversorLista {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
ArrayList<String> entradas = new ArrayList<>();
ArrayList<Integer> numeros = new ArrayList<>();
System.out.println("Digite números inteiros (digite 'fim' para encerrar):");
// Leitura das entradas como strings
String entrada;
while (true) {
entrada = scanner.nextLine();
if (entrada.equalsIgnoreCase("fim")) {
break;
}
entradas.add(entrada);
}
// Conversão das strings para inteiros
for (String valor : entradas) {
try {
// Usando parseInt para converter String para int
// e depois autoboxing para armazenar no ArrayList
int numero = Integer.parseInt(valor);
numeros.add(numero);
} catch (NumberFormatException e) {
System.out.println("Erro ao converter '" + valor + "': não é um número válido.");
}
}
// Cálculo da soma usando unboxing automático
int soma = 0;
for (Integer numero : numeros) {
soma += numero; // unboxing automático
}
// Exibição dos resultados
System.out.println("\nNúmeros convertidos:");
for (Integer numero : numeros) {
System.out.println(numero);
}
System.out.println("\nSoma dos números: " + soma);
System.out.println("Média: " + (numeros.isEmpty() ? 0 : (double) soma / numeros.size()));
scanner.close();
}
}