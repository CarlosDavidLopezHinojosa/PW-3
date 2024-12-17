package web.servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.MaterialDTO;
import web.model.business.DTOs.MaterialDTO.EstadoMaterial;
import web.model.data.DAOs.MaterialDAO;



@WebServlet("/modificarEstadoMaterial")
public class modificarEstadoMaterialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<MaterialDTO> materiales = MaterialDAO.obtenerMateriales();
            request.setAttribute("materiales", materiales);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al obtener los materiales: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/modificarEstadoMaterial.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int materialId = Integer.parseInt(request.getParameter("materialId"));
            EstadoMaterial nuevoEstado = EstadoMaterial.valueOf(request.getParameter("nuevoEstado"));

            MaterialDAO.modificarMaterialEstado(materialId, nuevoEstado);

            request.setAttribute("mensaje", "Estado del material modificado exitosamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al modificar el estado del material: " + e.getMessage());
        }

        doGet(request, response);
    }
}