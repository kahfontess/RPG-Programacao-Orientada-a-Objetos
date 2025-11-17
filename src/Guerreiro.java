//Classe Guerreiro e seu balanceamento.

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
        if (pontosMana < 10) {
            System.out.println("Mana insuficiente!");
            return;
        }

        pontosMana -= 10;

        int rolagem = dado.nextInt(6) + 1;
        int ataqueTotal = ataque + rolagem + 10;

        System.out.println("\n" + nome + " ergue sua arma para um Golpe Giratório!");
        System.out.println("Rolagem do dado: " + rolagem);
        System.out.println("Ataque total do especial: " + ataqueTotal);

        int dano = ataqueTotal - inimigo.defesa;
        if (dano < 0) dano = 0;

        inimigo.receberDano(dano);
        System.out.println(nome + " gira com força devastadora causando " + dano + " de dano!");
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
