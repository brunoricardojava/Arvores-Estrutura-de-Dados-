/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import javax.swing.JOptionPane;

/**
 *
 * @author Derian
 * dir=direita
 * esq=esquerda
 * h=altura
 */
public class AVl {
    public static void main(String[] args) {
        arvoreavl avl = new arvoreavl();
        avl.inserir(Integer.parseInt(JOptionPane.showInputDialog("Digite um valor inteiro para colocar na arvore")));
        avl.inserir(Integer.parseInt(JOptionPane.showInputDialog("Digite um valor inteiro para colocar na arvore")));
        avl.inserir(Integer.parseInt(JOptionPane.showInputDialog("Digite um valor inteiro para colocar na arvore")));
        avl.inserir(Integer.parseInt(JOptionPane.showInputDialog("Digite um valor inteiro para colocar na arvore")));
        avl.inserir(Integer.parseInt(JOptionPane.showInputDialog("Digite um valor inteiro para colocar na arvore")));
        System.out.println(""+avl);
    }
 
}
class arvoreavl {
    noavl raiz;
    int hesq=0,hdir=0,havl=0; // variaveis que representam a altura.
    public arvoreavl() {
        this.raiz = null;
    }
    
    int alturaesq(noavl atual,int valor){ // achar a altura de um no a esquerda dele.
        int altura=0;
        if (atual==this.raiz && atual.esq!=null) {
            altura++;
        }
        atual=atual.esq;
        while(atual!=null){
            if (valor==atual.valor) {
                return altura;
            }
            else{
                if (valor<atual.valor) {
                    atual=atual.esq;
                    altura++;
                }
                else{
                    atual=atual.dir;
                    altura++;
                }
            }
        }
        return 0;
    }
    
    int alturadir(noavl atual,int valor){// achar a altura de um no a direita dele.
        int altura=0;
        if (atual==this.raiz && atual.dir!=null) {
            altura++;
        }
        atual=atual.dir;
        while(atual!=null){
            if (valor==atual.valor) {
                return altura;
            }
            else{
                if (valor<atual.valor) {
                    atual=atual.esq;
                    altura++;
                }
                else{
                    atual=atual.dir;
                    altura++;
                }
            }
        }
        return 0;
    }
    
    void balanceararvore(int valor) { // funcao que pegara o necessario para fazer o balançeamento.
        noavl filho=this.raiz;
        noavl tio=null;
        noavl pai=null;
        noavl avo=null;
        noavl bisavo=null;
        noavl tioavo=null;
        while(filho!=null && filho.valor!=valor){ //loop para chegar ate o valor que acabou de ser inserido
            bisavo=avo;
            avo=pai;
            pai=filho;
            if (filho.valor>valor) {
                filho=filho.esq;
            }
            else{
                filho=filho.dir;
            }
        } // a partir daqui os ifs irao ver qual é a forma de balanceamento a ser aplicada
        if(bisavo==null){ // caso especial
            System.out.println("esq "+alturaesq(avo,valor));
            System.out.println("dir "+alturadir(avo,valor));
            if (alturaesq(avo,valor)>alturadir(avo,valor)){
                rotacaodir(avo,pai,filho);
            }
            else{
               rotacaoesq(avo,pai,filho); 
            }
        }
        else{
            System.out.println("esq "+alturaesq(bisavo,valor));
            System.out.println("dir "+alturadir(bisavo,valor));
            if(alturaesq(bisavo,valor)>alturadir(bisavo,valor)){
                tio=avo.dir;
                tioavo=bisavo.dir;
                if (alturaesq(avo,valor)>alturadir(avo,valor)){
                    System.out.println("rotacaodir");
                    rotacaodir(avo,pai,filho);
                }
                else{
                    System.out.println("rotacaodupladir");
                    rotacaodupladir(bisavo,avo,pai,filho,tio,tioavo); 
                }
            }
            else{
                tio=avo.esq;
                tioavo=bisavo.esq;
                if (alturadir(avo,valor)>alturaesq(avo,valor)){
                    System.out.println("rotacaoesq");
                    rotacaoesq(avo,pai,filho);
                }
                else{
                    System.out.println("rotacaoduplaesq");
                    rotacaoduplaesq(bisavo,avo,pai,filho,tio,tioavo);
                }
            }
        }
    }
    // aqui vem as 4 rotaçoes de balanceamento possivel
    void rotacaodir(noavl avo,noavl pai,noavl filho){
        avo.dir=new noavl(avo.valor,null,null);
        avo.valor=pai.valor;
        pai.valor=filho.valor;
        pai.esq=null;
    }
    
