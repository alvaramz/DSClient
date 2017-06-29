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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Implementación de los métodos REST.
 *
 * @author Ing. Adrián Alvarado Ramírez
 */
public class Metodos {

    public Respuesta get(URL pUrl) {
        int codigo = -1;
        String contenido = null;
        StringBuilder buffer = null;
        Respuesta res = null;

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) pUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            codigo = urlConnection.getResponseCode();

            // Se lee la respuesta del servidor
            BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
            buffer = new StringBuilder();
            int caracter;
            while ((caracter = br.read()) != -1) {
                buffer.append((char) caracter);
            }
            contenido = buffer.toString();

            // Se finaliza la conexión.
            urlConnection.disconnect();

            // Se llena el objeto respuesta
            res = new Respuesta();
            res.setCodigo(codigo);
            res.setContenido(contenido);

        } catch (IOException ioe) {
            contenido = "[ERROR] Ocurrió una excepción al intentar establecer la conexión : " + ioe.toString();
            res = new Respuesta();
            res.setCodigo(-1);
            res.setContenido(contenido);
        }

        return res;
    }

    public Respuesta post(URL pagina, HashMap<String, String> properties, HashMap<String, String> parametros) {
        int codigo = -1;
        String contenido = null;
        StringBuilder buffer = null;
        Respuesta res = null;

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) pagina.openConnection();
            urlConnection.setRequestMethod("POST");

            // Agrega las properties del request.
            for (String clave : properties.keySet()) {
                urlConnection.setRequestProperty(clave, properties.get(clave));
            }

            // Crea y asigna la hilera de parámetros
            Utils utils = new Utils();
            String hileraParametros = utils.crearHileraParametros(parametros);
            if (hileraParametros != null) {
                urlConnection.setDoOutput(true);
                DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());
                os.writeBytes(hileraParametros);
                os.flush();
                os.close();
            }

            urlConnection.connect();

            codigo = urlConnection.getResponseCode();

            // Se lee la respuesta
            BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
            buffer = new StringBuilder();
            int caracter;
            while ((caracter = br.read()) != -1) {
                buffer.append((char) caracter);
            }
            contenido = buffer.toString();

            // Se finaliza la conexión.
            urlConnection.disconnect();

            // Se llena el objeto respuesta
            res = new Respuesta();
            res.setCodigo(codigo);
            res.setContenido(contenido);

        } catch (IOException ioe) {
            contenido = "[ERROR] Ocurrió una excepción al intentar establecer la conexión : " + ioe.toString();
            res = new Respuesta();
            res.setCodigo(-1);
            res.setContenido(contenido);
        }

        return res;
    }

}
