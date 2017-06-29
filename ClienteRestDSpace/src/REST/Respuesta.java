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
package REST;

/**
 * Almacena una respuesta dada por un método REST
 * @author Ing. Adrián Alvarado Ramírez.
 */
public class Respuesta {

    /**
     * @return El código de respuesta
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo El código de respuesta
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return El contenido de la respuesta
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido El contenido de la respuesta.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    private int codigo;
    private String contenido;
    
}
