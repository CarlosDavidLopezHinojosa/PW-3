<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="web.model.data.DAOs.JugadorDAO" %>
<%@ page import="java.io.IOException" %>
<%
    CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
    if (customerBean == null) {
        response.sendRedirect("../views/loginview.jsp");
        return;
    }

    String currentPassword = request.getParameter("currentPassword");
    String newPassword = request.getParameter("newPassword");

    if (currentPassword.equals(customerBean.getPassword())) {
        JugadorDAO.cambiarContrasena(customerBean.getEmail(), newPassword);
        customerBean.setPassword(newPassword);
        session.setAttribute("customerBean", customerBean);
        response.sendRedirect("../views/updateview.jsp");
    } else {
        request.setAttribute("error", "La contraseña actual es incorrecta.");
        request.getRequestDispatcher("../views/changepassword.jsp").forward(request, response);
    }
%>