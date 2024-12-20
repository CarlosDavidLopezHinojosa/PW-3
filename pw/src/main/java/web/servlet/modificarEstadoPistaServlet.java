package web.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.PistaDTO;
import web.model.business.Gestores.GestorDePistas;
import web.model.data.common.DBConnection;



@WebServlet("/modificarEstadoPista")
public class modificarEstadoPistaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<PistaDTO> pistas = GestorDePistas.obtenerPistas();
            request.setAttribute("pistas", pistas);
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener las pistas: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/modificarEstadoPista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPista = Integer.parseInt(request.getParameter("idPista"));
            boolean nuevaDisponibilidad = Boolean.parseBoolean(request.getParameter("nuevaDisponibilidad"));
            GestorDePistas.modificarPistaDisponibilidad(idPista, nuevaDisponibilidad);
            request.setAttribute("mensaje", "Disponibilidad de la pista actualizada correctamente.");
        } catch (Exception e) {
            request.setAttribute("error", "Error al actualizar la disponibilidad de la pista: " + e.getMessage());
        }

        doGet(request, response);
    }

    /**
     * Modifica la disponibilidad de una pista en la base de datos.
     *
     * @param idPista El id de la pista cuya disponibilidad se va a modificar.
     * @param nuevaDisponibilidad La nueva disponibilidad que se asignará a la pista.
     */
    public static void modificarPistaDisponibilidad(int idPista, boolean nuevaDisponibilidad) {
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        conexion.updatePistaDisponibilidad(idPista, nuevaDisponibilidad);
        conexion.closeConnection();
    }
}