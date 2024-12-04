# PW-3

Repositorio de la práctica 3 de PW del Equipo 6

De la práctica anterior en principio tenemos que hacer algunos cambios que son los siguientes:

- [x] Crear ficheros config.properties y sql.properties para no tener que hardocdear los valores de la base de datos.
- [x] El patrón Factory de la P1 no se ha cambiado, según el criterio del profesor (Tenemos que averiguar cual es).
- [x] Posibles mejoras en la base de datos para no tener tablas con muchas tuplas como la clase Reserva, tal vez debamos implementar una especialización (Añadir contraseña a los jugadores).

De la práctica actual en principio lo que debemos hacer es lo siguiente:

- [x] Esta primera semana debemos entender y crear el servidor se Apache Tomcat que se usará en local, no es necesario HTTPs.
- [x] Tambien sería conveniente que entender Servlets y JSPs, ya que es lo que vamos a usar para la práctica.
- [ ] Hacer página de inicio, login, registro
- [ ] Para el final de la práctica poner todas las queries en función de los ficheros properties

## Pero me cago en la reputisima que me re-pario

Buenos días Equipo 6 hoy después de 2 noches sin dormir, ni comer, solo viviendo a base de phoskitos he conseguido configurar
el entorno para la puta práctica para ello he tenido que reescribir el kernel de mi sistema operativo, cosa que vi extremadamente sencilla a lo que
decidi crear mi propio sistema operativo para hacer la práctica, Suena lógico, ¿no?, dime que si, DILO.

Sin más dilatación:

Haz unzip de apache y a TRABAJAR (Las voces de mi cabeza se hacen más fuertes).

### Usar Maven

Tienes una extensión de Maven usala.

Antes que nada `maven install` para que te instale todas las dependencias.

Si haces algun cambio en CUALQUIER fichero deber hacer los siguiente: `maven clean` -> `maven package`
Y por ultimo reincia el servidor tomcat `restart in run mode`.

### Organización del proyecto

El mvc que nos enseñaron desde primaria, al menos que yo recuerde (Mi pasado me atormenta).

Entended que este esquema esta generalizado

```bash
mi-web-app/
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── com.miwebapp.controlador/ # Servlets (Controladores)
│ │ │ │ └── UsuarioServlet.java
│ │ │ ├── com.miwebapp.modelo/ # Clases de negocio, si las hay
│ │ │ ├── com.miwebapp.dao/ # Acceso a datos (DAO)
│ │ │ │ └── UsuarioDAO.java
│ │ │ ├── com.miwebapp.dto/ # Objetos de transferencia de datos (DTO)
│ │ │ │ └── UsuarioDTO.java
│ │ │ └── com.miwebapp.util/ # Utilidades y helpers
│ │ │ ├── DBConnection.java # Clase para manejar conexiones
│ │ │ └── OtrosHelpers.java # (Si es necesario)
│ │ ├── resources/ # Configuraciones adicionales
│ │ │ └── db.properties # Archivo de configuración de la BD
│ │ └── webapp/
│ │ ├── WEB-INF/ # Configuración y vistas internas
│ │ │ ├── web.xml # Descriptor de despliegue
│ │ │ └── vistas/ # Vistas JSP
│ │ │ └── usuarios.jsp
│ │ └── static/ # Recursos estáticos (CSS, JS, imágenes)
│ │ ├── css/
│ │ ├── js/
│ │ └── img/
│ └── test/ # Pruebas unitarias y de integración
│
├── pom.xml # Configuración de dependencias con Maven
└── README.md # Documentación del proyecto
```
