package web.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.MaterialDTO;
import web.model.data.DAOs.MaterialDAO;

@WebServlet("/verMateriales")
public class verMaterialesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<MaterialDTO> materiales = MaterialDAO.obtenerMateriales();
        // Set the list of materials as a request attribute
        request.setAttribute("materiales", materiales);

        // Forward the request to materiales.jsp
        request.getRequestDispatcher("/views/verMateriales.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}
