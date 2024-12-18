package web.model.data.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import web.model.business.DTOs.BonoDTO;
import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.MaterialDTO;
import web.model.business.DTOs.PistaDTO;
import web.model.business.DTOs.PistaDTO.TamanoPista;
import web.model.business.DTOs.Reservas.ReservaAdultosDTO;
import web.model.business.DTOs.Reservas.ReservaBonoFactoryDTO;
import web.model.business.DTOs.Reservas.ReservaDTO;
import web.model.business.DTOs.Reservas.ReservaFamiliarDTO;
import web.model.business.DTOs.Reservas.ReservaInfantilDTO;


/**
 * La clase DBConnection proporciona métodos para gestionar la conexión a una base de datos MySQL y realizar operaciones CRUD en varias tablas.
 * 
 * <p>Esta clase permite establecer y cerrar conexiones con la base de datos, así como realizar operaciones de inserción, selección, actualización y eliminación en las tablas Pista, Reserva, Bono, Jugador y Material.</p>
 * 
 * <p>Los métodos principales de esta clase son:</p>
 * <ul>
 *   <li>{@link #getConnection()}: Establece una conexión con la base de datos utilizando los parámetros de conexión especificados.</li>
 *   <li>{@link #insertIntoPista(int, String, boolean, boolean, TamanoPista, int)}: Inserta un nuevo registro en la tabla Pista.</li>
 *   <li>{@link #selectPistas()}: Recupera una lista de objetos PistaDTO desde la base de datos.</li>
 *   <li>{@link #selectPistasNoDisponibles()}: Recupera una lista de objetos PistaDTO que no están disponibles.</li>
 *   <li>{@link #selectPistasDisponibles()}: Recupera una lista de pistas disponibles de la base de datos.</li>
 *   <li>{@link #selectPistasDisponiblesPorFechaYTipo(LocalDateTime, boolean)}: Selecciona una lista de pistas disponibles según la fecha y el tipo de pista.</li>
 *   <li> {@link #selectPistasDisponiblesPorFechaYTipo(LocalDateTime, PistaDTO.TamanoPista)}: Selecciona una lista de pistas disponibles según la fecha y el tipo de pista.</li>
 *   <li>{@link #selectPistasDisponiblesFecha(LocalDateTime)}: Selecciona una lista de pistas disponibles en una fecha específica.</li>
 *   <li>{@link #selectPistasDisponiblesJugadoresTipo(int, TamanoPista)}: Selecciona una lista de pistas disponibles según el número máximo de jugadores y el tamaño de la pista.</li>
 *   <li>{@link #selectPistaNombre(String)}: Selecciona una lista de objetos PistaDTO de la base de datos cuyo nombre coincide con el nombre proporcionado.</li>
 *   <li>{@link #insertIntoReserva(int, int, LocalDate, int, int, int, int, float, float, PistaDTO.TamanoPista, String, int, int)}: Inserta un nuevo registro en la tabla Reserva.</li>
 *   <li>{@link #selectReservaPorId(int)}: Recupera un registro de la tabla Reserva basado en el ID proporcionado.</li>
 *   <li>{@link #selectReservasFuturas()}: Recupera una lista de reservas futuras desde la base de datos.</li>
 *   <li>{@link #selectReservasFuturasUsuario(int)}: Recupera una lista de reservas futuras para un usuario específico.</li>
 *   <li>{@link #selectReservaPistaDia(int, LocalDate)}: Selecciona las reservas de una pista específica en un día determinado.</li>
 *   <li>{@link #deleteReserva(int)}: Elimina una reserva de la base de datos basada en el ID proporcionado.</li>
 *   <li>{@link #selectReservaUsuario(int)}: Recupera una lista de reservas realizadas por un usuario específico.</li>
 *   <li>{@link #insertIntoBono(int, int, int, String, PistaDTO.TamanoPista)}: Inserta un nuevo registro en la tabla Bono.</li>
 *   <li>{@link #reducirBono(int)}: Reduce el número de sesiones de un bono en 1. Si el bono tiene solo una sesión, se elimina el bono de la base de datos.</li>
 *   <li>{@link #selectBonos(int)}: Recupera una lista de bonos asociados a un usuario específico desde la base de datos.</li>
 *   <li>{@link #insertIntoJugador(String, String, int, LocalDate, LocalDate, String)}: Inserta un nuevo registro en la tabla Jugador.</li>
 *   <li>{@link #updateJugador(String, String, int, LocalDate, LocalDate, String)}: Actualiza un registro en la tabla Jugador con los datos proporcionados.</li>
 *   <li>{@link #selectJugadores()}: Recupera una lista de objetos JugadorDTO desde la base de datos.</li>
 *   <li>{@link #selectJugadorEmail(String)}: Recupera un registro de la tabla Jugador basado en el correo electrónico proporcionado.</li>
 *   <li>{@link #selectJugadorId(int)}: Recupera un registro de la tabla Jugador basado en el ID proporcionado.</li>
 *   <li>{@link #insertIntoMaterial(int, MaterialDTO.TipoMaterial, boolean, MaterialDTO.EstadoMaterial, int)}: Inserta un nuevo registro en la tabla Material.</li>
 *   <li>{@link #selectMateriales()}: Recupera una lista de objetos MaterialDTO desde la base de datos.</li>
 *   <li>{@link #selectMaterialId(int)}: Recupera un registro de la tabla Material basado en el ID proporcionado.</li>
 *   <li>{@link #updateMaterialPista(int, int)}: Actualiza el idPista de un material en la tabla Material.</li>
 *   <li>{@link #closeConnection()}: Cierra la conexión a la base de datos si está abierta.</li>
 *   <li>{@link #getMaxId(String)}: Recupera el valor máximo de ID de la tabla especificada.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * DBConnection dbConnection = new DBConnection();
 * Connection connection = dbConnection.getConnection();
 * dbConnection.insertIntoPista(1, "Pista 1", true, false, TamanoPista.GRANDE, 4);
 * List<PistaDTO> pistas = dbConnection.selectPistas();
 * dbConnection.closeConnection();
 * }
 * </pre>
 * 
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 */
public class DBConnection {
	protected Connection connection = null;
	protected static DBConfig dbConfig = null;
    protected static Properties sqlProperties = new Properties();

