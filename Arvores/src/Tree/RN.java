/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *
 * @author Edu
 */
class noRN { // classe do no da arvore (parecida com a da lista)

    int valor;
    boolean cor;  // false para vermelho e true para preto
    noRN esq, dir;

    public noRN(int valor, boolean cor, noRN esq, noRN dir) {
        this.valor = valor;
        this.cor = cor;
        this.esq = esq;
        this.dir = dir;
    }
}

public class RN {

    noRN raiz;
    int hEsq, hDir, hRN = 0;  //alturas ,a esquerda, a direita e do nó 

    public RN() {
        this.raiz = null;
    }

    void inserir(int valor) {
        noRN atual = this.raiz;
        if (this.raiz == null) {
            this.raiz = new noRN(valor, true, null, null); // insere o primeiro nó,ou seja, o raiz, que será preto
        } else if (this.raiz != null) {
            if (valor > atual.valor) {
                while (atual.dir != null) {
                    if (valor > atual.valor) {
                        atual = atual.dir;
                    } else {
                        atual = atual.esq;
                    }
                }
                if (valor > atual.valor) {
                    atual.dir = new noRN(valor, false, null, null);
                } else {
                    atual.esq = new noRN(valor, false, null, null);
                }
                hDir = alturaDir(this.raiz, valor);//altura direita da raiz
            } else {
                while (atual.esq != null) {
                    if (valor > atual.valor) {
                        atual = atual.dir;
                    } else {
                        atual = atual.esq;
                    }
                }
                if (valor > atual.valor) {
                    atual.dir = new noRN(valor, false, null, null);
                } else {
                    atual.esq = new noRN(valor, false, null, null);
                }
                hEsq = alturaEsq(this.raiz, valor);//altura esquerda da raiz
            }
        } else if ((hDir - hEsq) == 2 || (hDir - hEsq) == -2) {// se isso for verdade(true) isso significa que a arvore precisa ser balanceada
            //balanceararvore(valor);
            hEsq = alturaEsq(this.raiz, valor); // 
            hDir = alturaDir(this.raiz, valor); // Recalcula o valor dos dois para dar a altura da arvore.

        }
        hRN = Math.max(hEsq, hDir);
    }

    int alturaEsq(noRN atual, int valor) { // achar a altura de um no a esquerda dele.
        int altura = 0;
        if (atual == this.raiz && atual.esq != null) {
            altura++;
        }
        atual = atual.esq;
        while (atual != null) {
            if (valor == atual.valor) {
                return altura;
            } else if (valor < atual.valor) {
                atual = atual.esq;
                altura++;
            } else {
                atual = atual.dir;
                altura++;
            }
        }
        return 0;
    }

    int alturaDir(noRN atual, int valor) {// achar a altura de um no a direita dele.
        int altura = 0;
        if (atual == this.raiz && atual.dir != null) {
            altura++;
        }
        atual = atual.dir;
        while (atual != null) {
            if (valor == atual.valor) {
                return altura;
            } else if (valor < atual.valor) {
                atual = atual.esq;
                altura++;
            } else {
                atual = atual.dir;
                altura++;
            }
        }
        return 0;
    }
    

    public static void main(String[] args) {
        RN a = new RN();
        a.inserir(10);
        a.inserir(20);
        a.inserir(5);
        a.inserir(3);
        a.inserir(30);
        a.inserir(40);
        a.inserir(1);

        System.out.println(a.hDir);
        System.out.println(a.hEsq);
    }

}
