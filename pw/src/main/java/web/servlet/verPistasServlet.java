package web.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.PistaDTO;
import web.model.data.DAOs.PistaDAO;

@WebServlet("/verPistas")
public class verPistasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<PistaDTO> pistas = PistaDAO.obtenerPistas();
            request.setAttribute("pistas", pistas);
            request.getRequestDispatcher("/views/verPistas.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al obtener las pistas: " + e.getMessage());
            request.getRequestDispatcher("/views/verPistas.jsp").forward(request, response);
        }
    }
}