# PW-3

Repositorio de la práctica 3 de PW del Equipo 6

De la práctica anterior en principio tenemos que hacer algunos cambios que son los siguientes:

- [x] Crear ficheros config.properties y sql.properties para no tener que hardocdear los valores de la base de datos.
- [x] El patrón Factory de la P1 no se ha cambiado, según el criterio del profesor (Tenemos que averiguar cual es).
- [x] Posibles mejoras en la base de datos para no tener tablas con muchas tuplas como la clase Reserva, tal vez debamos implementar una especialización (Añadir contraseña a los jugadores).

De la práctica actual en principio lo que debemos hacer es lo siguiente:

- [x] Esta primera semana debemos entender y crear el servidor se Apache Tomcat que se usará en local, no es necesario HTTPs.
- [x] Tambien sería conveniente que entender Servlets y JSPs, ya que es lo que vamos a usar para la práctica.
- [x] Hacer página de inicio, login,
- [ ] Hace el ejercicio 2, Javi, Alejandro.
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

Una vez hecho esto si quereís probar, podeis meteros en [la pagina web](http://localhost:8080/PW/).
Tambien podeís ver los jugadores que tenemos en la base de datos en [enlace a los jugadores](http://localhost:8080/PW/jugadores)

### Organización del proyecto

El mvc que nos enseñaron desde primaria, al menos que yo recuerde (Mi pasado me atormenta).

```bash
pw/
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── web/
│ │ │ │ ├── model/
│ │ │ │ │ ├── business/
│ │ │ │ │ │ ├── Beans/
│ │ │ │ │ │ │ └── CustomerBean.java
│ │ │ │ │ │ ├── DTOs/
│ │ │ │ │ │ │ ├── BonoDTO.java
│ │ │ │ │ │ │ ├── JugadorDTO.java
│ │ │ │ │ │ │ ├── MaterialDTO.java
│ │ │ │ │ │ │ ├── PistaDTO.java
│ │ │ │ │ │ │ └── Reservas/
│ │ │ │ │ │ │ │ ├── ReservaAdultosDTO.java
│ │ │ │ │ │ │ │ ├── ReservaBonoFactoryDTO.java
│ │ │ │ │ │ │ │ ├── ReservaDTO.java
│ │ │ │ │ │ │ │ ├── ReservaFactoryDTO.java
│ │ │ │ │ │ │ │ ├── ReservaFamiliarDTO.java
│ │ │ │ │ │ │ │ ├── ReservaIndFactoryDTO.java
│ │ │ │ │ │ │ │ └── ReservaInfantilDTO.java
│ │ │ │ │ │ ├── Gestores/
│ │ │ │ │ │ │ ├── GestorDePistas.java
│ │ │ │ │ │ │ ├── GestorDeReservas.java
│ │ │ │ │ │ │ └── GestorDeUsuarios.java
│ │ │ │ │ ├── data/
│ │ │ │ │ │ ├── common/
│ │ │ │ │ │ │ └── DBConnection.java
│ │ │ │ │ │ ├── DAOs/
│ │ │ │ │ │ │ ├── BonoDAO.java
│ │ │ │ │ │ │ ├── JugadorDAO.java
│ │ │ │ │ │ │ ├── MaterialDAO.java
│ │ │ │ │ │ │ ├── PistaDAO.java
│ │ │ │ │ │ │ └── ReservaDAO.java
│ │ │ │ ├── servlet/
│ │ │ │ │ └── VerJugadoresServlet.java
│ │ ├── resources/
│ │ │ ├── config.properties
│ │ │ ├── database/
│ │ │ │ ├── drop.sql
│ │ │ │ ├── insert.sql
│ │ │ │ ├── query.sql
│ │ │ │ └── squema.sql
│ │ │ └── sql.properties
│ │ └── webapp/
│ │ ├── WEB-INF/
│ │ │ └── web.xml
│ │ ├── controller/
│ │ │ ├── logincontroller.jsp
│ │ │ ├── logout.jsp
│ │ │ ├── registercontroller.jsp
│ │ │ ├── updatecontroller.jsp
│ │ │ ├── welcomeadmincontroller.jsp
│ │ │ └── welcomeclientcontroller.jsp
│ │ ├── static/
│ │ │ ├── css/
│ │ │ │ ├── jugadores.css
│ │ │ │ └── styles.css
│ │ │ ├── img/
│ │ │ └── js/
│ │ │ │ └── register.js
│ │ ├── views/
│ │ │ ├── jugadores.jsp
│ │ │ ├── loginview.jsp
│ │ │ ├── registerview.jsp
│ │ │ ├── updateview.jsp
│ │ │ ├── welcomeadminview.jsp
│ │ │ └── welcomeclientview.jsp
│ │ └── index.jsp
│ └── test/
│ └── java/
```
