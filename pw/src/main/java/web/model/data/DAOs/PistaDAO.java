package web.model.data.DAOs;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.PistaDTO.TamanoPista;
import web.model.data.common.DBConnection;



/**
 * La clase PistaDAO proporciona métodos para gestionar las pistas en una base de datos.
 * Permite insertar nuevas pistas, listar pistas disponibles y no disponibles, 
 * y obtener información sobre pistas específicas.
 * 
 * <p>La información de las pistas se almacena en una base de datos a través de la clase DBConnection. 
 * Cada pista se representa mediante un objeto de la clase PistaDTO, que contiene datos como el nombre, 
 * disponibilidad, si es exterior, tamaño y número máximo de jugadores.</p>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #insertarPista(String, boolean, boolean, TamanoPista, int)}: Inserta una nueva pista en la base de datos si no existe una con el mismo nombre.</li>
 *   <li>{@link #listarPistasDisponibles()}: Devuelve una lista con todas las pistas disponibles en la base de datos.</li>
 *   <li>{@link #listarPistasNoDisponibles()}: Devuelve una lista con todas las pistas no disponibles en la base de datos.</li>
 *   <li>{@link #listarPistasDisponibles(int, TamanoPista)}: Devuelve una lista con todas las pistas disponibles que tengan un tamaño y un número de jugadores concretos.</li>
 *   <li>{@link #listarPistasDisponiblesPorFechayTipo(LocalDateTime, boolean)}: Devuelve una lista con todas las pistas disponibles en una fecha concreta y de un tipo específico.</li>
 *   <li>{@link #listarPistasDisponiblesPorFechaYTipo(LocalDateTime, PistaDTO.TamanoPista)}: Devuelve una lista con todas las pistas disponibles en una fecha concreta y de un tipo específico.</li>  
 * <li>{@link #listarPistasDisponiblesPorFecha(LocalDateTime)}: Devuelve una lista con todas las pistas disponibles en una fecha concreta.</li>
 *   <li>{@link #booleanPistaNombre(String)}: Devuelve true si hay una pista con el nombre pasado por parámetro en la base de datos y false si no la hay.</li>
 *   <li>{@link #obtenerPistas()}: Devuelve una lista con todas las pistas en la base de datos.</li>
 *   <li>{@link #obtenerPistaNombre(String)}: Devuelve un objeto PistaDTO con los datos de la pista con el nombre pasado por parámetro.</li>
 *   <li>{@link #asociarMaterialAPista(int, int)}: Asocia un material a una pista específica en la base de datos.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * PistaDTO nuevaPista = PistaDAO.insertarPista("Pista 1", true, false, TamanoPista.GRANDE, 4);
 * List<PistaDTO> pistasDisponibles = PistaDAO.listarPistasDisponibles();
 * boolean existePista = PistaDAO.booleanPistaNombre("Pista 1");
 * PistaDTO pista = PistaDAO.obtenerPistaNombre("Pista 1");
 * PistaDAO.asociarMaterialAPista(pista.getId(), materialId);
 * }
 * </pre>
 * 
 * @see data.DTOs.PistaDTO
 * @see data.DBConnection
 */
public class PistaDAO {

    /**
     * Inserta una nueva pista en la base de datos si no existe una pista con el mismo nombre.
     *
     * @param nombre El nombre de la pista.
     * @param disponible El estado de disponibilidad de la pista.
     * @param esExterior Indica si la pista es exterior.
     * @param tamano El tamaño de la pista.
     * @param maxJugadores El número máximo de jugadores permitidos en la pista.
     * @return Un objeto PistaDTO que representa la pista insertada, o null si ya existe una pista con el mismo nombre.
     */
    public static PistaDTO insertarPista(String nombre, boolean disponible, boolean esExterior, TamanoPista tamano, int maxJugadores){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        if (conexion.selectPistaNombre(nombre).size() == 0) {
            int id = conexion.getMaxId("Pista") + 1;
            PistaDTO pista = new PistaDTO(nombre, id, disponible, esExterior, tamano, maxJugadores);
            conexion.insertIntoPista(id, nombre, disponible, esExterior, tamano, maxJugadores);
            conexion.closeConnection();

            return pista;
        } else {
            conexion.closeConnection();
            return null;
        }
    }



    /**
     * Lista todas las pistas disponibles en la base de datos.
     *
     * @return una lista de objetos PistaDTO que representan las pistas disponibles.
     */
    public static List<PistaDTO> listarPistasDisponibles(){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<PistaDTO> pistas_disponibles = conexion.selectPistasDisponibles();
        conexion.closeConnection();
        return pistas_disponibles;
    }


    /**
     * Lista todas las pistas que no están disponibles.
     *
     * @return Una lista de objetos PistaDTO que representan las pistas no disponibles.
     */
    public static List<PistaDTO> listarPistasNoDisponibles(){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<PistaDTO> pistas_no_disponibles = conexion.selectPistasNoDisponibles();
        conexion.closeConnection();
        return pistas_no_disponibles;
    }

