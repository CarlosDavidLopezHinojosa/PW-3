package data.DAOs;

import business.DTOs.MaterialDTO;
import business.DTOs.MaterialDTO.EstadoMaterial;
import business.DTOs.MaterialDTO.TipoMaterial;
import data.common.DBConnection;
import java.util.List;


/**
 * La clase MaterialDAO proporciona métodos para interactuar con la base de datos de materiales.
 * Permite insertar nuevos materiales, obtener una lista de todos los materiales y obtener un material específico por su ID.
 * 
 * <p>La información de los materiales se gestiona a través de la clase DBConnection, que maneja las conexiones y operaciones con la base de datos.
 * Cada material se representa mediante un objeto de la clase MaterialDTO, que contiene datos como el tipo de material, si es para uso exterior,
 * el estado del material, entre otros.</p>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #insertarMaterial(TipoMaterial, boolean, EstadoMaterial)}: Inserta un nuevo material en la base de datos.</li>
 *   <li>{@link #obtenerMateriales()}: Obtiene una lista de todos los materiales registrados en la base de datos.</li>
 *   <li>{@link #obtenerMaterialId(int)}: Obtiene un material específico por su ID.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * MaterialDTO nuevoMaterial = MaterialDAO.insertarMaterial(TipoMaterial.MADERA, true, EstadoMaterial.NUEVO);
 * List<MaterialDTO> materiales = MaterialDAO.obtenerMateriales();
 * MaterialDTO material = MaterialDAO.obtenerMaterialId(1);
 * }
 * </pre>
 * 
 * @see data.DTOs.MaterialDTO
 * @see data.DBConnection
 */
public class MaterialDAO {
    
    /**
     * Inserta un nuevo material en la base de datos.
     *
     * @param tipo El tipo de material a insertar.
     * @param usoExterior Indica si el material es para uso exterior.
     * @param estado El estado del material.
     * @return Un objeto MaterialDTO que representa el material insertado, o null si ocurre un error.
     */
    public static MaterialDTO insertarMaterial(TipoMaterial tipo, boolean usoExterior, EstadoMaterial estado){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        try {
            int id = conexion.getMaxId("Material") + 1;
            MaterialDTO material = new MaterialDTO(id, tipo, usoExterior, estado, -1);
            conexion.insertIntoMaterial(id, tipo, usoExterior, estado, -1);
            conexion.closeConnection();
            return material;
            
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Obtiene una lista de materiales desde la base de datos.
     *
     * @return una lista de objetos MaterialDTO que representan los materiales obtenidos.
     */
    public static List<MaterialDTO> obtenerMateriales(){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        List<MaterialDTO> materiales = conexion.selectMateriales();
        conexion.closeConnection();
        return materiales;
    }

    /**
     * Obtiene un material por su ID.
     *
     * @param materialId El ID del material que se desea obtener.
     * @return Un objeto MaterialDTO que representa el material con el ID especificado.
     */
    public static MaterialDTO obtenerMaterialId(int materialId){
        DBConnection conexion = new DBConnection();
        conexion.getConnection();
        MaterialDTO material = conexion.selectMaterialId(materialId);
        conexion.closeConnection();
        return material;
    }
}
