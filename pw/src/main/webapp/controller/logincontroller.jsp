<%@ page import="web.model.business.DTOs.JugadorDTO" %>
<%@ page import="web.model.data.DAOs.JugadorDAO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String error = null;

    if (email != null && password != null) {
        JugadorDTO jugador = JugadorDAO.getUsuarioEmail(email);

        if (jugador != null && 
            password.equals(jugador.getPassword()) && 
            email.equals(jugador.getEmail())) {
            // Usuario encontrado, crear CustomerBean y almacenarlo en la sesión
            CustomerBean customerBean = new CustomerBean();
            customerBean.setData(jugador);
            session.setAttribute("customerBean", customerBean);

            if (jugador.getRol() == JugadorDTO.Roles.ADMIN) {
                response.sendRedirect("../views/welcomeadminview.jsp");
                return;
            }
            else{
                response.sendRedirect("../views/welcomeclientview.jsp");
                return;
            }
        } else {
            // Usuario no encontrado, mostrar mensaje de error
            error = "Correo o contraseña incorrectos";
            request.setAttribute("error", error);
            request.getRequestDispatcher("../views/loginview.jsp").forward(request, response);
            return;
        }
    } else {
        // Datos de inicio de sesión no proporcionados
        error = "Por favor, ingrese su correo y contraseña.";
        request.setAttribute("error", error);
        request.getRequestDispatcher("../views/loginview.jsp").forward(request, response);
        return;
    }
%>