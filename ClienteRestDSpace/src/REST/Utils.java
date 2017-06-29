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

import java.util.HashMap;

/**
 * Proporciona funciones utilitarias para los métodos REST.
 *
 * @author Ing. Adrián Alvarado Ramírez
 */
public class Utils {

    /**
     * construye una hilera de parámetros para ser enviada en un request HTTP.
     * @param parametros HasMap con los parámetros para construir la hilera.
     * @return La hilera de parámetros construída.
     */
    public String crearHileraParametros(HashMap<String, String> parametros) {
        if (parametros != null && parametros.size() >= 1) {
            StringBuilder parametrosBuilder = new StringBuilder();
            boolean primero = true;

            for (String key : parametros.keySet()) {
                if (primero) {
                    primero = false;
                } else {
                    parametrosBuilder.append("&");
                }

                // Si el key es "", no se agrega
                if (!key.isEmpty()) {
                    parametrosBuilder.append(key);
                    parametrosBuilder.append("=");
                }

                parametrosBuilder.append(parametros.get(key));
            }

            return parametrosBuilder.toString();
        } else {
            return null;
        }
    }

}
