package business.DTOs.Reservas;

import business.DTOs.PistaDTO;


/**
 * La clase {@code ReservaAdultosDTO} extiende la clase {@link ReservaDTO} y representa una reserva específica para adultos,
 * con un número de participantes y una pista de tipo ADULTOS.
 * 
 * <p>Los atributos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getNumAdultos()}: El número de participantes en la reserva.</li>
 * </ul>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #ReservaAdultosDTO()}: Constructor vacío que inicializa el número de participantes a 1.</li>
 *   <li>{@link #getParticipantes()}: Obtiene el número de participantes en la reserva.</li>
 *   <li>{@link #setParticipantes(int)}: Establece el número de participantes en la reserva.</li>
 *   <li>{@link #toString()}: Devuelve una representación en cadena de la reserva, incluyendo detalles sobre el usuario, la fecha, la duración, la pista y el número de participantes.</li>
 *   <li>{@link #setIdPista(PistaDTO)}: Establece la pista para la reserva si está disponible y es de tipo ADULTOS.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * ReservaAdultosDTO reserva = new ReservaAdultosDTO();
 * reserva.setParticipantes(4);
 * PistaDTO pista = new PistaDTO(1, PistaDTO.TamanoPista.ADULTOS, true);
 * reserva.setIdPista(pista);
 * System.out.println(reserva);
 * }
 * </pre>
 * 
 * @see ReservaDTO
 * @see PistaDTO
 */
public class ReservaAdultosDTO extends ReservaDTO {


    /**
     * Constructor vacío que establece el número de participantes a 1.
     * Además, la pista debe ser de tipo ADULTOS.
     */
    public ReservaAdultosDTO() {
        setNumAdultos(1);
    }

    /**
     * Obtiene el número de participantes en la reserva.
     *
     * @return El número de participantes.
     */
    public int getParticipantes() {
        return getNumAdultos();
    }

    /**
     * Establece el número de participantes en la reserva.
     *
     * @param participantes El número de participantes.
     */
    public void setParticipantes(int participantes) {
        this.setNumAdultos(participantes);
    }

    /**
     * Devuelve una representación en cadena de la reserva, incluyendo detalles
     * sobre el usuario, la fecha, la duración, la pista y el número de participantes.
     *
     * @return Una cadena con la información de la reserva.
     */
    @Override
    public String toString() {
        return "Reserva [id usuario = " + getIdUsuario() + ", Fecha = " + getDiaYHora() + 
               ", duracion = " + getDuracion() + ", id pista = " + getIdPista() + 
               ", precio = " + getPrecio() + ", descuento = " + getDescuento() + 
               ", Participantes = " + this.getNumAdultos() + ", id reserva = " + getIdReserva() + "]";
    }

    /**
     * Establece la pista para la reserva si está disponible y es de tipo ADULTOS.
     * Imprime mensajes en caso de que la pista no cumpla con los requisitos.
     *
     * @param pistaUsada La pista que se intenta reservar.
     */
    public void setIdPista(PistaDTO pistaUsada) {
        if (pistaUsada.isDisponible()) {
            // Comprueba que la pista es del tamaño adecuado antes de asignarla
            if (pistaUsada.getTamano() == PistaDTO.TamanoPista.ADULTOS) {
                this.idPista = pistaUsada.getId();
            } else {
                System.out.println("El tipo de pista no es adecuado");
            }
        } else {
            System.out.println("La pista no está disponible");
        }
    }
}
