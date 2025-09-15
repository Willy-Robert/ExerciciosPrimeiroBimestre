package Compras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

class CarrinhoCompras {
private ArrayList<ItemCarrinho> itens;
public CarrinhoCompras() {
itens = new ArrayList<>();
}
public void adicionarItem(Produto produto, int quantidade) {
itens.add(new ItemCarrinho(produto, quantidade));
}
public BigDecimal getTotal() {
BigDecimal total = BigDecimal.ZERO;
for (ItemCarrinho item : itens) {
total = total.add(item.getSubtotal());
}
return total;
}
public void exibirCarrinho() {
System.out.println("\n===== CARRINHO DE COMPRAS =====");
for (ItemCarrinho item : itens) {
System.out.println(item);
}
System.out.println("-------------------------------");
System.out.println("TOTAL: R$ " + getTotal().setScale(2, RoundingMode.HALF_UP));
System.out.println("===============================");
}
}