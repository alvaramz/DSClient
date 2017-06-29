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

/**
 * Almacena la información del usuario en memoria.
 * @author Ing. Adrián Alvarado Ramírez.
 */
public class Usuario {

    /**
     * @return El nombre de usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre de usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return La contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password La contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Constructor de la clase.
     * @param pNombre El nombre de usuario.
     * @param pPassword La contraseña.
     */
    public Usuario(String pNombre, String pPassword){
        nombre = pNombre;
        password = pPassword;
    }
    
    private String nombre;
    private String password;
    
}
