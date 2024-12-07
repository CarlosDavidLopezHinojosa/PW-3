<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="java.time.LocalDate" %>
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
    LocalDate proximaReserva = null;

    ReservaDAO reservaDAO = new ReservaDAO();
    List<ReservaDTO> reservas = reservaDAO.obtenerReservasFuturasUsuario(customerBean.getId());

    if (reservas != null && !reservas.isEmpty()) {
        for (ReservaDTO reserva : reservas) {
            if (reserva.getDiaYHora().isAfter(fechaActual)) {
                proximaReserva = reserva.getDiaYHora();
                break;
            }
        }
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Redirigir a la vista del cliente con los atributos necesarios
    request.setAttribute("fechaActual", fechaActual.format(formatter));
    request.setAttribute("fechaInscripcion", fechaInscripcion.format(formatter));
    request.setAttribute("proximaReserva", proximaReserva != null ? proximaReserva.format(formatter) : null);
    request.getRequestDispatcher("../views/welcomeclientview.jsp").forward(request, response);
%>