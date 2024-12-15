package web.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fechaHoraStr = request.getParameter("fechaHora");
            String tipoPista = request.getParameter("tipoPista");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
            List<PistaDTO> pistas = null;

            if (fechaHora.isBefore(LocalDateTime.now())) {
                request.setAttribute("mensaje", "La fecha y hora no pueden ser pasadas.");
            } else {
                if (tipoPista == null || tipoPista.isEmpty()) {
                    pistas = PistaDAO.listarPistasDisponiblesPorFecha(fechaHora);
                } else {
                    boolean esExterior = "EXTERIOR".equalsIgnoreCase(tipoPista);
                    pistas = PistaDAO.listarPistasDisponiblesPorFechaYTipo(fechaHora, esExterior);
                }
            }

            request.setAttribute("pistas", pistas);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al buscar pistas: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/buscarPistaDisponible.jsp").forward(request, response);
    }
}
