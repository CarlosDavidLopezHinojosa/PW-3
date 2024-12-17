<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
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

    // Filtrar clientes y reservas directamente desde las consultas
    List<JugadorDTO> clientes = JugadorDAO.obtenerUsuarios()
        .stream()
        .filter(cliente -> cliente.getRol() == JugadorDTO.Roles.CLIENTE)
        .collect(Collectors.toList());

    LocalDateTime fechaActual = LocalDateTime.now();
    List<ReservaDTO> reservas = ReservaDAO.obtenerReservas()
        .stream()
        .filter(reserva -> reserva.getDiaYHora().toLocalDate().isBefore(fechaActual))
        .collect(Collectors.toList());

    // Mapear reservas completadas por cliente
    Map<Integer, Long> reservasCompletadas = reservas.stream()
        .collect(Collectors.groupingBy(ReservaDTO::getIdUsuario, Collectors.counting()));

    request.setAttribute("clientes", clientes);
    request.setAttribute("reservasCompletadas", reservasCompletadas);
    request.getRequestDispatcher("../views/welcomeadminview.jsp").forward(request, response);
%>