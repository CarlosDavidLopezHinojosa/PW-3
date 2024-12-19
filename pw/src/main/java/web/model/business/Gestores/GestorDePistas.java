package web.model.business.Gestores;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import web.model.business.DTOs.MaterialDTO;
import web.model.business.DTOs.MaterialDTO.EstadoMaterial;
import web.model.business.DTOs.MaterialDTO.TipoMaterial;
import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.PistaDTO.TamanoPista;
import web.model.data.DAOs.PistaDAO;

/**
 * La clase {@code GestorDePistas} gestiona la creación y mantenimiento de pistas y materiales asociados,
 * incluyendo la persistencia de estos datos en una base de datos.
 * Implementa el patrón Singleton para asegurar una única instancia de esta clase en toda la aplicación.
 * 
 * <p>La información de las pistas se almacena en una base de datos, mientras que
 * la información de los materiales también se almacena en la misma base de datos. Cada pista se representa mediante un
 * objeto de la clase {@link business.DTOs.PistaDTO}, y cada material se representa mediante un objeto de la clase
 * {@link business.DTOs.MaterialDTO}.</p>
 * 
 * <p>El patrón Singleton asegura que solo exista una instancia de {@code GestorDePistas} en todo el programa.</p>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getGestor()}: Proporciona la instancia única de {@code GestorDePistas}.</li>
 *   <li>{@link #crearPista(String, boolean, boolean, TamanoPista, int)}: Crea una nueva pista y la registra en la base de datos.</li>
 *   <li>{@link #crearMaterial(TipoMaterial, boolean, EstadoMaterial)}: Crea un nuevo material y lo registra en la base de datos.</li>
 *   <li>{@link #asociarMaterialAPista(PistaDTO, MaterialDTO)}: Asocia un material a una pista y actualiza la base de datos.</li>
 *   <li>{@link #listarPistasNoDisponibles()}: Devuelve una lista de las pistas no disponibles registradas en la base de datos.</li>
 *   <li>{@link #listarPistasDisponibles(int, TamanoPista)}: Lista las pistas disponibles que cumplen con el tamaño y número de jugadores especificados.</li>
 *   <li>{@link #listarPistasDisponibles()}: Lista todas las pistas disponibles.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * GestorDePistas gestor = GestorDePistas.getGestor();
 * PistaDTO nuevaPista = gestor.crearPista("Pista1", true, false, TamanoPista.GRANDE, 4);
 * MaterialDTO nuevoMaterial = gestor.crearMaterial(TipoMaterial.PELOTA, true, EstadoMaterial.NUEVO);
 * gestor.asociarMaterialAPista(nuevaPista, nuevoMaterial);
 * List<PistaDTO> pistasNoDisponibles = gestor.listarPistasNoDisponibles();
 * List<PistaDTO> pistasDisponibles = gestor.listarPistasDisponibles(4, TamanoPista.GRANDE);
 * List<PistaDTO> todasLasPistasDisponibles = gestor.listarPistasDisponibles();
 * }
 * </pre>
 * 
 * @see business.DTOs.PistaDTO
 * @see business.DTOs.MaterialDTO
 */
public class GestorDePistas {

    private static GestorDePistas gestor;

    /**
     * Constructor privado para implementar el patrón Singleton.
     */
    private GestorDePistas() {}

    /**
     * Obtiene la instancia única de {@code GestorDePistas}.
     *
     * @return La única instancia de {@code GestorDePistas}.
     */
    public static synchronized GestorDePistas getGestor() {
        if (gestor == null) {
            gestor = new GestorDePistas();
        }
        return gestor;
    }

    /**
     * Crea una nueva pista y la registra en la base de datos.
     * Si la pista ya existe, devuelve {@code null}.
     *
     * @param nombre        Nombre único de la pista.
     * @param disponible    Disponibilidad de la pista.
     * @param esExterior    Si la pista es exterior o interior.
     * @param tamano        Tamaño de la pista.
     * @param maxJugadores  Número máximo de jugadores.
     * @return              La pista creada, o {@code null} si ya existe una pista con ese nombre.
     */
    public static PistaDTO crearPista(String nombre, boolean disponible, boolean esExterior, TamanoPista tamano, int maxJugadores) {
        PistaDTO pista = PistaDAO.insertarPista(nombre, disponible, esExterior, tamano, maxJugadores);
        return pista;
    }

    /**
     * Devuelve una lista con las pistas de la base de datos que no están disponibles.
     * @return La lista de pistas no disponibles
     */
    public static List<PistaDTO> listarPistasNoDisponibles() {
        return PistaDAO.listarPistasNoDisponibles();
    }

    /**
     * Devuelve una lista con las pistas disponibles que cumplen con el tamaño y número de jugadores.
     *ºº
     * @param jugadores Número de jugadores.
     * @param tipo      Tamaño de la pista requerido.
     * @return          Una lista de pistas disponibles que cumplen con los criterios.
     */
    public static List<PistaDTO> listarPistasDisponibles(int jugadores, TamanoPista tipo) {
        return PistaDAO.listarPistasDisponibles(jugadores, tipo);
    }

    /**
     * Devuelve una lista de las pistas de la base de datos que están disponibles.
     *
     * @return Una lista con todas las pistas disponibles.
     */
    public static List<PistaDTO> listarPistasDisponibles() {
        return PistaDAO.listarPistasDisponibles();
    }

    public static List<PistaDTO> listarPistasDisponibles_G(int jugadores, TamanoPista tamano) {
        return PistaDAO.listarPistasDisponibles(jugadores, tamano);
    }

    public static List<PistaDTO> listarPistasDisponiblesPorFechaYTipo2(LocalDateTime fecha, boolean esExterior) {
        return PistaDAO.listarPistasDisponiblesPorFechaYTipo2(fecha, esExterior);
    }

    public static List<PistaDTO> listarPistasDisponiblesPorFechaYTipo(LocalDateTime diaYHora, int duracion, PistaDTO.TamanoPista tamanoPista) throws SQLException {
        return PistaDAO.listarPistasDisponiblesPorFechaYTipo(diaYHora, duracion, tamanoPista);
    }

    public static List<PistaDTO> listarPistasDisponiblesPorFecha(LocalDateTime fecha){
        return PistaDAO.listarPistasDisponiblesPorFecha(fecha);
    }

    public static boolean booleanPistaNombre(String nombre){
        return PistaDAO.booleanPistaNombre(nombre);
    }

    public static List<PistaDTO> obtenerPistas(){
        return PistaDAO.obtenerPistas();
    }

    public static PistaDTO obtenerPistaNombre(String pistaNombre){
        return PistaDAO.obtenerPistaNombre(pistaNombre);
    }

    public static PistaDTO obtenerPistaPorId(int idPista) throws SQLException {
        return PistaDAO.obtenerPistaPorId(idPista);
    }

    public static void asociarMaterialAPista(PistaDTO pista, MaterialDTO material) {
        PistaDAO.asociarMaterialAPista(pista.getId(), material.getId());
    }
}
