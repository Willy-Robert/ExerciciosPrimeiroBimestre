import javax.swing.*;
import java.awt.*;

public class Calculadora {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");

        JTextField campo1 = new JTextField(10);
        JTextField campo2 = new JTextField(10);

        JButton botao_sub = new JButton("Subtrair");
        JButton botao_som = new JButton("Somar");
        JButton botao_mult = new JButton("Multiplicar");
        JButton botao_div = new JButton("Dividir");

        JLabel resultado_sub = new JLabel("Resultado: ");
        JLabel resultado_som = new JLabel("Resultado: ");
        JLabel resultado_mult = new JLabel("Resultado:");
        JLabel resultado_div = new JLabel("Resultado: ");

        Color fundoJanela = new Color(230, 230,250);
        Color corBotoes = new Color(100, 149, 237);
        Color corTextBotoes = new Color(255, 255, 255);
        Color corResult = new Color(0, 128, 0);

        botao_sub.setBackground(corBotoes);
        botao_sub.setForeground(corTextBotoes);
        resultado_sub.setForeground(corResult);
        botao_som.setBackground(corBotoes);
        botao_som.setForeground(corTextBotoes);
        resultado_som.setForeground(corResult);
        botao_mult.setBackground(corBotoes);
        botao_mult.setForeground(corTextBotoes);
        resultado_mult.setForeground(corResult);
        botao_div.setBackground(corBotoes);
        botao_div.setForeground(corTextBotoes);
        resultado_div.setForeground(corResult);

        botao_sub.addActionListener(e -> {
            int subtrair = Integer.parseInt(campo1.getText()) - Integer.parseInt(campo2.getText());
            resultado_sub.setText("Resultado: " + subtrair);
        });

        botao_som.addActionListener(e -> {
            int soma = Integer.parseInt(campo1.getText()) + Integer.parseInt(campo2.getText());
            resultado_som.setText("Resultado: " + soma);
        });

        botao_mult.addActionListener(e ->  {
            int multiplicar = Integer.parseInt(campo1.getText()) * Integer.parseInt(campo2.getText());
            resultado_mult.setText("Resultado: " + multiplicar);
        });

        botao_div.addActionListener(e -> {
            int dividir = Integer.parseInt(campo1.getText()) / Integer.parseInt(campo2.getText());
            resultado_div.setText("Resultado: " + dividir);
        });

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(fundoJanela);

        JLabel label1 = new JLabel("Numero 1: ");
        JLabel label2 = new JLabel("Numero 2: ");
        label1.setFont(new Font("Arial", Font.PLAIN, 14));
        label1.setForeground(new Color(255, 0, 0));
        label2.setFont(new Font("Arial", Font.PLAIN, 14));
        label2.setForeground(new Color(255, 0, 0));

        panel.add(label1);
        panel.add(campo1);
        panel.add(label2);
        panel.add(campo2);
        panel.add(botao_som);
        panel.add(resultado_som);
        panel.add(botao_sub);
        panel.add(resultado_sub);
        panel.add(botao_mult);
        panel.add(resultado_mult);
        panel.add(botao_div);
        panel.add(resultado_div);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}