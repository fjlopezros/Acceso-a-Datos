# Acceso de Datos

## Descripción
Este repositorio contiene prácticas y proyectos relacionados con la asignatura de **Acceso a Datos**. Se incluyen ejercicios sobre diferentes tecnologías y métodos de acceso y manipulación de datos en aplicaciones.

## Estructura del Proyecto
El repositorio está dividido en los siguientes módulos:

### Módulo: tema1y2
#### Tema 1
En este tema se trabaja con el manejo de ficheros, serialización de objetos y procesamiento de datos en XML mediante diferentes técnicas como JDOM, DOM y SAX. Se exploran diversas formas de almacenamiento y recuperación de datos en ficheros y XML, aplicando diferentes estrategias para manipular la información.

- **AgendaTelefonica**: Práctica de serialización de objetos y CRUD (sin DAO pero con estructura similar).
- **EjercicioCorregido**: Lectura de ficheros de texto, serialización, ordenación por edad y lanzamiento de una excepción personalizada.
- **LecturaJDOM**: Ejercicio de lectura de archivos XML con JDOM.
- **EscrituraJDOM**: Ejercicio de escritura de archivos XML con JDOM.
- **LecturaEscritura_XML_DOM**: Manejo de archivos XML con DOM.
- **LecturaXMLconSAX**: Procesamiento de archivos XML con SAX.
- **SerializarJDOM**: Serialización de datos utilizando JDOM.
- **XML_XSL**: Transformador XML a HTML utilizando XSL.
- **ExcepciónConPruebas**: Implementación de una excepción personalizada con pruebas.
- **Repaso**: Práctica de consolidación basada en el ejercicio corregido.

#### Tema 2
En este tema se trabaja con bases de datos relacionales, explorando la conexión a bases de datos, el uso de DAO y ORM, así como el manejo de metadatos y sentencias SQL. Se realizan pruebas con diferentes técnicas de consulta y manipulación de datos, además de procedimientos almacenados.

- **ConectBBDD**: Ejemplo de conexión a una base de datos con DAO y ORM, incluyendo un `main` para probarlo.
- **Pruebas con DatabaseMetadata**: Ejercicio para explorar la información de la base de datos mediante `DatabaseMetadata`.
- **RecorriendoResultSet**: Práctica para recorrer los resultados de una consulta SQL.
- **ResultSetMetadata**: Ejercicio para analizar la estructura de los datos obtenidos en un `ResultSet`.
- **Sentencias**: Pruebas con procedimientos almacenados y diferentes tipos de sentencias SQL.
- **Repaso**: Aplicación de los conceptos trabajados en este tema mediante una práctica integradora.

### Módulo: tema3
En este tema se realiza una práctica con Hibernate, implementando una estructura **MVC** para gestionar jugadores de un equipo de baloncesto almacenados en una base de datos. Se usa Hibernate como ORM para mapear los objetos a la base de datos.

- **Estructura MVC**:
  - **Modelo**: Contiene las clases ORM que representan los objetos persistentes en la base de datos.
  - **Vista**: Interfaz gráfica con botones para navegar entre los jugadores.
  - **Controlador**: Maneja los eventos de la vista y gestiona las consultas a la base de datos.
  - **Util**: Contiene la configuración de la sesión de Hibernate.
  
- **Main**: Punto de entrada del programa, que inicia una sesión de Hibernate, permite elegir entre diferentes ejercicios y ejecuta consultas en la base de datos:
  - **Ejercicio 5**: Busca un jugador por su código y muestra su información.
  - **Ejercicio 6**: Lista todos los equipos y sus jugadores.
  - **Ejercicio 7**: Permite ingresar estadísticas a un jugador existente.
  - **Ejercicio 8**: Inicia una aplicación con interfaz gráfica para consultar y navegar entre los jugadores utilizando el controlador y la vista.

### Módulo: tema4
En este tema se trabaja con **Neodatis**, una base de datos orientada a objetos. Se implementan ejercicios prácticos para comprender el almacenamiento y recuperación de objetos en esta base de datos.

- **Ejercicio 10**: Inserción de objetos en una base de datos Neodatis (mejorado para incluir más parámetros de entrada y opciones de filtrado).
- **Ejercicio 11**: Consulta de objetos almacenados en Neodatis (ampliado para permitir diferentes criterios de búsqueda y agrupación).
- **Ejercicio 12**: Eliminación de objetos en la base de datos Neodatis.
- **Ejercicio 13**: Visualización de jugadores de un país determinado, agrupándolos por ciudad y calculando la media de edad.
