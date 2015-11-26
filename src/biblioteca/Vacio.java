/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author ricar
 */
class Vacio implements Serializable {
    Integer vacios[] = new Integer[89];
    int ultimo = 0;
    
    public void crearVacios() {
        for (int i = 0; i < vacios.length; i++) {
            vacios[i] = i + 11;
            ultimo++;
        }
    }
    
    public Integer getVacio() {
        Integer dato = vacios[0];
        mover();
        return dato;
    }
    
    private void mover() {
        this.vacios = Arrays.copyOfRange(vacios, 1, 90);
        ultimo--;
    }
    
    public Integer[] getVacios() {
        Integer disponibles[] = new Integer[90];
        for (int i = 0; i < this.vacios.length; i++) {
            disponibles[i] = this.vacios[i];
        }
        return disponibles;
    }
    
    public void ordenarVacios() {
        Integer aux;
        for (int i = 0; i < 99; i++) {
            int menor = i;
            if (this.vacios[menor] == null) {
                break;
            }
            for (int j = i+1; j < 99; j++) {
                if(this.vacios[j] != null) {
                    if (this.vacios[menor] > this.vacios[j]) {
                        menor = j;
                    }
                } else {
                    break;
                }
            }
            aux = this.vacios[i];
            this.vacios[i] = this.vacios[menor];
            this.vacios[menor] = aux;
        }
    }
    
    public void setVacio(int num) {
        if (num > 10) {
            this.vacios[ultimo] = num;
            ultimo++;
            ordenarVacios();
        }
    } 
}
