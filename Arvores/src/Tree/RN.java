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
    noRN atual;
    int hEsq, hDir, hRN = 0;  //alturas ,a esquerda, a direita e do nó 

    public RN() {
        this.raiz = null;
    }

    void inserir(int valor) {
        this.atual = this.raiz;
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
                //hDir = alturaDir(this.raiz, valor);//altura direita da raiz
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
                //hEsq = alturaEsq(this.raiz, valor);//altura esquerda da raiz
            }
        } else if ((hDir - hEsq) == 2 || (hDir - hEsq) == -2) {// se isso for verdade(true) isso significa que a arvore precisa ser balanceada
            //balanceararvore(valor);
            //hEsq = alturaEsq(this.raiz, valor); // 
            //hDir = alturaDir(this.raiz, valor); // Recalcula o valor dos dois para dar a altura da arvore.

        }
    }
    int alturaEsq(int valor){           //Procura o nó que contém esse valor e retorna sua altura a esquerda
        int altura = -1;
        this.atual = this.raiz;
        while(this.atual!=null){
            if(atual.valor==valor){
                while(atual!=null){
                    atual = atual.esq;
                    altura++;
                }
            }else if (valor>atual.valor){
                atual = atual.dir;
            }else{
                atual = atual.esq;
            }
        }
        return altura;
    }
    int alturaDir(int valor){           // Procura o nó que contém esse valor e retorna sua altura a direita
        int altura = -1;
        this.atual = this.raiz;
        while(this.atual!=null){
            if(atual.valor==valor){
                while(atual!=null){
                    atual = atual.dir;
                    altura++;
                }
            }else if (valor>atual.valor){
                atual = atual.dir;
            }else{
                atual = atual.esq;
            }
        }
        return altura;
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
        a.inserir(2);
        System.out.println(a.alturaEsq(1));
        System.out.println(a.alturaDir(1));
    }

}
