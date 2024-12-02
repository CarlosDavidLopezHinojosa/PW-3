package web.model.business.DTOs;

/**
 * La clase {@code BonoDTO} representa un bono que contiene información sobre sesiones disponibles,
 * usuario asociado, tipo de reserva y tamaño de la pista. Los bonos se gestionan a través de esta
 * clase de transferencia de datos (DTO).
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getId()}: Obtiene el ID del bono.</li>
 *   <li>{@link #setId(int)}: Establece el ID del bono.</li>
 *   <li>{@link #getSesiones()}: Obtiene el número de sesiones disponibles en el bono.</li>
 *   <li>{@link #setSesiones(int)}: Establece el número de sesiones disponibles en el bono.</li>
 *   <li>{@link #getIdUser()}: Obtiene el ID del usuario asociado al bono.</li>
 *   <li>{@link #setIdUser(int)}: Establece el ID del usuario asociado al bono.</li>
 *   <li>{@link #getTipoReserva()}: Obtiene el tipo de reserva asociado al bono.</li>
 *   <li>{@link #setTipoReserva(String)}: Establece el tipo de reserva asociado al bono.</li>
 *   <li>{@link #getPistaTamano()}: Obtiene el tamaño de la pista asignada al bono.</li>
 *   <li>{@link #setPistaTamano(PistaDTO.TamanoPista)}: Establece el tamaño de la pista asignada al bono.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * BonoDTO bono = new BonoDTO(1, 10, 123, "Reserva1", PistaDTO.TamanoPista.GRANDE);
 * bono.setSesiones(9);
 * int sesionesRestantes = bono.getSesiones();
 * }
 * </pre>
 * 
 * @see PistaDTO.TamanoPista
 */
public class BonoDTO {

    private int id;
    private int sesiones;
    private int idUser;
    private String tipoReserva;
    private PistaDTO.TamanoPista pistaTamano;

    /**
     * Crea un nuevo bono con los parámetros especificados.
     *
     * @param id           Identificador único del bono.
     * @param sesiones     Número de sesiones disponibles en el bono.
     * @param idUser       Identificador del usuario al que pertenece el bono.
     * @param tipoReserva  Tipo de reserva asociada al bono.
     * @param pistaTamano  Tamaño de la pista asignada al bono.
     */
    public BonoDTO(int id, int sesiones, int idUser, String tipoReserva, PistaDTO.TamanoPista pistaTamano) {
        this.id = id;
        this.sesiones = sesiones;
        this.idUser = idUser;
        this.tipoReserva = tipoReserva;
        this.pistaTamano = pistaTamano;
    }

    /**
     * Obtiene el ID del bono.
     *
     * @return ID del bono.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del bono.
     *
     * @param id Nuevo ID del bono.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el número de sesiones disponibles en el bono.
     *
     * @return Número de sesiones disponibles.
     */
    public int getSesiones() {
        return sesiones;
    }

    /**
     * Establece el número de sesiones disponibles en el bono.
     *
     * @param sesiones Número de sesiones.
     */
    public void setSesiones(int sesiones) {
        this.sesiones = sesiones;
    }

    /**
     * Obtiene el ID del usuario asociado al bono.
     *
     * @return ID del usuario.
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Establece el ID del usuario asociado al bono.
     *
     * @param idUser Nuevo ID del usuario.
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Obtiene el tipo de reserva asociado al bono.
     *
     * @return Tipo de reserva.
     */
    public String getTipoReserva() {
        return tipoReserva;
    }

    /**
     * Establece el tipo de reserva asociado al bono.
     *
     * @param tipoReserva Nuevo tipo de reserva.
     */
    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    /**
     * Obtiene el tamaño de la pista asignada al bono.
     *
     * @return Tamaño de la pista.
     */
    public PistaDTO.TamanoPista getPistaTamano() {
        return pistaTamano;
    }

    /**
     * Establece el tamaño de la pista asignada al bono.
     *
     * @param pistaTamano Nuevo tamaño de la pista.
     */
    public void setPistaTamano(PistaDTO.TamanoPista pistaTamano) {
        this.pistaTamano = pistaTamano;
    }

}
