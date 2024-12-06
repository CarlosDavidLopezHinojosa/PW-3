<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="web.model.business.DTOs.JugadorDTO" %>
<%@ page import="web.model.business.DTOs.JugadorDTO.Roles" %>
<%@ page import="web.model.business.Gestores.GestorDeUsuarios" %>
<%@ page import="web.model.data.DAOs.JugadorDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = request.getParameter("name");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String fechaNacimientoStr = request.getParameter("fechaNacimiento");
    String error = null;

    if (name != null && lastName != null && email != null && password != null && fechaNacimientoStr != null) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
        Roles rol = Roles.CLIENTE;

        JugadorDTO existingJugador = JugadorDAO.getUsuarioEmail(email);

        if (existingJugador == null) {
            // Usuario no existe, registrar nuevo usuario
            GestorDeUsuarios.darDeAlta(email, name, lastName, fechaNacimiento, password, rol);
            // Crear CustomerBean y almacenarolo en la sesión
            JugadorDTO jugador = JugadorDAO.getUsuarioEmail(email);

            CustomerBean customerBean = new CustomerBean();
            customerBean.setData(jugador);
            session.setAttribute("customerBean", customerBean);
            response.sendRedirect("../views/welcome.jsp");
            return;
        } else {
            // Usuario ya existe, mostrar mensaje de error
            error = "El correo ya está registrado";
            request.setAttribute("error", error);
            request.getRequestDispatcher("../views/registerview.jsp").forward(request, response);
            return;
        }
    } else {
        // Datos de registro no proporcionados
        error = "Por favor, complete todos los campos.";
        request.setAttribute("error", error);
        request.getRequestDispatcher("views/registerview.jsp").forward(request, response);
        return;
    }
%>