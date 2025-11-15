import java.util.*;

public class Inventario implements Cloneable {
    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        try{
            itens.get(itens.indexOf(item)).aumentarQtd(1);
        }catch (Exception _){
            itens.add(item);
        }
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
