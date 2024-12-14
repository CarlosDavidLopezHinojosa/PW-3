package web.model.business.DTOs.Reservas;
import java.time.LocalDateTime;

import web.model.business.DTOs.PistaDTO;



/**
 * La clase {@code ReservaFactoryDTO} es una fábrica para crear instancias de reservas individuales y con bono
 * de diferentes tipos: infantil, adultos y familiar.
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@code crearReservaInfantil}: para crear una reserva infantil.</li>
 *   <li>{@code crearReservaAdultos}: para crear una reserva de adultos.</li>
 *   <li>{@code crearReservaFamiliar}: para crear una reserva familiar.</li>
 * </ul>
 * 
 * </pre>
 * 
 * @see ReservaDTO
 * @see ReservaInfantilDTO
 * @see ReservaAdultosDTO
 * @see ReservaFamiliarDTO
 * @see PistaDTO.TamanoPista
 */    

public abstract class ReservaFactoryDTO {
    public abstract ReservaInfantilDTO crearReservaInfantil(String tipoReserva, int idUsuario, LocalDateTime diaYhora, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int idReserva, int numAdultos, int numNinos);

    public abstract ReservaAdultosDTO crearReservaAdultos(String tipoReserva, int idUsuario, LocalDateTime diaYhora, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int idReserva, int numAdultos, int numNinos);

    public abstract ReservaFamiliarDTO crearReservaFamiliar(String tipoReserva, int idUsuario, LocalDateTime diaYhora, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int idReserva, int numAdultos, int numNinos);

}
