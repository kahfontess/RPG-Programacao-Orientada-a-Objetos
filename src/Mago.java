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
        if (pontosMana >= 20) {
            pontosMana -= 20;
            int dano = (ataque * this.nivel / 2 + dado.nextInt(7*this.nivel))*2 + 15 - inimigo.defesa;
            inimigo.receberDano(Math.max(dano, 0));
            System.out.println(nome + " lançou uma Bola de Fogo! Dano: " + dano);
        } else {
            System.out.println("Mana insuficiente! Use outro ataque.");
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
