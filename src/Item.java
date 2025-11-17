//Funcionamento dos itens.

public class Item implements Comparable<Item> {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }


    public Item(Item outro) {
        this.nome = outro.nome;
        this.descricao = outro.descricao;
        this.efeito = outro.efeito;
        this.quantidade = outro.quantidade;
    }

    public void usar(Personagem p) {
        if (quantidade <= 0) {
            System.out.println("Nenhum " + nome + " restante!");
            return;
        }

        switch (efeito.toLowerCase()) {
            case "cura":
                p.restaurarVida(30);
                System.out.println(p.getNome() + " recuperou 30 de HP!");
                break;
            case "mana":
                p.restaurarMana(20);
                System.out.println(p.getNome() + " recuperou 20 de MP!");
                break;
            case "buff":
                p.xpBuff = true;
                System.out.println(p.getNome() + " ganhou buff de xp!!");
                break;
        }
        quantidade--;
    }

    public int getQuantidade() { return quantidade; }

    public String getNome() { return nome; }

    public void aumentarQtd(int qtd){ this.quantidade+=qtd; }

    @Override
    public int compareTo(Item outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item outro = (Item) obj;
            return this.nome.equalsIgnoreCase(outro.nome);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }

    @Override
    public Item clone() {
        return new Item(this);
    }

    @Override
    public String toString() {
        return nome + " (" + quantidade + ") - " + descricao;
    }
}
