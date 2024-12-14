package web.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.PistaDTO;
import web.model.data.DAOs.PistaDAO;

@WebServlet("/buscarPistaDisponible")
public class buscarPistaDisponibleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/buscarPistaDisponible.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fechaStr = request.getParameter("fecha");
            String tipoPista = request.getParameter("tipoPista");
            LocalDateTime fecha = LocalDateTime.parse(fechaStr);
            List<PistaDTO> pistas = null;

            if (fecha.isBefore(LocalDateTime.now())) {
                request.setAttribute("mensaje", "La fecha no puede ser pasada.");
            } else {
                if (tipoPista == null || tipoPista.isEmpty()) {
                    pistas = PistaDAO.listarPistasDisponiblesPorFecha(fecha);
                } else {
                    boolean esExterior = "EXTERIOR".equalsIgnoreCase(tipoPista);
                    pistas = PistaDAO.listarPistasDisponiblesPorFechaYTipo(fecha, esExterior);
                }
            }

            request.setAttribute("pistas", pistas);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al buscar pistas: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/buscarPistaDisponible.jsp").forward(request, response);
    }
}