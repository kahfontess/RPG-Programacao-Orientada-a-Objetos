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
        if (pontosMana < 15) {
            System.out.println("Mana insuficiente!");
            return;
        }

        pontosMana -= 15;

        int rolagem = dado.nextInt(6) + 1;
        int ataqueTotal = ataque + (rolagem * 3) + 12;

        System.out.println("\n" + nome + " prepara um Disparo Triplo!");
        System.out.println("Rolagem do dado: " + rolagem);
        System.out.println("Ataque total do especial: " + ataqueTotal + " (ataque base + rolagem*3 + bônus)");

        int dano = ataqueTotal - inimigo.defesa;
        if (dano < 0) dano = 0;

        inimigo.receberDano(dano);
        System.out.println(nome + " dispara três flechas certeiras causando " + dano + " de dano!");
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
