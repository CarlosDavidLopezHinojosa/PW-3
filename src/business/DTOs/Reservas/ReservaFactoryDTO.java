package business.DTOs.Reservas;
import business.DTOs.PistaDTO;
import java.time.LocalDate;



/**
 * La clase {@code ReservaFactoryDTO} es una fábrica para crear instancias de reservas individuales y con bono
 * de diferentes tipos: infantil, adultos y familiar. Esta clase proporciona métodos estáticos para crear
 * reservas según el tipo especificado y los detalles proporcionados.
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #crearReservaIndividual(String, int, LocalDate, int, int, float, float, PistaDTO.TamanoPista, int, int, int)}:
 *       Crea una reserva individual del tipo especificado.</li>
 *   <li>{@link #crearReservaBono(String, int, LocalDate, int, int, int, int, float, float, PistaDTO.TamanoPista, int, int, int)}:
 *       Crea una reserva con bono del tipo especificado.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * ReservaDTO reservaInfantil = ReservaFactoryDTO.crearReservaIndividual("infantil", 1, LocalDate.now(), 60, 101, 20.0f, 5.0f, PistaDTO.TamanoPista.PEQUENA, 1001, 0, 2);
 * ReservaDTO reservaAdultosBono = ReservaFactoryDTO.crearReservaBono("adultos", 2, LocalDate.now(), 2001, 1, 90, 102, 30.0f, 10.0f, PistaDTO.TamanoPista.GRANDE, 1002, 4, 0);
 * }
 * </pre>
 * 
 * @see ReservaDTO
 * @see ReservaInfantilDTO
 * @see ReservaAdultosDTO
 * @see ReservaFamiliarDTO
 * @see PistaDTO.TamanoPista
 */
public class ReservaFactoryDTO {

    /**
     * Crea una reserva individual del tipo especificado.
     *
     * @param tipoReserva El tipo de reserva a crear ("infantil", "adultos" o "familiar").
     * @param idUsuario   El identificador del usuario que realiza la reserva.
     * @param diaYHora    La fecha y hora para la cual se realiza la reserva.
     * @param duracion    La duración de la reserva en minutos.
     * @param idPista     El identificador de la pista reservada.
     * @return La instancia de Reserva creada según el tipo especificado.
     * @throws IllegalArgumentException Si el tipo de reserva no es reconocido.
     */
    public static ReservaDTO crearReservaIndividual(String tipoReserva, int idUsuario, LocalDate diaYhora, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int idReserva, int numAdultos, int numNinos) {
        ReservaDTO reserva1;
        switch (tipoReserva.toLowerCase()) {
            case "infantil":
                // Detalles para material: detalles[0] = Material.Tipo, detalles[1] = cantidad
                reserva1 = new ReservaInfantilDTO();
                reserva1.setDiaYHora(diaYhora);
                reserva1.setIdUsuario(idUsuario);

                reserva1.setDuracion(duracion);
                reserva1.setIdPista(idPista);
                reserva1.setPrecio(precio);
                reserva1.setDescuento(descuento);
                reserva1.setTamano_Pista(pistaTamano);
                reserva1.setIdReserva(idReserva);
                reserva1.setTipoReserva(tipoReserva);
                reserva1.setNumAdultos(1);
                reserva1.setNumNinos(numNinos);


                return reserva1;

            case "adultos":
                // Detalles para espacio: detalles[0] = tipoEspacio, detalles[1] = duracionHoras
                reserva1 = new ReservaAdultosDTO();
                reserva1.setDiaYHora(diaYhora);
                reserva1.setIdUsuario(idUsuario);
                reserva1.setDuracion(duracion);
                reserva1.setIdPista(idPista);
                reserva1.setPrecio(precio);
                reserva1.setDescuento(descuento);
                reserva1.setTamano_Pista(pistaTamano);
                reserva1.setIdReserva(idReserva);
                reserva1.setTipoReserva(tipoReserva);
                reserva1.setNumAdultos(numAdultos);
                reserva1.setNumNinos(0);
                return reserva1;

            case "familiar":
                // Detalles para espacio: detalles[0] = tipoEspacio, detalles[1] = duracionHoras
                reserva1 = new ReservaFamiliarDTO();
                reserva1.setDiaYHora(diaYhora);
                reserva1.setIdUsuario(idUsuario);
                reserva1.setDuracion(duracion);
                reserva1.setIdPista(idPista);
                reserva1.setPrecio(precio);
                reserva1.setDescuento(descuento);
                reserva1.setTamano_Pista(pistaTamano);
                reserva1.setIdReserva(idReserva);
                reserva1.setTipoReserva(tipoReserva);
                reserva1.setNumAdultos(numAdultos);
                reserva1.setNumNinos(numNinos);

                return reserva1;

            default:
                throw new IllegalArgumentException("Tipo de reserva desconocido: " + tipoReserva);
        }
    }

