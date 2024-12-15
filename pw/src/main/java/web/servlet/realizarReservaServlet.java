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
import jakarta.servlet.http.HttpSession;
import web.model.business.Beans.CustomerBean;
import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.data.DAOs.PistaDAO;
import web.model.data.DAOs.ReservaDAO;

@WebServlet("/realizarReserva")
public class realizarReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("buscarPistasDisponibles".equals(action)) {
            buscarPistasDisponibles(request, response);
        } else {
            HttpSession session = request.getSession();
            CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

            if (customer == null) {
                request.setAttribute("mensaje", "Usuario no autenticado. Por favor, inicie sesión para realizar una reserva.");
            }

            request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
        }
    }

    private void buscarPistasDisponibles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String tipoReservaStr = request.getParameter("tipoReserva");
            PistaDTO.TamanoPista tipoReserva = PistaDTO.TamanoPista.valueOf(tipoReservaStr.toUpperCase());
            String diaYHoraStr = request.getParameter("diaYHora");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime diaYHora = LocalDateTime.parse(diaYHoraStr, formatter);

            List<PistaDTO> pistas = PistaDAO.listarPistasDisponiblesPorFechaYTipo(diaYHora, tipoReserva);

            StringBuilder options = new StringBuilder();
            options.append("<option value=''>Seleccione una pista</option>");
            for (PistaDTO pista : pistas) {
                options.append("<option value='").append(pista.getId()).append("'>").append(pista.getNombre()).append("</option>");
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(options.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al buscar pistas disponibles: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

        if (customer == null) {
            request.setAttribute("mensaje", "Usuario no autenticado. Por favor, inicie sesión para realizar una reserva.");
            request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
            return;
        }

        try {
            String tipoReserva = request.getParameter("tipoReserva");
            int idUsuario = customer.getId();
            LocalDateTime diaYHora = LocalDateTime.parse(request.getParameter("diaYHora"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            // Validar que la fecha y hora no sean pasadas
            if (diaYHora.isBefore(LocalDateTime.now())) {
                request.setAttribute("mensaje", "La fecha y hora de la reserva no pueden ser pasadas.");
                request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
                return;
            }

            boolean esBono = Boolean.parseBoolean(request.getParameter("esBono"));
            int idBono = esBono ? Integer.parseInt(request.getParameter("idBono")) : -1;
            int nSesionBono = esBono ? Integer.parseInt(request.getParameter("nSesionBono")) : -1;
            int duracion = Integer.parseInt(request.getParameter("duracion"));
            int idPista = Integer.parseInt(request.getParameter("pista"));
            float precio = Float.parseFloat(request.getParameter("precio"));
            String codigoDescuento = request.getParameter("codigoDescuento");
            float descuento = 0;

            if (codigoDescuento != null && !codigoDescuento.isEmpty()) {
                if ("DESCUENTO".equals(codigoDescuento)) {
                    descuento = (precio > 0) ? 0.1f * precio : 2.0f;
                    request.setAttribute("mensaje", "Código de descuento introducido correctamente.");
                } else {
                    request.setAttribute("mensaje", "El código de descuento no es correcto.");
                    request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
                    return;
                }
            }

            int numAdultos = tipoReserva.equals("INFANTIL") ? 0 : Integer.parseInt(request.getParameter("numAdultos"));
            int numNinos = tipoReserva.equals("ADULTOS") ? 0 : Integer.parseInt(request.getParameter("numNinos"));
            PistaDTO.TamanoPista pistaTamano;

            if (tipoReserva.equals("ADULTOS")) {
                pistaTamano = PistaDTO.TamanoPista.ADULTOS;
            } else if (tipoReserva.equals("INFANTIL")) {
                pistaTamano = PistaDTO.TamanoPista.MINIBASKET;
            } else {
                pistaTamano = PistaDTO.TamanoPista.valueOf(request.getParameter("pistaTamano").toUpperCase());
            }

            ReservaDTO reserva = ReservaDAO.insertarReserva(tipoReserva, idUsuario, diaYHora, idBono, nSesionBono, duracion, idPista, precio, descuento, pistaTamano, numAdultos, numNinos);
            request.setAttribute("mensaje", "Reserva realizada exitosamente. ID de la reserva: " + reserva.getIdReserva());
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al realizar la reserva: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
    }
}
