package web.model.data.DAOs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.model.business.DTOs.BonoDTO;
import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.data.common.DBConnection;



/**
 * La clase BonoDAO proporciona métodos para interactuar con la base de datos de bonos.
 * Permite insertar nuevos bonos y recuperar la lista de bonos de un usuario específico.
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #insertarBono(int, int, String, PistaDTO.TamanoPista)}: Inserta un nuevo bono en la base de datos y devuelve un objeto BonoDTO con los datos del bono insertado.</li>
 *   <li>{@link #mostrarBonosUsuario(JugadorDTO)}: Devuelve una lista con todos los bonos de un usuario específico.</li>
 *   <li>{@link #mostrarBonosUsuario(int)}: Devuelve una lista con todos los bonos de un usuario específico.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * BonoDTO nuevoBono = BonoDAO.insertarBono(10, 1, "ReservaTipo", PistaDTO.TamanoPista.GRANDE);
 * List<BonoDTO> bonosUsuario = BonoDAO.mostrarBonosUsuario(jugador);
 * }
 * </pre>
 * 
 * @see data.DTOs.BonoDTO
 * @see data.DTOs.JugadorDTO
 * @see data.DTOs.PistaDTO
 * @see data.DBConnection
 */
public class BonoDAO {
    
    /**
     * Inserta un nuevo bono en la base de datos y devuelve el objeto BonoDTO creado.
     *
     * @param sesiones El número de sesiones del bono.
     * @param idUser El ID del usuario al que pertenece el bono.
     * @param tipoReserva El tipo de reserva asociado al bono.
     * @param pistaTamano El tamaño de la pista asociado al bono.
     * @return El objeto BonoDTO que representa el bono insertado.
     */
    public static BonoDTO insertarBono(int sesiones, int idUser, String tipoReserva, PistaDTO.TamanoPista pistaTamano){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        int id = conexion.getMaxId("Bono") + 1;
        BonoDTO bono = new BonoDTO(id, sesiones, idUser, tipoReserva, pistaTamano);
        
        conexion.insertIntoBono(id, sesiones, idUser, tipoReserva, pistaTamano);
        conexion.closeConnection();

        return bono;
    }

    /**
     * Recupera una lista de bonos asociados a un usuario específico.
     *
     * @param user El objeto JugadorDTO que representa al usuario del cual se desean obtener los bonos.
     * @return Una lista de objetos BonoDTO que representan los bonos del usuario.
     */
    public static List<BonoDTO> mostrarBonosUsuario(JugadorDTO user){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<BonoDTO> bonos = conexion.selectBonos(user.getId());
        conexion.closeConnection();
        return bonos;
    }

    /**
     * Recupera una lista de bonos asociados a un usuario específico.
     *
     * @param idUser El ID del usuario del cual se desean obtener los bonos.
     * @return Una lista de objetos BonoDTO que representan los bonos del usuario.
     */
    public static List<BonoDTO> mostrarBonosUsuario(int idUser){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<BonoDTO> bonos = conexion.selectBonos(idUser);
        conexion.closeConnection();
        return bonos;
    }

    public static void restarSesion(int idBono) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        try {
            int sesiones = dbConnection.obtenerSesionesBono(idBono);
            if (sesiones > 1) {
                dbConnection.actualizarSesionesBono(idBono, sesiones - 1);
            } else {
                dbConnection.eliminarBono(idBono);
            }
        } finally {
            dbConnection.closeConnection();
        }
    }

        public static List<BonoDTO> obtenerBonosDisponibles(int idUsuario, String tipoReserva) {
        List<BonoDTO> bonosDisponibles = new ArrayList<>();
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        try {
            List<BonoDTO> bonos = dbConnection.selectBonos(idUsuario);
            for (BonoDTO bono : bonos) {
                if (bono.getSesiones() > 0 && esBonoValidoParaReserva(bono, tipoReserva)) {
                    bonosDisponibles.add(bono);
                }
            }
        } finally {
            dbConnection.closeConnection();
        }
        return bonosDisponibles;
    }

    public static boolean esBonoValidoParaReserva(BonoDTO bono, String tipoReserva) {
        switch (tipoReserva.toUpperCase()) {
            case "ADULTOS":
                return bono.getPistaTamano() == PistaDTO.TamanoPista.ADULTOS;
            case "INFANTIL":
                return bono.getPistaTamano() == PistaDTO.TamanoPista.MINIBASKET;
            case "FAMILIAR":
                return bono.getPistaTamano() == PistaDTO.TamanoPista.MINIBASKET || bono.getPistaTamano() == PistaDTO.TamanoPista.VS3;
            default:
                return false;
        }
    }


}
