import java.util.Scanner;

public class EscolhaClasse {

    public static Personagem escolherClasse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.print("> ");
        int op = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o nome do seu personagem: ");
        String nome = sc.nextLine();

        switch (op) {
            case 1: return new Guerreiro(nome);
            case 2: return new Mago(nome);
            case 3: return new Arqueiro(nome);
            default:
                System.out.println("Opção inválida, escolhendo Guerreiro por padrão!");
                return new Guerreiro(nome);
        }
    }
}
