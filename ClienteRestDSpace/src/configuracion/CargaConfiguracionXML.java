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
package configuracion;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * Carga la configuración correspondiente de un archivo XML.
 *
 * NOTA: En esta versión el archivo contiene la contraseña sin encriptar. Para
 * más información sobre encriptarla, ver:
 * https://stackoverflow.com/questions/1132567/encrypt-password-in-configuration-files
 *
 * @author Ing. Adrián Alvarado Ramírez.
 */
public class CargaConfiguracionXML {

    /**
     * @return El usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario El usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private final String NOMBRE_ARCHIVO = "conf.xml";
    
    /**
     * Carga el archivo de configuración con el nombre NOMBRE_ARCHIVO. Este
     * nombre debe ser una ruta absoluta o relativa.
     * @return La información del usuario.
     */
    public void cargarConfiguracion() {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (archivo.exists() && archivo.canRead()) {
            try {
                Document documento = parse(archivo);
                
                String nombreUsuario = obtenerTextoNodo(documento,"//conf/user");
                String password = obtenerTextoNodo(documento, "//conf/password");
                
                setUsuario(new Usuario(nombreUsuario,password));               
                

            } catch (DocumentException de) {
                System.err.println("Error al parsear el archivo. Detalles :\n");
                System.err.println(de.toString());
            }
        } else {
            System.err.println("El archivo de configuración no existe o no puede ser leído");
        }
        
        
    }

    /**
     * Carga el documento y genera una representación en memoria.
     *
     * @param archivo La representación de los metadatos del archivo.
     * @return La representación del archivo en memoria, como un objeto
     * org.dom4j.Document.
     * @throws DocumentException Si ocurre un error durante la carga.
     */
    private Document parse(File archivo) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(archivo);
        return document;
    }

    /**
     * Obtiene el valor de un nodo, utilizando XPath.
     * @param path La regla XPath.
     * @return El valor del noto
     */
    private String obtenerTextoNodo(Document documento, String path) {
        Node nodo = documento.selectSingleNode(path);
        String texto = "";
        
        if(nodo != null){
            texto = nodo.getText();
        }else{
            System.err.printf("El nodo '%s' retornó un valor null", path);
        }
        
        return texto;
    }
    
    private Usuario usuario;

}
