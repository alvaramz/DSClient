# Como referencia para agregar el bitstream


```
 public Respuesta agregarBitSteam(Item item, String itemId, String token) {
        Respuesta respuesta = null;

        try {
            URL url = new URL(configuracion.getURLrest() + "/items/" + itemId + "/bitstreams?name=" + codificar(item.getNombre()));
            
            LinkedHashMap<String, String> parametros = new LinkedHashMap<>();
           
            LinkedHashMap<String, String> properties = new LinkedHashMap<>();
            properties.put("Content-Type", "multipart/form-data");
            properties.put("Accept", "application/json");
            properties.put("rest-dspace-token", token);

            String respuestaServidor = httpAbstraction.postOperation(url, null, properties,item.getBitstream());

            Pattern p = Pattern.compile("\\[[0-9]*\\]");
            if (p.matcher(respuestaServidor).matches()) {
                respuesta = new Respuesta("Error al agregar el bitstream " + item.getNombre(), false);
                System.err.printf("Error al agregar el bitstream, código de error %s", respuestaServidor);
            } else {
                respuesta = new Respuesta("Bitstream agregado correctamente", true);
            }

            return respuesta;
        } catch (MalformedURLException mfe) {
            System.err.println(mfe.toString());
        }

        return respuesta;       
    }
    
    /**
     * Elimina los espacios en blanco. El charset probado es:  charset = "ISO-8859-1";
     * @param nombre
     * @return 
     */
    private String codificar(String nombre){
        try {
            return URLEncoder.encode(nombre,configuracion.getCharset());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HTTPConector.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    
    
    ```
