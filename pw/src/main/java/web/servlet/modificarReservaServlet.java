package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.business.Gestores.GestorDePistas;
import web.model.business.Gestores.GestorDeReservas;
import web.model.business.Gestores.GestorDeUsuarios;

@WebServlet("/modificarReserva")
public class modificarReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");

        if (customer == null) {
            request.setAttribute("mensaje", "Usuario no autenticado.");
            request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
            return;
        }

        String email = customer.getEmail();
        JugadorDTO user = GestorDeUsuarios.getUsuarioEmail(email); // Método para obtener JugadorDTO por email
        
        if (user == null) {
            request.setAttribute("mensaje", "Usuario no encontrado.");
        } else {
            List<ReservaDTO> reservas = GestorDeReservas.obtenerReservasFuturasUsuario(user.getId());
            request.setAttribute("reservas", reservas);
        }

        request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
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
            JugadorDTO user = GestorDeUsuarios.getUsuarioEmail(email);
            if (user != null) {
                List<ReservaDTO> reservas = GestorDeReservas.obtenerReservasFuturasUsuario(user.getId());
                request.setAttribute("reservas", reservas);
            } else {
                request.setAttribute("mensaje", "Usuario no encontrado.");
            }
        } else if (idReservaStr != null && confirmar != null) {
            // Confirmar modificación de la reserva
            int idReserva = Integer.parseInt(idReservaStr);
            ReservaDTO reserva = GestorDeReservas.obtenerReservaPorId(idReserva);
            if (reserva != null && reserva.getDiaYHora().isAfter(LocalDateTime.now().plusHours(24))) {
                // Obtener los nuevos valores del formulario
                String nuevaFechaStr = request.getParameter("nuevaFecha");
                String nuevoTipoReserva = request.getParameter("tipoReserva");
                String nuevaDuracionStr = request.getParameter("duracion");
                String nuevaPistaStr = request.getParameter("pista");
                String numAdultosStr = request.getParameter("numAdultos");
                String numNinosStr = request.getParameter("numNinos");

                LocalDateTime nuevaFecha = LocalDateTime.parse(nuevaFechaStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                int nuevaDuracion = Integer.parseInt(nuevaDuracionStr);
                int nuevaPistaId = Integer.parseInt(nuevaPistaStr);
                int numAdultos = numAdultosStr != null && !numAdultosStr.isEmpty() ? Integer.parseInt(numAdultosStr) : 0;
                int numNinos = numNinosStr != null && !numNinosStr.isEmpty() ? Integer.parseInt(numNinosStr) : 0;

                // Validar que la nueva fecha no sea pasada
                if (nuevaFecha.isBefore(LocalDateTime.now())) {
                    request.setAttribute("mensaje", "La nueva fecha no puede ser pasada.");
                    request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
                    return;
                }

                try {
                    // Validar que la suma de participantes no supere el límite de la pista
                    PistaDTO nuevaPista = GestorDePistas.obtenerPistaPorId(nuevaPistaId);
                    int maxJugadores = nuevaPista.getMaxJugadores();
                    if (numAdultos + numNinos > maxJugadores) {
                        request.setAttribute("mensaje", "El número total de participantes supera el límite permitido para esta pista.");
                        request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
                        return;
                    }

                    // Validar que la pista esté disponible para la nueva fecha y duración
                    List<PistaDTO> pistasDisponibles = GestorDePistas.listarPistasDisponiblesPorFechaYTipo(nuevaFecha, nuevaDuracion, nuevaPista.getTamano());
                    boolean pistaDisponible = pistasDisponibles.stream().anyMatch(p -> p.getId() == nuevaPistaId);
                    if (!pistaDisponible) {
                        request.setAttribute("mensaje", "La pista seleccionada no está disponible para la nueva fecha y duración.");
                        request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
                        return;
                    }

                    // Calcular el nuevo precio
                    float nuevoPrecio = calcularPrecio(nuevaPista.getTamano().name(), nuevaDuracion);

                    // Actualizar la reserva
                    reserva.setDiaYHora(nuevaFecha);
                    reserva.setTipoReserva(nuevoTipoReserva);
                    reserva.setDuracion(nuevaDuracion);
                    reserva.setIdPista(nuevaPistaId);
                    reserva.setNumAdultos(numAdultos);
                    reserva.setNumNinos(numNinos);
                    reserva.setPrecio(nuevoPrecio);

                    GestorDeReservas.actualizarReserva(reserva);
                    request.setAttribute("mensaje", "Reserva modificada exitosamente.");
                } catch (SQLException e) {
                    request.setAttribute("mensaje", "Error al modificar la reserva: " + e.getMessage());
                }
            } else {
                request.setAttribute("mensaje", "No se puede modificar la reserva porque quedan menos de 24 horas para su inicio.");
            }
        } else if (idReservaStr != null) {
            // Preguntar al usuario si está seguro de la modificación
            int idReserva = Integer.parseInt(idReservaStr);
            ReservaDTO reserva = GestorDeReservas.obtenerReservaPorId(idReserva);
            if (reserva != null && reserva.getDiaYHora().isAfter(LocalDateTime.now().plusHours(24))) {
                request.setAttribute("mensaje", "¿Está seguro de que desea modificar la reserva con ID: " + idReserva + "?");
                request.setAttribute("idReserva", idReserva);
            } else {
                request.setAttribute("mensaje", "No se puede modificar la reserva porque quedan menos de 24 horas para su inicio.");
            }
        }

        request.getRequestDispatcher("/views/modificarReserva.jsp").forward(request, response);
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
