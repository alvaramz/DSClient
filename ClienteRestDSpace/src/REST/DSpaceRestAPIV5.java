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

import entidades.Item;
import entidades.Usuario;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Proporciona la implementación del API REST para conectarse con DSpace versión
 * 5.x
 *
 * @author Ing. Adrián Alvarado Ramírez.
 */
public class DSpaceRestAPIV5 implements IDSpaceRestAPI {

    /**
     * Proporciona acceso a los métodos REST.
     */
    private final Metodos met;

    /**
     * Ruta base del API REST de DSpace con el que se va a interactuar.
     */
    private final String rutaBaseREST;

    public DSpaceRestAPIV5(String pRutaBAseREST) {
        met = new Metodos();
        rutaBaseREST = pRutaBAseREST;
    }

    /**
     * Returns string "REST api is running", for testing that the API is up.
     *
     * Example Request: curl http://localhost:8080/rest/test
     *
     * Example Response: REST api is running.
     *
     * @return Respuesta con el resultado de la operación.
     */
    @Override
    public Respuesta test() {
        Respuesta res = null;

        try {
            URL urlTest = new URL(rutaBaseREST + "/test");
            return met.get(urlTest, null);
        } catch (MalformedURLException mfe) {
            System.err.printf("Error al ejecutar la operación test, detalle:\n%s\n", mfe.toString());
        }

        return res;
    }

    /**
     * Login to the REST API using a DSpace EPerson (user). It returns a token,
     * that can be used for future authenticated requests (as a value of the
     * rest-dspace-token request header).
     *
     * Example Request:
     *
     * curl -H "Content-Type: application/json" --data
     * '{"email":"admin@dspace.org", "password":"dspace"}'
     * http://localhost:8080/rest/login
     *
     * Example Response:
     *
     * 1febef81-5eb6-4e76-a0ea-a5be245563a5
     *
     * Invalid email/password combinations will receive an HTTP 403 Forbidden.
     *
     * @param usuario El usuario.
     * @return
     */
    @Override
    public Respuesta login(Usuario usuario) {
        Respuesta res = null;

        try {
            URL url = new URL(rutaBaseREST + "/login");

            String data = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", usuario.getNombre(), usuario.getPassword());
            HashMap<String, String> parametros = new HashMap<>();
            parametros.put("", data);

            HashMap<String, String> properties = new HashMap<>();
            properties.put("Content-Type", "application/json");

            res = met.post(url, properties, parametros);
            return res;
        } catch (MalformedURLException mfe) {
            System.err.println(mfe.toString());
        }

        return res;
    }

    /**
     * Receive information about the currently authenticated user token.
     *
     * Example Request:
     *
     * curl -X GET -H "Content-Type: application/json" -H "Accept:
     * application/json" -H "rest-dspace-token:
     * f2f478e2-90f2-4e77-a757-4e838ae94154" http://localhost:8080/rest/status
     *
     * Example Response:
     * {"okay":true,"authenticated":true,"email":"admin@dspace.org","fullname":"DSpace
     * Administrator","token":"f2f478e2-90f2-4e77-a757-4e838ae94154"}
     *
     * @param token El token a verificar.
     * @return Respuesta con el resultado. Si todo está bien, el resultado se
     * retorna en un XML.
     */
    @Override
    public Respuesta status(String token) {
        Respuesta res = null;

        try {
            URL url = new URL(rutaBaseREST + "/status");

            HashMap<String, String> properties = new HashMap<>();
            properties.put("Content-Type", "application/json");
            properties.put("Accept", "application/xml"); // También se puede application/json
            properties.put("rest-dspace-token", token);

            res = met.get(url, properties);
            return res;
        } catch (MalformedURLException mfe) {
            System.err.println(mfe.toString());
        }

        return res;
    }

    /**
     * Obtiene una comunidad dado un id especificado. Si no se especifica el
     * Accept, la retorna en forma de JSON. En esta implementación, se obtiene
     * un XML.
     *
     * @param id
     * @return
     */
    @Override
    public Respuesta obtenerComunidad(int id) {
        Respuesta res = null;

        try {
            URL urlTest = new URL(rutaBaseREST + "/communities/" + id);

            // Se definie el accept para que reciba un XML.
            HashMap<String, String> properties = new HashMap<>();
            properties.put("Accept", "application/xml");

            return met.get(urlTest, properties);
        } catch (MalformedURLException mfe) {
            System.err.printf("Error al ejecutar la operación obtenerComunidad, detalle:\n%s\n", mfe.toString());
        }

        return res;
    }

