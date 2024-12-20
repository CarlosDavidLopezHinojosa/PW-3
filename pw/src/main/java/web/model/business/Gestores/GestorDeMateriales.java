package web.model.business.Gestores;

import java.util.List;

import web.model.business.DTOs.MaterialDTO;
import web.model.business.DTOs.MaterialDTO.EstadoMaterial;
import web.model.business.DTOs.MaterialDTO.TipoMaterial;
import web.model.data.DAOs.MaterialDAO;

public class GestorDeMateriales {
    private static GestorDeMateriales gestor;

    private GestorDeMateriales() {}

    public static GestorDeMateriales getGestor() {
        if (gestor == null) {
            gestor = new GestorDeMateriales();
        }
        return gestor;
    }

    public MaterialDTO insertarMaterial(TipoMaterial tipo, boolean usoExterior, EstadoMaterial estado) {
        return MaterialDAO.insertarMaterial(tipo, usoExterior, estado);
    }

    public List<MaterialDTO> obtenerMateriales() {
        return MaterialDAO.obtenerMateriales();
    }

    public MaterialDTO obtenerMaterialId(int id) {
        return MaterialDAO.obtenerMaterialId(id);
    }

    public void modificarMaterialEstado(int id, EstadoMaterial nuevoEstado) {
        MaterialDAO.modificarMaterialEstado(id, nuevoEstado);
    }
    
}
