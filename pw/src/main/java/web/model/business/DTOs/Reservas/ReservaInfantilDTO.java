package web.model.business.DTOs.Reservas;

import web.model.business.DTOs.PistaDTO;


/**
 * La clase {@code ReservaInfantilDTO} representa una reserva específica para niños, extendiendo la funcionalidad
 * de la clase {@link ReservaDTO}. Esta clase incluye atributos y métodos adicionales para manejar el número de niños
 * en la reserva y asegurar que la pista asignada sea adecuada para actividades infantiles.
 * 
 * <p>Los atributos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getNinos()}: El número de niños en la reserva.</li>
 * </ul>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #ReservaInfantilDTO()}: Constructor por defecto que inicializa el número de niños a 1.</li>
 *   <li>{@link #getNinos()}: Obtiene el número de niños en la reserva.</li>
 *   <li>{@link #setNinos(int)}: Establece el número de niños en la reserva.</li>
 *   <li>{@link #toString()}: Devuelve una representación en cadena de la reserva infantil, incluyendo detalles como ID de usuario, fecha, duración, ID de pista, precio, descuento y número de niños.</li>
 *   <li>{@link #setIdPista(PistaDTO)}: Establece la pista para la reserva infantil si cumple con los requisitos de disponibilidad y tipo adecuado.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * ReservaInfantilDTO reserva = new ReservaInfantilDTO();
 * reserva.setNinos(5);
 * PistaDTO pista = new PistaDTO(PistaDTO.TamanoPista.MINIBASKET, true);
 * reserva.setIdPista(pista);
 * System.out.println(reserva);
 * }
 * </pre>
 * 
 * @see ReservaDTO
 * @see PistaDTO
 */
public class ReservaInfantilDTO extends ReservaDTO {
    /**
     * Constructor por defecto para una reserva infantil.
     * Inicializa el número de niños a 1.
     */
    public ReservaInfantilDTO() {
        this.setNumNinos(1);
        // La pista debe ser de tipo Minibasket.
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
     * @param numeroNinos el número de niños que participarán en la reserva.
     */
    public void setNinos(int numeroNinos) {
        this.setNumNinos(numeroNinos);
    }

    /**
     * Devuelve una representación en cadena de la reserva infantil.
     *
     * @return una cadena con la información de la reserva, incluyendo ID de usuario, fecha, duración,
     * ID de pista, precio, descuento y número de niños.
     */
    @Override
    public String toString() {
        return "Reserva [id usuario = " + getIdUsuario() + ", Fecha = " + getDiaYHora() +
               ", duracion = " + getDuracion() + ", id pista = " + getIdPista() +
               ", precio = " + getPrecio() + ", descuento = " + getDescuento() +
               ", Niños = " + getNinos() + ", id reserva = " + getIdReserva() + "]";
    }

    /**
     * Establece la pista para la reserva infantil si cumple los requisitos.
     * Verifica la disponibilidad y el tipo adecuado de la pista.
     *
     * @param pistaUsada la pista a ser asignada.
     */
    public void setIdPista(PistaDTO pistaUsada) {
        if (pistaUsada.isDisponible()) {
            // Verifica si la pista es de tipo Minibasket.
            if (pistaUsada.getTamano() == PistaDTO.TamanoPista.MINIBASKET) {
                this.idPista = pistaUsada.getId();
            } else {
                System.out.println("El tipo de pista no es adecuado");
            }
        } else {
            System.out.println("La pista no está disponible");
        }
    }
}
