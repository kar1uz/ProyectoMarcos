# 📌 ProyectoMarcos
Este proyecto está desarrollado con Springboot en la versión 3.4.5, en donde se está utilizando Maven, Lenguaje de programación Java con JDK 17 y está orientada al Desarrollo de una Página web de venta de servicios de internet.

## ⚙️ Instrucciones 
A continuación se redactara las instrucciones que se siguieron para clonar, contruir y ejecutar el proyecto.

### 💻 Pasos para clonar el Repositorio con GitHub Desktop
1. Instalar GitHub Desktop e iniciar sesión con su cuenta.
2. Si su cuenta aun no contiene repositorios entonces le aparecera al lado derecho varias opciones, entre ellas "Clone Repository" que se usara para clonar el repositorio. En caso tenga un repositorio abierto, entonces en la barra de menu superior le daremos click sobre "File" y posteriormente a "Clone Repository...".
3. Ahora nos aparecera 3 opciones, de las cuales escogeremos "URL". Dentro de esta colocaremos la siguiente dirección: "https://github.com/kar1uz/ProyectoMarcos.git". Seguidamente, escogeremos la ruta donde queremos que se almacene el repositorio a clonar, como sugerencia se puede crear una carpeta en Documentos y llamarla "GitHub", una vez definido estos 2 criterios le damos al botón "Clone".

GitHub Desktop descargará el repositorio a la ubicación que se especificó. Una vez completado, el repositorio estará disponible en la lista de repositorios locales en GitHub Desktop y se podra empezar a trabajar con él.

### 🏠 Pasos para la creación del proyecto
1. Entramos a la página de Spring Initializr y definimos que el proyecto se va a trabajar con Maven, con lenguaje Java y la versión de Spring Boot 3.4.5 ya que era la más estable hasta el momento.
2. Definimos el nombre del paquete en donde desarrollamos el proyecto y se escogió la versión de JDK 17.
3. Se agregó las siguientes dependencias:
    - Spring Data JPA
    - Thymeleaf
    - Validation
    - Spring Web
    - Spring Boot DevTools
    - H2 Database
    - Lombok
    - Spring Security
4. Generamos el proyecto y se descargará un zip.
5. Este zip lo descomprimimos y desde el IDE de preferencia lo abrimos para empezar a trabajar.

### 🚀 Pasos para ejecutar el proyecto
1. Una vez clonado el proyecto, en el GitHub Desktop nos aparecerá varias opciones, entre ellas una que se llama "Open in Visual Studio Code", la cual presionaremos para abrir el proyecto. En caso se desea trabajar con otro IDE como Apache NetBeans, simplemente dentro de este se debe ir a la ruta del repositorio en donde clonamos el proyecto y abrirlo desde la carpeta raiz (en donde se encuentran las carpetas "APF2" y "data" junto con el archivo "README.md").
2. Una vez tengamos el proyecto abierto, nos aseguramos de tener instalado en nuestro ordenador el JDK 17, en caso de usar Visual Studio Code es necesario también tener instalados las extensiones relacionadas a Java, luego de confirmar que tenemos instalado el JDK, esperamos a que se termine de descargar las dependencias y archivos adicionales del proyecto.
3. Posteriormente, nos dirijimos al archivo "DemoApplication.java", el cuál se encuentra en la ruta "APF2\ATF2\src\main\java\com\example\demo", presionamos click derecho sobre este archivo y en caso de que usemos Visual Studio Code le damos a "Run Java", caso contrario de que estemos usando Apache NetBeans le damos a "Run Proyect" que está identificado con este símbolo ▶️ o click derecho sobre el archivo mencionado y presionamos sobre "Run File".
4. Con esto, Spring Boot estará inicializando, seguidamente en el terminal aparecerá un mensaje de Hibernate, la cuál esta relacionado con JPA, finalmente entre las últimas líneas generadas nos aparecerá el puerto a usar que es el 8080. Cabe mencionar que al usar H2 Database como base de datos no es necesario importar un archivo sql desde otro lado, ya que al ejecutar el proyecto ya se estará habilitando de manera simultanea la base de datos.
5. En el navegador de preferencia ingresamos la siguiente dirección: "http://localhost:8080".
6. Tras esto nos aparecerá la pestaña de iniciar sesión, en donde de manera alternativa nos podemos registrar como nuevo usuario. Pero en caso de querer entrar como administrador podemos ingresar las siguientes credenciales:
    * 👨🏻‍💼 admin@tecnonet.com
    * 🔒 admin123
