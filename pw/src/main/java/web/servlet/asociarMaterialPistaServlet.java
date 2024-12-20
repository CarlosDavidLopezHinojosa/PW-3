package web.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.business.DTOs.MaterialDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.business.Gestores.GestorDeMateriales;
import web.model.business.Gestores.GestorDePistas;



@WebServlet("/asociarMaterialPista")
public class asociarMaterialPistaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int materialId = Integer.parseInt(request.getParameter("materialId"));
            int pistaId = Integer.parseInt(request.getParameter("pistaId"));

            GestorDeMateriales gestorDeMateriales = GestorDeMateriales.getGestor();
            MaterialDTO mat = gestorDeMateriales.obtenerMaterialId(materialId);
            if(mat.getIdPista() == (pistaId)){
                request.setAttribute("error", "El material que ha seleccionado ya esta asociado a esa pista");
            }
            else{
                PistaDTO pista = GestorDePistas.obtenerPistaPorId(pistaId);
                MaterialDTO material = gestorDeMateriales.obtenerMaterialId(materialId);
                GestorDePistas.asociarMaterialAPista(pista, material);
                request.setAttribute("mensaje", "Material asociado a la pista exitosamente.");
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error al asociar el material a la pista: " + e.getMessage());
        }
        doGet(request, response);
        request.getRequestDispatcher("/views/asociarMaterialPista.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            GestorDeMateriales gestorDeMateriales = GestorDeMateriales.getGestor();
            List<MaterialDTO> materiales = gestorDeMateriales.obtenerMateriales();
            List<PistaDTO> pistas = GestorDePistas.obtenerPistas();

            request.setAttribute("materiales", materiales);
            request.setAttribute("pistas", pistas);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al obtener los datos: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/asociarMaterialPista.jsp").forward(request, response);
    }
}