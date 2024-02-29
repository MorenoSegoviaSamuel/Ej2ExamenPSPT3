package org.example.ej2examenpspt3;


import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// Clase que actúa como observador de cambios en la lista de URLs y maneja la descarga y compresión de archivos.
class DownloaderAndZipper implements ListChangeListener<UrlInfo> {

    // Lista observable de URLs.
    private ObservableList<UrlInfo> urlList;
    // Contador para realizar un seguimiento del número de descargas completadas.
    private int count = 0;

    // Constructor que recibe la lista observable de URLs.
    public DownloaderAndZipper(ObservableList<UrlInfo> urlList) {
        this.urlList = urlList;
    }

    // Método llamado cuando se detectan cambios en la lista de URLs.
    @Override
    public void onChanged(Change<? extends UrlInfo> change) {
        // Iterar sobre los cambios detectados.
        while (change.next()) {
            // Verificar si se han añadido elementos a la lista.
            if (change.wasAdded()) {
                // Iterar sobre los elementos añadidos.
                for (UrlInfo addedUrlInfo : change.getAddedSubList()) {
                    // Obtener la URL y el texto único asociado al elemento añadido.
                    String url = addedUrlInfo.getUrl();
                    String uniqueString = addedUrlInfo.getCadenaTexto();

                    // Comprobar si la URL está vacía.
                    if (url.isEmpty()) {
                        // Informar que se procederá a descargar y comprimir los ficheros.
                        System.out.println("Se va a proceder a descargar y comprimir los ficheros");
                        // Iniciar el proceso de compresión de archivos.
                        CompletableFuture.runAsync(() -> {
                            try {
                                zipFiles();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        // Informar que se ha encolado la URL con su texto único asociado.
                        System.out.println(url + " encolado como " + uniqueString);
                        // Iniciar el proceso de descarga del archivo asociado a la URL.
                        CompletableFuture.runAsync(() -> {
                            try {
                                downloadFile(url, uniqueString);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            }
        }
    }

    // Método para descargar un archivo desde una URL y guardarlo localmente.
    private void downloadFile(String urlString, String fileName) throws IOException {
        URL url = new URL(urlString);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName));
            System.out.println("Descarga completada: " + fileName);
            count++;
            // Comprobar si se han completado todas las descargas.
            if (count == urlList.size() - 1) { // Se resta 1 para excluir la URL vacía
                System.out.println("Todas las descargas completadas.");
                // Iniciar el proceso de compresión de archivos.
                zipFiles();
            }
        }
    }

    // Método para comprimir los archivos descargados en un archivo ZIP.
    private void zipFiles() throws IOException {
        String zipFileName = "EnlacesComprimidos.zip";
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            // Iterar sobre la lista de URLs y agregar los archivos correspondientes al archivo ZIP.
            for (UrlInfo urlInfo : urlList) {
                String fileName = urlInfo.getCadenaTexto();
                File fileToZip = new File(fileName);
                // Verificar si el archivo existe antes de agregarlo al ZIP.
                if (!fileToZip.exists()) {
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    out.putNextEntry(zipEntry);
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        out.write(bytes, 0, length);
                    }
                }
            }
            // Informar la finalización del proceso de compresión.
            System.out.println("Archivos comprimidos en: " + zipFileName);
        }
    }
}
