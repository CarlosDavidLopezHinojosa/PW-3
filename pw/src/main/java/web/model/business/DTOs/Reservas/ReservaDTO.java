package web.model.business.DTOs.Reservas;

import java.time.LocalDate;

import web.model.business.DTOs.PistaDTO;


/**
 * La clase {@code ReservaDTO} representa una reserva con atributos específicos para el usuario, fecha y hora, bono, pista, precio, descuento, tipo de reserva y número de adultos y niños.
 * 
 * <p>Los atributos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getIdUsuario()}: El ID del usuario que realiza la reserva.</li>
 *   <li>{@link #getDiaYHora()}: La fecha y hora de la reserva.</li>
 *   <li>{@link #getIdBono()}: El ID del bono asociado a la reserva.</li>
 *   <li>{@link #getNSesionBono()}: El número de sesión del bono.</li>
 *   <li>{@link #getDuracion()}: La duración de la reserva.</li>
 *   <li>{@link #getIdPista()}: El ID de la pista reservada.</li>
 *   <li>{@link #getPrecio()}: El precio de la reserva.</li>
 *   <li>{@link #getDescuento()}: El descuento aplicado a la reserva.</li>
 *   <li>{@link #getTamano_Pista()}: El tamaño de la pista reservada.</li>
 *   <li>{@link #getIdReserva()}: El ID de la reserva.</li>
 *   <li>{@link #getTipoReserva()}: El tipo de reserva (ADULTOS, FAMILIAR, INFANTIL).</li>
 *   <li>{@link #getNumAdultos()}: El número de adultos en la reserva.</li>
 *   <li>{@link #getNumNinos()}: El número de niños en la reserva.</li>
 * </ul>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #ReservaDTO()}: Constructor por defecto.</li>
 *   <li>{@link #ReservaDTO(int, int, LocalDate, int, int, int, int, float, float, PistaDTO.TamanoPista, String, int, int)}: Constructor con todos los campos.</li>
 *   <li>{@link #getIdUsuario()}: Obtiene el ID del usuario que realiza la reserva.</li>
 *   <li>{@link #setIdUsuario(int)}: Establece el ID del usuario que realiza la reserva.</li>
 *   <li>{@link #getDiaYHora()}: Obtiene la fecha y hora de la reserva.</li>
 *   <li>{@link #setDiaYHora(LocalDate)}: Establece la fecha y hora de la reserva.</li>
 *   <li>{@link #getIdBono()}: Obtiene el ID del bono asociado a la reserva.</li>
 *   <li>{@link #setIdBono(int)}: Establece el ID del bono asociado a la reserva.</li>
 *   <li>{@link #getNSesionBono()}: Obtiene el número de sesión del bono.</li>
 *   <li>{@link #setNSesionBono(int)}: Establece el número de sesión del bono.</li>
 *   <li>{@link #getDuracion()}: Obtiene la duración de la reserva.</li>
 *   <li>{@link #setDuracion(int)}: Establece la duración de la reserva.</li>
 *   <li>{@link #getIdPista()}: Obtiene el ID de la pista reservada.</li>
 *   <li>{@link #setIdPista(int)}: Establece el ID de la pista reservada.</li>
 *   <li>{@link #getPrecio()}: Obtiene el precio de la reserva.</li>
 *   <li>{@link #setPrecio(float)}: Establece el precio de la reserva.</li>
 *   <li>{@link #getDescuento()}: Obtiene el descuento aplicado a la reserva.</li>
 *   <li>{@link #setDescuento(float)}: Establece el descuento aplicado a la reserva.</li>
 *   <li>{@link #getTamano_Pista()}: Obtiene el tamaño de la pista reservada.</li>
 *   <li>{@link #setTamano_Pista(PistaDTO.TamanoPista)}: Establece el tamaño de la pista reservada.</li>
 *   <li>{@link #getIdReserva()}: Obtiene el ID de la reserva.</li>
 *   <li>{@link #setIdReserva(int)}: Establece el ID de la reserva.</li>
 *   <li>{@link #getTipoReserva()}: Obtiene el tipo de reserva.</li>
 *   <li>{@link #setTipoReserva(String)}: Establece el tipo de reserva.</li>
 *   <li>{@link #getNumAdultos()}: Obtiene el número de adultos en la reserva.</li>
 *   <li>{@link #setNumAdultos(int)}: Establece el número de adultos en la reserva.</li>
 *   <li>{@link #getNumNinos()}: Obtiene el número de niños en la reserva.</li>
 *   <li>{@link #setNumNinos(int)}: Establece el número de niños en la reserva.</li>
 *   <li>{@link #toString()}: Devuelve una representación en cadena de la reserva.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * ReservaDTO reserva = new ReservaDTO(1, 123, LocalDate.now(), 456, 1, 60, 789, 100.0f, 10.0f, PistaDTO.TamanoPista.GRANDE, "ADULTOS", 2, 0);
 * System.out.println(reserva);
 * }
 * </pre>
 * 
 * @see PistaDTO
 */
public class ReservaDTO {

    private int idUsuario;
    private LocalDate diaYHora; // Fecha y hora de la reserva
    private int idBono;
    private int nSesionBono;
    private int duracion;
    public int idPista;
    private float precio;
    private float descuento;
    private PistaDTO.TamanoPista pistaTamano;
    private int idReserva;
    private String tipo;
    private int numAdultos;
    private int numNinos;

    public enum tipoReserva {ADULTOS,FAMILIAR,INFANTIL};
    
    // Constructor vacío
    public ReservaDTO() {
    }

