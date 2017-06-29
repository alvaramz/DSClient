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

import REST.Metodos;
import REST.Respuesta;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Prueba los métodos REST.
 * @author Ing. Adrián Alvarado Ramírez
 */
public class MetodosTest {
    
    public static void main(String args[]){
        try{
            URL url = new URL("http://localhost:8080/rest/test");
            Metodos metodos = new Metodos();
            Respuesta res = metodos.get(url);
            
            System.out.printf("El código de respuesta es [%d]\nEl contenido es:\n%s\n", res.getCodigo(),res.getContenido());
            
        }catch(MalformedURLException mfe){
            System.err.println(mfe.toString());
        }
        
    }
    
}
