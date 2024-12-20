package web.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.JugadorDTO;
import web.model.business.Gestores.GestorDeUsuarios;

@WebServlet("/jugadores")
public class VerJugadoresServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<JugadorDTO> jugadores = GestorDeUsuarios.obtenerUsuarios();
            // Set the list of players as a request attribute
            request.setAttribute("jugadores", jugadores);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al obtener la lista de jugadores: " + e.getMessage());
        }

        // Forward the request to jugadores.jsp
        request.getRequestDispatcher("/views/jugadores.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
