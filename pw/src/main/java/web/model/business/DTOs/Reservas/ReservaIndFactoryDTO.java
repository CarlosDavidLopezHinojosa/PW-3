package web.model.business.DTOs.Reservas;

import java.time.LocalDateTime;

import web.model.business.DTOs.PistaDTO;


/**
 * La clase {@code ReservaIndFactoryDTO} es una fábrica para crear instancias de reservas individuales
 * de diferentes tipos: infantil, adultos y familiar. Esta clase proporciona métodos para crear
 * reservas según el tipo especificado y los detalles proporcionados.
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *      <li>{@code crearReservaInfantil}: para crear una reserva infantil.</li>
 *      <li>{@code crearReservaAdultos}: para crear una reserva de adultos.</li>
 *      <li>{@code crearReservaFamiliar}: para crear una reserva familiar.</li>
 * </ul>
 * 
 * </pre>
 * 
 * @see ReservaDTO
 * @see ReservaFactoryDTO
 * @see ReservaInfantilDTO
 * @see ReservaAdultosDTO
 * @see ReservaFamiliarDTO
 * @see PistaDTO.TamanoPista
 */    
public class ReservaIndFactoryDTO extends ReservaFactoryDTO{
    /**
     * Crea una reserva individual del tipo Infantil.
     *
     * @param tipoReserva El tipo de reserva a crear ("infantil", "adultos" o "familiar").
     * @param idUsuario   El identificador del usuario que realiza la reserva.
     * @param diaYHora    La fecha y hora para la cual se realiza la reserva.
     * @param duracion    La duración de la reserva en minutos.
     * @param idPista     El identificador de la pista reservada.
     * @param precio      El precio de la reserva.
     * @param descuento   El descuento aplicado a la reserva.
     * @param pistaTamano El tamaño de la pista reservada.
     * @param idReserva   El identificador de la reserva.
     * @param numAdultos  El número de adultos en la reserva.
     * @param numNinos    El número de niños en la reserva.
     * @return La instancia de Reserva creada según el tipo especificado.
     * @throws IllegalArgumentException Si el tipo de reserva no es reconocido.
     */
    @Override
    public ReservaInfantilDTO crearReservaInfantil(String tipoReserva, int idUsuario, LocalDateTime diaYhora, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int idReserva, int numAdultos, int numNinos){
        // Detalles para material: detalles[0] = Material.Tipo, detalles[1] = cantidad
        ReservaInfantilDTO reserva = new ReservaInfantilDTO();
        reserva.setDiaYHora(diaYhora);
        reserva.setIdUsuario(idUsuario);
        reserva.setDuracion(duracion);
        reserva.setIdPista(idPista);
        reserva.setPrecio(precio);
        reserva.setDescuento(descuento);
        reserva.setTamano_Pista(pistaTamano);
        reserva.setIdReserva(idReserva);
        reserva.setTipoReserva(tipoReserva);
        reserva.setNumAdultos(numAdultos);
        reserva.setNumNinos(numNinos);

        return reserva;
    }

    /**
     * Crea una reserva individual del tipo Adultos.
     *
     * @param tipoReserva El tipo de reserva a crear ("infantil", "adultos" o "familiar").
     * @param idUsuario   El identificador del usuario que realiza la reserva.
     * @param diaYHora    La fecha y hora para la cual se realiza la reserva.
     * @param duracion    La duración de la reserva en minutos.
     * @param idPista     El identificador de la pista reservada.
     * @param precio      El precio de la reserva.
     * @param descuento   El descuento aplicado a la reserva.
     * @param pistaTamano El tamaño de la pista reservada.
     * @param idReserva   El identificador de la reserva.
     * @param numAdultos  El número de adultos en la reserva.
     * @param numNinos    El número de niños en la reserva.
     * @return La instancia de Reserva creada según el tipo especificado.
     * @throws IllegalArgumentException Si el tipo de reserva no es reconocido.
     */
    @Override
    public ReservaAdultosDTO crearReservaAdultos(String tipoReserva, int idUsuario, LocalDateTime diaYhora, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int idReserva, int numAdultos, int numNinos){
        // Detalles para material: detalles[0] = Material.Tipo, detalles[1] = cantidad
        ReservaAdultosDTO reserva = new ReservaAdultosDTO();
        reserva.setDiaYHora(diaYhora);
        reserva.setIdUsuario(idUsuario);
        reserva.setDuracion(duracion);
        reserva.setIdPista(idPista);
        reserva.setPrecio(precio);
        reserva.setDescuento(descuento);
        reserva.setTamano_Pista(pistaTamano);
        reserva.setIdReserva(idReserva);
        reserva.setTipoReserva(tipoReserva);
        reserva.setNumAdultos(numAdultos);
        reserva.setNumNinos(numNinos);

        return reserva;
    }

    /**
     * Crea una reserva individual del tipo Familiar.
     *
     * @param tipoReserva El tipo de reserva a crear ("infantil", "adultos" o "familiar").
     * @param idUsuario   El identificador del usuario que realiza la reserva.
     * @param diaYHora    La fecha y hora para la cual se realiza la reserva.
     * @param duracion    La duración de la reserva en minutos.
     * @param idPista     El identificador de la pista reservada.
     * @param precio      El precio de la reserva.
     * @param descuento   El descuento aplicado a la reserva.
     * @param pistaTamano El tamaño de la pista reservada.
     * @param idReserva   El identificador de la reserva.
     * @param numAdultos  El número de adultos en la reserva.
     * @param numNinos    El número de niños en la reserva.
     * @return La instancia de Reserva creada según el tipo especificado.
     * @throws IllegalArgumentException Si el tipo de reserva no es reconocido.
     */
    @Override
    public ReservaFamiliarDTO crearReservaFamiliar(String tipoReserva, int idUsuario, LocalDateTime diaYhora, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int idReserva, int numAdultos, int numNinos){
        // Detalles para material: detalles[0] = Material.Tipo, detalles[1] = cantidad
        ReservaFamiliarDTO reserva = new ReservaFamiliarDTO();
        reserva.setDiaYHora(diaYhora);
        reserva.setIdUsuario(idUsuario);
        reserva.setDuracion(duracion);
        reserva.setIdPista(idPista);
        reserva.setPrecio(precio);
        reserva.setDescuento(descuento);
        reserva.setTamano_Pista(pistaTamano);
        reserva.setIdReserva(idReserva);
        reserva.setTipoReserva(tipoReserva);
        reserva.setNumAdultos(numAdultos);
        reserva.setNumNinos(numNinos);

        return reserva;
    }
    
}
