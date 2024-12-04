package web.model.business.DTOs.Reservas;

import web.model.business.DTOs.PistaDTO;


/**
 * La clase {@code ReservaFamiliarDTO} representa una reserva familiar con atributos específicos para el número de adultos y niños.
 * Hereda de la clase {@link ReservaDTO} y añade funcionalidades adicionales para manejar reservas familiares.
 * 
 * <p>Los atributos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getAdultos()}: El número de adultos en la reserva.</li>
 *   <li>{@link #getNinos()}: El número de niños en la reserva.</li>
 * </ul>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #ReservaFamiliarDTO()}: Constructor por defecto que inicializa los valores de nAdultos a 1 y nNinos a 0.</li>
 *   <li>{@link #getAdultos()}: Obtiene el número de adultos en la reserva.</li>
 *   <li>{@link #setAdultos(int)}: Establece el número de adultos en la reserva.</li>
 *   <li>{@link #getNinos()}: Obtiene el número de niños en la reserva.</li>
 *   <li>{@link #setNinos(int)}: Establece el número de niños en la reserva.</li>
 *   <li>{@link #toString()}: Devuelve una representación en cadena de la reserva familiar.</li>
 *   <li>{@link #setIdPista(PistaDTO)}: Establece la pista para la reserva familiar si cumple los requisitos de disponibilidad y tipo adecuado.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * ReservaFamiliarDTO reserva = new ReservaFamiliarDTO();
 * reserva.setAdultos(2);
 * reserva.setNinos(3);
 * PistaDTO pista = new PistaDTO(PistaDTO.TamanoPista.MINIBASKET, true);
 * reserva.setIdPista(pista);
 * System.out.println(reserva);
 * }
 * </pre>
 * 
 * @see ReservaDTO
 * @see PistaDTO
 */
public class ReservaFamiliarDTO extends ReservaDTO {

    /**
     * Constructor por defecto para una reserva familiar.
     * Inicializa los valores de nAdultos a 1 y nNinos a 0.
     */
    public ReservaFamiliarDTO() {
        setAdultos(1);
        setNinos(0);
        // La pista debe ser de tipo Minibasket o 3x3.
    }

    /**
     * Obtiene el número de adultos en la reserva.
     *
     * @return el número de adultos en la reserva.
     */
    public int getAdultos() {
        return getNumAdultos();
    }

    /**
     * Establece el número de adultos en la reserva.
     *
     * @param adultos el número de adultos que participan en la reserva.
     */
    public void setAdultos(int adultos) {
        this.setNumAdultos(adultos);
    }

    /**
     * Obtiene el número de niños en la reserva.
     *
     * @return el número de niños en la reserva.
     */
    public int getNinos() {
        return this.getNumNinos();
    }

    /**
     * Establece el número de niños en la reserva.
     *
     * @param ninos el número de niños que participan en la reserva.
     */
    public void setNinos(int ninos) {
        this.setNumNinos(ninos);
    }

    /**
     * Devuelve una representación en cadena de la reserva familiar.
     *
     * @return una cadena con la información de la reserva, incluyendo ID de usuario, fecha, duración,
     * ID de pista, precio, descuento, número de niños y adultos.
     */
    @Override
    public String toString() {
        return "Reserva [id usuario = " + getIdUsuario() + ", Fecha = " + getDiaYHora() +
               ", duracion = " + getDuracion() + ", id pista = " + getIdPista() +
               ", precio = " + getPrecio() + ", descuento = " + getDescuento() +
               ", Niños = " + this.getNinos() + ", Adultos = " + this.getAdultos() + ", id reserva = " + getIdReserva() + "]";
    }

    /**
     * Establece la pista para la reserva familiar si cumple los requisitos.
     * Verifica la disponibilidad y el tipo adecuado de la pista.
     *
     * @param pistaUsada la pista a ser asignada.
     */
    public void setIdPista(PistaDTO pistaUsada) {
        if (pistaUsada.isDisponible()) {
            // Verifica si la pista es de tipo Minibasket o 3x3.
            if (pistaUsada.getTamano() == PistaDTO.TamanoPista.VS3 ||
                pistaUsada.getTamano() == PistaDTO.TamanoPista.MINIBASKET) {
                this.idPista = pistaUsada.getId();
            } else {
                // Mensaje si el tipo de pista no es adecuado
                System.out.println("El tipo de pista no es adecuado");
            }
        } else {
            // Mensaje si la pista no está disponible
            System.out.println("La pista no está disponible");
        }
    }
}
