<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="web.model.business.DTOs.JugadorDTO" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page import="web.model.data.DAOs.JugadorDAO" %>
<%@ page import="web.model.data.DAOs.ReservaDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
    if (customerBean == null || customerBean.getRol() != JugadorDTO.Roles.ADMIN) {
        response.sendRedirect("../views/loginview.jsp");
        return;
    }

    List<JugadorDTO> clientes = JugadorDAO.obtenerUsuarios();
    clientes.removeIf(cliente -> cliente.getRol() != JugadorDTO.Roles.CLIENTE);
    List<ReservaDTO> reservas = ReservaDAO.obtenerReservas();
    Map<Integer, Integer> reservasCompletadas = new HashMap<>();

    LocalDate fechaActual = LocalDate.now();
    for (JugadorDTO cliente : clientes) {
        if (cliente.getRol() == JugadorDTO.Roles.ADMIN) {
            continue;
        }
        int numReservasCompletadas = 0;
        for (ReservaDTO reserva : reservas) {
            if (reserva.getIdUsuario() == cliente.getId() && reserva.getDiaYHora().isBefore(fechaActual)) {
                numReservasCompletadas++;
            }
        }
        reservasCompletadas.put(cliente.getId(), numReservasCompletadas);
    }

    request.setAttribute("clientes", clientes);
    request.setAttribute("reservasCompletadas", reservasCompletadas);
    request.getRequestDispatcher("../views/welcomeadminview.jsp").forward(request, response);
%>