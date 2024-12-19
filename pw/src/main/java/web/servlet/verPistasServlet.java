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

@WebServlet("/verPistas")
public class verPistasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener todas las pistas de la base de datos
            List<PistaDTO> pistas = GestorDePistas.obtenerPistas();
            // Establecer la lista de pistas como un atributo en el objeto request
            request.setAttribute("pistas", pistas);
            // Reenviar la solicitud a la página JSP verPistas.jsp
            request.getRequestDispatcher("/views/verPistas.jsp").forward(request, response);
        } catch (Exception e) {
            // Establecer un mensaje de error en caso de excepción
            request.setAttribute("mensaje", "Error al obtener las pistas: " + e.getMessage());
            // Reenviar la solicitud a la página JSP verPistas.jsp
            request.getRequestDispatcher("/views/verPistas.jsp").forward(request, response);
        }
    }
}
