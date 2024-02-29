package org.example.ej2examenpspt3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Random;
import java.util.Scanner;


// Clase principal que inicia la aplicación y maneja la interacción con el usuario.
public class Main {

    // Método principal que inicia la aplicación.
    public static void main(String[] args) {
        // Método para deshabilitar la validación de certificados SSL.
        disableCertificateValidation();

        // Lista observable para almacenar información sobre las URLs introducidas por el usuario.
        ObservableList<UrlInfo> urlList = FXCollections.observableArrayList();

        // Instancia del observador para descargar y comprimir ficheros.
        DownloaderAndZipper downloaderAndZipper = new DownloaderAndZipper(urlList);

        // Agregar el observador a la lista observable.
        urlList.addListener(downloaderAndZipper);

        // Scanner para leer la entrada del usuario desde la consola.
        Scanner sc = new Scanner(System.in);

        // Ciclo para solicitar URLs al usuario hasta que escriba "exit".
        while (true) {
            System.out.print("Introduce una URL (o 'exit' para salir): ");
            String url = sc.nextLine();

            // Salir del ciclo si el usuario escribe "exit".
            if ("exit".equalsIgnoreCase(url)) {
                break;
            }

            // Agregar la URL y un texto único a la lista observable.
            urlList.add(new UrlInfo(url, generarTexto()));
        }

        // Cerrar el Scanner después de utilizarlo.
        sc.close();
    }

    // Método para generar un texto único para cada URL introducida por el usuario.
    private static String generarTexto() {
        Random random = new Random();
        StringBuilder texto = new StringBuilder();
        // Generar un texto de 20 caracteres aleatorios utilizando letras minúsculas.
        for (int i = 0; i < 20; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            texto.append(randomChar);
        }
        return texto.toString();
    }

    // Método para deshabilitar la validación de certificados SSL.
    private static void disableCertificateValidation() {
        try {
            // Crear una confianza de todos los certificados.
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Configurar la conexión SSL para confiar en todos los certificados.
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Configurar el verificador de nombres de host para aceptar todos los nombres de host.
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
