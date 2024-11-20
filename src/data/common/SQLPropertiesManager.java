package data.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * SQLPropertiesManager es una clase auxiliar para cargar y gestionar las consultas SQL
 * definidas en un archivo de propiedades.
 */
public class SQLPropertiesManager {

    private static final String PROPERTIES_FILE_PATH = "sql.properties";
    private Properties properties;

    /**
     * Constructor que carga las propiedades desde el archivo especificado.
     */
    public SQLPropertiesManager() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading SQL properties file.");
            e.printStackTrace();
        }
    }

    /**
     * Recupera una consulta SQL por su clave.
     *
     * @param key La clave de la consulta SQL.
     * @return La consulta SQL correspondiente, o null si la clave no existe.
     */
    public String getSQL(String key) {
        return properties.getProperty(key);
    }
}