package web.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.MaterialDTO;
import web.model.business.DTOs.MaterialDTO.EstadoMaterial;
import web.model.business.DTOs.MaterialDTO.TipoMaterial;
import web.model.business.Gestores.GestorDeMateriales;



@WebServlet("/crearMaterial")
public class crearMaterialServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TipoMaterial tipoMaterial = TipoMaterial.valueOf(request.getParameter("tipoMaterial"));
            boolean usoExterior = request.getParameter("usoExterior") != null;
            EstadoMaterial estadoMaterial = EstadoMaterial.valueOf(request.getParameter("estadoMaterial"));

            GestorDeMateriales gestorDeMateriales = GestorDeMateriales.getGestor();
            MaterialDTO nuevoMaterial = gestorDeMateriales.insertarMaterial(tipoMaterial, usoExterior, estadoMaterial);

            if (nuevoMaterial != null) {
                request.setAttribute("mensaje", "Material creado exitosamente. ID del material: " + nuevoMaterial.getId());
            } else {
                request.setAttribute("mensaje", "Error al crear el material.");
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al crear el material: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/crearMaterial.jsp").forward(request, response);
    }
}