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
import web.model.business.DTOs.BonoDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.business.Gestores.GestorDeBonos;
import web.model.business.Gestores.GestorDePistas;
import web.model.business.Gestores.GestorDeReservas;

@WebServlet("/realizarReserva")
public class realizarReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("buscarPistasDisponibles".equals(action)) {
            buscarPistasDisponibles(request, response);
        } else if ("buscarBonosDisponibles".equals(action)) {
            buscarBonosDisponibles(request, response);
        } else {
            HttpSession session = request.getSession();
            CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

            if (customer == null) {
                response.sendRedirect(request.getContextPath() + "/views/loginview.jsp");
            } else {
                GestorDeBonos gestorDeBonos = GestorDeBonos.getGestor();
                int tipoReserva = Integer.parseInt(request.getParameter("tipoReserva"));
                List<BonoDTO> bonos = gestorDeBonos.obtenerBonosDisponibles(customer.getId(), String.valueOf(tipoReserva));
                request.setAttribute("bonos", bonos);
            }

            request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
        }
    }

    private void buscarPistasDisponibles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String tipoReservaStr = request.getParameter("tipoReserva");
            String diaYHoraStr = request.getParameter("diaYHora");
            String duracionStr = request.getParameter("duracion");
    
            if (tipoReservaStr == null || diaYHoraStr == null || duracionStr == null || tipoReservaStr.isEmpty() || diaYHoraStr.isEmpty() || duracionStr.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Tipo de reserva, fecha y hora, y duración son obligatorios.");
                return;
            }
    
            ReservaDTO.tipoReserva tipoReserva = ReservaDTO.tipoReserva.valueOf(tipoReservaStr.toUpperCase());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime diaYHora = LocalDateTime.parse(diaYHoraStr, formatter);
            int duracion = Integer.parseInt(duracionStr);
    
            List<PistaDTO> pistas;
            if (tipoReserva == ReservaDTO.tipoReserva.ADULTOS) {
                pistas = GestorDePistas.listarPistasDisponiblesPorFechaYTipo(diaYHora, duracion, PistaDTO.TamanoPista.ADULTOS);
            } else if (tipoReserva == ReservaDTO.tipoReserva.INFANTIL) {
                pistas = GestorDePistas.listarPistasDisponiblesPorFechaYTipo(diaYHora, duracion, PistaDTO.TamanoPista.MINIBASKET);
            } else if (tipoReserva == ReservaDTO.tipoReserva.FAMILIAR) {
                List<PistaDTO> pistasMinibasket = GestorDePistas.listarPistasDisponiblesPorFechaYTipo(diaYHora, duracion, PistaDTO.TamanoPista.MINIBASKET);
                List<PistaDTO> pistasVS3 = GestorDePistas.listarPistasDisponiblesPorFechaYTipo(diaYHora, duracion, PistaDTO.TamanoPista.VS3);
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
                    options.append("<option value='").append(pista.getId()).append("' data-tamano='").append(pista.getTamano()).append("'>")
                           .append(pista.getNombre()).append(" - ").append(pista.getTamano()).append(" - Max Participantes: ").append(pista.getMaxJugadores()).append("</option>");
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

    private void buscarBonosDisponibles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

            if (customer == null) {
                response.sendRedirect(request.getContextPath() + "/views/loginview.jsp");
                return;
            }

            String tipoReservaStr = request.getParameter("tipoReserva");
            if (tipoReservaStr == null || tipoReservaStr.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Tipo de reserva es obligatorio.");
                return;
            }

            GestorDeBonos gestor = GestorDeBonos.getGestor();
            List<BonoDTO> bonos = gestor.obtenerBonosDisponibles(customer.getId(), tipoReservaStr);
            StringBuilder options = new StringBuilder();
            options.append("<option value=''>Seleccione un bono</option>");
            for (BonoDTO bono : bonos) {
                options.append("<option value='").append(bono.getId()).append("' data-tamano='").append(bono.getPistaTamano()).append("'>")
                       .append("Bono ID: ").append(bono.getId()).append(" - Sesiones restantes: ").append(bono.getSesiones()).append("</option>");
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(options.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al buscar bonos disponibles: " + e.getMessage());
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
            String diaYHoraStr = request.getParameter("diaYHora");
            String duracionStr = request.getParameter("duracion");
            String idPistaStr = request.getParameter("pista");
            String pistaTamanoStr = request.getParameter("pistaTamano");

            if (tipoReserva == null || diaYHoraStr == null || duracionStr == null || idPistaStr == null || pistaTamanoStr == null || tipoReserva.isEmpty() || diaYHoraStr.isEmpty() || duracionStr.isEmpty() || idPistaStr.isEmpty() || pistaTamanoStr.isEmpty()) {
                request.setAttribute("mensaje", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
                return;
            }

            int idUsuario = customer.getId();
            LocalDateTime diaYHora = LocalDateTime.parse(diaYHoraStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            // Validar que la fecha y hora no sean pasadas
            if (diaYHora.isBefore(LocalDateTime.now())) {
                request.setAttribute("mensaje", "La fecha y hora de la reserva no pueden ser pasadas.");
                request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
                return;
            }

            boolean esBono = Boolean.parseBoolean(request.getParameter("esBono"));
            int idBono = esBono ? Integer.parseInt(request.getParameter("idBono")) : -1;
            int duracion = Integer.parseInt(duracionStr);
            int idPista = Integer.parseInt(idPistaStr);
            float precio = calcularPrecio(pistaTamanoStr, duracion);
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
            PistaDTO.TamanoPista pistaTamano = PistaDTO.TamanoPista.valueOf(pistaTamanoStr.toUpperCase());

            // Obtener el número máximo de jugadores de la pista
            PistaDTO pista = GestorDePistas.obtenerPistaPorId(idPista);
            int maxJugadores = pista.getMaxJugadores();

            // Comprobar si la suma de participantes supera el límite
            if (numAdultos + numNinos > maxJugadores) {
                request.setAttribute("mensaje", "El número total de participantes supera el límite permitido para esta pista.");
                request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
                return;
            }

            GestorDeReservas.insertarReserva(tipoReserva, idUsuario, diaYHora, idBono, duracion, idPista, precio, descuento, pistaTamano, numAdultos, numNinos);

            // Restar una sesión al bono si es una reserva de bono
            if (esBono) {
                GestorDeBonos gestor = GestorDeBonos.getGestor();
                gestor.restarSesion(idBono);
            }

            request.setAttribute("mensaje", "Reserva realizada exitosamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al realizar la reserva: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/realizarReserva.jsp").forward(request, response);
    }


    private float calcularPrecio(String pistaTamano, int duracion) {
        switch (pistaTamano) {
            case "MINIBASKET":
                return 0.1f * duracion;
            case "VS3":
                return 0.13f * duracion;
            case "ADULTOS":
                return 0.15f * duracion;
            default:
                return 0;
        }
    }
}
