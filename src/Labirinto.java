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

            for(int i = 0; i < this.arrayDeRatos.size(); ++i) {
                this.threads.add(new Thread(() -> this.movimentarRato(i)));
            }

            for(Thread t : this.threads) {
                t.start();

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var4) {
                    Thread.currentThread().interrupt();
                }
            }

            this.threads.clear();
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
            if (System.getProperty("os.name").contains("Windows")) {
                (new ProcessBuilder(new String[]{"cmd", "/c", "cls"})).inheritIO().start().waitFor();
            } else {
                System.out.print("\u001b[H\u001b[2J");
                System.out.flush();
            }
        } catch (Exception var2) {
        }

    }
}
