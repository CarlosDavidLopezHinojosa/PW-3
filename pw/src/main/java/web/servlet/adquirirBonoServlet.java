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
import web.model.business.Gestores.GestorDeBonos;

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
            CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

            if (customer == null) {
                request.setAttribute("mensaje", "Usuario no autenticado.");
                request.getRequestDispatcher("/views/adquirirBono.jsp").forward(request, response);
                return;
            }

            int idUser = customer.getId();
            //String sesionesStr = request.getParameter("sesiones");
            String tipoReserva = request.getParameter("tipoReserva");
            String pistaTamanoStr = request.getParameter("pistaTamano");

            if (/**sesionesStr == null ||**/ tipoReserva == null || pistaTamanoStr == null /**|| sesionesStr.isEmpty()**/ || tipoReserva.isEmpty() || pistaTamanoStr.isEmpty()) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("/views/adquirirBono.jsp").forward(request, response);
                return;
            }

            int sesiones = 5;
            PistaDTO.TamanoPista pistaTamano = PistaDTO.TamanoPista.valueOf(pistaTamanoStr.toUpperCase());

            GestorDeBonos gestorDeBonos = GestorDeBonos.getGestor();
            BonoDTO nuevoBono = gestorDeBonos.insertarBono(sesiones, idUser, tipoReserva, pistaTamano);

            request.setAttribute("mensaje", "Bono creado exitosamente con ID: " + nuevoBono.getId());
        } catch (Exception e) {
            request.setAttribute("error", "Error al crear el bono: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/adquirirBono.jsp").forward(request, response);
    }
}