    /**
     * Crea una reserva con bono del tipo especificado.
     *
     * @param tipoReserva  El tipo de reserva a crear ("infantil", "adultos" o "familiar").
     * @param idUsuario    El identificador del usuario que realiza la reserva.
     * @param diaYHora     La fecha y hora para la cual se realiza la reserva.
     * @param idBono       El identificador del bono asociado a la reserva.
     * @param nSesionBono  El número de sesión del bono en uso.
     * @param duracion     La duración de la reserva en minutos.
     * @param idPista      El identificador de la pista reservada.
     * @return La instancia de Reserva creada según el tipo especificado y el bono.
     * @throws IllegalArgumentException Si el tipo de reserva no es reconocido.
     */
public static ReservaDTO crearReservaBono(String tipoReserva, int idUsuario, LocalDate diaYhora, int idBono, int nSesionBono, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int idReserva, int numAdultos, int numNinos) {
        ReservaDTO reserva1;
        switch (tipoReserva.toLowerCase()) {
            case "infantil":
                // Detalles para material: detalles[0] = Material.Tipo, detalles[1] = cantidad
                reserva1 = new ReservaInfantilDTO();
                reserva1.setDiaYHora(diaYhora);
                reserva1.setIdUsuario(idUsuario);
                reserva1.setIdBono(idBono);
                reserva1.setNSesionBono(nSesionBono);
                reserva1.setDuracion(duracion);
                reserva1.setIdPista(idPista);
                reserva1.setPrecio(precio);
                reserva1.setDescuento(descuento);
                reserva1.setTamano_Pista(pistaTamano);
                reserva1.setIdReserva(idReserva);

                reserva1.setTipoReserva(tipoReserva);
                reserva1.setNumAdultos(1);
                reserva1.setNumNinos(numNinos);
                return reserva1;

            case "adultos":
                // Detalles para espacio: detalles[0] = tipoEspacio, detalles[1] = duracionHoras
                reserva1 = new ReservaAdultosDTO();
                reserva1.setDiaYHora(diaYhora);
                reserva1.setIdUsuario(idUsuario);
                reserva1.setIdBono(idBono);
                reserva1.setNSesionBono(nSesionBono);
                reserva1.setDuracion(duracion);
                reserva1.setIdPista(idPista);
                reserva1.setPrecio(precio);
                reserva1.setDescuento(descuento);
                reserva1.setTamano_Pista(pistaTamano);
                reserva1.setIdReserva(idReserva);

                reserva1.setTipoReserva(tipoReserva);
                reserva1.setNumAdultos(numAdultos);
                reserva1.setNumNinos(0);

                return reserva1;

            case "familiar":
                // Detalles para espacio: detalles[0] = tipoEspacio, detalles[1] = duracionHoras
                reserva1 = new ReservaFamiliarDTO();
                reserva1.setDiaYHora(diaYhora);
                reserva1.setIdUsuario(idUsuario);
                reserva1.setIdBono(idBono);
                reserva1.setNSesionBono(nSesionBono);
                reserva1.setDuracion(duracion);
                reserva1.setIdPista(idPista);
                reserva1.setPrecio(precio);
                reserva1.setDescuento(descuento);
                reserva1.setTamano_Pista(pistaTamano);
                reserva1.setIdReserva(idReserva);

                reserva1.setTipoReserva(tipoReserva);
                reserva1.setNumAdultos(numAdultos);
                reserva1.setNumNinos(numNinos);

                return reserva1;

            default:
                throw new IllegalArgumentException("Tipo de reserva desconocido: " + tipoReserva);
        }
    }}
