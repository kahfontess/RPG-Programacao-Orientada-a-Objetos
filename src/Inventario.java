//Funcionamento inventario.

import java.util.*;

public class Inventario implements Cloneable {
    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        // Se já existe um item com o mesmo nome, soma a quantidade inteira do item recebido
        for (Item i : itens) {
            if (i.equals(item)) {
                i.aumentarQtd(item.getQuantidade());
                Collections.sort(itens);
                return;
            }
        }
        // senão adiciona uma cópia do item recebido
        itens.add(item.clone());
        Collections.sort(itens);
    }


    public void listarItens() {
        System.out.println("\n--- Inventário ---");
        for (Item i : itens) System.out.println(i);
    }

    public Inventario() {}

    public Inventario(Inventario outro) {
        for (Item i : outro.itens)
            this.itens.add(i.clone());
    }

    @Override
    public Inventario clone() {
        return new Inventario(this);
    }

    public List<Item> getItens() { return itens; }
}
