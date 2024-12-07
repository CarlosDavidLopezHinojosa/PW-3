package web.model.data.DAOs;

import java.time.LocalDate;
import java.util.List;

import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.Reservas.ReservaBonoFactoryDTO;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.business.DTOs.Reservas.ReservaIndFactoryDTO;
import web.model.data.common.DBConnection;


/**
 * La clase ReservaDAO proporciona métodos para gestionar las reservas en el sistema.
 * Permite insertar nuevas reservas, obtener reservas futuras, obtener reservas futuras de un usuario específico,
 * obtener reservas de una pista en un día específico y eliminar reservas existentes.
 * 
 * <p>La información de las reservas se almacena en una base de datos a través de la clase DBConnection.
 * Cada reserva se representa mediante un objeto de la clase ReservaDTO, que contiene datos como el tipo de reserva,
 * el usuario, la fecha y hora, el bono, la duración, la pista, el precio, el descuento, el tamaño de la pista,
 * el número de adultos y el número de niños.</p>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #insertarReserva(String, int, LocalDate, int, int, int, int, float, float, PistaDTO.TamanoPista, int, int)}: Inserta una nueva reserva en la base de datos y devuelve un objeto ReservaDTO con los datos de la reserva insertada.</li>
 *   <li>{@link #obtenerReservasFuturas()}: Devuelve una lista con las reservas cuya fecha sea posterior a la fecha actual.</li>
 *   <li>{@link #obtenerReservasFuturasUsuario(int)}: Devuelve una lista con las reservas cuya fecha sea posterior a la fecha actual y el id del usuario sea el pasado como argumento.</li>
 *   <li>{@link #obtenerReservasPistaDia(int, LocalDate)}: Devuelve una lista con las reservas cuya fecha sea igual a la fecha actual para una pista específica.</li>
 *   <li>{@link #eliminarReserva(int)}: Elimina la reserva con el id pasado por parámetro de la base de datos.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * ReservaDTO nuevaReserva = ReservaDAO.insertarReserva("tipo", 1, LocalDate.now(), 1, 1, 60, 1, 100.0f, 10.0f, PistaDTO.TamanoPista.GRANDE, 2, 2);
 * List<ReservaDTO> reservasFuturas = ReservaDAO.obtenerReservasFuturas();
 * List<ReservaDTO> reservasUsuario = ReservaDAO.obtenerReservasFuturasUsuario(1);
 * List<ReservaDTO> reservasPistaDia = ReservaDAO.obtenerReservasPistaDia(1, LocalDate.now());
 * ReservaDAO.eliminarReserva(nuevaReserva.getId());
 * }
 * </pre>
 * 
 * @see data.DTOs.ReservaDTO
 * @see data.DBConnection
 */
public class ReservaDAO {

