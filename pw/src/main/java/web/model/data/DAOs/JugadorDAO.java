package web.model.data.DAOs;

import java.time.LocalDate;
import java.util.List;

import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.JugadorDTO.Roles;
import web.model.data.common.DBConnection;

/**
 * La clase JugadorDAO proporciona métodos para interactuar con la base de datos de jugadores.
 * Permite verificar la existencia de un usuario por su email, obtener un usuario por su email,
 * dar de alta un nuevo usuario, modificar la información de un usuario existente y obtener una lista
 * de todos los usuarios registrados en la base de datos.
 * 
 * <p>La información de los jugadores se gestiona a través de la clase DBConnection, que maneja las conexiones
 * y operaciones con la base de datos. Cada jugador se representa mediante un objeto de la clase JugadorDTO,
 * que contiene datos como el email, nombre, apellidos, fecha de nacimiento, entre otros.</p>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #existeUsuarioEmail(String)}: Verifica si existe un usuario con el email proporcionado en la base de datos.</li>
 *   <li>{@link #existeUsuarioId(int)}: Verifica si existe un usuario con el id proporcionado en la base de datos.</li>
 *   <li>{@link #getUsuarioEmail(String)}: Obtiene un usuario de la base de datos cuyo email coincida con el proporcionado.</li>
 *   <li>{@link #darDeAlta(String, String, String, LocalDate)}: Añade un nuevo usuario a la base de datos y devuelve un objeto JugadorDTO con los datos del usuario añadido.</li>
 *   <li>{@link #modificarInformacion(int, String, String, String, LocalDate)}: Modifica la información de un usuario existente en la base de datos.</li>
 *   <li>{@link #obtenerUsuarios()}: Devuelve una lista con todos los usuarios registrados en la base de datos.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * boolean existe = JugadorDAO.existeUsuarioEmail("email@example.com");
 * JugadorDTO jugador = JugadorDAO.getUsuarioEmail("email@example.com");
 * JugadorDTO nuevoJugador = JugadorDAO.darDeAlta("email@example.com", "Nombre", "Apellidos", LocalDate.now());
 * JugadorDAO.modificarInformacion(nuevoJugador.getId(), "nuevoemail@example.com", "NuevoNombre", "NuevoApellidos", LocalDate.now());
 * List<JugadorDTO> jugadores = JugadorDAO.obtenerUsuarios();
 * }
 * </pre>
 * 
 * @see data.DTOs.JugadorDTO
 * @see data.DBConnection
 */
public class JugadorDAO {

    /**
     * Verifica si existe un usuario con el correo electrónico proporcionado.
     *
     * @param email El correo electrónico del usuario a verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    public static boolean existeUsuarioEmail(String email) {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        if (conexion.selectJugadorEmail(email) == null) {
            conexion.closeConnection();
            return false;
        } else {
            conexion.closeConnection();
            return true;
        }
    }

    /**
     * Verifica si existe un usuario con el ID proporcionado.
     *
     * @param id El ID del usuario a verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    public static boolean existeUsuarioId(int id) {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        if (conexion.selectJugadorId(id) == null) {
            conexion.closeConnection();
            return false;
        } else {
            conexion.closeConnection();
            return true;
        }
    }

    /**
     * Obtiene un objeto JugadorDTO basado en el correo electrónico proporcionado.
     *
     * @param email El correo electrónico del jugador que se desea buscar.
     * @return Un objeto JugadorDTO que contiene la información del jugador asociado con el correo electrónico proporcionado.
     */
    public static JugadorDTO getUsuarioEmail(String email) {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        JugadorDTO jugador = conexion.selectJugadorEmail(email);
        conexion.closeConnection();
        return jugador;
    }

    /**
     * Crea un nuevo jugador y lo da de alta en la base de datos.
     *
     * @param email El correo electrónico del jugador.
     * @param nombre El nombre del jugador.
     * @param apellidos Los apellidos del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @return Un objeto JugadorDTO que representa al jugador dado de alta.
     */
    public static JugadorDTO darDeAlta(String email, String nombre, String apellidos, LocalDate fechaNacimiento, String password, Roles rol){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        int id = conexion.getMaxId("Jugador") + 1;
        JugadorDTO jugador = new JugadorDTO(nombre, apellidos, id, fechaNacimiento, LocalDate.now(), email, password, rol);
        conexion.insertIntoJugador(nombre, apellidos, id, fechaNacimiento, LocalDate.now(), email,password, rol);
        conexion.closeConnection();
        return jugador;
    }

    /**
     * Modifica la información de un jugador en la base de datos.
     *
     * @param id El identificador del jugador.
     * @param email El correo electrónico del jugador.
     * @param nombre El nombre del jugador.
     * @param apellidos Los apellidos del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     */
    public static void modificarInformacion(int id, String email, String nombre, String apellidos, LocalDate fechaNacimiento){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        conexion.updateJugador(nombre, apellidos, id, fechaNacimiento, LocalDate.now(), email);
        conexion.closeConnection();
    }

    /**
     * Obtiene una lista de objetos JugadorDTO desde la base de datos.
     * 
     * @return Una lista de objetos JugadorDTO que representan a los jugadores obtenidos de la base de datos.
     */
    public static List<JugadorDTO> obtenerUsuarios(){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<JugadorDTO> jugadores = conexion.selectJugadores();
        conexion.closeConnection();
        return jugadores;
    }

    
}
