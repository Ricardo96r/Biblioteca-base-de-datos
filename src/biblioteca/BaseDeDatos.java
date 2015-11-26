/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.Serializable;

/**
 * Datos de la matriz:
 * idHash / nombre / isbn / fecha / estado / anterior / proximo
 * Maximo 100 filas por overflow
 * @author ricar
 */
class BaseDeDatos implements Serializable {
    public Object datos[][] = new Object[100][7];
    public IndiceNombre indice = new IndiceNombre();
    public Vacio vacio = new Vacio();

    public void agregarDato(String nombre, long isbn, String fecha) {
        int idHash = hashing(isbn);
        boolean estado = true;
        Integer anterior = null;
        Integer proximo = null;
        Integer siguiente = idHash;
        if (this.datos[idHash][0] != null) {
            idHash = vacio.getVacio();
            while(siguiente != null) {
                Integer ant = (Integer) this.datos[siguiente][0];
                siguiente = (Integer) this.datos[siguiente][6];
                if (siguiente == null) {
                    this.datos[ant][6] = idHash;
                    anterior = ant;
                }
            }
        }
        agregarDatoMatriz(idHash, nombre, isbn, fecha, estado, anterior, proximo);
        this.indice.agregarIndice(idHash, nombre);
    }
    
    public void eliminarDato(Object[] dato) {
        int idHash = (int)dato[0];
        if (idHash <= 10) {
            Integer siguiente = (Integer) dato[6];
            this.indice.eliminarIndice((String) dato[1]);
            System.out.println((String) dato[1]);
            if (siguiente != null) {
                this.vacio.setVacio((int) this.datos[siguiente][0]);
                this.datos[idHash][0] = idHash;
                this.datos[idHash][1] = this.datos[siguiente][1];
                this.datos[idHash][2] = this.datos[siguiente][2];
                this.datos[idHash][3] = this.datos[siguiente][3];
                this.datos[idHash][4] = this.datos[siguiente][4];
                this.datos[idHash][5] = null;
                this.datos[idHash][6] = this.datos[siguiente][6];
                Integer siguiente2 = (Integer) this.datos[idHash][6];
                if (siguiente2 != null) {
                    this.datos[siguiente2][5] = idHash;
                 }
                for (int i = 0; i < 7; i++) {
                    this.datos[siguiente][i] = null;
                }
                this.indice.cambiarHash((String) this.datos[idHash][1], idHash);
            } else {
                for (int i = 0; i < 7; i++) {
                    this.datos[idHash][i] = null;
                }
            }
        } else {
            Integer anterior = (Integer) this.datos[idHash][5];
            Integer siguiente = (Integer) this.datos[idHash][6];
            if (anterior != null) {
                this.datos[anterior][6] = this.datos[idHash][6];
            }
            if (siguiente != null) {
                this.datos[siguiente][5] = this.datos[idHash][5];
            }
            this.indice.eliminarIndice((String) dato[1]);
            for (int i = 0; i < 7; i++) {
                this.datos[idHash][i] = null;   
            }
            vacio.setVacio(idHash);
        }
    }
    
    public Object[] buscarNombre(String nombre) {
        Integer busqueda = this.indice.buscarIndice(nombre);
        if (busqueda == null) {
            return null;
        }
        return this.datos[this.indice.buscarIndice(nombre)];
    }
    
    public Object[] buscarIsbn(Long isbn) {
        Integer siguiente = hashing(isbn);
        boolean encontrado = false;
        while(!encontrado) {
            try {
                if (siguiente != null) {
                    if (!this.datos[siguiente][2].equals(isbn)) {
                        siguiente = (Integer) this.datos[siguiente][6];
                    } else {
                        encontrado = true;
                    }
                } else {
                    encontrado = true;
                }
            } catch(Exception e) {
                siguiente = null;
                encontrado = true;
            }
        }
        if (siguiente == null) {
            return null;
        }
        return this.datos[siguiente];
    }
    
    public void agregarDatoMatriz(int idHash, String nombre, long isbn, String fecha, boolean estado, Integer anterior, Integer proximo) {
        this.datos[idHash][0] = idHash;
        this.datos[idHash][1] = nombre;
        this.datos[idHash][2] = isbn;
        this.datos[idHash][3] = fecha;
        this.datos[idHash][4] = estado;
        this.datos[idHash][5] = anterior;
        this.datos[idHash][6] = proximo;
    }    
    
    public int hashing(Long isbn) {
        return (int)(isbn%11);
    }
    
    public Integer[] getIdHashes() {
        Integer hashes[] = new Integer[100];
        for (int i = 0; i < 100; i++) {
            hashes[i] = (Integer) this.datos[i][0];
        }
        return hashes;
    }
    
    public String[] getNombres() {
        String nombres[] = new String[100];
        for (int i = 0; i < 100; i++) {
            nombres[i] = (String) this.datos[i][1];
        }
        return nombres;
    }
        
    public Long[] getIsbns() {
        Long isbns[] = new Long[100];
        for (int i = 0; i < 100; i++) {
            isbns[i] = (Long) this.datos[i][2];
        }
        return isbns;
    }
        
    public String[] getFechas() {
        String fechas[] = new String[100];
        for (int i = 0; i < 100; i++) {
            fechas[i] = (String) this.datos[i][3];
        }
        return fechas;
    }
    
    public Boolean[] getEstados() {
        Boolean estados[] = new Boolean[100];
        for (int i = 0; i < 100; i++) {
            estados[i] = (Boolean) this.datos[i][4];
        }
        return estados;
    }
    
    public Integer[] getAnteriores() {
        Integer anteriores[] = new Integer[100];
        for (int i = 0; i < 100; i++) {
            anteriores[i] = (Integer) this.datos[i][5];
        }
        return anteriores;
    }
    
    public Integer[] getProximos() {
        Integer anteriores[] = new Integer[100];
        for (int i = 0; i < 100; i++) {
            anteriores[i] = (Integer) this.datos[i][6];
        }
        return anteriores;
    }

    public void setDatos(Object[][] datos) {
        this.datos = datos;
    }

    public IndiceNombre getIndice() {
        return indice;
    }

    public void setIndice(IndiceNombre indice) {
        this.indice = indice;
    }

    public Vacio getVacio() {
        return vacio;
    }

    public void setVacio(Vacio vacio) {
        this.vacio = vacio;
    }
    
    
}
