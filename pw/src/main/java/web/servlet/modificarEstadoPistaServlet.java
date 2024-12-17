package web.servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.PistaDTO;
import web.model.data.DAOs.PistaDAO;



@WebServlet("/modificarEstadoPista")
public class modificarEstadoPistaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<PistaDTO> pistas = PistaDAO.obtenerPistas();
            request.setAttribute("pistas", pistas);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al obtener las pistas: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/modificarEstadoPista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPista = Integer.parseInt(request.getParameter("idPista"));
            boolean nuevaDisponibilidad = Boolean.parseBoolean(request.getParameter("nuevaDisponibilidad"));
            PistaDAO.modificarPistaDisponibilidad(idPista, nuevaDisponibilidad);
            request.setAttribute("mensaje", "Disponibilidad de la pista actualizada correctamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al actualizar la disponibilidad de la pista: " + e.getMessage());
        }

        doGet(request, response);
    }
}