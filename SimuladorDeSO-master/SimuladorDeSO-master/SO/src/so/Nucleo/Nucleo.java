/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.Nucleo;

import so.Biblioteca.Processo;
import so.Memoria.Memoria;

/**
 *
 * @author Bruno
 */
public class Nucleo {
    static int linha = 0;
    public boolean Alocar(Processo processo){ //Metodo Alocar processo
        if (Memoria.memoria.size()>=2048){ // verifica se a memoria esta cheia
            return false; // finaliza
        }
        Memoria.memoria.add(processo); // adiciona o processo na memoria
        return true; // finaliza
    }
    public boolean Desalocar(int index){ // Metodo desalocar 
        Memoria.memoria.remove(index); //remove o processo
        return true; //finaliza
    }
   
    public void calcularQuantum(){ //metodo calcular quantum
        if(Memoria.memoria.get(linha).finalizado == true){ //verifica se o processo ja esta finalizado
            linha++; //caso esteja acrescenta a posicao  
        }
        else if (Memoria.memoria.get(linha).quantum == 80){ //verifica se o quantum ja esta em 80%
            Memoria.memoria.get(linha).quantum +=20; //caso esteja soma mais um quantum 
            Memoria.memoria.get(linha).finalizado = true; //finaliza o processo com finalização recebendo verdadeira
            
            linha++; //soma posicao para ir para o proximo
            if (linha >= Memoria.memoria.size()){ //verifica se chegou no final e volta
                linha = 0;
                return;
            }
            if(Memoria.memoria.get(linha).prioridade == Memoria.memoria.get(linha-1).prioridade){ //verifica se a prioridade do processo é a mesma do processo anterior para pegar o tanto de quanto que falta
                 Memoria.memoria.get(linha).contadorQuantum = Memoria.memoria.get(linha-1).contadorQuantum -1; //contador recebe o resto de quantum da posicao anteriore
            }
            if (linha == Memoria.memoria.size()){ //verifica se a posicao é do tamanho da lista
                    linha = 0; //se for a posicao recebe 0
            }
           
        }
        else if (Memoria.memoria.get(linha).finalizado == false){ //se o processo não estiver finalizado
            Memoria.memoria.get(linha).quantum += 20; //acrescenta quantum no processo
            Memoria.memoria.get(linha).contadorQuantum--; //decrementa o valor do contador de quantum
            
            if (Memoria.memoria.get(linha).contadorQuantum == 0){ //verifica se o contador é igual a 0
                if (Memoria.memoria.get(linha).prioridade== 3){ // e verifica a prioridade
                    Memoria.memoria.get(linha).contadorQuantum = 4; // prioridade alta o contador de quantum recebe a quantidade de quanto de acordo com a prioridade
                }else if (Memoria.memoria.get(linha).prioridade == 2){
                    Memoria.memoria.get(linha).contadorQuantum = 3;
                }else if (Memoria.memoria.get(linha).prioridade == 1){
                    Memoria.memoria.get(linha).contadorQuantum = 2;
                }  
                linha++; //acrescenta a posicao para não comparar com a posicao Nula
                
                if (linha >= Memoria.memoria.size()){ //verifica se é o tamanho da lista volta
                    linha = 0;
                    return;
                }
                while(true){ // busca por prioridade diferente
                    if(Memoria.memoria.get(linha).prioridade == Memoria.memoria.get(linha-1).prioridade){ //verifica se a prioridade é igual a prioridade do processo anterior
                        linha++; //acrecenta a posicao
                        if(linha == Memoria.memoria.size()){ //se a posicao for do tamanho da lista de processo
                            linha = 0; //a posicao recebe 0
                            break; //para o busca
                        }
                    }else{ //se as prioridades forem diferentes 
                        break; //so para a busca
                    }
                }
            }
        }
    }
}
