/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author Edu
 */
class noRN { // classe do no da arvore (parecida com a da lista)

    int valor;
    boolean cor;  // false para vermelho e true para preto
    noRN esq,dir;

    public noRN(int valor, boolean cor, noRN esq,noRN dir) {
        this.valor = valor;
        this.cor = cor;
        this.esq = esq;
        this.dir = dir;
    }
}
public class RN {
    noRN raiz;
    int hEsq , hDir, hRN = 0;  //alturas ,a esquerda, a direita e do nó 

    public RN() {
        this.raiz = null;   
    }
    void inserir(int valor){
        noRN atual = this.raiz;
        if(this.raiz == null){
            this.raiz = new noRN(valor,true,null,null); // insere o primeiro nó,ou seja, o raiz, que será preto
        }else if (this.raiz!=null){
            if(valor>atual.valor){
                while(atual.dir!=null){
                    if(valor>atual.valor){
                        atual = atual.dir ;
                    }else{
                        atual = atual.esq;
                    }
                }
                if(valor>atual.valor){
                    atual.dir = new noRN (valor,false,null,null);
                }else{
                    atual.esq = new noRN (valor,false,null,null);
                }
                altura direita da raiz
            }else{
                while (atual.esq != null){
                    if(valor>atual.valor){
                        atual = atual.dir;
                    }else{
                        atual = atual.esq;
                    }
                }
                if(valor>atual.valor){
                    atual.dir = new noRN (valor,false,null,null);
                }else{
                    atual.esq = new noRN (valor,false,null,null);
                }
                altura esquerda da raiz
            }
        }
        if else(){
            
        }
    }
    
}
