//Jogo RPG - Programação Orientada a Objetos (POO)

import java.util.*;

public class Jogo {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        tutorial();
        Personagem jogador = EscolhaClasse.escolherClasse();
        jogador.inventario.adicionarItem(new Item("Poção de Cura", "Recupera 30 HP", "cura", 2));
        jogador.inventario.adicionarItem(new Item("Poção de Mana", "Recupera 20 MP", "mana", 1));

        historiaInicial(jogador);
    }

    private static void tutorial() {
        System.out.println("=== Tutorial do RPG ===");
        System.out.println("📜 Bem-vindo ao mundo de Maligno&Douglas!");
        System.out.println("Neste jogo, você fará escolhas que mudam o rumo da sua história.");
        System.out.println("👉 Ataques básicos não consomem mana.");
        System.out.println("🔥 Ataques especiais são poderosos, mas gastam MP.");
        System.out.println("💊 Use itens do inventário para recuperar HP e MP.");
        System.out.println("🎲 O combate é decidido com base em atributos e sorte dos dados.");
        System.out.println("💀 Cuidado: suas decisões podem levar à glória... ou à morte.");
        System.out.println("========================\n");
    }

    private static void historiaInicial(Personagem jogador) {
        System.out.println("\n🌅 Amanhece em Maligno&Douglas. Você desperta em um vilarejo abandonado, cercado por névoa.");
        System.out.println("Fragmentos de memórias surgem... uma torre em chamas, um grito distante, e então... silêncio.");
        System.out.println("Um ancião encapuzado surge das sombras e fala com voz rouca:");
        System.out.println("\"Você finalmente acordou, " + jogador.getNome() + ". As sombras tomaram o reino, e só você pode restaurar a luz.\"");
        System.out.println("\"Mas o caminho será perigoso. Escolha sabiamente seus passos.\"");

        System.out.println("\nO ancião aponta para dois caminhos:");
        System.out.println("1 - Seguir pela Floresta Negra 🌲");
        System.out.println("2 - Caminhar até a Caverna dos Ecos 💎");
        System.out.print("> ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 1) florestaNegra(jogador);
        else if (escolha == 2) cavernaDosEcos(jogador);
        else {
            System.out.println("Você hesita e o ancião desaparece na névoa. Resta apenas seguir em frente.");
            explorar(jogador);
        }
    }

    // Floresta
    private static void florestaNegra(Personagem jogador) {
        System.out.println("\n🌲 A Floresta Negra é silenciosa... demais. O vento carrega sussurros e passos.");
        System.out.println("De repente, um par de olhos amarelos brilha na escuridão.");
        Inimigo goblin = new Inimigo("goblin", 1);
        batalhar(jogador, goblin);

        if (!jogador.estaVivo()) return;

        System.out.println("\nApós derrotar o goblin, você encontra uma bolsa caída.");
        System.out.println("Dentro dela há uma Poção de Mana e uma moeda dourada com o símbolo do sol.");
        jogador.inventario.adicionarItem(new Item("Poção de Mana", "Recupera 20 MP", "mana", 1));

        System.out.println("\nMais adiante, você encontra uma ponte antiga sobre um rio caudaloso.");
        System.out.println("1 - Tentar atravessar a ponte.");
        System.out.println("2 - Procurar outro caminho pelo rio.");
        System.out.print("> ");
        int escolha = sc.nextInt();

        if (escolha == 1) {
            System.out.println("\nVocê pisa na ponte e ela misteriosamente começa a ceder!");
            if (new Random().nextBoolean()) {
                System.out.println("Você atravessa por pouco, com o coração disparado.");
                System.out.println("Do outro lado, um mercador misterioso surge e oferece ajuda.");
                mercador(jogador);
            } else {
                System.out.println("A ponte desaba! Você cai e perde 20 de HP.");
                jogador.receberDano(20);
                System.out.println("Ferido, você rasteja até uma margem segura.");
                explorar(jogador);
            }
        } else {
            System.out.println("\nVocê segue pelo rio e encontra um santuário escondido entre as árvores.");
            System.out.println("Um brilho toca seu corpo. HP e MP restaurados!");
            jogador.restaurarVida(999);
            jogador.restaurarMana(999);
            explorar(jogador);
        }
    }

    // Caverna
    private static void cavernaDosEcos(Personagem jogador) {
        System.out.println("\n💎 A caverna brilha com cristais azuis e um zumbido mágico ecoa nas paredes.");
        System.out.println("Você sente sua mana sendo lentamente restaurada...");
        jogador.restaurarMana(30);

        System.out.println("\nHá dois túneis:");
        System.out.println("1 - O túnel à esquerda, coberto por teias de aranha.");
        System.out.println("2 - O túnel à direita, iluminado por cristais pulsantes.");
        System.out.print("> ");
        int escolha = sc.nextInt();

        if (escolha == 1) {
            System.out.println("\n🕸️ Você se enrosca nas teias e sente algo se movendo...");
            Inimigo aranha = new Inimigo("Aranha_G", 2);
            batalhar(jogador, aranha);
            if (jogador.estaVivo()) {
                System.out.println("Ao derrotar a aranha, você encontra uma pedra mágica que brilha intensamente.");
                jogador.inventario.adicionarItem(new Item("Pedra da Luz", "Emite energia sagrada", "mana", 1));
                explorar(jogador);
            }
        } else {
            System.out.println("\n✨ O túnel leva até uma câmara circular com um altar antigo.");
            System.out.println("Você sente uma presença espiritual...");
            System.out.println("\"Herói... leve esta bênção e siga em frente.\"");
            jogador.restaurarVida(999);
            jogador.restaurarMana(999);
            System.out.println("Seus status foram completamente restaurados!");
            explorar(jogador);
        }
    }

    // Mercador
    private static void mercador(Personagem jogador) {
        System.out.println("\n🧙‍♂️ Mercador: \"Vejo que você é um viajante cansado. Talvez eu possa ajudar.\"");
        System.out.println("1 - Comprar Poção de Cura (10 moedas)");
        System.out.println("2 - Comprar Poção de Mana (10 moedas)");
        System.out.println("3 - Agradecer e seguir caminho");
        System.out.print("> ");
        int escolha = sc.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("Você comprou uma Poção de Cura!");
                jogador.inventario.adicionarItem(new Item("Poção de Cura", "Recupera 30 HP", "cura", 1));
                break;
            case 2:
                System.out.println("Você comprou uma Poção de Mana!");
                jogador.inventario.adicionarItem(new Item("Poção de Mana", "Recupera 20 MP", "mana", 1));
                break;
            default:
                System.out.println("Você agradece e segue viagem.");
        }

        System.out.println("\nO mercador desaparece misteriosamente na neblina...");
        explorar(jogador);
    }

    // Exploração
    private static void explorar(Personagem jogador) {
        Random rnd = new Random();
        int npc_encounter = 0;
        while (jogador.estaVivo()) {

            jogador.exibirStatus();
            System.out.println("\n📍 O que deseja fazer?");
            System.out.println("1 - Explorar os arredores");
            System.out.println("2 - Mostrar inventario");
            System.out.println("3 - Usar item");
            System.out.println("4 - Descansar");
            System.out.println("5 - Encerrar jornada");
            System.out.print("> ");
            int op = sc.nextInt();
            sc.nextLine();
//probabilidade de eventos e de inimigos (o dragao tem status maior que do boss por ser raro)
            switch (op) {
                case 1:
                    int evento = rnd.nextInt(100);
                    if (evento < 40) {
                        int value = rnd.nextInt(0,100);
                        Inimigo inimigo = null;
                        if(value<=1) inimigo = new Inimigo("Dragao", jogador.nivel+rnd.nextInt(jogador.nivel<4?(jogador.nivel-1)*-1:-3,+1));
                        if(value>1&&value<=30)inimigo = new Inimigo("Bandido", jogador.nivel+rnd.nextInt(jogador.nivel<4?(jogador.nivel-1)*-1:-3,+3));
                        if(value>30&&value<=45)inimigo = new Inimigo("Aranha_G", jogador.nivel+rnd.nextInt(jogador.nivel<4?(jogador.nivel-1)*-1:-3,+2));
                        if(value>45&&value<=60)inimigo = new Inimigo("orc", jogador.nivel+rnd.nextInt(jogador.nivel<4?(jogador.nivel-1)*-1:-3,+3));
                        if(value>60&&value<=75)inimigo = new Inimigo("goblin", jogador.nivel+rnd.nextInt(jogador.nivel<4?(jogador.nivel-1)*-1:-3,+3));
                        if(value>75)inimigo = new Inimigo("Slime", jogador.nivel+rnd.nextInt(jogador.nivel<4?(jogador.nivel-1)*-1:-3,+3));
                        batalhar(jogador, inimigo);
                    } else if (evento < 70) {
                        System.out.println("Você encontrou um baú trancado. Forçar?");
                        System.out.println("1 - Sim | 2 - Não");
                        int forcar = sc.nextInt();
                        if (forcar == 1 && rnd.nextBoolean()) {
                            System.out.println("Você encontrou uma Poção Rara!");
                            jogador.inventario.adicionarItem(new Item("Poção Rara", "Cura 50 HP", "cura", 1));
                        } else {
                            System.out.println("Uma armadilha explode! -15 HP!");
                            jogador.receberDano(15);
                        }
                    } else {
                        System.out.println("Você encontra " +
                                (npc_encounter==0?"um":"o") +
                                " viajante " +
                                (npc_encounter>0?"novamente ":"")//se npc_encounter > 0 adiciona "novamente", se nao nada
                                +"e ele te fala algumas coisas");
                        String message = "";
                        switch (npc_encounter){
                            case 0:
                                message = "\"Há muito existia uma Torre envolvida por escuridão... chamada de Torre Negra\"";
                                npc_encounter++;
                                break;
                            case 1:
                                if(jogador.nivel>=2){
                                    message = "\"à 100 anos o heroi \"Kafontess\" derrotou o mestre da Torre negra\"";
                                    npc_encounter++;
                                }else{
                                    message = "\"Você esta muito fraco ainda\"";
                                }

                                break;
                            case 2:
                                if(jogador.nivel>=4) {
                                    message = "\"Antes de ser morto, o mestre da Torre Negra lançou uma maldição\n" +
                                            "\"Quando a proxima lua sangrenta chegar eu me levantarei dos mortos para sede de sangue matar\"\"";
                                    npc_encounter++;
                                }else{
                                    message = "\"Você esta muito fraco ainda\"";
                                }
                                break;
                            case 3:
                                if(jogador.nivel>=6){
                                    message = "Apos 100 anos de sua morte, a proxima lua de sangue se aproxima...";
                                    npc_encounter++;
                                }else{
                                    message = "\"Você esta muito fraco ainda\"";
                                }
                                break;
                            case 4:
                                if(jogador.nivel>8){
                                    message = "Hoje a noite teremos a lua de sangue... Conto com voce heroi";
                                    npc_encounter++;
                                }else{
                                    message = "\"Você esta muito fraco ainda\"";
                                }
                                break;
                            default:
                                break;

                        }
                        System.out.println(message);

                        if (npc_encounter >= 4){
                            System.out.println ("Você está pronto para a batalha FINAL? \n (y)Sim \n (n)Não");
                           String escolha = sc.nextLine();
                           if (escolha.equalsIgnoreCase("y")) {
                               //iniciar boss fight

                               System.out.println("Você e o viajante entram na torre negra");
                               System.out.println("\"Não esperava ver você tão cedo Kafontess\"");
                               System.out.println("Você olha para o viajante incredulo que ele, esse tempo todo, era o herói da lenda\n" +
                                       "Porém antes que pudesse fazer qualquer coisa uma espada é atirada em sua direção\n" +
                                       "Porém no ultimo segundo Kafontess entra em sua frente, recebendo a espada em seu peito\n" +
                                       "\"Eu André o grande e maligno Lorde das Trevas finalmente tive minha vigança!\"");

                               batalhar(jogador, new Inimigo("ANDRE", jogador.nivel + 2));

                               System.out.println("Você derrota o grande Lorde das Trevas, o maligno André!\n" +
                                       "E após essa grande batalha, deita-se para descansar usando o braço do derrotado maligno como seu apoio, nunca mais acordando...");
                               System.exit(0);

                           } else if (escolha.equalsIgnoreCase("n")) {
                               //ignorar boss fight
                           }
                           else {
                               System.out.println("Nenhuma das escolhas foi escolhida.");
                           }
                        }



                    }
                    break;
                case 2:
                    jogador.inventario.listarItens();
                    break;
                case 3:
                    jogador.inventario.listarItens();
                    System.out.print("Digite o nome do item: ");
                    String nomeItem = sc.nextLine();
                    for (Item i : jogador.inventario.getItens()) {
                        if (i.getNome().equalsIgnoreCase(nomeItem)) {
                            i.usar(jogador);
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Você acende uma fogueira e descansa. HP e MP parcialmente restaurados.");
                    jogador.restaurarVida(30);
                    jogador.restaurarMana(20);
                    break;
                case 5:
                    System.out.println("Você decide encerrar a jornada por hoje...");
                    return;
                default:
                    System.out.println("Escolha inválida!");
            }
        }

        System.out.println("\n💀 " + jogador.getNome() + " caiu em batalha... mas sua lenda viverá em Maligno&Douglas.");
    }

    // Combate
    private static void batalhar(Personagem jogador, Inimigo inimigo) {
        System.out.println("\nUm " + inimigo.getNome() + " apareceu!");
        Random dado = new Random();

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            jogador.exibirStatus();
            inimigo.exibirStatus();

            System.out.println("\n1 - Ataque básico");
            System.out.println("2 - Ataque especial");
            System.out.println("3 - Tentar fugir");
            System.out.print("> ");
            int escolha;
            try {
                escolha = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Use 1, 2 ou 3.");
                continue;
            }

            // Ação do jogador
            switch (escolha) {
                case 1: {
                    // rola dado e verifica defesa
                    int rolagem = dado.nextInt(6) + 1;
                    int ataqueTotal = jogador.ataque + rolagem;
                    System.out.println("\nVocê rola o dado e tira " + rolagem + ". Ataque total: " + ataqueTotal);
                    if (ataqueTotal > inimigo.defesa) {
                        int dano = ataqueTotal - inimigo.defesa;
                        inimigo.receberDano(dano);
                        System.out.println(jogador.getNome() + " acertou o ataque básico e causou " + dano + " de dano!");
                    } else {
                        System.out.println(jogador.getNome() + " atacou, mas não atravessou a defesa do inimigo.");
                    }
                    break;
                }

                case 2: {
                    // ataque especial também usa rolagem somada à fórmula do ataque especial, garante giro d defesa

                    int rolagem = dado.nextInt(6) + 1;
                    System.out.println("\nVocê prepara um ataque especial (rolagem: " + rolagem + ").");
                    jogador.atacarEspecial(inimigo);
                    break;
                }

                case 3: {
                    int rolagemJogador = dado.nextInt(6) + 1;
                    int rolagemInimigo = dado.nextInt(6) + 1;
                    System.out.println("\nVocê rola o dado e tira " + rolagemJogador + ".");
                    System.out.println("O inimigo rola o dado e tira " + rolagemInimigo + ".");

                    if (rolagemJogador >= rolagemInimigo) {
                        System.out.println("\n🔥 Você conseguiu fugir com sucesso!");
                        String lower = inimigo.getNome().toLowerCase();
                        if (lower.contains("goblin")) {
                            System.out.println("Você some entre as árvores, deixando o goblin confuso.");
                        } else if (lower.contains("lobo")) {
                            System.out.println("Os uivos ecoam enquanto você some na escuridão.");
                        } else if (lower.contains("orc")) {
                            System.out.println("O orc te perde de vista entre as pedras.");
                        } else {
                            System.out.println("Você foge desesperadamente e perde o inimigo de vista.");
                        }
                        System.out.println("Você respira fundo e decide continuar sua jornada...\n");
                        return;
                    } else {
                        System.out.println("\n⚠️ A fuga falhou! O inimigo aproveita a abertura.");
                        // inimigo ataca uma vez quando a fuga falha
                        // ataque do inimigo usa rolagem + ataque e compara com defesa do jogador
                        int rolagemI = dado.nextInt(6) + 1;
                        int ataqueInimigo = inimigo.ataque + rolagemI;
                        System.out.println("O inimigo rola " + rolagemI + " (ataque total " + ataqueInimigo + ").");
                        if (ataqueInimigo > jogador.defesa) {
                            int dano = ataqueInimigo - jogador.defesa;
                            jogador.receberDano(dano);
                            System.out.println(inimigo.getNome() + " acertou um golpe e causou " + dano + " de dano!");
                        } else {
                            System.out.println(inimigo.getNome() + " atacou, mas não venceu sua defesa.");
                        }
                    }
                    break;
                }

                default:
                    System.out.println("Escolha inválida!");
                    continue;
            }

            // Se o inimigo ainda vive e o jogador não tentou fugir ele volta para o batalhar
            if (inimigo.estaVivo() && escolha != 3) {
                int rolagemInimigo = dado.nextInt(6) + 1;
                int ataqueInimigo = inimigo.ataque + rolagemInimigo;
                System.out.println("\nO inimigo contra-ataca! (rolagem: " + rolagemInimigo + ", ataque total: " + ataqueInimigo + ")");
                if (ataqueInimigo > jogador.defesa) {
                    int dano = ataqueInimigo - jogador.defesa;
                    jogador.receberDano(dano);
                    System.out.println(inimigo.getNome() + " causou " + dano + " de dano em " + jogador.getNome() + "!");
                } else {
                    System.out.println(inimigo.getNome() + " atacou, mas não foi suficiente para romper sua defesa.");
                }
            }
        }

        // Resultado da luta
        if (jogador.estaVivo()) {
            System.out.println("\n🏆 Você derrotou o inimigo!");
            jogador.restaurarVida(10);
            System.out.println("Você recuperou um pouco de HP após a luta.");
            int xpGain = inimigo.dropXp();
            System.out.println("Você ganhou " + xpGain + " de XP");
            jogador.receberXp(xpGain);

            // loot para o jogador
            if (inimigo.inventario != null && !inimigo.inventario.getItens().isEmpty()) {
                System.out.println("\n🎁 Você saqueia o inimigo e encontra:");
                Inventario loot = inimigo.inventario.clone(); // clone profundo
                for (Item it : loot.getItens()) {
                    // adiciona o item e soma ao o que ja tem
                    jogador.inventario.adicionarItem(it.clone());
                    System.out.println("- " + it.getNome() + " x" + it.getQuantidade());
                }
            } else {
                System.out.println("\n(sem loot)");
            }

        } else {
            System.out.println("\n💀 Você foi derrotado...");
        }
    }

}
