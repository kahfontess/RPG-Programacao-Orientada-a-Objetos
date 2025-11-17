//Classe Mago e seu balanceamento.

public class Mago extends Personagem {

    public Mago(String nome) {
        super(nome, 80, 120, 10, 5, 1);
    }

    public Mago(Mago outro) {
        super(outro);
    }

    @Override
    public void atacarBasico(Personagem inimigo) {
        int dano = ataque + dado.nextInt(8) - inimigo.defesa;
        if (dano < 0) dano = 0;
        inimigo.receberDano(dano);
        System.out.println(nome + " deu uma cajadada: " + dano + " de dano!");
    }

    @Override
    public void atacarEspecial(Personagem inimigo) {
        if (pontosMana < 20) {
            System.out.println("Mana insuficiente!");
            return;
        }

        pontosMana -= 20;

        int rolagem = dado.nextInt(6) + 1;
        int ataqueTotal = ataque + (rolagem * 4) + 8;

        System.out.println("\n" + nome + " conjura uma Bola de Fogo!");
        System.out.println("Rolagem do dado: " + rolagem);
        System.out.println("Poder mágico total: " + ataqueTotal);

        int dano = ataqueTotal - inimigo.defesa;
        if (dano < 0) dano = 0;

        inimigo.receberDano(dano);
        System.out.println("A explosão flamejante atinge o inimigo causando " + dano + " de dano!");
    }


    @Override
    public String toString() {
        return "nome: " + this.nome + "\n" +

                "Nivel: " + this.nivel + "\n"+
                "vidaMaxima: " + this.vidaMaxima + "\n" +
                "manaMaxima: " + this.manaMaxima + "\n" +
                "defesa: " + this.defesa + "\n" +
                "ataque: " + this.ataque + "\n";



    }

}
