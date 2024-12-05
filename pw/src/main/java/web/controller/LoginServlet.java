package web.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.Beans.CustomerBean;
import web.model.business.DTOs.JugadorDTO;
import web.model.data.DAOs.JugadorDAO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige a la página de inicio de sesión
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        JugadorDAO jugadorDAO = new JugadorDAO();
        JugadorDTO jugador = jugadorDAO.getUsuarioEmail(email);

        if(!jugador.getPassword().equals(password) ) {
            jugador = null;
        }

        if (jugador != null) {
            // Usuario encontrado, crear CustomerBean y almacenarlo en la sesión
            CustomerBean customerBean = new CustomerBean();
            customerBean.setId(jugador.getId());
            customerBean.setNombre(jugador.getNombre());
            customerBean.setApellidos(jugador.getApellidos());
            customerBean.setEmail(jugador.getEmail());
            customerBean.setFechaNacimiento(jugador.getFechaNacimiento());

            request.getSession().setAttribute("customerBean", customerBean);
            response.sendRedirect("views/welcome.jsp");
        } else {
            // Usuario no encontrado, redirigir a la página de inicio de sesión con un mensaje de error
            request.setAttribute("error", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }
}