    /**
     * Constructor con todos los campos.
     *
     * @param idReserva   El ID de la reserva.
     * @param idUsuario   El ID del usuario que realiza la reserva.
     * @param diaYHora    La fecha y hora de la reserva.
     * @param idBono      El ID del bono asociado a la reserva.
     * @param nSesionBono El número de sesión del bono.
     * @param duracion    La duración de la reserva en minutos.
     * @param idPista     El ID de la pista reservada.
     * @param precio      El precio de la reserva.
     * @param descuento   El descuento aplicado a la reserva.
     * @param pistaTamano El tamaño de la pista reservada.
     * @param tipo        El tipo de reserva (ADULTOS, FAMILIAR, INFANTIL).
     * @param numAdultos  El número de adultos en la reserva.
     * @param numNinos    El número de niños en la reserva.
     */
    public ReservaDTO(int idReserva, int idUsuario, LocalDate diaYHora, int idBono, int nSesionBono, int duracion,
                      int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano,String tipo, int numAdultos, int numNinos) {
        this.idUsuario = idUsuario;
        this.diaYHora = diaYHora;
        this.idBono = idBono;
        this.nSesionBono = nSesionBono;
        this.duracion = duracion;
        this.idPista = idPista;
        this.precio = precio;
        this.descuento = descuento;
        this.pistaTamano = pistaTamano;
        this.idReserva = idReserva;

        this.tipo = tipo;
        this.numAdultos = numAdultos;
        this.numNinos = numNinos;
    }

    /**
     * Obtiene el ID del usuario que realiza la reserva.
     *
     * @return El ID del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario que realiza la reserva.
     *
     * @param idUsuario El ID del usuario.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene la fecha y hora de la reserva.
     *
     * @return La fecha y hora de la reserva.
     */
    public LocalDate getDiaYHora() {
        return diaYHora;
    }

    /**
     * Establece la fecha y hora de la reserva.
     *
     * @param diaYHora La fecha y hora de la reserva.
     */
    public void setDiaYHora(LocalDate diaYHora) {
        this.diaYHora = diaYHora;
    }

    /**
     * Obtiene el ID del bono asociado a la reserva.
     *
     * @return El ID del bono.
     */
    public int getIdBono() {
        return idBono;
    }

    /**
     * Establece el ID del bono asociado a la reserva.
     *
     * @param idBono El ID del bono.
     */
    public void setIdBono(int idBono) {
        this.idBono = idBono;
    }

    /**
     * Obtiene el número de sesión del bono.
     *
     * @return El número de sesión del bono.
     */
    public int getNSesionBono() {
        return nSesionBono;
    }

    /**
     * Establece el número de sesión del bono.
     *
     * @param nSesionBono El número de sesión del bono.
     */
    public void setNSesionBono(int nSesionBono) {
        this.nSesionBono = nSesionBono;
    }

    /**
     * Obtiene la duración de la reserva en minutos.
     *
     * @return La duración de la reserva en minutos.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración de la reserva en minutos.
     *
     * @param duracion La duración de la reserva en minutos.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene el ID de la pista reservada.
     *
     * @return El ID de la pista reservada.
     */
    public int getIdPista() {
        return idPista;
    }

    /**
     * Establece el ID de la pista reservada.
     *
     * @param idPista El ID de la pista reservada.
     */
    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    /**
     * Obtiene el precio de la reserva.
     *
     * @return El precio de la reserva.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la reserva.
     *
     * @param precio El precio de la reserva.
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el descuento aplicado a la reserva.
     *
     * @return El descuento aplicado a la reserva.
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * Establece el descuento aplicado a la reserva.
     *
     * @param descuento El descuento aplicado a la reserva.
     */
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    /**
     * Obtiene el tamaño de la pista reservada.
     *
     * @return El tamaño de la pista.
     */
    public PistaDTO.TamanoPista getTamano_Pista() {
        return pistaTamano;
    }

    /**
     * Establece el tamaño de la pista reservada.
     *
     * @param tamano El tamaño de la pista.
     */
    public void setTamano_Pista(PistaDTO.TamanoPista tamano) {
        this.pistaTamano = tamano;
    }

    /**
     * Obtiene el ID de la reserva.
     *
     * @return El ID de la reserva.
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Establece el ID de la reserva.
     *
     * @param idReserva El ID de la reserva.
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Obtiene el tipo de reserva.
     *
     * @return El tipo de reserva.
     */
    public String getTipoReserva() {
        return tipo;
    }

    /**
     * Establece el tipo de reserva.
     *
     * @param tipo El tipo de reserva.
     */
    public void setTipoReserva(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el número de adultos en la reserva.
     *
     * @return El número de adultos en la reserva.
     */
    public int getNumAdultos() {
        return numAdultos;
    }

    /**
     * Establece el número de adultos en la reserva.
     *
     * @param numAdultos El número de adultos en la reserva.
     */
    public void setNumAdultos(int numAdultos) {
        this.numAdultos = numAdultos;
    }

    /**
     * Obtiene el número de niños en la reserva.
     *
     * @return El número de niños en la reserva.
     */
    public int getNumNinos() {
        return numNinos;
    }

    /**
     * Establece el número de niños en la reserva.
     *
     * @param numNinos El número de niños en la reserva.
     */
    public void setNumNinos(int numNinos) {
        this.numNinos = numNinos;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "idUsuario='" + idUsuario + '\'' +
                ", diaYHora=" + diaYHora +
                ", idBono='" + idBono + '\'' +
                ", nSesionBono=" + nSesionBono +
                ", duracion=" + duracion +
                ", idPista='" + idPista + '\'' +
                ", precio=" + precio +
                ", descuento=" + descuento +
                ", pistaTamano=" + pistaTamano +
                ", idReserva= " + idReserva +
                '}';
    }
}
