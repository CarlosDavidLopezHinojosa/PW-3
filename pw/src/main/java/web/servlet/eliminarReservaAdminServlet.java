package web.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.data.DAOs.ReservaDAO;




@WebServlet("/eliminarReservaAdmin")
public class eliminarReservaAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ReservaDTO> reservasFuturas = ReservaDAO.obtenerReservasFuturas();
            request.setAttribute("reservasFuturas", reservasFuturas);
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener las reservas futuras: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/eliminarReservaAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int reservaId = Integer.parseInt(request.getParameter("reservaId"));

            ReservaDAO.eliminarReserva(reservaId);

            request.setAttribute("mensaje", "Reserva eliminada exitosamente.");
        } catch (Exception e) {
            request.setAttribute("error", "Error al eliminar la reserva: " + e.getMessage());
        }

        doGet(request, response);
    }
}
