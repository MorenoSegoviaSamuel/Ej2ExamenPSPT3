package org.example.ej2examenpspt3;

// Clase que representa información sobre una URL y el texto único asociado.
class UrlInfo {
    private String url; // URL introducida por el usuario.
    private String cadenaTexto; // Texto único asociado a la URL.

    // Constructor que inicializa la URL y el texto único.
    public UrlInfo(String url, String cadenaTexto) {
        this.url = url;
        this.cadenaTexto = cadenaTexto;
    }

    // Método para obtener la URL.
    public String getUrl() {
        return url;
    }

    // Método para obtener el texto único asociado a la URL.
    public String getCadenaTexto() {
        return cadenaTexto;
    }
}