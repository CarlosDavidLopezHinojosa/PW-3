<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="web.model.data.DAOs.JugadorDAO" %>
<%@ page import="web.model.business.DTOs.JugadorDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
    if (customerBean == null) {
        response.sendRedirect("../views/loginview.jsp");
        return;
    }

    String name = request.getParameter("name");
    String lastName = request.getParameter("lastName");

    JugadorDAO jugadorDAO = new JugadorDAO();
    JugadorDTO jugador = JugadorDAO.getUsuarioEmail(customerBean.getEmail());

    jugador.setNombre(name);
    jugador.setApellidos(lastName);

    JugadorDAO.modificarInformacion(customerBean.getId(),customerBean.getEmail(),name,lastName,customerBean.getFechaNacimiento());

    // Actualizar el CustomerBean en la sesión
    customerBean.setNombre(name);
    customerBean.setApellidos(lastName);
    session.setAttribute("customerBean", customerBean);

  if(customerBean.getRol() == JugadorDTO.Roles.ADMIN){
        response.sendRedirect("welcomeadmincontroller.jsp");
  }
  else{
        response.sendRedirect("welcomeclientcontroller.jsp");
  }
%>