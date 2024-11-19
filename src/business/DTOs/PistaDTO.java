package business.DTOs;


/**
 * La clase {@code PistaDTO} representa una pista que contiene información sobre su nombre,
 * disponibilidad, si es exterior, tamaño y el número máximo de jugadores. Las pistas se gestionan
 * a través de esta clase de transferencia de datos (DTO).
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getNombre()}: Obtiene el nombre de la pista.</li>
 *   <li>{@link #setNombre(String)}: Establece el nombre de la pista.</li>
 *   <li>{@link #getId()}: Obtiene el ID de la pista.</li>
 *   <li>{@link #setId(int)}: Establece el ID de la pista.</li>
 *   <li>{@link #isDisponible()}: Verifica si la pista está disponible.</li>
 *   <li>{@link #setDisponible(boolean)}: Establece la disponibilidad de la pista.</li>
 *   <li>{@link #isEsExterior()}: Verifica si la pista es exterior.</li>
 *   <li>{@link #setEsExterior(boolean)}: Establece si la pista es exterior.</li>
 *   <li>{@link #getTamano()}: Obtiene el tamaño de la pista.</li>
 *   <li>{@link #setTamano(TamanoPista)}: Establece el tamaño de la pista.</li>
 *   <li>{@link #getMaxJugadores()}: Obtiene el número máximo de jugadores de la pista.</li>
 *   <li>{@link #setMaxJugadores(int)}: Establece el número máximo de jugadores de la pista.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * PistaDTO pista = new PistaDTO("Pista Central", 1, true, false, PistaDTO.TamanoPista.ADULTOS, 10);
 * pista.setDisponible(false);
 * boolean esExterior = pista.isEsExterior();
 * }
 * </pre>
 * 
 * @see PistaDTO.TamanoPista
 */
public class PistaDTO {

    // Enumeración para TamanoPista
    public enum TamanoPista {
        MINIBASKET, ADULTOS, VS3
    }

    // Atributos del DTO
    private String nombre;
    private int id;
    private boolean disponible;
    private boolean esExterior;
    private TamanoPista tamano;
    private int maxJugadores;

    /**
     * Constructor vacío (necesario para serialización).
     */
    public PistaDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre       el nombre de la pista
     * @param id           el ID de la pista
     * @param disponible   si la pista está disponible
     * @param esExterior   si la pista es exterior
     * @param tamano       el tamaño de la pista
     * @param maxJugadores el número máximo de jugadores
     */
    public PistaDTO(String nombre, int id, boolean disponible, boolean esExterior, TamanoPista tamano, int maxJugadores) {
        this.nombre = nombre;
        this.id = id;
        this.disponible = disponible;
        this.esExterior = esExterior;
        this.tamano = tamano;
        this.maxJugadores = maxJugadores;
    }

    /**
     * Obtiene el nombre de la pista.
     *
     * @return el nombre de la pista
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la pista.
     *
     * @param nombre el nombre de la pista
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID de la pista.
     *
     * @return el ID de la pista
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la pista.
     *
     * @param id el ID de la pista
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Verifica si la pista está disponible.
     *
     * @return {@code true} si la pista está disponible, {@code false} en caso contrario
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece si la pista está disponible.
     *
     * @param disponible {@code true} si la pista está disponible, {@code false} en caso contrario
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Verifica si la pista es exterior.
     *
     * @return {@code true} si la pista es exterior, {@code false} en caso contrario
     */
    public boolean isEsExterior() {
        return esExterior;
    }

    /**
     * Establece si la pista es exterior.
     *
     * @param esExterior {@code true} si la pista es exterior, {@code false} en caso contrario
     */
    public void setEsExterior(boolean esExterior) {
        this.esExterior = esExterior;
    }

    /**
     * Obtiene el tamaño de la pista.
     *
     * @return el tamaño de la pista
     */
    public TamanoPista getTamano() {
        return tamano;
    }

    /**
     * Establece el tamaño de la pista.
     *
     * @param tamano el tamaño de la pista
     */
    public void setTamano(TamanoPista tamano) {
        this.tamano = tamano;
    }

    /**
     * Obtiene el número máximo de jugadores.
     *
     * @return el número máximo de jugadores
     */
    public int getMaxJugadores() {
        return maxJugadores;
    }

    /**
     * Establece el número máximo de jugadores.
     *
     * @param maxJugadores el número máximo de jugadores
     */
    public void setMaxJugadores(int maxJugadores) {
        this.maxJugadores = maxJugadores;
    }

    /**
     * Devuelve una representación en cadena de la pista.
     *
     * @return una cadena que representa la pista
     */
    @Override
    public String toString() {
        return "PistaDTO{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", disponible=" + disponible +
                ", esExterior=" + esExterior +
                ", tamano=" + tamano +
                ", maxJugadores=" + maxJugadores +
                '}';
    }
}
