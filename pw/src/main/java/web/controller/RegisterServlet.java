package web.controller;

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

        JugadorDAO jugadorDAO = new JugadorDAO();
        JugadorDTO existingJugador = jugadorDAO.getUsuarioEmail(email);

        if (existingJugador == null) {
            // Usuario no existe, registrar nuevo usuario
            JugadorDTO newJugador = new JugadorDTO();
            newJugador.setNombre(name);
            newJugador.setApellidos(lastName);
            newJugador.setEmail(email);
            newJugador.setPassword(password); // Asegúrate de manejar la contraseña de manera segura
            newJugador.setFechaNacimiento(fechaNacimiento);

            jugadorDAO.darDeAlta(email, name, lastName, fechaNacimiento, password);

            // Crear CustomerBean y almacenarlo en la sesión
            CustomerBean customerBean = new CustomerBean();
            customerBean.setId(newJugador.getId());
            customerBean.setNombre(newJugador.getNombre());
            customerBean.setApellidos(newJugador.getApellidos());
            customerBean.setEmail(newJugador.getEmail());
            customerBean.setFechaNacimiento(newJugador.getFechaNacimiento());

            request.getSession().setAttribute("customerBean", customerBean);
            response.sendRedirect("views/welcome.jsp");
        } else {
            // Usuario ya existe, mostrar mensaje de error
            request.setAttribute("error", "El correo ya está registrado");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        }
    }
}