    @Override
    public Respuesta obtenerColeccion(int id) {
        Respuesta res = null;

        try {
            URL urlTest = new URL(rutaBaseREST + "/collections/" + id);

            // Se definie el accept para que reciba un XML.
            HashMap<String, String> properties = new HashMap<>();
            properties.put("Accept", "application/xml");

            return met.get(urlTest, properties);
        } catch (MalformedURLException mfe) {
            System.err.printf("Error al ejecutar la operación obtenerColeccion, detalle:\n%s\n", mfe.toString());
        }

        return res;
    }

    @Override
    public Respuesta obtenerItem(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Crea un item en la colección especificada:
     *
     * Ejemplo: curl -v -X POST -H "Content-Type: application/json" -H
     * "rest-dspace-token:289045f9-ff6d-41c5-80b0-010a8119f5c4" --data
     * '{"name":"Prueba","type":"item","expand":["metadata","parentCollection","parentCollectionList","parentCommunityList","bitstreams","all"],"parentCollection":null,"parentCollectionList":null,"parentCommunityList":null,"bitstreams":null,"archived":"true","withdrawn":"false"}'
     * https://localhost:8443/rest/collections/2/items --insecure 
     * 
     * Cuando se crea el item, si todo sale bien (con un 200 OK), se retorna el
     * item (xml) del cual se obtiene el id necesario para introducir los metadatos.
     *
     * @param item El item, con el nombre que se va a utilizar.
     * @param colectionId El identificador de la colección.
     * @param token El token del usuario.
     * @return Respuesta con el resultado, si está correcto, retorna un 200 y el item creado en forma de xml.
     */
    @Override
    public Respuesta crearItem(Item item, int colectionId, String token) {
        Respuesta res = null;

        try {
            URL url = new URL(rutaBaseREST + "/collections/" + colectionId + "/items");

            String data = crearDataCrearItem(item.getNombre());
            HashMap<String, String> parametros = new HashMap<>();
            parametros.put("", data);

            HashMap<String, String> properties = new HashMap<>();
            properties.put("Content-Type", "application/json");
            properties.put("rest-dspace-token", token);

            res = met.post(url, properties, parametros);
            return res;
        } catch (MalformedURLException mfe) {
            System.err.println(mfe.toString());
        }

        return res;
    }

    @Override
    public Respuesta agregarMetadatos(Item item, int itemId, String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Respuesta agregarBitStream(Item item, int itemId, String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Logout from the REST API, by providing a header rest-dspace-token. After
     * being posted this token will no longer work.
     *
     * Example Request:
     *
     * curl -X POST -H "Content-Type: application/json" -H "rest-dspace-token:
     * 1febef81-5eb6-4e76-a0ea-a5be245563a5" http://localhost:8080/rest/logout
     *
     * Invalid token will result in HTTP 400 Invalid Request
     *
     * @param token El token del usuario para el que se va a cerrar la sesión.
     * @return Respuesta con el resultado de la operación.
     */
    @Override
    public Respuesta logout(String token) {
        Respuesta res = null;

        try {
            URL url = new URL(rutaBaseREST + "/logout");

            HashMap<String, String> properties = new HashMap<>();
            properties.put("Content-Type", "application/json");
            properties.put("rest-dspace-token", token);

            res = met.post(url, properties, null);
            return res;
        } catch (MalformedURLException mfe) {
            System.err.println(mfe.toString());
        }

        return res;
    }

    /**
     * Retorna la hilera para el data de crarItem.
     *
     * @param nombre El nombre del item.
     * @return Hilera JSON para el data del item.
     */
    private String crearDataCrearItem(String nombre) {
        String formato = "{\"name\":\"%s\",\"type\":\"item\",\"expand\":[\"metadata\",\"parentCollection\",\"parentCollectionList\",\"parentCommunityList\",\"bitstreams\",\"all\"],\"parentCollection\":null,\"parentCollectionList\":null,\"parentCommunityList\":null,\"bitstreams\":null,\"archived\":\"true\",\"withdrawn\":\"false\"}";
        return String.format(formato, nombre);
    }

}
