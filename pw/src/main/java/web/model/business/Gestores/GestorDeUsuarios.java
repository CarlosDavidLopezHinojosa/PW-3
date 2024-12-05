package web.model.business.Gestores;

import java.time.LocalDate;
import java.util.List;

import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.JugadorDTO.Roles;
import web.model.data.DAOs.JugadorDAO;

/**
 * La clase GestorDeUsuarios implementa el patrón de diseño Singleton para gestionar usuarios en un sistema.
 * Proporciona métodos para dar de alta nuevos usuarios, modificar la información de usuarios existentes,
 * y listar todos los usuarios registrados en la base de datos.
 * 
 * <p>La información de los usuarios se almacena en una base de datos a través de la clase JugadorDAO. 
 * Cada usuario se representa mediante un objeto de la clase JugadorDTO, que contiene datos como el email, 
 * nombre, apellidos, y fecha de nacimiento.</p>
 * 
 * <p>El patrón Singleton asegura que solo exista una instancia de GestorDeUsuarios en todo el programa.</p>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getGestor()}: Proporciona la instancia única de GestorDeUsuarios.</li>
 *   <li>{@link #darDeAlta(String, String, String, LocalDate)}: Da de alta un nuevo usuario en el sistema.</li>
 *   <li>{@link #modificarInformacion(int, String, String, String, LocalDate)}: Modifica la información de un usuario existente.</li>
 *   <li>{@link #listarUsuarios()}: Lista todos los usuarios registrados en la base de datos.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * GestorDeUsuarios gestor = GestorDeUsuarios.getGestor();
 * JugadorDTO nuevoJugador = gestor.darDeAlta("email@example.com", "Nombre", "Apellidos", LocalDate.now());
 * gestor.modificarInformacion(nuevoJugador.getId(), "nuevoemail@example.com", "NuevoNombre", "NuevoApellidos", LocalDate.now());
 * List<JugadorDTO> usuarios = gestor.listarUsuarios();
 * }
 * </pre>
 * 
 * @see business.DTOs.JugadorDTO
 * @see data.DAOs.JugadorDAO
 */
public class GestorDeUsuarios {

    // Instancia, constructor privado y constructor público para la implementación del patrón de diseño Singleton
    // Con esto nos aseguramos de que en todo programa de Java solo pueda haber una instancia de esta clase
    private static GestorDeUsuarios gestor;

    private GestorDeUsuarios() {}

    /**
     * Método que proporciona una instancia única de GestorDeUsuarios (Singleton).
     * @return La instancia de GestorDeUsuarios.
     */
    public static synchronized GestorDeUsuarios getGestor() {
        if (gestor == null) {
            gestor = new GestorDeUsuarios();
        }
        return gestor;
    }

    /**
     * Da de alta un nuevo usuario en el sistema. 
     * Crea un nuevo objeto Jugador con los datos proporcionados y lo almacena en un archivo.
     * Si el archivo no existe, lo crea; si existe, verifica que el email no esté ya registrado.
     * @param email Email del usuario.
     * @param nombre Nombre del usuario.
     * @param apellidos Apellidos del usuario.
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     * @param password Contraseña del usuario.
     * @return El objeto Jugador del usuario creado, o null si ya estaba registrado.
     */
    public static JugadorDTO darDeAlta(String email, String nombre, String apellidos, LocalDate fechaNacimiento, String password, Roles rol) {
        return JugadorDAO.darDeAlta(email, nombre, apellidos, fechaNacimiento, password, rol);
    }

    /**
     * Modifica la información de un usuario existente. 
     * Se espera que el id del usuario sea el mismo que se pasó como argumento.
     * @param usuario El objeto Jugador con la información actualizada.
     */
    public static void modificarInformacion(int id, String email, String nombre, String apellidos, LocalDate fechaNacimiento) {
        JugadorDAO.modificarInformacion(id, email, nombre, apellidos, fechaNacimiento);
    }

    /**
     * Lista todos los usuarios registrados en la base de datos. 
     * Imprime la información de cada usuario en la consola.
     */
    public static List<JugadorDTO> listarUsuarios() {
        return JugadorDAO.obtenerUsuarios();
    }
}
