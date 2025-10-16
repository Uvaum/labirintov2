//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Labirinto extends Mapa {
    private Rato rato;
    private volatile boolean queijoEncontrado = false;
    private final List<Thread> threads = new ArrayList();

    public Labirinto() {
        this.gerarMapa();
        this.quantidadeDeRatos();

        for(int i = 0; i < this.arrayDeRatos.size(); ++i) {
            Rato r = (Rato)this.arrayDeRatos.get(i);
            this.posicionarRato(r);
            this.gerarCaminhoGarantido(r);
        }

        this.posicionarQueijo();

        while(!this.queijoEncontrado) {
            this.limparConsole();
            this.printarMapa();

//            for(int i = 0; i < this.arrayDeRatos.size(); ++i) {
//                final int index= i;
//                this.threads.add(new Thread(() -> this.movimentarRato(index)));
//            }
            for(int i = 0; i < this.arrayDeRatos.size(); ++i) {
                final int index = i;
                Thread t = new Thread(() -> this.movimentarRato(index));
                this.threads.add(t);
                t.start();
            }


//            for(Thread t : this.threads) {
//                t.start();
//
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException var4) {
//                    Thread.currentThread().interrupt();
//                }
//            }
            for(Thread t : this.threads) {
                try {
                    t.join(); // Espera a thread terminar
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            this.threads.clear();
            try {
                Thread.sleep(100); // Ajuste este valor para controlar a velocidade da animação
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }

    }

    private void movimentarRato(int index) {
        this.rato = (Rato)this.arrayDeRatos.get(index);
        this.rato.movimentoAutomatico(labirinto);
        if (this.rato.getX() == queijoPosicaoX && this.rato.getY() == queijoPosicaoY) {
            synchronized(System.out) {
                PrintStream var10000 = System.out;
                int var10001 = this.rato.getRatoIdentificador();
                var10000.println("\n\n\u001b[32mPARABÉNS AO RATO " + (var10001 + 1) + ", FOI O PRIMEIRO A CHEGAR AO SEU QUEIJO!!!\u001b[0m");
            }

            this.queijoEncontrado = true;
        }

    }

    private void limparConsole() {
        try {
            String os = System.getProperty("os.name");
            if (System.console() == null) {
                // Provavelmente está em um IDE ou ambiente web
                // Usa uma abordagem mais simples
                for(int i = 0; i < 50; i++) {
                    System.out.println();
                }
                System.out.println("=".repeat(50)); // Separador visual
            } else if(os.contains("Windows")) {
                new ProcessBuilder(new String[]{"cmd", "/c", "cls"}).inheritIO().start().waitFor();
            } else {
                System.out.print("\u001b[H\u001b[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
            System.out.println("=".repeat(50));


        }

    }
}
