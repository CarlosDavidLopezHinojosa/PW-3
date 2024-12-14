package web.model.business.Gestores;

import java.time.LocalDateTime;
import java.util.List;

import web.model.business.DTOs.BonoDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.data.DAOs.BonoDAO;
import web.model.data.DAOs.ReservaDAO;

/**
 * La clase GestorDeReservas implementa el patrón de diseño Singleton para gestionar reservas en un sistema.
 * Proporciona métodos para hacer reservas individuales, crear bonos, hacer reservas con bonos, consultar reservas futuras,
 * consultar reservas de una pista en un día específico, y eliminar reservas.
 * 
 * <p>La información de las reservas se almacena en una base de datos a través de los DAOs correspondientes. 
 * Cada reserva se representa mediante un objeto de la clase ReservaDTO, que contiene datos como el ID del usuario, 
 * fecha, día y hora, ID del bono, número de sesión del bono, duración, ID de la pista, tamaño de la pista, precio, y descuento.</p>
 * 
 * <p>El patrón Singleton asegura que solo exista una instancia de GestorDeReservas en todo el programa.</p>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getGestor()}: Proporciona la instancia única de GestorDeReservas.</li>
 *   <li>{@link #hacerReservaIndividual(String, int, LocalDate, int, int, float, float, PistaDTO.TamanoPista, int, int)}: Permite a un usuario hacer una reserva individual.</li>
 *   <li>{@link #crearBono(int, int, String, PistaDTO.TamanoPista)}: Permite a un usuario crear un bono.</li>
 *   <li>{@link #hacerReservaBono(String, int, LocalDate, int, int, int, int, float, float, PistaDTO.TamanoPista, int, int)}: Permite a un usuario hacer una reserva utilizando un bono.</li>
 *   <li>{@link #consultarReservasFuturas()}: Consulta todas las reservas futuras registradas.</li>
 *   <li>{@link #consultarReservaPistaDia(int, LocalDate)}: Consulta las reservas de una pista específica en un día específico.</li>
 *   <li>{@link #eliminarReserva(int)}: Elimina una reserva específica por ID.</li>
 *   <li>{@link #consultarMisReservasFuturas(int)}: Consulta las reservas futuras de un usuario específico.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * GestorDeReservas gestor = GestorDeReservas.getGestor();
 * int idUsuario = 1;
 * gestor.hacerReservaIndividual("adultos", idUsuario, LocalDate.now().plusDays(1), 60, 1, 20.0f, 0.0f, PistaDTO.TamanoPista.ADULTOS, 2, 0);
 * gestor.crearBono(10, idUsuario, "adultos", PistaDTO.TamanoPista.ADULTOS);
 * gestor.hacerReservaBono("adultos", idUsuario, LocalDate.now().plusDays(1), 1, 1, 60, 1, 20.0f, 0.0f, PistaDTO.TamanoPista.ADULTOS, 2, 0);
 * gestor.consultarReservasFuturas();
 * gestor.consultarReservaPistaDia(1, LocalDate.now().plusDays(1));
 * gestor.eliminarReserva(1);
 * gestor.consultarMisReservasFuturas(idUsuario);
 * }
 * </pre>
 * 
 * @see business.DTOs.BonoDTO
 * @see business.DTOs.PistaDTO
 * @see business.DTOs.Reservas.ReservaDTO
 * @see data.DAOs.BonoDAO
 * @see data.DAOs.ReservaDAO
 */
public class GestorDeReservas {
    // Instancia, constructor privado y constructor público para la implementación del patrón de diseño Singleton
    // Con esto nos aseguramos de que en todo programa de Java solo pueda haber una instancia de esta clase
    private static GestorDeReservas gestor;

    private GestorDeReservas() {}
    /**
     * Devuelve la instancia única de la clase GestorDeReservas.
     * Este método está sincronizado para asegurar la seguridad en hilos.
     * Si la instancia no existe, se creará.
     *
     * @return la instancia única de GestorDeReservas
     */
    public static synchronized GestorDeReservas getGestor() {
        if (gestor == null) {
            gestor = new GestorDeReservas();
        }
        return gestor;
    }

