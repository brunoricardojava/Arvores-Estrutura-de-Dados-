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
    int hEsq = 0, hDir = 0, hRN = 0;  //alturas ,a esquerda, a direita e do nó 

    public RN() {
        this.raiz = null;
    }

    void inserir(int valor) {
        //this.atual = this.raiz;
        if (this.raiz == null) {
            this.raiz = new noRN(valor, true, null, null); // insere o primeiro nó,ou seja, o raiz, que será preto
            this.atual = this.raiz;
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
            balanceararvore(valor);
            hEsq = alturaEsq(valor); // 
            hDir = alturaDir(valor); // Recalcula o valor dos dois para dar a altura da arvore.

        }
    }
    
    void balanceararvore(int valor) { // funcao que pegara o necessario para fazer o balançeamento.
        noRN filho=this.raiz;
        noRN tio=null;
        noRN pai=null;
        noRN avo=null;
        noRN bisavo=null;
        noRN tioavo=null;
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
            System.out.println("esq "+alturaEsq(valor));
            System.out.println("dir "+alturaDir(valor));
            if (alturaEsq(valor)>alturaDir(valor)){
                rotacaoDir(avo,pai,filho);
            }
            else{
               rotacaoEsq(avo,pai,filho); 
            }
        }
        else{
            System.out.println("esq "+alturaEsq(valor));
            System.out.println("dir "+alturaDir(valor));
            if(alturaEsq(valor)>alturaDir(valor)){
                tio=avo.dir;
                tioavo=bisavo.dir;
                if (alturaEsq(valor)>alturaDir(valor)){
                    System.out.println("rotacaodir");
                    rotacaoDir(avo,pai,filho);
                }
                else{
                    System.out.println("rotacaodupladir");
                    rotacaoduplaDir(bisavo,avo,pai,filho,tio,tioavo); 
                }
            }
            else{
                tio=avo.esq;
                tioavo=bisavo.esq;
                if (alturaDir(valor)>alturaEsq(valor)){
                    System.out.println("rotacaoesq");
                    rotacaoEsq(avo,pai,filho);
                }
                else{
                    System.out.println("rotacaoduplaesq");
                    rotacaoduplaEsq(bisavo,avo,pai,filho,tio,tioavo);
                }
            }
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
    
    void rotacaoDir(noRN avo,noRN pai,noRN filho){
        avo.dir=new noRN(avo.valor,false,null,null);
        avo.valor=pai.valor;
        pai.valor=filho.valor;
        pai.esq=null;
    }
    
    void rotacaoduplaDir(noRN bisavo,noRN avo,noRN pai,noRN filho,noRN tio,noRN tioavo){
        tioavo.dir=new noRN(tioavo.valor,false,null,null);
        tioavo.esq=new noRN(tio.valor,false,null,null);
        tioavo.valor=bisavo.valor;
        bisavo.valor=avo.valor;
        avo.valor=pai.valor;
        pai.valor=filho.valor;
        pai.esq=null;
    }
    
    void rotacaoEsq(noRN avo,noRN pai,noRN filho){
        avo.esq=new noRN(avo.valor,false,null,null);
        avo.valor=pai.valor;
        pai.valor=filho.valor;
        pai.dir=null;
    }
    
    void rotacaoduplaEsq(noRN bisavo,noRN avo,noRN pai,noRN filho,noRN tio,noRN tioavo){
        tioavo.esq=new noRN(tioavo.valor,false,null,null);
        tioavo.dir=new noRN(tio.valor,false,null,null);
        tioavo.valor=bisavo.valor;
        bisavo.valor=avo.valor;
        avo.valor=pai.valor;
        pai.valor=filho.valor;
        pai.dir=null;
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
