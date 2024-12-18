package web.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.business.Beans.CustomerBean;
import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.data.DAOs.JugadorDAO;
import web.model.data.DAOs.PistaDAO;
import web.model.data.DAOs.ReservaDAO;

@WebServlet("/modificarReserva")
public class modificarReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
        String action = request.getParameter("action");
        if ("buscarPistasDisponibles".equals(action)) {
            buscarPistasDisponibles(request, response);
            return;
        } else {
            if (customer == null) {
                request.setAttribute("mensaje", "Usuario no autenticado.");
                request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
                return;
            }
        }

        String email = customer.getEmail();
        JugadorDTO user = JugadorDAO.getUsuarioEmail(email); // Método para obtener JugadorDTO por email
        
        if (user == null) {
            request.setAttribute("mensaje", "Usuario no encontrado.");
        } else {
            List<ReservaDTO> reservas = ReservaDAO.obtenerReservasFuturasUsuario(user.getId());
            request.setAttribute("reservas", reservas);
        }

        request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
    }

    private void buscarPistasDisponibles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String tipoReservaStr = request.getParameter("tipoReserva");
            String diaYHoraStr = request.getParameter("diaYHora");

            if (tipoReservaStr == null || diaYHoraStr == null || tipoReservaStr.isEmpty() || diaYHoraStr.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Tipo de reserva y fecha y hora son obligatorios.");
                return;
            }

            ReservaDTO.tipoReserva tipoReserva = ReservaDTO.tipoReserva.valueOf(tipoReservaStr.toUpperCase());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime diaYHora = LocalDateTime.parse(diaYHoraStr, formatter);

            List<PistaDTO> pistas;
            PistaDTO.TamanoPista tamanoAdultos = PistaDTO.TamanoPista.ADULTOS;
            PistaDTO.TamanoPista tamanoMinibasket = PistaDTO.TamanoPista.MINIBASKET;
            PistaDTO.TamanoPista tamanoVS3 = PistaDTO.TamanoPista.VS3;
            if (tipoReserva == ReservaDTO.tipoReserva.ADULTOS) {
                pistas = PistaDAO.listarPistasDisponiblesPorFechaYTipo(diaYHora, tamanoAdultos);
            } else if (tipoReserva == ReservaDTO.tipoReserva.INFANTIL) {
                pistas = PistaDAO.listarPistasDisponiblesPorFechaYTipo(diaYHora, tamanoMinibasket);
            } else if (tipoReserva == ReservaDTO.tipoReserva.FAMILIAR) {
                List<PistaDTO> pistasMinibasket = PistaDAO.listarPistasDisponiblesPorFechaYTipo(diaYHora, tamanoMinibasket);
                List<PistaDTO> pistasVS3 = PistaDAO.listarPistasDisponiblesPorFechaYTipo(diaYHora, tamanoVS3);
                pistas = new ArrayList<>();
                pistas.addAll(pistasMinibasket);
                pistas.addAll(pistasVS3);
            } else {
                pistas = new ArrayList<>();
            }

            StringBuilder options = new StringBuilder();
            options.append("<option value=''>Seleccione una pista</option>");
            for (PistaDTO pista : pistas) {
                if (esPistaValidaParaReserva(tipoReservaStr, pista.getTamano())) {
                    options.append("<option value='").append(pista.getId()).append("' data-tamano='").append(pista.getTamano()).append("'>").append(pista.getId()).append(" - ").append(pista.getNombre()).append("</option>");
                }
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(options.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al buscar pistas disponibles: " + e.getMessage());
        }
    }

    private boolean esPistaValidaParaReserva(String tipoReserva, PistaDTO.TamanoPista tamanoPista) {
        switch (tipoReserva.toUpperCase()) {
            case "ADULTOS":
                return tamanoPista == PistaDTO.TamanoPista.ADULTOS;
            case "FAMILIAR":
                return tamanoPista == PistaDTO.TamanoPista.MINIBASKET || tamanoPista == PistaDTO.TamanoPista.VS3;
            case "INFANTIL":
                return tamanoPista == PistaDTO.TamanoPista.MINIBASKET;
            default:
                return false;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

        if (customer == null) {
            request.setAttribute("mensaje", "Usuario no autenticado.");
            request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
            return;
        }

        String email = customer.getEmail();
        String idReservaStr = request.getParameter("idReserva");
        String confirmar = request.getParameter("confirmar");

        if (idReservaStr == null) {
            // Buscar reservas futuras del usuario
            JugadorDTO user = JugadorDAO.getUsuarioEmail(email);
            if (user != null) {
                List<ReservaDTO> reservas = ReservaDAO.obtenerReservasFuturasUsuario(user.getId());
                request.setAttribute("reservas", reservas);
            } else {
                request.setAttribute("mensaje", "Usuario no encontrado.");
            }
        } else if (idReservaStr != null && confirmar != null) {
            // Confirmar modificación de la reserva
            int idReserva = Integer.parseInt(idReservaStr);
            ReservaDTO reserva = ReservaDAO.obtenerReservaPorId(idReserva);
            if (reserva != null && reserva.getDiaYHora().isAfter(LocalDateTime.now().plusHours(24))) {
                // Aquí puedes agregar la lógica para modificar la reserva
                // Por ejemplo, cambiar la fecha de la reserva
                String nuevaFechaStr = request.getParameter("nuevaFecha");
                LocalDateTime nuevaFecha = LocalDateTime.parse(nuevaFechaStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                reserva.setDiaYHora(nuevaFecha);
                ReservaDAO.actualizarReserva(reserva);
                request.setAttribute("mensaje", "Reserva modificada exitosamente.");
            } else {
                request.setAttribute("mensaje", "No se puede modificar la reserva porque quedan menos de 24 horas para su inicio.");
            }
        } else if (idReservaStr != null) {
            // Preguntar al usuario si está seguro de la modificación
            int idReserva = Integer.parseInt(idReservaStr);
            ReservaDTO reserva = ReservaDAO.obtenerReservaPorId(idReserva);
            if (reserva != null && reserva.getDiaYHora().isAfter(LocalDateTime.now().plusHours(24))) {
                request.setAttribute("mensaje", "¿Está seguro de que desea modificar la reserva con ID: " + idReserva + "?");
                request.setAttribute("idReserva", idReserva);
            } else {
                request.setAttribute("mensaje", "No se puede modificar la reserva porque quedan menos de 24 horas para su inicio.");
            }
        }

        request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
    }
}
