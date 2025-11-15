public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 140, 40, 20, 15, 1);
    }

    public Guerreiro(Guerreiro outro) {
        super(outro);
    }

    @Override
    public void atacarBasico(Personagem inimigo) {
        int dano = ataque + dado.nextInt(6) - inimigo.defesa;
        if (dano < 0) dano = 0;
        inimigo.receberDano(dano);
        System.out.println(nome + " atacou com a espada causando " + dano + " de dano!");
    }

    @Override
    public void atacarEspecial(Personagem inimigo) {
        if (pontosMana >= 10) {
            pontosMana -= 10;
            int dano = ataque*2 + dado.nextInt(12) + 10 - inimigo.defesa;
            inimigo.receberDano(Math.max(dano, 0));
            System.out.println(nome + " usou GOLPE GIRATÓRIO! Causou " + dano + " de dano!");
        } else {
            System.out.println("Mana insuficiente! Use um ataque básico.");
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