    /**
     * Inserta una nueva reserva en la base de datos y devuelve el objeto ReservaDTO creado.
     *
     * @param tipoReserva El tipo de reserva.
     * @param idUsuario El ID del usuario que realiza la reserva.
     * @param diaYHora La fecha y hora de la reserva.
     * @param idBono El ID del bono asociado a la reserva.
     * @param nSesionBono El número de sesión del bono.
     * @param duracion La duración de la reserva en minutos.
     * @param idPista El ID de la pista reservada.
     * @param precio El precio de la reserva.
     * @param descuento El descuento aplicado a la reserva.
     * @param pistaTamano El tamaño de la pista (enum PistaDTO.TamanoPista).
     * @param numAdultos El número de adultos en la reserva.
     * @param numNinos El número de niños en la reserva.
     * @return El objeto ReservaDTO que representa la reserva creada.
     */
    public static ReservaDTO insertarReserva(String tipoReserva, int idUsuario, LocalDate diaYHora, int idBono, int nSesionBono, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, int numAdultos, int numNinos){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        int id = conexion.getMaxId("Reserva") + 1;
        ReservaDTO reserva;
        if(idBono==-1 && nSesionBono==-1){
            ReservaIndFactoryDTO reservaIndFactory = new ReservaIndFactoryDTO();

            reserva = switch (tipoReserva) {
                case "ADULTOS" -> reservaIndFactory.crearReservaAdultos(tipoReserva, idUsuario, diaYHora, duracion, idPista, precio, descuento, pistaTamano, id, numAdultos, numNinos);
                case "INFANTIL" -> reservaIndFactory.crearReservaInfantil(tipoReserva, idUsuario, diaYHora, duracion, idPista, precio, descuento, pistaTamano, id, numAdultos, numNinos);
                case "FAMILIAR" -> reservaIndFactory.crearReservaFamiliar(tipoReserva, idUsuario, diaYHora, duracion, idPista, precio, descuento, pistaTamano, id, numAdultos, numNinos);
                default -> throw new IllegalArgumentException("Tipo de reserva desconocido: " + tipoReserva);
            };
            
        }
        else{
            switch (tipoReserva) {
                case "ADULTOS" -> reserva = ReservaBonoFactoryDTO.crearReservaAdultos(tipoReserva, idUsuario, diaYHora, idBono, nSesionBono, duracion, idPista, precio, descuento, pistaTamano, id, numAdultos, numNinos);
                case "INFANTIL" -> reserva = ReservaBonoFactoryDTO.crearReservaInfantil(tipoReserva, idUsuario, diaYHora, idBono, nSesionBono, duracion, idPista, precio, descuento, pistaTamano, id, numAdultos, numNinos);
                case "FAMILIAR" -> reserva = ReservaBonoFactoryDTO.crearReservaFamiliar(tipoReserva, idUsuario, diaYHora, idBono, nSesionBono, duracion, idPista, precio, descuento, pistaTamano, id, numAdultos, numNinos);
                default -> throw new IllegalArgumentException("Tipo de reserva desconocido: " + tipoReserva);
                }
        }
        
        conexion.insertIntoReserva(id, idUsuario, diaYHora, idBono, nSesionBono, duracion, idPista, precio, descuento, pistaTamano, tipoReserva, numAdultos, numNinos);

        conexion.closeConnection();

        return reserva;
    }

    /**
     * Obtiene una lista de reservas futuras desde la base de datos.
     *
     * @return Una lista de objetos ReservaDTO que representan las reservas futuras.
     */
    public static List<ReservaDTO> obtenerReservasFuturas(){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<ReservaDTO> reservas = conexion.selectReservasFuturas();
        conexion.closeConnection();
        return reservas;
    }

    /**
     * Obtiene una lista de reservas futuras para un usuario específico.
     *
     * @param idUsuario El ID del usuario para el cual se desean obtener las reservas futuras.
     * @return Una lista de objetos ReservaDTO que representan las reservas futuras del usuario.
     */
    public static List<ReservaDTO> obtenerReservasFuturasUsuario(int idUsuario) {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<ReservaDTO> reservas = conexion.selectReservasFuturasUsuario(idUsuario);
        conexion.closeConnection();
        return reservas;
    }
    
    /**
     * Obtiene una lista de reservas para una pista específica en un día determinado.
     *
     * @param idPista El identificador de la pista.
     * @param dia El día para el cual se desean obtener las reservas.
     * @return Una lista de objetos ReservaDTO que representan las reservas de la pista en el día especificado.
     */
    public static List<ReservaDTO> obtenerReservasPistaDia(int idPista, LocalDate dia){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<ReservaDTO> reservas = conexion.selectReservaPistaDia(idPista, dia);
        conexion.closeConnection();
        return reservas;
    }

    /**
     * Elimina una reserva de la base de datos.
     *
     * @param id El identificador de la reserva a eliminar.
     */
    public static void eliminarReserva(int id){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        conexion.deleteReserva(id);
        conexion.closeConnection();
    }

    public static List<ReservaDTO> obtenerReservas(){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<ReservaDTO> reservas = conexion.selectTodasReservas();
        conexion.closeConnection();
        return reservas;
    }
}
