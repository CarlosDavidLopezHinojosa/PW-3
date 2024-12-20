package web.model.business.Gestores;

import java.sql.SQLException;
import java.util.List;

import web.model.business.DTOs.BonoDTO;
import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.data.DAOs.BonoDAO;

public class GestorDeBonos {
    private static GestorDeBonos gestor;

    private GestorDeBonos() {}

    public static synchronized GestorDeBonos getGestor() {
        if (gestor == null) {
            gestor = new GestorDeBonos();
        }
        return gestor;
    }

    public BonoDTO insertarBono(int sesiones, int idUser, String tipoReserva, PistaDTO.TamanoPista pistaTamano) {
        return BonoDAO.insertarBono(sesiones, idUser, tipoReserva, pistaTamano);
    }

    public List<BonoDTO> mostrarBonosUsuario(JugadorDTO user) {
        return BonoDAO.mostrarBonosUsuario(user);
    }

    public List<BonoDTO> mostrarBonosUsuario(int idUser) {
        return BonoDAO.mostrarBonosUsuario(idUser);
    }

    public void restarSesion(int idBono) throws SQLException {
        BonoDAO.restarSesion(idBono);
    }

    public List<BonoDTO> obtenerBonosDisponibles(int idUsuario, String tipoReserva) {
        return BonoDAO.obtenerBonosDisponibles(idUsuario, tipoReserva);
    }

    public static boolean esBonoValidoParaReserva_G(BonoDTO bono, String pista) {
        return BonoDAO.esBonoValidoParaReserva(bono, pista);
    }
}