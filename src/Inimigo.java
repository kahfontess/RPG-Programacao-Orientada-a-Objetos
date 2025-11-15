import java.util.Arrays;

public class Inimigo extends Personagem {

    private Inimigo(String nome, int vida, int mana, int ataque, int defesa, int nivel) {
        super(nome, vida, mana, ataque, defesa, nivel);
    }


    public Inimigo(String type, int nivel){
        Inimigo aux;
        try{
            aux = TipoInimigo.tipos.valueOf(type.toUpperCase()).getInimigo();
        }catch (Exception _) {
            System.err.println("Inimigo nao existe");
            aux = new Inimigo("Erro", 0,0,0,0,1);
        }
        super(aux.setNivel(nivel));
    }


    @Override
    public void atacarBasico(Personagem inimigo) {
        int dano = ataque + dado.nextInt(8) - inimigo.defesa;
        if (dano < 0) dano = 0;
        inimigo.receberDano(dano);
        System.out.println(nome + " atacou causando " + dano + " de dano!");
    }

    @Override
    public void atacarEspecial(Personagem inimigo) {
        atacarBasico(inimigo); // inimigos simples não usam mana
    }

    public static class TipoInimigo {

        public static enum tipos {

            ///SLIME(new Inimigo("Slime", 10, 5, 2, 1, 1){
              ///  @Override
               /// public void atacarBasico(Personagem inimigo) {
                 ///   int dano = ataque + dado.nextInt(8) - inimigo.defesa;
                   /// if (dano < 0) dano = 0;
                   /// inimigo.receberDano(dano);
                   /// System.out.println(nome + " atacou causando " + dano + " de dano!");
               /// }

               /// @Override
               /// public void atacarEspecial(Personagem inimigo) {
               ///     atacarBasico(inimigo); // inimigos simples não usam mana
                ///}
            ///}),
            SLIME(new Inimigo("Slime", 10, 5, 2, 1, 1)),
            GOBLIN(new Inimigo("Goblin", 35, 7, 3, 2, 1)),
            ORC(new Inimigo("Orc", 50, 10, 5, 3, 1)),
            BANDIDO(new Inimigo("Bandido", 60, 0, 8, 6, 1)),
            ARANHA_G(new Inimigo("Aranha Gigante", 70, 0, 10, 3, 1)),
            DRAGAO(new Inimigo("Dragão", 250, 50, 50, 50, 1)),

            ANDRE(new Inimigo("André, o maligno Lorde das Trevas", 180, 20, 38, 30, 1){
                @Override
                public void atacarBasico(Personagem inimigo) {
                    int dano = ataque + dado.nextInt(8) - inimigo.defesa;
                    if (dano < 0) dano = 0;
                    inimigo.receberDano(dano);
                    System.out.println(nome + " atacou causando " + dano + " de dano!");
                }

                @Override
                public void atacarEspecial(Personagem inimigo) {
                    atacarBasico(inimigo); // inimigos simples não usam mana
                }
            });



            private final Inimigo inimigo;

            tipos(Inimigo inimigo) {
                this.inimigo = inimigo;
            }

            public Inimigo getInimigo() {
                return inimigo;
            }
        }

        public static int count(){
            return (int)Arrays.stream(tipos.values()).count();
        }
    }

}