    void rotacaodupladir(noavl bisavo,noavl avo,noavl pai,noavl filho,noavl tio,noavl tioavo){
        tioavo.dir=new noavl(tioavo.valor,null,null);
        tioavo.esq=new noavl(tio.valor,null,null);
        tioavo.valor=bisavo.valor;
        bisavo.valor=avo.valor;
        avo.valor=pai.valor;
        pai.valor=filho.valor;
        pai.esq=null;
    }
    
    void rotacaoesq(noavl avo,noavl pai,noavl filho){
        avo.esq=new noavl(avo.valor,null,null);
        avo.valor=pai.valor;
        pai.valor=filho.valor;
        pai.dir=null;
    }
    
    void rotacaoduplaesq(noavl bisavo,noavl avo,noavl pai,noavl filho,noavl tio,noavl tioavo){
        tioavo.esq=new noavl(tioavo.valor,null,null);
        tioavo.dir=new noavl(tio.valor,null,null);
        tioavo.valor=bisavo.valor;
        bisavo.valor=avo.valor;
        avo.valor=pai.valor;
        pai.valor=filho.valor;
        pai.dir=null;
    }
    
    void inserir(int valor) { //funcao que insere o numero na arvore, ela tambem ja chama a funcao de balanceamento caso seja necessario
        noavl atual=this.raiz;
        if (this.raiz == null) {
            this.raiz = new noavl(valor,null,null);
        }else if (this.raiz !=null){
            if (valor>atual.valor) {
                while (atual.dir != null) {
                    if (valor>atual.valor) {
                        atual=atual.dir;
                    }else{
                        atual=atual.esq;
                    }
                }
                if (valor>atual.valor) {
                    atual.dir = new noavl(valor,null,null);
                }else{
                    atual.esq = new noavl(valor,null,null);
                }
                hdir=alturadir(this.raiz,valor);// me da o valor a direita a partir da raiz
            }else{
                while (atual.esq != null) {
                    if (valor>atual.valor) {
                        atual=atual.dir;
                    }
                    else{
                        atual=atual.esq;
                    }
                }
                if (valor>atual.valor) {
                    atual.dir = new noavl(valor,null,null);
                }else{
                    atual.esq = new noavl(valor,null,null);
                }
                hesq=alturaesq(this.raiz,valor);// me da o valor a esquerda a partir da raiz
            }
        }
        if ((hdir-hesq)==2 || (hdir-hesq)==-2) {// se isso for verdade(true) isso significa que a arvore precisa ser balanceada
            balanceararvore(valor);
            hesq=alturaesq(this.raiz,valor); // 
            hdir=alturadir(this.raiz,valor); // Recalcula o valor dos dois para dar a altura da arvore.
        }
        havl=Math.max(hesq,hdir); //altura da arvore
    }
    
    @Override // função para exibir a arvore (nao implementada totalmente exibe apenas as folhas da esquerda)
    public String toString() {
        if (this.raiz == null) {
            return "Arvore vazia";
        }

        StringBuilder builder = new StringBuilder("[");
        noavl atual = this.raiz;
        while (atual.esq != null) {
            builder.append(atual.valor);
            builder.append(", ");
            atual = atual.esq;
        }
        builder.append(atual.valor);
        builder.append("]");

        return builder.toString();
    }
}

class noavl { // classe do no da arvore (parecida com a da lista)

    int valor;
    noavl esq,dir;

    public noavl(int valor, noavl esq,noavl dir) {
        this.valor = valor;
        this.esq = esq;
        this.dir = dir;
    }
}
