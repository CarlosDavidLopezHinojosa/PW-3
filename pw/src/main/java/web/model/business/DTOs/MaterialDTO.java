package web.model.business.DTOs;


/**
 * La clase {@code MaterialDTO} representa un material que contiene información sobre su tipo,
 * uso exterior, estado y pista asociada. Los materiales se gestionan a través de esta
 * clase de transferencia de datos (DTO).
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getId()}: Obtiene el ID del material.</li>
 *   <li>{@link #setId(int)}: Establece el ID del material.</li>
 *   <li>{@link #getTipo()}: Obtiene el tipo de material.</li>
 *   <li>{@link #setTipo(TipoMaterial)}: Establece el tipo de material.</li>
 *   <li>{@link #isUsoExterior()}: Verifica si el material es para uso exterior.</li>
 *   <li>{@link #setUsoExterior(boolean)}: Establece si el material es para uso exterior.</li>
 *   <li>{@link #getEstado()}: Obtiene el estado del material.</li>
 *   <li>{@link #setEstado(EstadoMaterial)}: Establece el estado del material.</li>
 *   <li>{@link #getIdPista()}: Obtiene el ID de la pista asociada al material.</li>
 *   <li>{@link #setIdPista(int)}: Establece el ID de la pista asociada al material.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * MaterialDTO material = new MaterialDTO(1, MaterialDTO.TipoMaterial.PELOTAS, true, MaterialDTO.EstadoMaterial.DISPONIBLE, 5);
 * material.setEstado(MaterialDTO.EstadoMaterial.RESERVADO);
 * boolean esUsoExterior = material.isUsoExterior();
 * }
 * </pre>
 * 
 * @see MaterialDTO.TipoMaterial
 * @see MaterialDTO.EstadoMaterial
 */
public class MaterialDTO {

    // Enumeraciones para TipoMaterial y EstadoMaterial
    public enum TipoMaterial {
        PELOTAS, CANASTAS, CONOS
    }

    public enum EstadoMaterial {
        DISPONIBLE, RESERVADO, MAL_ESTADO
    }

    // Atributos del DTO
    private int id;
    private TipoMaterial tipo;
    private boolean usoExterior;
    private EstadoMaterial estado;
    private int idPista;

    /**
     * Constructor vacío (necesario para serialización).
     */
    public MaterialDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id          el ID del material
     * @param tipo        el tipo de material
     * @param usoExterior si el material es para uso exterior
     * @param estado      el estado del material
     * @param idPista     el ID de la pista asociada al material
     */
    public MaterialDTO(int id, TipoMaterial tipo, boolean usoExterior, EstadoMaterial estado, int idPista) {
        this.id = id;
        this.tipo = tipo;
        this.usoExterior = usoExterior;
        this.estado = estado;
        this.idPista = idPista;
    }

    /**
     * Obtiene el ID del material.
     *
     * @return el ID del material
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del material.
     *
     * @param id el ID del material
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo de material.
     *
     * @return el tipo de material
     */
    public TipoMaterial getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de material.
     *
     * @param tipo el tipo de material
     */
    public void setTipo(TipoMaterial tipo) {
        this.tipo = tipo;
    }

    /**
     * Verifica si el material es para uso exterior.
     *
     * @return {@code true} si el material es para uso exterior, {@code false} en caso contrario
     */
    public boolean isUsoExterior() {
        return usoExterior;
    }

    /**
     * Establece si el material es para uso exterior.
     *
     * @param usoExterior {@code true} si el material es para uso exterior, {@code false} en caso contrario
     */
    public void setUsoExterior(boolean usoExterior) {
        this.usoExterior = usoExterior;
    }

    /**
     * Obtiene el estado del material.
     *
     * @return el estado del material
     */
    public EstadoMaterial getEstado() {
        return estado;
    }

    /**
     * Establece el estado del material.
     *
     * @param estado el estado del material
     */
    public void setEstado(EstadoMaterial estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el ID de la pista asociada al material.
     *
     * @return el ID de la pista asociada al material
     */
    public int getIdPista() {
        return idPista;
    }

    /**
     * Establece el ID de la pista asociada al material.
     *
     * @param idPista el ID de la pista asociada al material
     */
    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    // Método toString() para una representación en cadena
    @Override
    public String toString() {
        return "MaterialDTO{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", usoExterior=" + usoExterior +
                ", estado='" + estado + '\'' +
                ", pistaId=" + idPista +
                '}';
    }
}

