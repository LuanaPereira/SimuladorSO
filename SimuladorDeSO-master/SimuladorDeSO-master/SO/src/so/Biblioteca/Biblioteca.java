/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.Biblioteca;

import so.Memoria.Memoria;
import so.Nucleo.Nucleo;

/**
 *
 * @author Bruno
 */
public class Biblioteca {
    Nucleo so = new Nucleo(); //cria um nucleo para poder mandar as informacoes para o processo
   
    public void Processar(){ //metodo para acessar o nucleo
        so.calcularQuantum();
    }
    
    public boolean CriarProcesso(String nome, int prioridade){ //cria processo
        Processo processo = new Processo(); //cria o processo
        processo.nome = nome; //atribui o nome do processo
        processo.prioridade = prioridade; //atribui a prioridade do processo
        processo.quantum = 0; //atribui a % de quantum
        processo.finalizado = false; //atribui a situacao do processo
        if (prioridade == 3){ //verifica a prioridade para determinar o valor do quantum
            processo.contadorQuantum = 4;
        }
        else if (prioridade == 2){
            processo.contadorQuantum = 3;
        }
        else if (prioridade == 1){
            processo.contadorQuantum = 2;
        }
        so.Alocar(processo); //manda o processo para o nucleo
        
        Escalonador(); //ordena em ordem de prioridade 
        return true; 
    }
    public boolean FinalizarProcesso(int index){

        so.Desalocar(index); //desaloca o processo finalizando
        return true;
    }

    public void Escalonador(){ //ordenar o processo
        for(int i = 0; i < Memoria.memoria.size(); i++){
            for(int j = 0; j < Memoria.memoria.size(); j++){
                if(Memoria.memoria.get(i).prioridade > Memoria.memoria.get(j).prioridade){
                    Processo aux = Memoria.memoria.get(j);
                    Memoria.memoria.set(j, Memoria.memoria.get(i));
                    Memoria.memoria.set(i, aux);
                }
            }
        }
    }
}
