package web.controller.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.JugadorDTO;
import web.model.data.DAOs.JugadorDAO;

@WebServlet("/ejemplo")
public class EjemploServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JugadorDAO jugadorDAO = new JugadorDAO();
        List<JugadorDTO> jugadores = jugadorDAO.obtenerUsuarios();

        response.setContentType("text/html");
        response.getWriter().println("<h1>Lista de Jugadores</h1>");
        response.getWriter().println("<ul>");
        for (JugadorDTO jugador : jugadores) {
            response.getWriter().println("<li>" + jugador + "</li>");
        }
        response.getWriter().println("</ul>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}