    /**
     * Método estático para realizar una reserva individual para un jugador.
     * 
     * @param user El jugador que desea realizar la reserva.
     * 
     * <p>El método solicita al usuario que introduzca varios datos necesarios para la reserva:</p>
     * <ul>
     * <li> Tipo de reserva (infantil, adultos o familiar).</li>
     * <li> Fecha y hora de la reserva en formato aaaa-mm-ddTHH:mm.</li>
     * <li> Duración de la reserva en minutos (60, 90, 120).</li>
     * <li> Tipo de pista que desea reservar (MINIBASKET, ADULTOS, VS3).</li>
     * <li> Número de jugadores para la reserva.</li>
     * </ul>
     * 
     * <p>Si en cualquier momento el usuario introduce "CANCELAR", la operación se cancela.</p>
     * 
     * <p>El método verifica que la fecha y hora de la reserva sea al menos 24 horas después del momento actual.</p>
     * 
     * <p>Si hay pistas disponibles con las características especificadas, se muestran al usuario.</p>
     * <p>El usuario debe introducir el ID de la pista que desea reservar.</p>
     * 
     * <p>El precio de la reserva se calcula en función de la duración.</p>
     * <p>Si el jugador tiene más de 2 años de inscripción, se aplica un descuento del 10%.</p>
     * 
     * <p>Finalmente, se solicita al usuario que confirme la reserva.</p>
     * <p>Si el usuario confirma, se crea y almacena la reserva.</p>
     * <p>Si el usuario no confirma, la reserva se cancela.</p>
     */
    public static ReservaDTO hacerReservaIndividual(String tipoReserva, int idUsuario, LocalDateTime diaYHora, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int numAdultos, int numNinos) {
        return ReservaDAO.insertarReserva(tipoReserva, idUsuario, diaYHora, -1, -1, duracion, idPista, precio, descuento, pistaTamano, numAdultos, numNinos);
    }

    /**
     * 
     * Crea un bono para un jugador especificado.
     * 
     * @param user El jugador para el cual se creará el bono.
     * 
     * <p>
     * El método solicita al usuario que introduzca el tipo de reserva del bono 
     * (infantil, adultos o familiar) y el tipo de pista que desea reservar 
     * (MINIBASKET, ADULTOS, VS3). Si el usuario introduce "CANCELAR" en cualquier 
     * momento, la operación se cancela.</p>
     * 
     * <p>
     * Una vez que se han introducido los datos válidos, se solicita al usuario 
     * que confirme la creación del bono. Si el usuario confirma, el bono se 
     * registra y se muestra un mensaje de confirmación. Si el usuario no 
     * confirma, se muestra un mensaje de cancelación.</p>
     * 
     * <p>
     * En caso de errores de entrada, se muestran mensajes de error apropiados 
     * y se solicita al usuario que vuelva a introducir los datos.</p>
     */
    public static BonoDTO crearBono(int sesiones, int idUser, String tipoReserva, PistaDTO.TamanoPista pistaTamano) {
        return BonoDAO.insertarBono(sesiones, idUser, tipoReserva, pistaTamano);
    }

/**
 * Realiza una reserva utilizando un bono disponible del usuario
 *
 * @param user El jugador que desea hacer la reserva.
 * <p>
 * Este método permite al jugador hacer una reserva utilizando un bono que 
 * tiene disponible. A continuación se describen los pasos que sigue el 
 * método:
 * </p>
 *
 * <ol>
 *   <li>Muestra los bonos disponibles del usuario.</li>
 *   <li>Solicita al usuario que introduzca el ID del bono con el que desea hacer la reserva.</li>
 *   <li>Solicita al usuario que introduzca la fecha y hora de la reserva.</li>
 *   <li>Verifica que la fecha y hora de la reserva sea al menos 24 horas en el futuro.</li>
 *   <li>Solicita al usuario que introduzca la duración de la reserva en minutos (60, 90, 120).</li>
 *   <li>Solicita al usuario que introduzca el número de jugadores para la reserva.</li>
 *   <li>Lista las pistas disponibles según el número de jugadores y el tamaño de la pista.</li>
 *   <li>Solicita al usuario que introduzca el ID de la pista que desea reservar.</li>
 *   <li>Calcula el precio final de la reserva aplicando un descuento.</li>
 *   <li>Solicita al usuario que confirme la reserva.</li>
 *   <li>Si la reserva es confirmada, se crea la reserva y se almacena.</li>
 * </ol>
 *
 * <p>
 * El método permite al usuario cancelar la operación en cualquier momento 
 * escribiendo <code>CANCELAR</code>.
 * </p>
 *
 * <h3>Excepciones manejadas:</h3>
 * <ul>
 *   <li><code>IOException</code>: Captura errores de entrada/salida.</li>
 *   <li><code>NumberFormatException</code>: Captura errores de formato numérico.</li>
 *   <li><code>Exception</code>: Captura errores de formato de fecha.</li>
 * </ul>
 */
    public static ReservaDTO hacerReservaBono(String tipoReserva, int idUsuario, LocalDateTime diaYHora, int idBono, int nSesionBono, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int numAdultos, int numNinos) {
        return ReservaDAO.insertarReserva(tipoReserva, idUsuario, diaYHora, idBono, nSesionBono, duracion, idPista, precio, descuento, pistaTamano, numAdultos, numNinos);
    }

