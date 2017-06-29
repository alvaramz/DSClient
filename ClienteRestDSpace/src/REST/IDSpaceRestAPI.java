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

/**
 * Interface que define los métodos del API.
 *
 * @author Ing. Adrián Alvarado Ramírez.
 */
public interface IDSpaceRestAPI {

    /**
     * Ejecuta la operación test para verificar si el API está en ejecución.
     *
     * @param rutaBaseREST
     * @return Respuesta con el resultado del request.
     */
    public Respuesta test(String rutaBaseREST);

    /**
     * Elejcuta la operación login.
     *
     * @param usuario
     * @return Respuesta con el resultado de la operación de login.
     */
    public Respuesta login(Usuario usuario);

    /**
     * Ejecuta la operación status, la cuál verifica si un usuario está
     * autenticado, dado un token.
     *
     * @param token El token para verificar si está autenticado.
     * @return Respuesta de la verificación.
     */
    public Respuesta status(String token);

    /**
     * Obtiene una comunidad con el id especificado por parámetro.
     *
     * @param id El identificador de la comunidad.
     * @return Respuesta con el resultado de solicitar la comunidad.
     */
    public Respuesta obtenerComunidad(int id);

    /**
     * Obtiene una colección con el id enviado por parámetro.
     *
     * @param id El identificador de la colección.
     * @return Respuesta con el resultado del request.
     */
    public Respuesta obtenerColeccion(int id);

    /**
     * Obtiene un item con el id especificado por parámetro.
     *
     * @param id El identificador del item.
     * @return Respuesta de la solicitud del item.
     */
    public Respuesta obtenerItem(int id);

    /**
     * Crea un item en una colección especificada por parámetro.
     *
     * @param item El item a agregar a la colección.
     * @param colectionId El identificador de la colección.
     * @return Respuesta de la operación. Si la operación se efectúa sin
     * problemas, el contenido retornado en la respuesta es el id del item.
     */
    public Respuesta crearItem(Item item, int colectionId);

    /**
     * Agrega los metadatos a un item indicado por parámetro.
     *
     * @param item El item, con la información de los metadatos a agregar.
     * @param itemId El identificador del item.
     * @return Respuesta con el resultado de la operación.
     */
    public Respuesta agregarMetadatos(Item item, int itemId);

    /**
     * Agrega un bitstream a un item identificado por parámetro.
     *
     * @param item El item, con el bitstream.
     * @param itemId El identificador del item.
     * @return Respuesta de la operación.
     */
    public Respuesta agregarBitStream(Item item, int itemId);

}
