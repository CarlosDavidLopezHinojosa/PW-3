<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page import="web.model.data.DAOs.ReservaDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
    if (customerBean == null) {
        response.sendRedirect("../views/loginview.jsp");
        return;
    }

    LocalDate fechaActual = LocalDate.now();
    LocalDate fechaInscripcion = customerBean.getFechaInscripcion(); // Assuming this is the registration date
    LocalDateTime proximaReserva = null;

    ReservaDAO reservaDAO = new ReservaDAO();
    List<ReservaDTO> reservas = reservaDAO.obtenerReservasFuturasUsuario(customerBean.getId());

    if (reservas != null && !reservas.isEmpty()) {
        for (ReservaDTO reserva : reservas) {
            if (reserva.getDiaYHora().isAfter(LocalDateTime.now())) {
                proximaReserva = reserva.getDiaYHora();
                break;
            }
        }
    }

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Redirigir a la vista del cliente con los atributos necesarios
    request.setAttribute("fechaActual", fechaActual.format(dateFormatter));
    request.setAttribute("fechaInscripcion", fechaInscripcion.format(dateFormatter));
    request.setAttribute("proximaReserva", proximaReserva != null ? proximaReserva.format(dateTimeFormatter) : null);
    request.getRequestDispatcher("../views/welcomeclientview.jsp").forward(request, response);
%>
