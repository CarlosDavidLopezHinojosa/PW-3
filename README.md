# PW-3
Repositorio de la práctica 3 de PW del Equipo 9

De la práctica anterior en principio tenemos que hacer algunos cambios que son los siguientes:

- [x] Crear ficheros config.properties y sql.properties para no tener que hardocdear los valores de la base de datos.
- [x] El patrón Factory de la P1 no se ha cambiado, según el criterio del profesor (Tenemos que averiguar cual es).
- [ ] Posibles mejoras en la base de datos para no tener tablas con muchas tuplas como la clase Reserva, tal vez debamos implementar una especialización.

De la práctica actual en principio lo que debemos hacer es lo siguiente:

- [x] Esta primera semana debemos entender y crear el servidor se Apache Tomcat que se usará en local, no es necesario HTTPs.
- [ ] Tambien sería conveniente que entender Servlets y JSPs, ya que es lo que vamos a usar para la práctica.

Para poner en marcha el servido descomprimir el zip y hacer lo que dice el pdf de configuración paso a paso.



## DIOS MIOOOOO SE ME FRIZAN LAS BOLAS, ME LAS FRIZO
Hola clase soy Javier Gómez de 2ºA y voy a presentar mi trabajo "cómo cojones se compila tomcat sin que explote". Empecemos:
- Abajo a la izquierda hay una pestaña SERVERS. Ábrela, y a la derecha de donde pone SERVERS hay un icono si pasas el ratón por encima. Dale para añadir un servidor y cuando te pregunte si lo descargas contesta no. Se te abre el explorador de archivos y tienes que seleccionar la carpeta de apache-tomcat (suponiendo que la has descomprimido).
- En la pestaña que se abre ve abajo del todo y dale a finish (finlandés, pa los que no sepan inglés).
- En SERVERS se ha creado un servidor de Tomcat. Haz click derecho y dale a Start server. Sale un textaco en la línea de comandos, tienes que esperar a que salga el texto `org.apache.catalina.startup.Catalina.start Server startup in [<x>] millisecond`.
- Esto hay que hacerlo solo si la carpeta `web` por lo que sea no existe. Primero, crea la carpeta `web` en la raíz del proyecto. Luego, abre los comandos de VSCode (la combinación en Windows y Linux debe ser shift + comando + P, pero no estoy seguro), y ejecuta el comando `Maven: New Project...`. Selecciona `maven-archetype-webapp`, 1.4, com.example (viene así por defecto), y como nombre pon web. En la terminal se ejecutará algo. Cuando pare (que no tardará mucho), tienes que darle a enter y luego escribir `y`.
- Dentro de la carpeta `web/web/src/main/webapp/` está todo el código fuente de la página web. Puedes reorganizarlo en subcarpetas, pero nunca sacarlo de `webap`. Haz las modificaciones que quieras.
- Modificaciones hechas, abajo a la izquierda habrá una pestaña MAVEN que tendrá el proyecto `web Maven Webapp`. Haz click derecho $\rightarrow$ Run Maven Commands... $\rightarrow$ package para compilar el proyecto. Ten en cuenta que esto es solo si el proyecto no lo has desplegado ya.
- Una vez compilado, se habrá creado en `wen/web/src/target` el archivo `web.war`. Ese es el ejecutable. Para desplegarlo, haz click derecho $\rightarrow$ Run on Server $\rightarrow$ Tomcat 10.x $\rightarrow$ No. La terminal sigue a lo suyo, espera a que aparezca el mensaje que indica se ha finalizado en $x$ segundos, y tendrás tu página web ejecutándose en `localhost:8080/web`. 
- Si haces más cambios y queres recompilar, hay que hacer esto. ATENCIÓN: no hacerlo paso por paso hará que todo explote:
    - En SERVERS, habrá un nuevo elemento debajo de Tomcat 10.x. Haz click derecho $\rightarrow$ Remove Deployment **asegurándote antes de que el servidor Tomcat que hay justo encima está en estado Started y no Stopped porque sino explotará**.
    - ¿Recuerdas dónde le dabas para compilar el proyecto? Pues vas a ir allí, al primer elemento de la pestaña MAVEN y vas a ir donde estaba la opción package, pero en vez de ejecutar package del tirón vas a ejecutar primero clean y después package.
    - Ahora es todo seguir los pasos de antes después de compilar. Despliega el .war y podrás acceder a tu página web actualizada. 