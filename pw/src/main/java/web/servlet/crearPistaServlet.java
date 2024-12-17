package web.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.PistaDTO.TamanoPista;
import web.model.data.DAOs.PistaDAO;



@WebServlet("/crearPista")
public class crearPistaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombrePista = request.getParameter("nombrePista");
            boolean disponible = request.getParameter("disponible") != null;
            boolean esExterior = request.getParameter("esExterior") != null;
            TamanoPista tamanoPista = TamanoPista.valueOf(request.getParameter("tamanoPista"));
            int maxJugadores = Integer.parseInt(request.getParameter("maxJugadores"));

            PistaDTO nuevaPista = PistaDAO.insertarPista(nombrePista, disponible, esExterior, tamanoPista, maxJugadores);

            if (nuevaPista != null) {
                request.setAttribute("mensaje", "Pista creada exitosamente. ID de la pista: " + nuevaPista.getId());
            } else {
                request.setAttribute("mensaje", "Error al crear la pista. Ya existe una pista con el mismo nombre.");
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al crear la pista: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/crearPista.jsp").forward(request, response);
    }
}