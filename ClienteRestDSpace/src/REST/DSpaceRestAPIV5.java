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
 * Proporciona la implementación del API REST para conectarse con DSpace versión 5.x
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
    
    public DSpaceRestAPIV5(String pRutaBAseREST){
        met = new Metodos();
        rutaBaseREST = pRutaBAseREST;
    }

    @Override
    public Respuesta test() {
       Respuesta res = null; 
        
       try{
           URL urlTest = new URL(rutaBaseREST + "/test");
           return met.get(urlTest, null);
       }catch(MalformedURLException mfe){
           System.err.printf("Error al ejecutar la operación test, detalle:\n%s\n", mfe.toString());
       }
       
       return res;
    }

    @Override
    public Respuesta login(Usuario usuario) {
        Respuesta res = null;
        
        try {
            URL url = new URL(rutaBaseREST + "/login");
            
            
            String data = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", usuario.getNombre(), usuario.getPassword());
            HashMap<String, String> parametros = new HashMap<>();
            parametros.put("", data);
            
            HashMap<String,String> properties = new HashMap<>();
            properties.put("Content-Type", "application/json");

            res = met.post(url, properties, parametros);
            return res;
        } catch (MalformedURLException mfe) {
            System.err.println(mfe.toString());
        }
        
        return res;
    }

    @Override
    public Respuesta status(String token) {
         Respuesta res = null;
        
        try {
            URL url = new URL(rutaBaseREST + "/status");
                          
            HashMap<String,String> properties = new HashMap<>();
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

    @Override
    public Respuesta obtenerComunidad(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Respuesta obtenerColeccion(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Respuesta obtenerItem(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Respuesta crearItem(Item item, int colectionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Respuesta agregarMetadatos(Item item, int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Respuesta agregarBitStream(Item item, int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