    /**
     * Lista las pistas disponibles según el número de jugadores y el tamaño de la pista.
     *
     * @param jugadores El número de jugadores para los cuales se necesita la pista.
     * @param tamano El tamaño de la pista requerido.
     * @return Una lista de objetos PistaDTO que representan las pistas disponibles.
     */
    public static List<PistaDTO> listarPistasDisponibles(int jugadores, TamanoPista tamano){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<PistaDTO> pistas_disponibles = conexion.selectPistasDisponiblesJugadoresTipo(jugadores, tamano);
        conexion.closeConnection();
        return pistas_disponibles;
    }

    /**
     * Lista las pistas disponibles en una fecha específica y de un tipo específico.
     * @param fecha La fecha para la que se desean obtener las pistas disponibles.
     * @param esExterior Indica si se desean pistas exteriores o interiores.
     * @return
     */
    public static List<PistaDTO> listarPistasDisponiblesPorFechaYTipo2(LocalDateTime fecha, boolean esExterior) {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<PistaDTO> pistas_disponibles = conexion.selectPistasDisponiblesPorFechaYTipo2(fecha, esExterior);
        conexion.closeConnection();
        return pistas_disponibles;
    }

    /**
     * Lista las pistas disponibles en una fecha específica y de un tamaño específico.
     *
     * @param diaYHora La fecha y hora para la que se desean obtener las pistas disponibles.
     * @param duracion La duración de la reserva en minutos.
     * @param tamanoPista El tamaño de la pista requerido.
     * @return Una lista de objetos PistaDTO que representan las pistas disponibles.
     * @throws SQLException Si ocurre un error al intentar recuperar los registros de la base de datos.
     */
    public static List<PistaDTO> listarPistasDisponiblesPorFechaYTipo(LocalDateTime diaYHora, int duracion, PistaDTO.TamanoPista tamanoPista) throws SQLException {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<PistaDTO> pistasDisponibles = conexion.selectPistasDisponiblesPorFechaYTipo(diaYHora, duracion, tamanoPista);
        conexion.closeConnection();
        return pistasDisponibles;
    }

    /**
     * Lista las pistas disponibles en una fecha específica.
     *
     * @param fecha La fecha para la que se desean obtener las pistas disponibles.
     * @return Una lista de objetos PistaDTO que representan las pistas disponibles.
     */
    public static List<PistaDTO> listarPistasDisponiblesPorFecha(LocalDateTime fecha){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<PistaDTO> pistas_disponibles = conexion.selectPistasDisponiblesFecha(fecha);
        conexion.closeConnection();
        return pistas_disponibles;
    }

    /**
     * Verifica si existe una pista con el nombre especificado en la base de datos.
     *
     * @param nombre El nombre de la pista a buscar.
     * @return true si existe una pista con el nombre dado, false en caso contrario.
     */
    public static boolean booleanPistaNombre(String nombre){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        boolean existe = (conexion.selectPistaNombre(nombre).size() != 0);
        conexion.closeConnection();
        return existe;
    }

    /**
     * Obtiene una lista de objetos PistaDTO desde la base de datos.
     *
     * @return una lista de objetos PistaDTO.
     */
    public static List<PistaDTO> obtenerPistas(){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<PistaDTO> pistas = conexion.selectPistas();
        conexion.closeConnection();
        return pistas;
    }

    /**
     * Obtiene una pista por su nombre.
     *
     * @param pistaNombre El nombre de la pista a buscar.
     * @return Un objeto PistaDTO que representa la pista encontrada, o null si no se encuentra ninguna pista con ese nombre.
     */
    public static PistaDTO obtenerPistaNombre(String pistaNombre){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<PistaDTO> pistas = conexion.selectPistaNombre(pistaNombre);
        PistaDTO pista = pistas.isEmpty() ? null : pistas.get(0);
        conexion.closeConnection();
        return pista;
    }

    public static void asociarMaterialAPista(int idPista, int idMaterial){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        conexion.updateMaterialPista(idPista, idMaterial);
        conexion.closeConnection();
    }

    /**
     * Recupera un objeto PistaDTO desde la base de datos basado en el ID proporcionado.
     *
     * @param idPista El ID de la pista que se desea recuperar.
     * @return Un objeto PistaDTO que representa la pista recuperada.
     * @throws SQLException Si ocurre un error al intentar recuperar el registro de la base de datos.
     */
    public static PistaDTO obtenerPistaPorId(int idPista) throws SQLException {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        PistaDTO pista = conexion.selectPistaPorId(idPista);
        conexion.closeConnection();
        return pista;
    }

    /**
     * Modifica la disponibilidad de una pista en la base de datos.
     *
     * @param idPista El id de la pista cuya disponibilidad se va a modificar.
     * @param nuevaDisponibilidad La nueva disponibilidad que se asignará a la pista.
     */
    public static void modificarPistaDisponibilidad(int idPista, boolean nuevaDisponibilidad) {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        conexion.updatePistaDisponibilidad(idPista, nuevaDisponibilidad);
        conexion.closeConnection();
    }

}
