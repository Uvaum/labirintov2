//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Mapa {
    protected static char[][] labirinto;
    protected List<Rato> arrayDeRatos = new ArrayList();
    protected static int queijoPosicaoX;
    protected static int queijoPosicaoY;

    public void quantidadeDeRatos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quantos ratos você deseja?\nSua resposta: ");
        int numRats = scanner.nextInt();

        for(int i = 0; i < numRats; ++i) {
            Rato r = new Rato();
            r.identificarRato(i);
            this.arrayDeRatos.add(r);
        }

    }

    public void posicionarRato(Rato r) {
        Random random = new Random();
        r.setX(random.nextInt(23) + 1);
        r.setY(random.nextInt(73) + 1);
        labirinto[r.getX()][r.getY()] = 'R';
    }

    public void gerarMapa() {
        Random random = new Random();
        labirinto = new char[25][75];

        for(int i = 0; i < labirinto.length; ++i) {
            for(int j = 0; j < labirinto[i].length; ++j) {
                if (i != 0 && i != 24 && j != 0 && j != 74) {
                    if (random.nextInt(3) % 2 == 0) {
                        labirinto[i][j] = ' ';
                    } else {
                        labirinto[i][j] = 9618;
                    }
                } else {
                    labirinto[i][j] = 9608;
                }
            }
        }

    }

    public void gerarCaminhoGarantido(Rato r) {
        int i;
        for(i = 1; i < r.getX() + 1; ++i) {
            labirinto[i][1] = ' ';
        }

        for(int j = 1; j < r.getY() + 1; ++j) {
            labirinto[i - 1][j] = ' ';
        }

    }

    public void posicionarQueijo() {
        queijoPosicaoX = 1;
        queijoPosicaoY = 1;
        labirinto[1][1] = 9632;
    }

    public void printarMapa() {
        for(int i = 0; i < labirinto.length; ++i) {
            System.out.println();

            for(int j = 0; j < labirinto[i].length; ++j) {
                char c = labirinto[i][j];
                switch (c) {
                    case '.':
                        this.printarCaminhoPercorrido(i, j);
                        break;
                    case 'R':
                        this.printarRato(i, j);
                        break;
                    case '■':
                        this.printarQueijo(i, j);
                        break;
                    default:
                        System.out.print(c);
                }
            }
        }

    }

    public void printarRato(int i, int j) {
        String cor = this.corDoRato(i, j);
        System.out.print(cor + labirinto[i][j] + "\u001b[0m");
    }

    public void printarQueijo(int i, int j) {
        System.out.print("\u001b[33m" + labirinto[i][j] + "\u001b[0m");
    }

    public void printarCaminhoPercorrido(int i, int j) {
        System.out.print("\u001b[31m" + labirinto[i][j] + "\u001b[0m");
    }

    public String corDoRato(int i, int j) {
        int index = -1;

        for(int k = 0; k < this.arrayDeRatos.size(); ++k) {
            Rato r = (Rato)this.arrayDeRatos.get(k);
            if (r.getX() == i && r.getY() == j) {
                index = k;
                break;
            }
        }

        String var10000;
        switch (index) {
            case 0 -> var10000 = "\u001b[34m";
            case 1 -> var10000 = "\u001b[32m";
            case 2 -> var10000 = "\u001b[33m";
            case 3 -> var10000 = "\u001b[36m";
            case 4 -> var10000 = "\u001b[35m";
            case 5 -> var10000 = "\u001b[31m";
            case 6 -> var10000 = "\u001b[94m";
            case 7 -> var10000 = "\u001b[37m";
            case 8 -> var10000 = "\u001b[90m";
            case 9 -> var10000 = "\u001b[93m";
            case 10 -> var10000 = "\u001b[91m";
            default -> var10000 = "\u001b[31m";
        }

        return var10000;
    }
}

