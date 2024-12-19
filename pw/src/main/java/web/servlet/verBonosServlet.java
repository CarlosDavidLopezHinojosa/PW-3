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
import web.model.business.DTOs.BonoDTO;
import web.model.business.Gestores.GestorDeBonos;

@WebServlet("/verBonos")
public class verBonosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

            if (customer == null) {
                request.setAttribute("mensaje", "Usuario no autenticado.");
                request.getRequestDispatcher("/views/verBonos.jsp").forward(request, response);
                return;
            }

            GestorDeBonos gestor = GestorDeBonos.getGestor();
            List<BonoDTO> bonos = gestor.mostrarBonosUsuario(customer.getId());
            request.setAttribute("bonos", bonos);

            request.getRequestDispatcher("/views/verBonos.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al obtener los bonos: " + e.getMessage());
            request.getRequestDispatcher("/views/verBonos.jsp").forward(request, response);
        }
    }
}
