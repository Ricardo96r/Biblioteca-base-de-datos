/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.Serializable;

/**
 *
 * @author ricar
 */
public class IndiceNombre implements Serializable {
    public Object indice[][] = new Object[100][2];
    public int ultimo = 0;
    
    public void agregarIndice(int idHash, String nom) {
        System.out.println(ultimo);
        agregarIndiceMatriz(ultimo, idHash, nom);
        ultimo++;
        ordenarIndice();
    }
    
    private void agregarIndiceMatriz(int idNum, int idHash, String nom) {
        indice[idNum][0] = idHash;
        indice[idNum][1] = nom;
    }
    
    private void ordenarIndice() {
        Object aux[];
        int menor;
        for(int k=0;k<100;k++) {
            if (this.indice[k][0] == null) {
                break;
            }
            menor = k;
            for(int f = k+1; f < 100; f++) {
                if (this.indice[f][0] == null) {
                    break;
                }
                if (this.indice[f][1].toString().compareToIgnoreCase(this.indice[menor][1].toString())<0) {
                    menor = f;
                }
            }
            aux=this.indice[k];
            this.indice[k]=this.indice[menor];
            this.indice[menor]=aux;
        }
    }
    
    public Integer buscarIndice(String nombre) {
        int inicio = 0;
        int fin = ultimo - 1;
        int pos;
        while(inicio <= fin){
            pos = (inicio+fin) / 2;
            if(this.indice[pos][1].toString().compareToIgnoreCase(nombre)==0) {
                return (Integer) this.indice[pos][0];
            }
            else if(this.indice[pos][1].toString().compareToIgnoreCase(nombre)<0){
                inicio = pos+1;
            } else {
                fin = pos-1;
            }
        }
    return null;
    }
    
    public Integer buscarPosicion(String nombre) {
        int inicio = 0;
        int fin = ultimo - 1;
        int pos;
        while(inicio <= fin){
            pos = (inicio+fin) / 2;
            if(this.indice[pos][1].toString().compareToIgnoreCase(nombre)==0) {
                return pos;
            }
            else if(this.indice[pos][1].toString().compareToIgnoreCase(nombre)<0){
                inicio = pos+1;
            } else {
                fin = pos-1;
            }
        }
    return null;
    }
    
    public void cambiarHash(String nombre, int idHash) {
        Integer busqueda = buscarPosicion(nombre);
        if (busqueda != null) {
            this.indice[busqueda][0] = idHash;
        }
    }
    
    public void mover(int desde) {
        while(desde < ultimo) {
            this.indice[desde] = this.indice[desde+1];
            desde++;
        }
        for (int i = 0; i < 2; i++) {
            this.indice[ultimo][i] = null;
        }
    }
    
    public void eliminarIndice(String nombre) {
        Integer dato = buscarPosicion(nombre);
            for (int i = 0; i < 2; i++) {
                this.indice[dato][i] = null;
            }
            mover(dato);
            ultimo--;
    }
    
    public Integer[] getIdHashes() {
        Integer hashes[] = new Integer[100];
        for (int i = 0; i < 100; i++) {
            hashes[i] = (Integer) this.indice[i][0];
        }
        return hashes;
    }
    
    public String[] getNombres() {
        String nombres[] = new String[100];
        for (int i = 0; i < 100; i++) {
            nombres[i] = (String) this.indice[i][1];
        }
        return nombres;
    }
    
}
