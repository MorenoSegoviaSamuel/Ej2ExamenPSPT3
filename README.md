# Aplicación de Descarga y Compresión de Archivos desde URLs
Esta aplicación Java permite al usuario introducir URLs, descargar los archivos correspondientes y comprimirlos en un archivo ZIP. Proporciona una forma sencilla de manejar múltiples descargas y organizar los archivos resultantes en un solo archivo comprimido.

Instrucciones de Uso:

Ejecución de la Aplicación:
Compila y ejecuta la clase Main en tu entorno de desarrollo Java.
La aplicación solicitará al usuario que ingrese URLs una por una.
Ingresa las URLs que deseas descargar. Escribe 'exit' para salir cuando hayas terminado.

Descarga y Compresión:
Cada URL ingresada será encolada para su descarga.
Una vez que todas las URLs se han introducido y encolado para descargar, la aplicación procederá a descargar los archivos.
Después de completar la descarga de todos los archivos, se comprimirán en un archivo ZIP llamado EnlacesComprimidos.zip.

Consideraciones:
La aplicación deshabilita la validación de certificados SSL para permitir la descarga de archivos desde cualquier URL sin restricciones de certificados.
Cada URL introducida generará un texto único asociado que servirá como nombre de archivo para la descarga y también como identificador en el archivo ZIP.
La aplicación utiliza JavaFX para manejar la interfaz de usuario y la lista observable para rastrear las URLs introducidas.
Los archivos descargados se guardarán localmente en el directorio de trabajo actual.
Es importante verificar la conexión a Internet y la disponibilidad de las URLs introducidas para garantizar el éxito de la descarga.

Requisitos:
Java Development Kit (JDK)
JavaFX (incluido en JDK 8u60 y versiones posteriores)
Un entorno de desarrollo Java compatible (por ejemplo, IntelliJ IDEA, Eclipse)

Notas Adicionales:
Esta aplicación proporciona una manera conveniente de gestionar y organizar la descarga de archivos desde múltiples URLs y comprimirlos en un solo archivo ZIP.
Asegúrate de manejar adecuadamente las excepciones que puedan surgir durante la descarga y la compresión de archivos para garantizar un funcionamiento suave de la aplicación.
