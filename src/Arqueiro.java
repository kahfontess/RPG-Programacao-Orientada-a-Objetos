//Classe arqueiro e seu balanceamento.

public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(nome, 86, 50, 40, 8, 1);
    }

    public Arqueiro(Arqueiro outro) {
        super(outro);
    }

    @Override
    public void atacarBasico(Personagem inimigo) {
        int dano = ataque + dado.nextInt(10) - inimigo.defesa;
        if (dano < 0) dano = 0;
        inimigo.receberDano(dano);
        System.out.println(nome + " disparou uma flecha causando " + dano + " de dano!");
    }

    @Override
    public void atacarEspecial(Personagem inimigo) {
        if (pontosMana >= 15) {
            pontosMana -= 15;
            int dano = ataque + dado.nextInt(10) * 3 + 12 - inimigo.defesa;
            inimigo.receberDano(Math.max(dano, 0));
            System.out.println(nome + " usou Disparo Triplo! Dano: " + dano);
        } else {
            System.out.println("Mana insuficiente!");
        }
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