7. Tras iniciar sesión como administrador podemos gestionar los planes que tenemos o deseamos registrar, consultar los usuarios registrados, los contratos que vigentes y los mensaje recibidos.
8. Como usuario podemos contratar los planes que observamos en la plataforma e incluso mandar mensajes a la empresa.
9. Para acceder a la base de datos, en el navegador colocaremos la siguiente dirección: "http://localhost:8080/h2-console". A continuación rellenaremos la tabla que nos aparece de la siguiente manera:

    |      Login      |                                |
    |:---------------:|:------------------------------:|
    |`Saved Settings:`|    `Generic H2 (Embedded)`     |
    |`Setting Name:`  |    `Generic H2 (Embedded)`     |
    |`Driver Class:`  |         org.h2.Driver          |
    |  `JDBC URL:`    | jdbc:h2:file:./data/tecnonetdb |
    |   `User Name:`  |             sa                 |
    |   `Password:`   |                                |

10. Presionamos sobre el botón Connect y con ello ya estaríamos dentro de la base de datos.

## 🧑‍💻 Credenciales de Usuarios de Prueba
Para ingresar como administrador ingresamos las siguientes credenciales:

* 👨🏻‍💼 admin@tecnonet.com
* 🔒 admin123

Para ingresar como usuario tenemos las siguientes credenciales:
- Carlos Hernesto Casas Torres
    * 👨🏻‍💼 CarlosCasas@gmail.com
    * 🔒 carlos159
- Miguel Angel Tenorio Mesa
    * 👨🏻‍💼 migueltenorio25@gmail.com
    * 🔒 miguel753
- Camila Valentina Mendez Sosa
    * 👨🏻‍💼 camivalen74@gmail.com
    * 🔒 cami456
- Samuel Calderon Rivera
    * 👨🏻‍💼 billonario@gmail.com
    * 🔒 billonario123

## 🔗 Comandos SQL
Las tablas y categorías se crean de manera independiente mediante las anotaciones implementadas en la carpeta model, pero en caso se quiera agregar una tabla de manera manual, se podría usar el siguiente código:

```sql
CREATE TABLE USUARIOS (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,
    rol VARCHAR(50) DEFAULT 'ROLE_USER' 
);
```

Para generar usuarios iniciales se utilizó el siguiente código:

```sql
INSERT INTO usuarios (nombre, apellido, email, password_hash, fecha_registro, activo, tipo_usuario) VALUES
('Carlos Hernesto','Casas Torres','CarlosCasas@gmail.com','$2a$10$ETpxthB5ljNzR9vCWjBl9exlECB3UD6nVzQxCFuHcoS2bxRQTGEmi','2025-06-17 04:35:58',1,'USUARIO'),
('Miguel Angel','Tenorio Mesa','migueltenorio25@gmail.com','$2a$10$dZU86tRfIg51oS2nRvoLHuBBwr85cy.FswdvEpai9QB1XIn05Jnwm','2025-06-17 06:41:14',1,'USUARIO'),
('Camila Valentina','Mendez Sosa','camivalen74@gmail.com','$2a$10$pDWHohmRsMLdRM1YI0CY8OvZMzX5eQtfL.7WygTiNph4iKTI7dPje','2025-06-17 06:56:02',1,'USUARIO');
```

Estos fueron los puntos a destacar dentro de la realización de este proyecto.