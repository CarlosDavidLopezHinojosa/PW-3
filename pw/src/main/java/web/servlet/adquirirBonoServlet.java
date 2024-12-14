package web.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.business.Beans.CustomerBean;
import web.model.business.DTOs.BonoDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.data.DAOs.BonoDAO;

@WebServlet("/adquirirBono")
public class adquirirBonoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/adquirirBono.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            CustomerBean customer = (CustomerBean) session.getAttribute("customer");

            if (customer == null) {
                request.setAttribute("mensaje", "Usuario no autenticado.");
                request.getRequestDispatcher("/views/adquirirBono.jsp").forward(request, response);
                return;
            }

            int idUser = customer.getId();
            int sesiones = Integer.parseInt(request.getParameter("sesiones"));
            String tipoReserva = request.getParameter("tipoReserva");
            PistaDTO.TamanoPista pistaTamano = PistaDTO.TamanoPista.valueOf(request.getParameter("pistaTamano").toUpperCase());

            BonoDTO nuevoBono = BonoDAO.insertarBono(sesiones, idUser, tipoReserva, pistaTamano);

            request.setAttribute("mensaje", "Bono creado exitosamente con ID: " + nuevoBono.getId());
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al crear el bono: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/adquirirBono.jsp").forward(request, response);
    }
}