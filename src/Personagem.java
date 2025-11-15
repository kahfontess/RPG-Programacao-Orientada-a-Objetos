import java.util.Random;

public abstract class Personagem {
    protected String nome;
    protected int pontosVida;
    protected int vidaMaxima;
    protected int pontosMana;
    protected int manaMaxima;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Inventario inventario;
    protected  int xp = 0;
    protected boolean xpBuff = false;

    protected Random dado = new Random();

    public Personagem(String nome, int vida, int mana, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = vida;
        this.vidaMaxima = vida;
        this.pontosMana = mana;
        this.manaMaxima = mana;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    // Construtor de cópia
    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.vidaMaxima = outro.vidaMaxima;
        this.pontosMana = outro.pontosMana;
        this.manaMaxima = outro.manaMaxima;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.inventario = new Inventario(outro.inventario);
        this.xp = outro.xp;
        this.xpBuff = outro.xpBuff;
    }

    public abstract void atacarEspecial(Personagem inimigo);
    public abstract void atacarBasico(Personagem inimigo);

    public boolean estaVivo() {
        return this.pontosVida > 0;
    }

    public void receberDano(int dano) {
        this.pontosVida -= dano;
        if (this.pontosVida < 0) this.pontosVida = 0;
    }

    public void receberXp(int qtd){
        this.xp+= qtd*(this.xpBuff?2:1);// se tem buff de xp, xp *2, se nao xp *1
        if(this.xpBuff) this.xpBuff= false;
        int growFactor = 20*((int)((this.nivel*1.5)%3));
        if(this.xp>=growFactor){
            subirNivel();
        }
    }

    public int dropXp(){
        return (this.vidaMaxima+this.manaMaxima+this.ataque+this.defesa)*this.nivel;
    }

    public void subirNivel() {
        this.nivel++;
        this.xp = 0;
        System.out.println(this.nome+" subiu para o nivel "+this.nivel);
        // Aumenta atributos conforme o nível
        this.vidaMaxima += ((this.vidaMaxima*3)/10);
        this.manaMaxima += ((this.manaMaxima*4)/12);
        this.ataque += ((this.ataque*4)/15);
        this.defesa += ((this.defesa*3)/15);

        // Recupera vida e mana ao máximo
        this.pontosVida = this.vidaMaxima;
        this.pontosMana = this.manaMaxima;
    }

    public Personagem setNivel(int nivel) {
        this.nivel = nivel;
        this.xp = 0;

        // Aumenta atributos conforme o nível
        this.vidaMaxima += (int) (((double)this.vidaMaxima * 3) /10) *nivel;
        this.manaMaxima += (int) (((double)this.manaMaxima*4)/12)* nivel;
        this.ataque += (int) (((double)this.ataque*4)/15)* nivel;
        this.defesa += (int) (((double)this.defesa*3)/15)* nivel;

        // Recupera vida e mana ao máximo
        this.pontosVida = this.vidaMaxima;
        this.pontosMana = this.manaMaxima;
        return this;
    }

    public void restaurarMana(int valor) {
        pontosMana = Math.min(manaMaxima, pontosMana + valor);
    }

    public void restaurarVida(int valor) {
        pontosVida = Math.min(vidaMaxima, pontosVida + valor);
    }

    public void exibirStatus() {
        System.out.println("\n[" + nome + "] - HP: " + pontosVida + "/" + vidaMaxima +
                " | MP: " + pontosMana + "/" + manaMaxima +
                " | Nível: " + nivel);
    }

    public String getNome() { return nome; }
    public int getPontosVida() { return pontosVida; }
    public int getPontosMana() { return pontosMana; }

    @Override
    public String toString() {
        return "nome: " + this.nome + "\n" +

                "vidaMaxima: " + this.vidaMaxima + "\n" +
                "manaMaxima: " + this.manaMaxima + "\n" +
                "defesa: " + this.defesa + "\n" +
                "ataque: " + this.ataque + "\n";

    }

}