	// Important: This configuration is hard-coded here for illustrative purposes only
	//Vamos a usar mi bd porque Aurora aun no ha corregido la P2
	protected static String url = dbConfig != null ? dbConfig.getUrl() : null;
	protected static String user = dbConfig != null ? dbConfig.getUsername() : null;
	protected static String password = dbConfig != null ? dbConfig.getPassword() : null;


	/**
	 * Establece una conexión con la base de datos utilizando los parámetros de conexión
	 * especificados (url, usuario y contraseña). 
	 * 
	 * @return Connection La conexión establecida con la base de datos.
	 * @throws SQLException Si ocurre un error al intentar establecer la conexión con la base de datos.
	 * @throws ClassNotFoundException Si el controlador JDBC no se encuentra en el classpath.
	 */
	public DBConnection getConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection) DriverManager.getConnection(url, user, password);
		} 
		catch (SQLException e) {
			System.err.println("Connection to MySQL has failed!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		}
		return this;
	}

	public static void setSql(InputStream input) {
		try {
            if (input == null) {
                System.out.println("Sorry, unable to find the SQL configuration file.");
                return;
            }
            sqlProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	public static void setConfig(InputStream input) {
		DBConnection.dbConfig = new DBConfig(input);
		url = DBConnection.dbConfig.getUrl();
		user = DBConnection.dbConfig.getUsername();
		password = DBConnection.dbConfig.getPassword();
	}

	/**
	 * Inserta un nuevo registro en la tabla Pista.
	 *
	 * @param id El identificador único de la pista.
	 * @param nombre El nombre de la pista.
	 * @param disponible Indica si la pista está disponible.
	 * @param esExterior Indica si la pista es exterior.
	 * @param tamano El tamaño de la pista.
	 * @param maxJugadores El número máximo de jugadores que pueden usar la pista.
	 */
	public void insertIntoPista(int id, String nombre, boolean disponible, boolean esExterior, TamanoPista tamano, int maxJugadores) {
		String query = sqlProperties.getProperty("insertIntoPista");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			stmt.setString(2, nombre);
			stmt.setBoolean(3, disponible);
			stmt.setBoolean(4, esExterior);
			stmt.setString(5, tamano.name());
			stmt.setInt(6, maxJugadores);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while inserting record into Pista table.");
			e.printStackTrace();
		}
	}

	/**
	 * Recupera una lista de objetos PistaDTO desde la base de datos.
	 *
	 * @return una lista de objetos PistaDTO que representan las pistas obtenidas de la tabla Pista.
	 * @throws SQLException si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<PistaDTO> selectPistas() {
		List<PistaDTO> pistas = new ArrayList<>();
		String query = sqlProperties.getProperty("selectPistas");
		try (PreparedStatement stmt = this.connection.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				PistaDTO pista = new PistaDTO(
					rs.getString("nombre"),
					rs.getInt("id"),
					rs.getBoolean("disponible"),
					rs.getBoolean("esExterior"),
					TamanoPista.valueOf(rs.getString("tamano")),
					rs.getInt("maxJugadores")
				);
				pistas.add(pista);
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Pista table.");
			e.printStackTrace();
		}
		return pistas;
	}

	/**
	 * Recupera una lista de objetos PistaDTO que no están disponibles.
	 *
	 * @return una lista de PistaDTO que representan las pistas no disponibles.
	 * @throws SQLException si ocurre un error al recuperar los registros de la base de datos.
	 */
	public List<PistaDTO> selectPistasNoDisponibles() {
		List<PistaDTO> pistasNoDisponibles = new ArrayList<>();
		String query = sqlProperties.getProperty("selectPistasNoDisponibles");
		try (PreparedStatement stmt = this.connection.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				PistaDTO pista = new PistaDTO(
					rs.getString("nombre"),
					rs.getInt("id"),
					rs.getBoolean("disponible"),
					rs.getBoolean("esExterior"),
					TamanoPista.valueOf(rs.getString("tamano")),
					rs.getInt("maxJugadores")
				);
				pistasNoDisponibles.add(pista);
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Pista table.");
			e.printStackTrace();
		}
		return pistasNoDisponibles;
	}

	/**
	 * Recupera una lista de pistas disponibles de la base de datos.
	 *
	 * @return Una lista de objetos PistaDTO que representan las pistas disponibles.
	 * @throws SQLException Si ocurre un error al recuperar los registros de la tabla Pista.
	 */
	public List<PistaDTO> selectPistasDisponibles() {
		List<PistaDTO> pistasDisponibles = new ArrayList<>();
		String query = sqlProperties.getProperty("selectPistasDisponibles");
		try (PreparedStatement stmt = this.connection.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				PistaDTO pista = new PistaDTO(
					rs.getString("nombre"),
					rs.getInt("id"),
					rs.getBoolean("disponible"),
					rs.getBoolean("esExterior"),
					TamanoPista.valueOf(rs.getString("tamano")),
					rs.getInt("maxJugadores")
				);
				pistasDisponibles.add(pista);
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Pista table.");
			e.printStackTrace();
		}
		return pistasDisponibles;
	}

	/**
	 * Selecciona una lista de pistas disponibles según la fecha y el tipo de pista.
	 * @param fecha La fecha en la que se desean buscar las pistas disponibles.
	 * @param esExterior Indica si la pista es exterior.
	 * @return
	 */
	public List<PistaDTO> selectPistasDisponiblesPorFechaYTipo(LocalDateTime fecha, boolean esExterior) {
        List<PistaDTO> pistasDisponibles = new ArrayList<>();
		String query = sqlProperties.getProperty("selectPistasDisponiblesFechaExterior");
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setBoolean(1, esExterior);
			stmt.setTimestamp(2, Timestamp.valueOf(fecha));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PistaDTO pista = new PistaDTO();
                    pista.setId(rs.getInt("id"));
                    pista.setNombre(rs.getString("nombre"));
                    pista.setDisponible(rs.getBoolean("disponible"));
                    pista.setEsExterior(rs.getBoolean("esExterior"));
                    pista.setTamano(TamanoPista.valueOf(rs.getString("tamano")));
                    pista.setMaxJugadores(rs.getInt("maxJugadores"));
                    pistasDisponibles.add(pista);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pistasDisponibles;
    }

	/**
	 * Selecciona una lista de pistas disponibles según la fecha y el tipo de pista.
	 * @param fecha La fecha en la que se desean buscar las pistas disponibles.
	 * @param tamano El tamaño de la pista (enum TamanoPista).
	 * @return	Una lista de objetos PistaDTO que representan las pistas disponibles en la fecha especificada.
	 */
	public List<PistaDTO> selectPistasDisponiblesPorFechaYTipo(LocalDateTime fecha, PistaDTO.TamanoPista tamano) {
		List<PistaDTO> pistasDisponibles = new ArrayList<>();
		String query = sqlProperties.getProperty("selectPistasDisponiblesFechaTamano");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setString(1, tamano.name());
			stmt.setTimestamp(2, Timestamp.valueOf(fecha));
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					PistaDTO pista = new PistaDTO(
						rs.getString("nombre"),
						rs.getInt("id"),
						rs.getBoolean("disponible"),
						rs.getBoolean("esExterior"),
						TamanoPista.valueOf(rs.getString("tamano")),
						rs.getInt("maxJugadores")
					);
					pistasDisponibles.add(pista);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Pista table.");
			e.printStackTrace();
		}
		return pistasDisponibles;
	}

	/**
	 * Selecciona una lista de pistas disponibles en una fecha específica.
	 *
	 * @param fecha La fecha en la que se desean buscar las pistas disponibles.
	 * @return Una lista de objetos PistaDTO que representan las pistas disponibles en la fecha especificada.
	 * @throws SQLException Si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<PistaDTO> selectPistasDisponiblesFecha(LocalDateTime fecha) {
		List<PistaDTO> pistasDisponibles = new ArrayList<>();
		String query = sqlProperties.getProperty("selectPistasDisponiblesFecha");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setTimestamp(1, Timestamp.valueOf(fecha));
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					PistaDTO pista = new PistaDTO(
						rs.getString("nombre"),
						rs.getInt("id"),
						rs.getBoolean("disponible"),
						rs.getBoolean("esExterior"),
						TamanoPista.valueOf(rs.getString("tamano")),
						rs.getInt("maxJugadores")
					);
					pistasDisponibles.add(pista);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Pista table.");
			e.printStackTrace();
		}
		return pistasDisponibles;
	}


	/**
	 * Selecciona una lista de pistas disponibles según el número máximo de jugadores y el tamaño de la pista.
	 *
	 * @param maxJugadores El número máximo de jugadores permitido en la pista.
	 * @param tamano El tamaño de la pista (enum TamanoPista).
	 * @return Una lista de objetos PistaDTO que representan las pistas disponibles que cumplen con los criterios especificados.
	 * @throws SQLException Si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<PistaDTO> selectPistasDisponiblesJugadoresTipo(int maxJugadores, TamanoPista tamano) {
		List<PistaDTO> pistasDisponibles = new ArrayList<>();
		String query = sqlProperties.getProperty("selectPistasDisponiblesJugadoresTipo");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, maxJugadores);
			stmt.setString(2, tamano.name());
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					PistaDTO pista = new PistaDTO(
						rs.getString("nombre"),
						rs.getInt("id"),
						rs.getBoolean("disponible"),
						rs.getBoolean("esExterior"),
						TamanoPista.valueOf(rs.getString("tamano")),
						rs.getInt("maxJugadores")
					);
					pistasDisponibles.add(pista);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Pista table.");
			e.printStackTrace();
		}
		return pistasDisponibles;

	}
	
	/**
	 * Selecciona una lista de objetos PistaDTO de la base de datos cuyo nombre coincide con el nombre proporcionado.
	 *
	 * @param nombre El nombre de la pista que se desea buscar.
	 * @return Una lista de objetos PistaDTO que coinciden con el nombre proporcionado.
	 * @throws SQLException Si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<PistaDTO> selectPistaNombre(String nombre) {
		List<PistaDTO> pistas = new ArrayList<>();
		String query = sqlProperties.getProperty("selectPistaNombre");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setString(1, nombre);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					PistaDTO pista = new PistaDTO(
						rs.getString("nombre"),
						rs.getInt("id"),
						rs.getBoolean("disponible"),
						rs.getBoolean("esExterior"),
						TamanoPista.valueOf(rs.getString("tamano")),
						rs.getInt("maxJugadores")
					);
					pistas.add(pista);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Pista table.");
			e.printStackTrace();
		}
		return pistas;
	}

	/**
	 * Actualiza la disponibilidad de una pista en la base de datos.
	 *
	 * @param idPista El id de la pista que se actualizará.
	 * @param nuevaDisponibilidad La nueva disponibilidad que se asignará a la pista.
	 */
	public void updatePistaDisponibilidad(int idPista, boolean nuevaDisponibilidad) {
		String query = sqlProperties.getProperty("updatePistaDisponibilidad");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setBoolean(1, nuevaDisponibilidad);
			stmt.setInt(2, idPista);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while updating Pista availability.");
			e.printStackTrace();
		}
	}

	/**
	 * Inserta un nuevo registro en la tabla Reserva.
	 *
	 * @param id El identificador único de la reserva.
	 * @param idUsuario El identificador del usuario que realiza la reserva.
	 * @param diaYHora La fecha y hora de la reserva.
	 * @param idBono El identificador del bono asociado a la reserva.
	 * @param nSesionBono El número de sesión del bono.
	 * @param duracion La duración de la reserva en minutos.
	 * @param idPista El identificador de la pista reservada.
	 * @param precio El precio de la reserva.
	 * @param descuento El descuento aplicado a la reserva.
	 * @param pistaTamano El tamaño de la pista reservada.
	 * @param tipoReserva El tipo de reserva.
	 * @param numAdultos El número de adultos en la reserva.
	 * @param numNinos El número de niños en la reserva.
	 */
	public void insertIntoReserva(int id, int idUsuario, LocalDateTime diaYHora, int idBono, int nSesionBono, int duracion, int idPista, float precio, float descuento, PistaDTO.TamanoPista pistaTamano, String tipoReserva, int numAdultos, int numNinos) {
		String query = sqlProperties.getProperty("insertIntoReserva");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			stmt.setInt(2, idUsuario);
			stmt.setTimestamp(3, Timestamp.valueOf(diaYHora));
			stmt.setInt(4, idBono);
			stmt.setInt(5, nSesionBono);
			stmt.setInt(6, duracion);
			stmt.setInt(7, idPista);
			stmt.setFloat(8, precio);
			stmt.setFloat(9, descuento);
			stmt.setString(10, pistaTamano.name());
			stmt.setString(11, tipoReserva);
			stmt.setInt(12, numAdultos);
			stmt.setInt(13, numNinos);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while inserting record into Reserva table.");
			e.printStackTrace();
		}
	}

	/**
	 * Recupera un registro de la tabla Reserva basado en el ID proporcionado.
	 *
	 * @param id El ID de la reserva que se desea recuperar.
	 * @return Un objeto ReservaDTO que representa la reserva recuperada.
	 * @throws SQLException Si ocurre un error al intentar recuperar el registro de la base de datos.
	 */
	public ReservaDTO selectReservaPorId(int id) {
		ReservaDTO reserva = null;
		String query = sqlProperties.getProperty("selectReservaPorId");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.ADULTOS.name())){
						reserva = ReservaBonoFactoryDTO.crearReservaAdultos(
								rs.getString("tipo"),
								rs.getInt("idUsuario"),
								rs.getTimestamp("diaYHora").toLocalDateTime(),
								rs.getInt("idBono"),
								rs.getInt("nSesionBono"),
								rs.getInt("duracion"),
								rs.getInt("idPista"),
								rs.getFloat("precio"),
								rs.getFloat("descuento"),
								PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
								rs.getInt("id"),
								rs.getInt("numAdultos"),
								rs.getInt("numNinos")
							);
					}
					else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.FAMILIAR.name())){
						reserva = ReservaBonoFactoryDTO.crearReservaFamiliar(
								rs.getString("tipo"),
								rs.getInt("idUsuario"),
								rs.getTimestamp("diaYHora").toLocalDateTime(),
								rs.getInt("idBono"),
								rs.getInt("nSesionBono"),
								rs.getInt("duracion"),
								rs.getInt("idPista"),
								rs.getFloat("precio"),
								rs.getFloat("descuento"),
								PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
								rs.getInt("id"),
								rs.getInt("numAdultos"),
								rs.getInt("numNinos")
							);
					}
					else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.INFANTIL.name())){
						reserva = ReservaBonoFactoryDTO.crearReservaInfantil(
								rs.getString("tipo"),
								rs.getInt("idUsuario"),
								rs.getTimestamp("diaYHora").toLocalDateTime(),
								rs.getInt("idBono"),
								rs.getInt("nSesionBono"),
								rs.getInt("duracion"),
								rs.getInt("idPista"),
								rs.getFloat("precio"),
								rs.getFloat("descuento"),
								PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
								rs.getInt("id"),
								rs.getInt("numAdultos"),
								rs.getInt("numNinos")
							);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving record from Reserva table.");
			e.printStackTrace();
		}
		return reserva;
	}
	
	/**
	 * Recupera una lista de reservas futuras desde la base de datos.
	 *
	 * Este método ejecuta una consulta SQL para seleccionar todos los registros de la tabla "Reserva"
	 * donde el campo "diaYHora" es mayor que la marca de tiempo actual, indicando reservas futuras.
	 * Mapea cada registro a un objeto ReservaDTO y lo agrega a una lista.
	 *
	 * @return Una lista de objetos ReservaDTO que representan las reservas futuras.
	 */
	public List<ReservaDTO> selectReservasFuturas() {
		List<ReservaDTO> reservasFuturas = new ArrayList<>();
		String query = sqlProperties.getProperty("selectReservasFuturas");
		try (PreparedStatement stmt = this.connection.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.ADULTOS.name())){
					ReservaAdultosDTO reserva = ReservaBonoFactoryDTO.crearReservaAdultos(
						rs.getString("tipo"),
						rs.getInt("idUsuario"),
						rs.getTimestamp("diaYHora").toLocalDateTime(),
						rs.getInt("idBono"),
						rs.getInt("nSesionBono"),
						rs.getInt("duracion"),
						rs.getInt("idPista"),
						rs.getFloat("precio"),
						rs.getFloat("descuento"),
						PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
						rs.getInt("id"),
						rs.getInt("numAdultos"),
						rs.getInt("numNinos")	
					);
					reservasFuturas.add(reserva);
				}
				else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.FAMILIAR.name())){
					ReservaFamiliarDTO reserva = ReservaBonoFactoryDTO.crearReservaFamiliar(
						rs.getString("tipo"),
						rs.getInt("idUsuario"),
						rs.getTimestamp("diaYHora").toLocalDateTime(),
						rs.getInt("idBono"),
						rs.getInt("nSesionBono"),
						rs.getInt("duracion"),
						rs.getInt("idPista"),
						rs.getFloat("precio"),
						rs.getFloat("descuento"),
						PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
						rs.getInt("id"),
						rs.getInt("numAdultos"),
						rs.getInt("numNinos")	
					);
					reservasFuturas.add(reserva);
				}
				else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.INFANTIL.name())){
					ReservaInfantilDTO reserva = ReservaBonoFactoryDTO.crearReservaInfantil(
						rs.getString("tipo"),
						rs.getInt("idUsuario"),
						rs.getTimestamp("diaYHora").toLocalDateTime(),
						rs.getInt("idBono"),
						rs.getInt("nSesionBono"),
						rs.getInt("duracion"),
						rs.getInt("idPista"),
						rs.getFloat("precio"),
						rs.getFloat("descuento"),
						PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
						rs.getInt("id"),
						rs.getInt("numAdultos"),
						rs.getInt("numNinos")	
					);
					reservasFuturas.add(reserva);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Reserva table.");
			e.printStackTrace();
		}
		return reservasFuturas;
	}

	/**
	 * Recupera una lista de reservas futuras para un usuario específico.
	 *
	 * @param idUsuario El ID del usuario para el cual se desean obtener las reservas futuras.
	 * @return Una lista de objetos ReservaDTO que representan las reservas futuras del usuario.
	 * @throws SQLException Si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<ReservaDTO> selectReservasFuturasUsuario(int idUsuario) {
		List<ReservaDTO> reservasFuturasUsuario = new ArrayList<>();
		String query = sqlProperties.getProperty("selectReservasFuturasUsuario");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, idUsuario);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.ADULTOS.name())){
						ReservaAdultosDTO reserva = ReservaBonoFactoryDTO.crearReservaAdultos(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservasFuturasUsuario.add(reserva);
					}
					else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.FAMILIAR.name())){
						ReservaFamiliarDTO reserva = ReservaBonoFactoryDTO.crearReservaFamiliar(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservasFuturasUsuario.add(reserva);
					}
					else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.INFANTIL.name())){
						ReservaInfantilDTO reserva = ReservaBonoFactoryDTO.crearReservaInfantil(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservasFuturasUsuario.add(reserva);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Reserva table.");
			e.printStackTrace();
		}
		return reservasFuturasUsuario;
	}
	
	/**
	 * Selecciona las reservas de una pista específica en un día determinado.
	 *
	 * @param idPista El identificador de la pista.
	 * @param dia El día para el cual se desean obtener las reservas.
	 * @return Una lista de objetos ReservaDTO que representan las reservas encontradas.
	 * @throws SQLException Si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<ReservaDTO> selectReservaPistaDia(int idPista, LocalDateTime dia) {
		List<ReservaDTO> reservas = new ArrayList<>();
		String query = sqlProperties.getProperty("selectReservaPistaDia");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, idPista);
			stmt.setTimestamp(2, Timestamp.valueOf(dia));
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.ADULTOS.name())){
						ReservaAdultosDTO reserva = ReservaBonoFactoryDTO.crearReservaAdultos(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservas.add(reserva);
					}
					else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.FAMILIAR.name())){
						ReservaFamiliarDTO reserva = ReservaBonoFactoryDTO.crearReservaFamiliar(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservas.add(reserva);
					}
					else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.INFANTIL.name())){
						ReservaInfantilDTO reserva = ReservaBonoFactoryDTO.crearReservaInfantil(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservas.add(reserva);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Reserva table.");
			e.printStackTrace();
		}
		return reservas;
	}


	/**
	 * Elimina una reserva de la base de datos basada en el ID proporcionado.
	 *
	 * @param id El ID de la reserva que se desea eliminar.
	 * @throws SQLException Si ocurre un error al intentar eliminar la reserva de la base de datos.
	 */
	public void deleteReserva(int id) {
		String query = sqlProperties.getProperty("deleteReserva");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Error while deleting record from Reserva table.");
			e.printStackTrace();
		}
	}

	/**
	 * Recupera una lista de reservas realizadas por un usuario específico.
	 *
	 * @param idUsuario El ID del usuario cuyas reservas se desean recuperar.
	 * @return Una lista de objetos ReservaDTO que representan las reservas del usuario.
	 * @throws SQLException Si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<ReservaDTO> selectReservaUsuario(int idUsuario) {
		List<ReservaDTO> reservas = new ArrayList<>();
		String query = sqlProperties.getProperty("selectReservaUsuario");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, idUsuario);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.ADULTOS.name())){
						ReservaAdultosDTO reserva = ReservaBonoFactoryDTO.crearReservaAdultos(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservas.add(reserva);
					}
					else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.FAMILIAR.name())){
						ReservaFamiliarDTO reserva = ReservaBonoFactoryDTO.crearReservaFamiliar(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservas.add(reserva);
					}
					else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.INFANTIL.name())){
						ReservaInfantilDTO reserva = ReservaBonoFactoryDTO.crearReservaInfantil(
							rs.getString("tipo"),
							rs.getInt("idUsuario"),
							rs.getTimestamp("diaYHora").toLocalDateTime(),
							rs.getInt("idBono"),
							rs.getInt("nSesionBono"),
							rs.getInt("duracion"),
							rs.getInt("idPista"),
							rs.getFloat("precio"),
							rs.getFloat("descuento"),
							PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
							rs.getInt("id"),
							rs.getInt("numAdultos"),
							rs.getInt("numNinos")	
						);
						reservas.add(reserva);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Reserva table.");
			e.printStackTrace();
		}
		return reservas;
	}

	public List<ReservaDTO> selectTodasReservas() {
		List<ReservaDTO> reservas = new ArrayList<>();
		String query = sqlProperties.getProperty("selectTodasReservas");
		try (PreparedStatement stmt = this.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.ADULTOS.name())){
					ReservaAdultosDTO reserva = ReservaBonoFactoryDTO.crearReservaAdultos(
						rs.getString("tipo"),
						rs.getInt("idUsuario"),
						rs.getTimestamp("diaYHora").toLocalDateTime(),
						rs.getInt("idBono"),
						rs.getInt("nSesionBono"),
						rs.getInt("duracion"),
						rs.getInt("idPista"),
						rs.getFloat("precio"),
						rs.getFloat("descuento"),
						PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
						rs.getInt("id"),
						rs.getInt("numAdultos"),
						rs.getInt("numNinos")	
					);
					reservas.add(reserva);
				}
				else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.FAMILIAR.name())){
					ReservaFamiliarDTO reserva = ReservaBonoFactoryDTO.crearReservaFamiliar(
						rs.getString("tipo"),
						rs.getInt("idUsuario"),
						rs.getTimestamp("diaYHora").toLocalDateTime(),
						rs.getInt("idBono"),
						rs.getInt("nSesionBono"),
						rs.getInt("duracion"),
						rs.getInt("idPista"),
						rs.getFloat("precio"),
						rs.getFloat("descuento"),
						PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
						rs.getInt("id"),
						rs.getInt("numAdultos"),
						rs.getInt("numNinos")	
					);
					reservas.add(reserva);
				}
				else if(rs.getString("tipo").equals(ReservaDTO.tipoReserva.INFANTIL.name())){
					ReservaInfantilDTO reserva = ReservaBonoFactoryDTO.crearReservaInfantil(
						rs.getString("tipo"),
						rs.getInt("idUsuario"),
						rs.getTimestamp("diaYHora").toLocalDateTime(),
						rs.getInt("idBono"),
						rs.getInt("nSesionBono"),
						rs.getInt("duracion"),
						rs.getInt("idPista"),
						rs.getFloat("precio"),
						rs.getFloat("descuento"),
						PistaDTO.TamanoPista.valueOf(rs.getString("pistaTamano")),
						rs.getInt("id"),
						rs.getInt("numAdultos"),
						rs.getInt("numNinos")	
					);
					reservas.add(reserva);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Reserva table.");
			e.printStackTrace();
		}
		return reservas;
	}


	/**
	 * Inserta un nuevo registro en la tabla Bono.
	 *
	 * @param id           El identificador del bono.
	 * @param sesiones     El número de sesiones del bono.
	 * @param idUser       El identificador del usuario asociado al bono.
	 * @param tipoReserva  El tipo de reserva asociado al bono.
	 * @param pistaTamano  El tamaño de la pista asociado al bono.
	 */
	public void insertIntoBono(int id, int sesiones, int idUser, String tipoReserva, PistaDTO.TamanoPista pistaTamano) {
		String query = sqlProperties.getProperty("insertIntoBono");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			stmt.setInt(2, sesiones);
			stmt.setInt(3, idUser);
			stmt.setString(4, tipoReserva);
			stmt.setString(5, pistaTamano.name());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while inserting record into Bono table.");
			e.printStackTrace();
		}
	}

	/**
	 * Reduce el número de sesiones de un bono en 1. Si el bono tiene solo una sesión,
	 * se elimina el bono de la base de datos.
	 *
	 * @param idBono El ID del bono a reducir.
	 */
	public void reducirBono(int idBono) {
		String selectQuery = sqlProperties.getProperty("selectSesionesBono");
		String updateQuery = sqlProperties.getProperty("updateSesionesBono");
		String deleteQuery = sqlProperties.getProperty("deleteBono");
		try (PreparedStatement selectStmt = this.connection.prepareStatement(selectQuery)) {
			selectStmt.setInt(1, idBono);
			try (ResultSet rs = selectStmt.executeQuery()) {
				if (rs.next()) {
					int sesiones = rs.getInt("sesiones");
					if (sesiones > 1) {
						try (PreparedStatement updateStmt = this.connection.prepareStatement(updateQuery)) {
							updateStmt.setInt(1, idBono);
							updateStmt.executeUpdate();
						}
					} else {
						try (PreparedStatement deleteStmt = this.connection.prepareStatement(deleteQuery)) {
							deleteStmt.setInt(1, idBono);
							deleteStmt.executeUpdate();
						}
					}
				} else {
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while reducing Bono sessions.");
			e.printStackTrace();
		}
	}

	/**
	 * Recupera una lista de bonos asociados a un usuario específico desde la base de datos.
	 *
	 * @param idUser El ID del usuario cuyos bonos se desean recuperar.
	 * @return Una lista de objetos BonoDTO que representan los bonos del usuario.
	 * @throws SQLException Si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<BonoDTO> selectBonos(int idUser) {
		List<BonoDTO> bonos = new ArrayList<>();
		String query = sqlProperties.getProperty("selectBonos");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, idUser);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					BonoDTO bono = new BonoDTO(
						rs.getInt("id"),
						rs.getInt("sesiones"),
						rs.getInt("idUser"),
						rs.getString("tipoReserva"),
						PistaDTO.TamanoPista.valueOf(rs.getString("tamanoPista"))
					);
					bonos.add(bono);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Bono table.");
			e.printStackTrace();
		}
		return bonos;
	}


	/**
	 * Inserta un nuevo registro en la tabla Jugador.
	 *
	 * @param nombre El nombre del jugador.
	 * @param apellidos Los apellidos del jugador.
	 * @param id El identificador único del jugador.
	 * @param fechaNacimiento La fecha de nacimiento del jugador.
	 * @param fechaInscripcion La fecha de inscripción del jugador.
	 * @param email El correo electrónico del jugador.
	 * @throws SQLException Si ocurre un error al insertar el registro en la base de datos.
	 */
	public void insertIntoJugador(String nombre, String apellidos, int id, LocalDate fechaNacimiento, LocalDate fechaInscripcion, String email, String password, JugadorDTO.Roles rol) {
		String query = sqlProperties.getProperty("insertIntoJugador");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setString(1, nombre);
			stmt.setString(2, apellidos);
			stmt.setInt(3, id);
			stmt.setDate(4, java.sql.Date.valueOf(fechaNacimiento));
			stmt.setDate(5, java.sql.Date.valueOf(fechaInscripcion));
			stmt.setString(6, email);
			stmt.setString(7, password);
			stmt.setString(8, rol.name());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while inserting record into Jugador table.");
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza un registro en la tabla Jugador con los datos proporcionados.
	 *
	 * @param nombre El nuevo nombre del jugador.
	 * @param apellidos Los nuevos apellidos del jugador.
	 * @param id El ID del jugador a actualizar.
	 * @param fechaNacimiento La nueva fecha de nacimiento del jugador.
	 * @param fechaInscripcion La nueva fecha de inscripción del jugador.
	 * @param email El nuevo correo electrónico del jugador.
	 * @throws SQLException Si ocurre un error al actualizar el registro en la base de datos.
	 */
	public void updateJugador(String nombre, String apellidos, int id, LocalDate fechaNacimiento, LocalDate fechaInscripcion, String email) {
		String query = sqlProperties.getProperty("updateJugador");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setString(1, nombre);
			stmt.setString(2, apellidos);
			stmt.setDate(3, java.sql.Date.valueOf(fechaNacimiento));
			stmt.setDate(4, java.sql.Date.valueOf(fechaInscripcion));
			stmt.setString(5, email);
			stmt.setInt(6, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while updating record in Jugador table.");
			e.printStackTrace();
		}
	}


	/**
	 * Recupera una lista de objetos JugadorDTO desde la base de datos.
	 *
	 * @return Una lista de objetos JugadorDTO que representan los registros de la tabla Jugador.
	 * @throws SQLException Si ocurre un error al recuperar los registros de la base de datos.
	 */
	public List<JugadorDTO> selectJugadores() {
		List<JugadorDTO> jugadores = new ArrayList<>();
		String query = sqlProperties.getProperty("selectJugadores");
		try (PreparedStatement stmt = this.connection.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				JugadorDTO jugador = new JugadorDTO(
					rs.getString("nombre"),
					rs.getString("apellidos"),
					rs.getInt("id"),
					rs.getDate("fechaNacimiento").toLocalDate(),
					rs.getDate("fechaInscripcion").toLocalDate(),
					rs.getString("email"), 
					rs.getString("password"),
					JugadorDTO.Roles.valueOf(rs.getString("rol"))
				);
				jugadores.add(jugador);
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Jugador table.");
			e.printStackTrace();
		}
		return jugadores;
	}

	/**
	 * Recupera un registro de la tabla Jugador basado en el correo electrónico proporcionado.
	 *
	 * @param email El correo electrónico del jugador que se desea buscar.
	 * @return Un objeto JugadorDTO que contiene los datos del jugador si se encuentra un registro con el correo electrónico proporcionado,
	 *         o null si no se encuentra ningún registro.
	 * @throws SQLException Si ocurre un error al intentar recuperar el registro de la base de datos.
	 */
	public JugadorDTO selectJugadorEmail(String email) {
		JugadorDTO jugador = null;
		String query = sqlProperties.getProperty("selectJugadorEmail");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					jugador = new JugadorDTO(
						rs.getString("nombre"),
						rs.getString("apellidos"),
						rs.getInt("id"),
						rs.getDate("fechaNacimiento").toLocalDate(),
						rs.getDate("fechaInscripcion").toLocalDate(),
						rs.getString("email"),
						rs.getString("password"),
						JugadorDTO.Roles.valueOf(rs.getString("rol"))

					);
				} else {
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving record from Jugador table.");
			e.printStackTrace();
		}
		return jugador;
	}

	/**
	 * Recupera un registro de la tabla Jugador basado en el ID proporcionado.
	 *
	 * @param id El ID del jugador que se desea buscar.
	 * @return Un objeto JugadorDTO que contiene los datos del jugador si se encuentra un registro con el ID proporcionado,
	 *         o null si no se encuentra ningún registro.
	 * @throws SQLException Si ocurre un error al intentar recuperar el registro de la base de datos.
	 */
	public JugadorDTO selectJugadorId(int id) {
		JugadorDTO jugador = null;
		String query = sqlProperties.getProperty("selectJugadorId");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					jugador = new JugadorDTO(
						rs.getString("nombre"),
						rs.getString("apellidos"),
						rs.getInt("id"),
						rs.getDate("fechaNacimiento").toLocalDate(),
						rs.getDate("fechaInscripcion").toLocalDate(),
						rs.getString("email"),
						rs.getString("password"),
						JugadorDTO.Roles.valueOf(rs.getString("rol"))
					);
				} else {
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving record from Jugador table.");
			e.printStackTrace();
		}
		return jugador;
	}

	/**
	 * Inserta un nuevo registro en la tabla Material.
	 *
	 * @param id El identificador del material.
	 * @param tipo El tipo de material (enum MaterialDTO.TipoMaterial).
	 * @param usoExterior Indica si el material es para uso exterior.
	 * @param estado El estado del material (enum MaterialDTO.EstadoMaterial).
	 * @param idPista El identificador de la pista asociada al material.
	 */
	public void insertIntoMaterial(int id, MaterialDTO.TipoMaterial tipo, boolean usoExterior, MaterialDTO.EstadoMaterial estado, int idPista) {
		String query = sqlProperties.getProperty("insertIntoMaterial");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			stmt.setString(2, tipo.name());
			stmt.setBoolean(3, usoExterior);
			stmt.setString(4, estado.name());
			stmt.setInt(5, idPista);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while inserting record into Material table.");
			e.printStackTrace();
		}
	}

	/**
	 * Recupera una lista de objetos MaterialDTO desde la base de datos.
	 *
	 * @return una lista de objetos MaterialDTO que representan los materiales recuperados de la tabla Material.
	 * @throws SQLException si ocurre un error al intentar recuperar los registros de la base de datos.
	 */
	public List<MaterialDTO> selectMateriales() {
		List<MaterialDTO> materiales = new ArrayList<>();
		String query = sqlProperties.getProperty("selectMateriales");
		try (PreparedStatement stmt = this.connection.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				MaterialDTO material = new MaterialDTO(
					rs.getInt("id"),
					MaterialDTO.TipoMaterial.valueOf(rs.getString("tipo")),
					rs.getBoolean("usoExterior"),
					MaterialDTO.EstadoMaterial.valueOf(rs.getString("estado")),
					rs.getInt("idPista")
				);
				materiales.add(material);
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving records from Material table.");
			e.printStackTrace();
		}
		return materiales;
	}

	/**
	 * Recupera un registro de la tabla Material basado en el ID proporcionado.
	 *
	 * @param id El ID del material que se desea recuperar.
	 * @return Un objeto MaterialDTO que contiene los datos del material recuperado,
	 *         o null si no se encuentra ningún registro con el ID proporcionado.
	 * @throws SQLException Si ocurre un error al intentar recuperar el registro de la base de datos.
	 */
	public MaterialDTO selectMaterialId(int id) {
		MaterialDTO material = null;
		String query = sqlProperties.getProperty("selectMaterialId");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					material = new MaterialDTO(
						rs.getInt("id"),
						MaterialDTO.TipoMaterial.valueOf(rs.getString("tipo")),
						rs.getBoolean("usoExterior"),
						MaterialDTO.EstadoMaterial.valueOf(rs.getString("estado")),
						rs.getInt("idPista")
					);
				} else {
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving record from Material table.");
			e.printStackTrace();
		}
		return material;
	}

	/**
	 * Actualiza el idPista de un material en la tabla Material.
	 *
	 * @param idPista El nuevo idPista que se asignará al material.
	 * @param idMaterial El id del material que se actualizará.
	 * @throws SQLException Si ocurre un error al ejecutar la actualización en la base de datos.
	 */
	public void updateMaterialPista(int idPista, int idMaterial) {
		String query = sqlProperties.getProperty("updateMaterialPista");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setInt(1, idPista);
			stmt.setInt(2, idMaterial);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Error while updating record in Material table.");
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza el estado de un material en la tabla Material.
	 *
	 * @param idMaterial El id del material que se actualizará.
	 * @param nuevoEstado El nuevo estado que se asignará al material.
	 * @throws SQLException Si ocurre un error al ejecutar la actualización en la base de datos.
	 */
	public void updateMaterialEstado(int idMaterial, MaterialDTO.EstadoMaterial nuevoEstado) {
		String query = sqlProperties.getProperty("updateMaterialEstado");
		try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setString(1, nuevoEstado.name());
			stmt.setInt(2, idMaterial);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while updating record in Material table.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Cierra la conexión a la base de datos si está abierta.
	 * 
	 * Este método verifica si la conexión a la base de datos no es nula y no está cerrada.
	 * Si la conexión está abierta, la cierra y muestra un mensaje de confirmación en la consola.
	 * En caso de que ocurra una SQLException durante el proceso de cierre, se captura la excepción
	 * y se imprime un mensaje de error en la consola junto con el stack trace de la excepción.
	 */
	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}

	
	/**
	 * Recupera el valor máximo de ID de la tabla especificada.
	 *
	 * @param table el nombre de la tabla de la cual recuperar el ID máximo.
	 * @return el valor máximo de ID de la tabla especificada, o -1 si ocurre un error.
	 */
	public int getMaxId(String table) {
		String query = sqlProperties.getProperty("getMaxId").replace("{table}", table);
		try (Statement stmt = this.connection.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Failed to retrieve the maximum Pista ID.");
			e.printStackTrace();
		}
		return -1; // Devuelve -1 si no se pudo obtener el ID
	}

	/**
     * Actualiza una reserva en la base de datos.
     *
     * @param reserva El objeto ReservaDTO que representa la reserva a actualizar.
     */
    public void updateReserva(ReservaDTO reserva) {
        String query = sqlProperties.getProperty("updateReserva");
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
			stmt.setTimestamp(1, Timestamp.valueOf(reserva.getDiaYHora()));
            stmt.setInt(2, reserva.getIdReserva());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
