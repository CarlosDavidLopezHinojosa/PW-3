package web.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.business.Beans.CustomerBean;
import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.data.DAOs.JugadorDAO;
import web.model.data.DAOs.ReservaDAO;

@WebServlet("/cancelarReserva")
public class cancelarReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean customer = (CustomerBean) session.getAttribute("customer");

        if (customer == null) {
            request.setAttribute("mensaje", "Usuario no autenticado.");
            request.getRequestDispatcher("/views/cancelarReserva.jsp").forward(request, response);
            return;
        }

        String email = customer.getEmail();
        JugadorDTO user = JugadorDAO.getUsuarioEmail(email); // Método para obtener JugadorDTO por email
        
        if (user == null) {
            request.setAttribute("mensaje", "Usuario no encontrado.");
        } else {
            List<ReservaDTO> reservas = ReservaDAO.obtenerReservasFuturasUsuario(user.getId());
            request.setAttribute("reservas", reservas);
        }

        request.getRequestDispatcher("/views/cancelarReserva.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idReservaStr = request.getParameter("idReserva");
        String confirmar = request.getParameter("confirmar");

        if (idReservaStr != null && confirmar != null) {
            // Confirmar cancelación de la reserva
            int idReserva = Integer.parseInt(idReservaStr);
            ReservaDTO reserva = ReservaDAO.obtenerReservaPorId(idReserva);
            if (reserva != null && reserva.getDiaYHora().isAfter(LocalDateTime.now().plusHours(24))) {
                ReservaDAO.eliminarReserva(idReserva);
                request.setAttribute("mensaje", "Reserva cancelada exitosamente.");
            } else {
                request.setAttribute("mensaje", "No se puede cancelar la reserva porque quedan menos de 24 horas para su inicio.");
            }
        } else if (idReservaStr != null) {
            // Preguntar al usuario si está seguro de la cancelación
            int idReserva = Integer.parseInt(idReservaStr);
            ReservaDTO reserva = ReservaDAO.obtenerReservaPorId(idReserva);
            if (reserva != null && reserva.getDiaYHora().isAfter(LocalDateTime.now().plusHours(24))) {
                request.setAttribute("mensaje", "¿Está seguro de que desea cancelar la reserva con ID: " + idReserva + "?");
                request.setAttribute("idReserva", idReserva);
            } else {
                request.setAttribute("mensaje", "No se puede cancelar la reserva porque quedan menos de 24 horas para su inicio.");
            }
        }

        doGet(request, response);
    }
}