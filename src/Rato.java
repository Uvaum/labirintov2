//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Stack;

public class Rato {
    private int ratoIdentificador;
    private int x;
    private int y;
    private final Stack<Character> stack = new Stack();

    public void identificarRato(int ratoIdentificador) {
        this.ratoIdentificador = ratoIdentificador;
    }

    public void movimentoAutomatico(char[][] m) {
        char W = m[this.x - 1][this.y];
        char A = m[this.x][this.y - 1];
        char S = m[this.x + 1][this.y];
        char D = m[this.x][this.y + 1];
        if (W != 9618 && W != 9608 && W != '.' && W != 'R') {
            this.stack.push('W');
            this.moverParaCima(m);
        } else if (A != 9618 && A != 9608 && A != '.' && A != 'R') {
            this.stack.push('A');
            this.moverParaEsquerda(m);
        } else if (S != 9618 && S != 9608 && S != '.' && S != 'R') {
            this.stack.push('S');
            this.moverParaBaixo(m);
        } else if (D != 9618 && D != 9608 && D != '.' && D != 'R') {
            this.stack.push('D');
            this.moverParaDireita(m);
        } else if (this.stack.size() > 1) {
            char topo = (Character)this.stack.peek();
            if (topo == 'W') {
                this.stack.pop();
                this.moverParaBaixo(m);
            } else if (topo == 'A') {
                this.stack.pop();
                this.moverParaDireita(m);
            } else if (topo == 'D') {
                this.stack.pop();
                this.moverParaEsquerda(m);
            } else if (topo == 'S') {
                this.stack.pop();
                this.moverParaCima(m);
            }
        }

    }

    public void moverParaCima(char[][] m) {
        m[this.x][this.y] = '.';
        m[this.x - 1][this.y] = 'R';
        --this.x;
    }

    public void moverParaBaixo(char[][] m) {
        m[this.x][this.y] = '.';
        m[this.x + 1][this.y] = 'R';
        ++this.x;
    }

    public void moverParaEsquerda(char[][] m) {
        m[this.x][this.y] = '.';
        m[this.x][this.y - 1] = 'R';
        --this.y;
    }

    public void moverParaDireita(char[][] m) {
        m[this.x][this.y] = '.';
        m[this.x][this.y + 1] = 'R';
        ++this.y;
    }

    public int getRatoIdentificador() {
        return this.ratoIdentificador;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

