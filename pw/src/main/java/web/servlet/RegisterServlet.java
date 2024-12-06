package web.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.Beans.CustomerBean;
import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.JugadorDTO.Roles;
import web.model.business.Gestores.GestorDeUsuarios;
import web.model.data.DAOs.JugadorDAO;

@WebServlet("/Registro")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige a la página de registro
        request.getRequestDispatcher("views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
        Roles rol = Roles.CLIENTE;

        JugadorDTO existingJugador = JugadorDAO.getUsuarioEmail(email);
        

        if (existingJugador == null) {
            // Usuario no existe, registrar nuevo usuario
            GestorDeUsuarios.darDeAlta(email, name, lastName, fechaNacimiento, password, rol);
            // Crear CustomerBean y almacenarlo en la sesión
            JugadorDTO jugador = JugadorDAO.getUsuarioEmail(email);

            CustomerBean customerBean = new CustomerBean();
            customerBean.setData(jugador);

            request.getSession().setAttribute("customerBean", customerBean);
            response.sendRedirect("views/welcome.jsp");
        } else {
            // Usuario ya existe, mostrar mensaje de error
            request.setAttribute("error", "El correo ya está registrado");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        }
    }
}
