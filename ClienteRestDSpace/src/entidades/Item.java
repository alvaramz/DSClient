/*
 * Copyright (c) 2017 Ing. Adrián Alvarado Ramírez

 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package entidades;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa un item de una colección.
 * @author Ing. Adrián Alvarado Ramírez.
 */
public class Item {

    /**
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Los metadatos.
     */
    public ArrayList<HashMap<String,String>> getMetadatos() {
        return metadatos;
    }

    /**
     * @param metadatos Los metadatos.
     */
    public void setMetadatos(ArrayList<HashMap<String,String>> metadatos) {
        this.metadatos = metadatos;
    }

    /**
     * @return El bitstream
     */
    public byte[] getBitstream() {
        return bitstream;
    }

    /**
     * @param bitstream El bitstream.
     */
    public void setBitstream(byte[] bitstream) {
        this.bitstream = bitstream;
    }
    
    private String nombre;  
    private ArrayList<HashMap<String,String>> metadatos;
    private byte[] bitstream;
    
}