    /**
     * Consulta las reservas futuras
     *
     * <p>
     * Este método lee las reservas futuras de la base de datos y las imprime en la consola. 
     * Si no hay reservas futuras registradas, imprime un mensaje indicando que no hay reservas registradas.
     * </p>
     *
     * <p>
     * La base de datos contiene registros de reservas con los siguientes campos:
     * </p>
     *
     * <ol>
     *   <li><strong>ID Usuario</strong></li>
     *   <li><strong>Nombre</strong></li>
     *   <li><strong>Día y Hora</strong> - en formato ISO-8601</li>
     *   <li><strong>ID Bono</strong></li>
     *   <li><strong>Número de Sesión del Bono</strong></li>
     *   <li><strong>Duración</strong></li>
     *   <li><strong>ID Pista</strong></li>
     *   <li><strong>Tamaño de la Pista</strong></li>
     *   <li><strong>Precio</strong></li>
     *   <li><strong>Descuento</strong></li>
     * </ol>
     *
     * <p>
     * El método solo imprime las reservas que están programadas para una fecha y hora 
     * futuras.
     * </p>
     *
     * <p>
     * Si ocurre una <code>SQLException</code> durante la consulta a la base de datos, se imprime 
     * la traza de la excepción.
     * </p>
     */
    public static List<ReservaDTO> consultarReservasFuturas() {
        return ReservaDAO.obtenerReservasFuturas();
    }
    
    /**
     * Consultar Reserva de Pista por Día
     *
     * <p>
     * Este método permite consultar las reservas de una pista específica para un día determinado.
     * Solicita al usuario el ID de la pista y la fecha en formato <code>aaaa-mm-ddThh:mm</code>.
     * Si el usuario introduce "CANCELAR" en cualquier momento, la operación se cancela.
     * </p>
     *
     * <p>
     * Las reservas se leen de un archivo llamado <code>Reservas.txt</code>. Si el archivo no existe,
     * se informa al usuario que no hay reservas registradas.
     * </p>
     *
     * <p>
     * El archivo de reservas debe contener registros con campos separados por un asterisco (<code>*</code>).
     * Cada línea en el archivo representa una reserva con los siguientes campos:
     * </p>
     *
     * <ol>
     *   <li><strong>ID Usuario</strong></li>
     *   <li><strong>Fecha</strong></li>
     *   <li><strong>Día y Hora</strong> - en formato ISO-8601</li>
     *   <li><strong>ID Bono</strong></li>
     *   <li><strong>Número de Sesión del Bono</strong></li>
     *   <li><strong>Duración</strong></li>
     *   <li><strong>ID Pista</strong></li>
     *   <li><strong>Tamaño de la Pista</strong></li>
     *   <li><strong>Precio</strong></li>
     *   <li><strong>Descuento</strong></li>
     * </ol>
     *
     * <p>
     * El método imprime las reservas que coinciden con el ID de la pista y que están programadas
     * para el día especificado.
     * </p>
     *
     * <p>
     * Si ocurre una <code>IOException</code> durante la lectura del archivo, se imprime la traza de la excepción.
     * Si el formato de la fecha es incorrecto, se solicita al usuario que introduzca la fecha nuevamente.
     * </p>
     */
    public static List<ReservaDTO> consultarReservaPistaDia(int idPista, LocalDateTime dia) {
        return ReservaDAO.obtenerReservasPistaDia(idPista, dia);

    }

