// Testes de balanceamento de personagens e inimigos.

public class teste {
    public static void main(String[] args) {
       Inimigo ANDRE = new Inimigo( "Andre", 10);
        System.out.print(ANDRE.toString()+"\n\n");

        Inimigo DRAGAO = new Inimigo( "Dragao", 10);
        System.out.print(DRAGAO.toString()+"\n\n");

        Guerreiro A = new Guerreiro("Guerreiro");
        System.out.print(A.setNivel(8).toString()+"\n\n");

        Mago B = new Mago("Mago");
        System.out.print(B.setNivel(8).toString()+"\n\n");

        Arqueiro C = new Arqueiro("Arqueiro");
        System.out.print(C.setNivel(8).toString()+"\n\n");
    }
}
