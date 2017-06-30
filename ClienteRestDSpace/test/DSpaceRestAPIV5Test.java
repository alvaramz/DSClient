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

import REST.DSpaceRestAPIV5;
import REST.Respuesta;
import entidades.Item;
import entidades.Usuario;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Métodos para probar la funcionalidad de la clase DSpaceRestAPIV5.java
 *
 * @author Ing. Adrián Alvarado Ramírez.
 */
public class DSpaceRestAPIV5Test {

    private final static String RUTA_BASE = "http://localhost:8080/rest";
    private final static DSpaceRestAPIV5 rest = new DSpaceRestAPIV5(RUTA_BASE);
    private static String token = "";
    
    public static void main(String args[]) {

        testTest();

        loginTest();

        statusTest();

        obtenerComunidadTest();

        obtenerColeccionTest();
        
        //crearItem();
        // Se usa el id 1 de item para el resto de pruebas.
        System.out.println("--------------Probando agregar metadatos----------");
        Item item = new Item();
        item.setNombre("Item de prueba");
        LinkedHashMap<String, String> tituloEspanol = new LinkedHashMap<String,String>();
        tituloEspanol.put("key", "dc.title");
        tituloEspanol.put("language", "es");
        tituloEspanol.put("value",  new String("Título en español".getBytes(), Charset.availableCharsets().get("ISO-8859-1")));
        
        LinkedHashMap<String, String> tituloIngles = new LinkedHashMap<String,String>();
        tituloIngles.put("key", "dc.title");
        tituloIngles.put("language", "en");
        tituloIngles.put("value", "Title in english");
        
        item.setMetadatos(new ArrayList<LinkedHashMap<String, String>>());
        item.getMetadatos().add(tituloEspanol);
        item.getMetadatos().add(tituloIngles);
        
        Respuesta res = rest.agregarMetadatos(item, 1, token);
        imprimirRespuesta(res);
        System.out.println("-------------Fin de agregar metadatos----");

        // Cierra la sesión al finalizar.
        logoutTest();

    }

    private static void crearItem() {
        Item item = new Item();
        item.setNombre("Item de prueba");
        Respuesta res = rest.crearItem(item, 1, token);
        imprimirRespuesta(res);
    }

    private static void obtenerColeccionTest() {
        Respuesta res = rest.obtenerColeccion(1);
        imprimirRespuesta(res);
    }

    private static void obtenerComunidadTest() {
        Respuesta res = rest.obtenerComunidad(1);
        imprimirRespuesta(res);
    }

    private static void logoutTest() {
        Respuesta res = rest.logout(token);
        imprimirRespuesta(res);
        res = rest.status(token);
        imprimirRespuesta(res);
    }

    private static void statusTest() {
        Respuesta res = rest.status(token);
        imprimirRespuesta(res);
        res = rest.status("");
        imprimirRespuesta(res);
    }

    private static void loginTest() {
        Usuario usuario = new Usuario("admin@gmail.com", "admin");
        Respuesta res = rest.login(usuario);
        imprimirRespuesta(res);

        if (res != null && res.getCodigo() == 200) {
            token = res.getContenido();
        }

    }

    private static void testTest() {
        Respuesta res = rest.test();
        imprimirRespuesta(res);
    }

    private static void imprimirRespuesta(Respuesta res) {
        if (res != null) {
            System.out.printf("El código retornado es: [%d]\nEl contenido es:\n%s\n", res.getCodigo(), res.getContenido());
        } else {
            System.err.print("El sistema retornó una respuesta null");
        }
    }

}
