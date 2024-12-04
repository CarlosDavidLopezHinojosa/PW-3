package web.controller.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.JugadorDTO;
import web.model.data.DAOs.JugadorDAO;

@WebServlet("/RegisterServlet")
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

            // Redirigir a la página de bienvenida
            request.getSession().setAttribute("usuario", newJugador);
            response.sendRedirect("views/welcome.jsp");
        } else {
            // Usuario ya existe, mostrar mensaje de error
            request.setAttribute("error", "El correo ya está registrado");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        }
    }
}