    /**
    * Consultar reservas futuras de un jugador
    * @param user El jugador cuyas reservas futuras se desean consultar.
    * <p>
    * Este método muestra las reservas futuras de un jugador específico desde la base de datos. 
    * Las reservas se filtran por el ID del jugador y se muestran solo aquellas cuya fecha y hora son posteriores al momento actual.
    * </p>
    *
    * <p>
    * La base de datos contiene registros de reservas con los siguientes campos:
    * </p>
    *
    * <ol>
    *   <li><strong>ID Jugador</strong></li>
    *   <li><strong>Nombre</strong></li>
    *   <li><strong>Día y Hora</strong> - en formato ISO-8601</li>
    *   <li><strong>ID Bono</strong></li>
    *   <li><strong>Número de Sesión del Bono</strong></li>
    *   <li><strong>Duración</strong></li>
    *   <li><strong>ID Pista</strong></li>
    *   <li><strong>Tamaño de la Pista</strong></li>
    *   <li><strong>Precio</strong></li>
    *   <li><strong>Descuento</strong></li>
    * </ol>
    *
    * <p>
    * Si no se encuentran reservas futuras para el jugador especificado, se imprime un mensaje indicando que no hay reservas futuras.
    * </p>
    *
    * <p>
    * Si ocurre una <code>SQLException</code> durante la consulta a la base de datos, se imprime la traza de la excepción.
    * </p>
    */
    public static List<ReservaDTO> consultarMisReservasFuturas(int idUsuario) {
        return ReservaDAO.obtenerReservasFuturasUsuario(idUsuario);
    }

    /**
    * Elimina una reserva de un jugador.
    * 
    * @param user El jugador que desea cancelar su reserva.
    * <p>
    * Este método permite a un jugador eliminar una de sus reservas futuras. Primero, se muestran
    * las reservas futuras del jugador y se solicita la fecha de la reserva que desea cancelar.
    * Si el jugador introduce "CANCELAR", la operación se cancela.
    * </p>
    *
    * <p>
    * La base de datos contiene registros de reservas con los siguientes campos:
    * </p>
    *
    * <ol>
    *   <li><strong>ID Jugador</strong></li>
    *   <li><strong>Nombre</strong></li>
    *   <li><strong>Día y Hora</strong> - en formato ISO-8601</li>
    *   <li><strong>ID Bono</strong></li>
    *   <li><strong>Número de Sesión del Bono</strong></li>
    *   <li><strong>Duración</strong></li>
    *   <li><strong>ID Pista</strong></li>
    *   <li><strong>Tamaño de la Pista</strong></li>
    *   <li><strong>Precio</strong></li>
    *   <li><strong>Descuento</strong></li>
    * </ol>
    *
    * <p>
    * El método elimina la reserva de la base de datos que coincide con la fecha proporcionada.
    * </p>
    *
    * <p>
    * Si ocurre una <code>SQLException</code> durante la operación de eliminación, 
    * se imprime la traza de la excepción.
    * </p>
    *
    * <p>
    * Si no se puede encontrar la reserva especificada, se imprime un mensaje de error correspondiente.
    * </p>
    */
    public static void eliminarReserva(int id) {
        ReservaDAO.eliminarReserva(id);
    }
}
