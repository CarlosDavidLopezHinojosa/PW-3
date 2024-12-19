package web.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.business.Beans.CustomerBean;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.business.Gestores.GestorDeReservas;

@WebServlet("/verReservas")
public class verReservasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

            if (customer == null) {
                request.setAttribute("mensaje", "Usuario no autenticado.");
                request.getRequestDispatcher("/views/verReservas.jsp").forward(request, response);
                return;
            }

            List<ReservaDTO> reservas = GestorDeReservas.obtenerReservasFuturasUsuario(customer.getId());
            request.setAttribute("reservas", reservas);

            request.getRequestDispatcher("/views/verReservas.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al obtener las reservas: " + e.getMessage());
            request.getRequestDispatcher("/views/verReservas.jsp").forward(request, response);
        }
    }
}
