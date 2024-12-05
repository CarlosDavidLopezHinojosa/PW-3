package web.model.business.DTOs;

import java.time.LocalDate;


/**
 * La clase {@code JugadorDTO} representa un jugador que contiene información personal y de inscripción.
 * Esta clase de transferencia de datos (DTO) se utiliza para gestionar los datos de los jugadores.
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getNombre()}: Obtiene el nombre del jugador.</li>
 *   <li>{@link #setNombre(String)}: Establece el nombre del jugador.</li>
 *   <li>{@link #getApellidos()}: Obtiene los apellidos del jugador.</li>
 *   <li>{@link #setApellidos(String)}: Establece los apellidos del jugador.</li>
 *   <li>{@link #getId()}: Obtiene el ID del jugador.</li>
 *   <li>{@link #setId(int)}: Establece el ID del jugador.</li>
 *   <li>{@link #getFechaNacimiento()}: Obtiene la fecha de nacimiento del jugador.</li>
 *   <li>{@link #setFechaNacimiento(LocalDate)}: Establece la fecha de nacimiento del jugador.</li>
 *   <li>{@link #getFechaInscripcion()}: Obtiene la fecha de inscripción del jugador.</li>
 *   <li>{@link #setFechaInscripcion(LocalDate)}: Establece la fecha de inscripción del jugador.</li>
 *   <li>{@link #getEmail()}: Obtiene el correo electrónico del jugador.</li>
 *   <li>{@link #setEmail(String)}: Establece el correo electrónico del jugador.</li>
 *   <li>{@link #getPassword()}: Obtiene la contraseña del jugador.</li>
 *   <li>{@link #setPassword(String)}: Establece la contraseña del jugador.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * JugadorDTO jugador = new JugadorDTO("Carlos", "Perez", 1, LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), "carlos.perez@example.com");
 * jugador.setNombre("Juan");
 * String nombre = jugador.getNombre();
 * }
 * </pre>
 * 
 * @
 */
public class JugadorDTO {

    // Atributos del DTO
    private String nombre;
    private String apellidos;
    private int id;
    private LocalDate fechaNacimiento;
    private LocalDate fechaInscripcion;
    private String email;
    private String password;
    private Roles rol;

    public enum Roles {
        ADMIN, CLIENTE
    }

    /**
     * Constructor vacío (necesario para serialización).
     */
    public JugadorDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre            el nombre del jugador
     * @param apellidos         los apellidos del jugador
     * @param id                el ID del jugador
     * @param fechaNacimiento   la fecha de nacimiento del jugador
     * @param fechaInscripcion  la fecha de inscripción del jugador
     * @param email             el email del jugador
     * @param password          la contraseña del jugador
     */
    public JugadorDTO(String nombre, String apellidos, int id, LocalDate fechaNacimiento, LocalDate fechaInscripcion, String email, String password, Roles rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaInscripcion = fechaInscripcion;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre            el nombre del jugador
     * @param apellidos         los apellidos del jugador
     * @param id                el ID del jugador
     * @param fechaNacimiento   la fecha de nacimiento del jugador
     * @param fechaInscripcion  la fecha de inscripción del jugador
     * @param email             el email del jugador
     * @param password          la contraseña del jugador
     */
    public JugadorDTO(String nombre, String apellidos, int id, LocalDate fechaNacimiento, LocalDate fechaInscripcion, String email, Roles rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaInscripcion = fechaInscripcion;
        this.email = email;
        this.rol = rol;
    }
    

    /**
     * Obtiene el nombre del jugador.
     *
     * @return el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombre el nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del jugador.
     *
     * @return los apellidos del jugador
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del jugador.
     *
     * @param apellidos los apellidos del jugador
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el ID del jugador.
     *
     * @return el ID del jugador
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del jugador.
     *
     * @param id el ID del jugador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de nacimiento del jugador.
     *
     * @return la fecha de nacimiento del jugador
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del jugador.
     *
     * @param fechaNacimiento la fecha de nacimiento del jugador
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la fecha de inscripción del jugador.
     *
     * @return la fecha de inscripción del jugador
     */
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Establece la fecha de inscripción del jugador.
     *
     * @param fechaInscripcion la fecha de inscripción del jugador
     */
    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Obtiene el email del jugador.
     *
     * @return el email del jugador
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del jugador.
     *
     * @param email el email del jugador
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del jugador.
     *
     * @return la contraseña del jugador
     */
    public String getPassword() {
        return password;
    }


    /**
     * Establece la contraseña del jugador.
     *
     * @param password la contraseña del jugador
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol del jugador.
     *
     * @return el rol del jugador
     */
    public Roles getRol() {
        return rol;
    }

    /**
     * Establece el rol del jugador.
     *
     * @param rol el rol del jugador
     */
    public void setRol(Roles rol) {
        this.rol = rol;
    }


    // Método toString() para una representación en cadena
    @Override
    public String toString() {
        return "JugadorDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", id='" + id + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaInscripcion=" + fechaInscripcion +
                ", email='" + email + '\'' +
                '}';
    }
}
