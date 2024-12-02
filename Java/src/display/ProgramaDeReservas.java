package display;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import business.DTOs.BonoDTO;
import business.DTOs.JugadorDTO;
import business.DTOs.MaterialDTO;
import business.DTOs.MaterialDTO.EstadoMaterial;
import business.DTOs.MaterialDTO.TipoMaterial;
import business.DTOs.PistaDTO;
import business.DTOs.PistaDTO.TamanoPista;
import business.DTOs.Reservas.ReservaDTO;
import business.Gestores.GestorDePistas;
import business.Gestores.GestorDeReservas;
import business.Gestores.GestorDeUsuarios;
import data.DAOs.BonoDAO;
import data.DAOs.JugadorDAO;
import data.DAOs.MaterialDAO;
import data.DAOs.PistaDAO;

public class ProgramaDeReservas {

    // Singleton
    private static ProgramaDeReservas programaDeReservas;
    private ProgramaDeReservas() {
        // Private constructor to prevent instantiation
    }
    public static ProgramaDeReservas getProgramaDeReservas() {
        if (programaDeReservas == null) {
            programaDeReservas = new ProgramaDeReservas();
        }
        return programaDeReservas;
    }

    public static void main(String[] args) {

        ProgramaDeReservas programaDeReservas = getProgramaDeReservas();
        char opc = '0';
        while (opc != '3') {
            opc = programaDeReservas.menuInicio();
            switch (opc) {
                case '1':
                    programaDeReservas.registrarse();
                    break;
                case '2':
                    programaDeReservas.iniciarSesion();
                    break;
                case '3':
                    programaDeReservas.salir();
                    break;
                default:
                    System.err.println("ERROR: No deberías poder leer esto (código: 1)");
                    return;
            }
        }

    }

    // Menú inicial
    private char menuInicio() {
        char opc = '0';
        System.out.println("--- Programa de reserva de pistas de baloncesto ---");
        System.out.println("¿Qué desea hacer?\n");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir\n");
        boolean flag = false;
        while (!flag) {
            try {
                opc = (char) System.in.read();
                System.in.read(); // \n
                if (opc == '1' || opc == '2' || opc == '3') {
                    flag = true;
                } else {
                    System.out.println("Opción no válida. Por favor, introduzca 1, 2 o 3\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return opc;
    }

    // Proceso de registro
    private void registrarse() {
        boolean avanzar = false;
        
        String email = "";
        while (avanzar == false) {
             
            System.out.println("Introduzca su email o CANCELAR para abortar\n");
            // Leemos de la terminal
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                email = new String(buffer, 0, leido).trim();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Comprobamos si el usuario quiere abortar
            if (email.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            }

            /* CÓDIGO ANTIGUO: Buscamos en Jugadores.txt si hay algún usuario con el email especificado
            try {
                
                final String emailComprobar = email;
                File bd = new File("Jugadores.txt");
                if (bd.exists()) {
                    List<String> lines = Files.readAllLines(bd.toPath());
                    boolean exists = lines.stream().anyMatch(line -> line.split("\\*")[0].equals(emailComprobar));
                    if (exists) {
                        System.out.println("\nYa existe un usuario con ese email, vuelva a intentarlo\n");
                    } else {
                        avanzar = true;
                    }
                } else {
                    avanzar = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            
            // Buscamos en la base de datos si hay algún usuario con el email especificado
            if (JugadorDAO.existeUsuarioEmail(email)) {
                System.out.println("\nYa existe un usuario con ese email, vuelva a intentarlo\n"); 
            } else {
                avanzar = true;
            }
        }

        avanzar = false;
        String nombre = "";
        // Leemos el nombre
        System.out.println("Ahora introduzca su nombre\n");
        try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            nombre = new String(buffer,0,leido).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leemos los apellidos
        String apellidos = "";
        System.out.println("Ahora introduzca sus apellidos\n");
        try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            apellidos = new String(buffer,0,leido).trim();
        } catch (IOException e) {
            e.printStackTrace();
        } 

        // Leemos la fecha de nacimiento
        avanzar = false;
        String fechaNacimiento = "";
        System.out.println("Ahora introduzca su fecha de nacimiento (formato: aaaa-mm-dd)\n");
        while (!avanzar) {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            fechaNacimiento = new String(buffer, 0, leido).trim();
            if (fechaNacimiento.matches("\\d{4}-\\d{2}-\\d{2}")) {
                avanzar = true;
            } else {
                System.out.println("Formato de fecha no válido. Por favor, inténtelo de nuevo.\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        }

        // Leemos la contraseña
        String password = "";
        System.out.println("Ahora introduzca una contraseña\n");
        try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            password = new String(buffer,0,leido).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalDate fechaNacimientoLocalDateTime = LocalDate.parse(fechaNacimiento);
        JugadorDTO user = GestorDeUsuarios.darDeAlta(email, nombre, apellidos, fechaNacimientoLocalDateTime, password);
        menuIniciado(user);
    }

    private void iniciarSesion() {
        boolean avanzar = false;
        String email = "";
        while (!avanzar) {
            System.out.println("Introduzca su email para iniciar sesión o CANCELAR para abortar\n");
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                email = new String(buffer, 0, leido).trim();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (email.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            }

            if (email.equals("admin")) {
                menuAdmin();
                return;
            }

            /* CÓDIGO ANTIGUO
            try {
                final String emailComprobar = email;
                File bd = new File("Jugadores.txt");
                if (!bd.exists()) {
                    System.out.println("\nNo existe un usuario con ese email\n");
                    return;
                }
                List<String> lines = Files.readAllLines(bd.toPath());
                boolean exists = lines.stream().anyMatch(line -> line.split("\\*")[0].equals(emailComprobar));
                if (!exists) {
                    System.out.println("\nNo existe un usuario con ese email, vuelva a intentarlo\n");
                } else {
                    avanzar = true;
                    for (String line : lines) {
                        String[] parts = line.split("\\*");
                        if (parts[0].equals(email)) {
                            int id = Integer.parseInt(parts[1]);
                            String nombre = parts[2];
                            String apellidos = parts[3];
                            LocalDate fechaNacimiento = LocalDate.parse(parts[4]);
                            LocalDate fechaInscripcion = LocalDate.parse(parts[5]);
                            Jugador user = new Jugador(nombre, apellidos, id, fechaNacimiento, email);
                            user.setFechaInscripcion(fechaInscripcion);
                            menuIniciado(user);
                            return;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            JugadorDTO user = JugadorDAO.getUsuarioEmail(email);
            if (user == null) {
                System.out.println("\nNo existe un usuario con ese email, vuelva a intentarlo\n"); 
            } else {
                avanzar = true;
                menuIniciado(user);
            }
        }
    }

    private void salir() {
        System.out.println("Gracias por usar el programa de reserva de pistas de baloncesto\n");
        System.exit(0);
    }
    
    private void menuIniciado(JugadorDTO user) {
        char opc = '0';
        while (opc != '7') {
            System.out.println("--- Menú de Usuario ---");
            System.out.println("1. Hacer una reserva");
            System.out.println("2. Obtener un bono");
            System.out.println("3. Ver mis reservas");
            System.out.println("4. Cancelar una reserva");
            System.out.println("5. Consultar las reservas existentes para un día y pista concretos");
            System.out.println("6. Modificar mis datos de usuario");
            System.out.println("7. Salir\n");

            try {
                opc = (char) System.in.read();
                System.in.read(); // \n
                switch (opc) {
                    case '1':
                        procesoHacerReserva(user);
                        break;
                    case '2':
                        crearBono(user);
                        break;
                    case '3':
                        consultarMisReservasFuturas(user);
                        break;
                    case '4':
                        eliminarReserva(user);
                        break;
                    case '5':
                        consultarReservaPistaDia();
                        break;
                    case '6':
                        procesoModificarInformacion(user);
                        break;
                    case '7':
                        System.out.println("Saliendo...\n");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, introduzca un número del 1 al 7\n");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void menuAdmin() {
        char opc = '0';
        while (opc != '6') {
            System.out.println("--- Menú de Administrador ---");
            System.out.println("1. Listar usuarios registrados");
            System.out.println("2. Crear una pista");
            System.out.println("3. Crear un material");
            System.out.println("4. Asociar material a una pista");
            System.out.println("5. Listar reservas futuras");
            System.out.println("6. Salir\n");

            try {
                opc = (char) System.in.read();
                System.in.read(); // \n
                switch (opc) {
                    case '1':
                        listarUsuarios();
                        break;
                    case '2':
                        procesoCrearPista();
                        break;
                    case '3':
                        procesoCrearMaterial();
                        break;
                    case '4':
                        procesoAsociarMaterialAPista();
                        break;
                    case '5':
                        consultarReservasFuturas();
                        break;
                    case '6':
                        System.out.println("Saliendo...\n");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, introduzca un número del 1 al 6\n");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void procesoCrearPista() {
        String opc = "";
        boolean avanzar = false;
        String nombre = "";
        while (!avanzar) {
            System.out.println("Introduzca el nombre de la pista o CANCELAR para salir:\n");
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                nombre = new String(buffer, 0, leido).trim();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (nombre.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            }

            if (!nombre.isEmpty()) {
                avanzar = true;
            } else {
                System.out.println("El nombre de la pista no puede estar vacío. Por favor, inténtelo de nuevo.\n");
            }

            if (PistaDAO.booleanPistaNombre(nombre)) {
               System.out.println("\nYa existe una pista con ese nombre, vuelva a intentarlo\n"); 
               avanzar = false;
            } else {
                avanzar = true;
            }
        }

        boolean disponible = false;
        avanzar = false;
        while (!avanzar) {
            System.out.println("¿La pista está disponible? (si/no) o CANCELAR para salir:\n");
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                opc = new String(buffer, 0, leido).trim().toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (opc.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            } else if (opc.equals("si")) {
                disponible = true;
                avanzar = true;
            } else if (opc.equals("no")) {
                disponible = false;
                avanzar = true;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca si, no o CANCELAR.\n");
            }
            
        }

        boolean exterior = false;
        avanzar = false;
        while (!avanzar) {
            System.out.println("¿La pista es exterior? (si/no) o CANCELAR para salir:\n");
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                opc = new String(buffer, 0, leido).trim().toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (opc.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            } else if (opc.equals("si")) {
                exterior = true;
                avanzar = true;
            } else if (opc.equals("no")) {
                exterior = false;
                avanzar = true;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca si, no o CANCELAR.\n");
            }
        }

        TamanoPista tamano = null;
        avanzar = false;
        while (!avanzar) {
            System.out.println("Introduzca el tamaño de la pista (MINIBASKET, ADULTOS, VS3) o CANCELAR para salir:\n");
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                opc = new String(buffer, 0, leido).trim().toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (opc.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            } else {
                try {
                    tamano = TamanoPista.valueOf(opc);
                    avanzar = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Tamaño no válido. Por favor, introduzca MINIBASKET, ADULTOS o VS3.\n");
                }
            }
        }

        int maxJugadores = 0;
        avanzar = false;
        while (!avanzar) {
            System.out.println("Introduzca el máximo de jugadores que admite la pista o CANCELAR para salir:\n");
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                opc = new String(buffer, 0, leido).trim();
                if (opc.equals("CANCELAR")) {
                    System.out.println("\nAbortando...\n");
                    return;
                } else {
                    maxJugadores = Integer.parseInt(opc);
                    avanzar = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Número no válido. Por favor, introduzca un número entero.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nResumen de la pista a crear:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Disponible: " + (disponible ? "Sí" : "No"));
        System.out.println("Exterior: " + (exterior ? "Sí" : "No"));
        System.out.println("Tamaño: " + tamano);
        System.out.println("Máximo de jugadores: " + maxJugadores);
        System.out.println("\n¿Desea crear la pista? (si/no)");

        avanzar = false;
        while (!avanzar) {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            opc = new String(buffer, 0, leido).trim().toLowerCase();
            if (opc.equals("si")) {
                GestorDePistas.crearPista(nombre, disponible, exterior, tamano, maxJugadores);
                avanzar = true;
            } else if (opc.equals("no")) {
                System.out.println("\nAbortando...\n"); 
                avanzar = true;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca si o no.\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

    private void procesoCrearMaterial() {
        String opc = "";
        boolean avanzar = false;

        TipoMaterial tipo = null;
        while (!avanzar) {
            System.out.println("Introduzca el tipo de material (PELOTAS, CANASTAS, CONOS) o CANCELAR para salir:\n");
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            opc = new String(buffer, 0, leido).trim().toUpperCase();
            } catch (IOException e) {
            e.printStackTrace();
            }

            if (opc.equals("CANCELAR")) {
            System.out.println("\nAbortando...\n");
            return;
            } else {
            try {
                tipo = TipoMaterial.valueOf(opc);
                avanzar = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo no válido. Por favor, introduzca PELOTAS, CANASTAS o CONOS.\n");
            }
            }
        }

        boolean usoExterior = false;
        avanzar = false;
        while (!avanzar) {
            System.out.println("¿El material puede ser usado en exterior? (si/no) o CANCELAR para salir:\n");
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            opc = new String(buffer, 0, leido).trim().toLowerCase();
            } catch (IOException e) {
            e.printStackTrace();
            }

            if (opc.equals("CANCELAR")) {
            System.out.println("\nAbortando...\n");
            return;
            } else if (opc.equals("si")) {
            usoExterior = true;
            avanzar = true;
            } else if (opc.equals("no")) {
            usoExterior = false;
            avanzar = true;
            } else {
            System.out.println("Respuesta no válida. Por favor, introduzca si, no o CANCELAR.\n");
            }
        }

        EstadoMaterial estado = null;
        avanzar = false;
        while (!avanzar) {
            System.out.println("Introduzca el estado del material (DISPONIBLE, RESERVADO, MAL_ESTADO) o CANCELAR para salir:\n");
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            opc = new String(buffer, 0, leido).trim().toUpperCase();
            } catch (IOException e) {
            e.printStackTrace();
            }

            if (opc.equals("CANCELAR")) {
            System.out.println("\nAbortando...\n");
            return;
            } else {
            try {
                estado = EstadoMaterial.valueOf(opc);
                avanzar = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Estado no válido. Por favor, introduzca DISPONIBLE, RESERVADO o MAL_ESTADO.\n");
            }
            }
        }

        System.out.println("\nResumen del material a crear:");
        System.out.println("Tipo: " + tipo);
        System.out.println("Uso exterior: " + (usoExterior ? "Sí" : "No"));
        System.out.println("Estado: " + estado);
        System.out.println("\n¿Desea crear el material? (si/no)");

        avanzar = false;
        while (!avanzar) {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            opc = new String(buffer, 0, leido).trim().toLowerCase();
            if (opc.equals("si")) {
                GestorDePistas.crearMaterial(tipo, usoExterior, estado);
                avanzar = true;
            } else if (opc.equals("no")) {
                System.out.println("\nAbortando...\n");
                avanzar = true;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca si o no.\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

    private void procesoAsociarMaterialAPista() {
        
        List<MaterialDTO> materiales = MaterialDAO.obtenerMateriales();

        if (materiales.isEmpty()) {
            System.out.println("No hay materiales registrados.");
            return;
        }

        System.out.println("--- Lista de Materiales ---");
        for (MaterialDTO material : materiales) {
        System.out.print("ID: " + material.getId() + ", Tipo: " + material.getTipo() + ", Uso exterior: " + (material.isUsoExterior() ? "Sí" : "No") + ", Estado: " + material.getEstado());
                    int idPista = material.getIdPista();
                    if (idPista == -1) {
                        System.out.println("Pista asociada: ninguna");
                    } else {
                        System.out.println("Pista asociada: " + idPista);
                    }
        }

        System.out.println("Introduzca el ID del material que desea asociar a una pista o CANCELAR para salir:\n");
        String opc = "";
        boolean avanzar = false;
        int materialId = 0;
        while (!avanzar) {
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                opc = new String(buffer, 0, leido).trim();
                if (opc.equals("CANCELAR")) {
                    System.out.println("\nAbortando...\n");
                    return;
                } else {
                    materialId = Integer.parseInt(opc);
                    avanzar = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ID no válido. Por favor, introduzca un número entero.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MaterialDTO material = MaterialDAO.obtenerMaterialId(materialId);

        if (material == null) {
            System.out.println("No se encontró un material con el ID especificado.\n");
            return;
        }

        List<PistaDTO> pistas = PistaDAO.obtenerPistas();

        if (pistas.isEmpty()) {
            System.out.println("No hay pistas registradas.");
            return;
        }

        System.out.println("--- Lista de Pistas ---");
        for (PistaDTO pista : pistas) {
            System.out.println("ID: " + pista.getId() + ", Nombre: " + pista.getNombre() + ", Disponible: " + (pista.isDisponible() ? "Sí" : "No") + ", Exterior: " + (pista.isEsExterior() ? "Sí" : "No") + ", Tamaño: " + pista.getTamano() + ", Máximo de jugadores: " + pista.getMaxJugadores());
        }

        System.out.println("Introduzca el nombre de la pista a la que desea asociar el material o CANCELAR para salir:\n");
        opc = "";
        avanzar = false;
        String pistaNombre = "";
        while (!avanzar) {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            opc = new String(buffer, 0, leido).trim();
            if (opc.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            } else {
                pistaNombre = opc;
                avanzar = true;
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        }

        PistaDTO pista = PistaDAO.obtenerPistaNombre(pistaNombre);

        if (pista == null) {
            System.out.println("No se encontró una pista con el nombre especificado.\n");
            return;
        }

        GestorDePistas.asociarMaterialAPista(pista, material);
        System.out.println("\nMaterial asociado a la pista con éxito.\n");
    }

    private void procesoHacerReserva(JugadorDTO user) {
        boolean avanzar = false;
        String opc = "";
        while (!avanzar) {
            System.out.println("¿Desea hacer una nueva reserva o canjear la reserva de un bono? (nueva/bono) o CANCELAR para salir:\n");
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                opc = new String(buffer, 0, leido).trim().toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (opc.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            } else if (opc.equals("nueva")) {
                hacerReservaIndividual(user);
                avanzar = true;
            } else if (opc.equals("bono")) {
                hacerReservaBono(user);
                avanzar = true;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca nueva, bono o CANCELAR.\n");
            }
        }
    }

    private void procesoModificarInformacion(JugadorDTO user) {
        boolean avanzar = false;
        String opc = "";

        System.out.println("Datos actuales del usuario:");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Nombre: " + user.getNombre());
        System.out.println("Apellidos: " + user.getApellidos());
        System.out.println("Fecha de nacimiento: " + user.getFechaNacimiento());

       // Modificar email
       System.out.println("Introduzca el nuevo email o CANCELAR para salir:\n");
       try {
           byte[] buffer = new byte[100];
           int leido = System.in.read(buffer);
           opc = new String(buffer, 0, leido).trim();
           if (opc.equals("CANCELAR")) {
               System.out.println("\nAbortando...\n");
               return;
           } else {
               user.setEmail(opc);
           }
       } catch (IOException e) {
           e.printStackTrace();
       } 

        // Modificar nombre
        System.out.println("Introduzca el nuevo nombre o CANCELAR para salir:\n");
        try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            opc = new String(buffer, 0, leido).trim();
            if (opc.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            } else {
                user.setNombre(opc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Modificar apellidos
        System.out.println("Introduzca los nuevos apellidos o CANCELAR para salir:\n");
        try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            opc = new String(buffer, 0, leido).trim();
            if (opc.equals("CANCELAR")) {
                System.out.println("\nAbortando...\n");
                return;
            } else {
                user.setApellidos(opc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Modificar fecha de nacimiento
        avanzar = false;
        while (!avanzar) {
            System.out.println("Introduzca la nueva fecha de nacimiento (formato: aaaa-mm-dd) o CANCELAR para salir:\n");
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                opc = new String(buffer, 0, leido).trim();
                if (opc.equals("CANCELAR")) {
                    System.out.println("\nAbortando...\n");
                    return;
                }
                if (opc.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    user.setFechaNacimiento(LocalDate.parse(opc));
                    avanzar = true;
                } else {
                    System.out.println("Formato de fecha no válido. Por favor, inténtelo de nuevo.\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        GestorDeUsuarios.modificarInformacion(user.getId(), user.getEmail(), user.getNombre(), user.getApellidos(), user.getFechaNacimiento());

        System.out.println("Datos actualizados correctamente.\n");
    }

    public void hacerReservaIndividual(JugadorDTO user) {
        int idJugador = user.getId();
        String tipoReserva = "";
        LocalDate diaYHora = null;
        int duracion = 0;
        int idPista = 0;
        float precio = 0;
        float descuento = 0;
        PistaDTO.TamanoPista pistaTamano = null;
        int numAdultos = 0;
        int numNinos = 0;
        boolean avanzar = false;

        System.out.println("Introduzca el tipo de reserva (INFANTIL, ADULTOS o FAMILIAR) o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            tipoReserva = new String(buffer, 0, leido).trim();
            if (tipoReserva.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            if (!tipoReserva.equals("INFANTIL") && !tipoReserva.equals("ADULTOS") && !tipoReserva.equals("FAMILIAR")) {
                System.out.println("\nError: tipo de reserva no válido. Por favor, introduzca 'INFANTIL', 'ADULTOS' o 'FAMILIAR'.\n");
            } else {
                avanzar = true;
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        } while (avanzar == false);

        avanzar = false;
        System.out.println("Introduzca la fecha de la reserva en formato aaaa-mm-dd o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String input = new String(buffer, 0, leido).trim();
            if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            diaYHora = LocalDate.parse(input);
            avanzar = true;
            } catch (IOException e) {
            e.printStackTrace();
            } catch (Exception e) {
            System.out.println("\nError: formato de fecha no válido. Por favor, introduzca la fecha en formato aaaa-mm-ddTHH:mm.\n");
            }
        } while (avanzar == false);
        
        if (diaYHora.isBefore(LocalDate.now().plusDays(1))) {
            System.out.println("No se admiten reservas con menos de 24 horas de antelación.");
            return;
        }

        avanzar = false;
        System.out.println("Introduzca la duración de la reserva en minutos (60, 90, 120) o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String input = new String(buffer, 0, leido).trim();
            if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            duracion = Integer.parseInt(input);
            if (duracion != 60 && duracion != 90 && duracion != 120) {
                System.out.println("\nError: duración no válida. Por favor, introduzca 60, 90 o 120 minutos.\n");
            } else {
                avanzar = true;
            }
            } catch (IOException e) {
            e.printStackTrace();
            } catch (NumberFormatException e) {
            System.out.println("\nError: formato de duración no válido. Por favor, introduzca un número entero.\n");
            }
        } while (avanzar == false);

        avanzar = false;
        System.out.println("Introduzca el tipo de pista que desea reservar (MINIBASKET, ADULTOS, VS3) o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String input = new String(buffer, 0, leido).trim();
            if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            pistaTamano = PistaDTO.TamanoPista.valueOf(input);
            avanzar = true;
            } catch (IOException e) {
            e.printStackTrace();
            } catch (IllegalArgumentException e) {
            System.out.println("\nError: tipo de pista no válido. Por favor, introduzca 'MINIBASKET', 'ADULTOS' o 'VS3'.\n");
            }
        } while (avanzar == false);

        if (tipoReserva.equals("INFANTIL")) {
            System.out.println("NOTA: en las reservas de tipo infantil, se asume que asistirá un adulto además de los niños, que se incluye en el número de jugadores");
        }

        avanzar = false;
        if (tipoReserva.equals("INFANTIL") || tipoReserva.equals("FAMILIAR")) {
            numAdultos = 1;
            System.out.println("Introduzca el número de niños que jugarán o escriba CANCELAR para salir\n");
            do {
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                String input = new String(buffer, 0, leido).trim();
                if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
                }
                numNinos = Integer.parseInt(input);
                if (numNinos <= 0) {
                System.out.println("\nError: número de niños no válido. Por favor, introduzca un número entero positivo.\n");
                } else {
                avanzar = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("\nError: formato de número de niños no válido. Por favor, introduzca un número entero.\n");
            }
            } while (!avanzar);
        }

        avanzar = false;
        if (tipoReserva.equals("ADULTOS") || tipoReserva.equals("FAMILIAR")) {
            System.out.println("Introduzca el número de adultos que jugarán o escriba CANCELAR para salir\n");
            do {
                try {
                    byte[] buffer = new byte[100];
                    int leido = System.in.read(buffer);
                    String input = new String(buffer, 0, leido).trim();
                    if (input.equalsIgnoreCase("CANCELAR")) {
                        System.out.println("Operación cancelada.");
                        return;
                    }
                    numAdultos = Integer.parseInt(input);
                    if (numAdultos <= 0) {
                        System.out.println("\nError: número de adultos no válido. Por favor, introduzca un número entero positivo.\n");
                    } else {
                        avanzar = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("\nError: formato de número de adultos no válido. Por favor, introduzca un número entero.\n");
                }
            } while (!avanzar);
        }

        int nJugadores = numAdultos + numNinos;
        List<PistaDTO> listaPistas = GestorDePistas.listarPistasDisponibles(nJugadores, pistaTamano);
        if (listaPistas.size() == 0) {
            System.out.println("No hay pistas libres con las características especificadas.");
            return;
        } else {
            System.out.println("Pistas disponibles:");
            for (PistaDTO pista : listaPistas) {
                System.out.println("ID: " + pista.getId() + ", Nombre: " + pista.getNombre() + ", Exterior: " + pista.isEsExterior());
            }
        }

        avanzar = false;
        System.out.println("Introduzca el ID de la pista que desea reservar o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String input = new String(buffer, 0, leido).trim();
            if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            idPista = Integer.parseInt(input);
            for (PistaDTO pista : listaPistas) {
                if (pista.getId() == (idPista)) {
                    avanzar = true;
                }
            }
            if (!avanzar) {
                System.out.println("\nError: ID de pista no válido. Por favor, introduzca un ID de pista válido.\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        } while (avanzar == false);

        precio = duracion / 3.0f;
        if (user.getFechaInscripcion().plusYears(2).isBefore(LocalDate.now())) {
            descuento = 10;
        }
        float precioFinal = precio - (precio * descuento / 100);
        System.out.println("El precio final de la reserva es: " + precioFinal + " euros.");
        System.out.println("¿Desea confirmar la reserva? (si/no)\n");
        avanzar = false;
        do {
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                String respuesta = new String(buffer, 0, leido).trim().toLowerCase();
                if (respuesta.equals("si")) {
                    avanzar = true;
                    GestorDeReservas.hacerReservaIndividual(tipoReserva, idJugador, diaYHora, duracion, idPista, precio, descuento, pistaTamano, numAdultos, numNinos);
                    System.out.println("Reserva confirmada.");
                } else if (respuesta.equals("no")) {
                    avanzar = true;
                    System.out.println("Reserva cancelada.");
                } else {
                    System.out.println("\nError: respuesta no válida. Por favor, introduzca 'sí' o 'no'.\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (avanzar == false);
    }

    void hacerReservaBono(JugadorDTO user) {
        List<BonoDTO> bonos = BonoDAO.mostrarBonosUsuario(user);

        if (bonos == null || bonos.size() == 0) {
            System.out.println("No tiene bonos disponibles\n");
            return;
        }

        System.out.println("");
        System.out.println("--- Lista de Bonos ---");
        for (BonoDTO bono : bonos) {
            System.out.println("ID: " + bono.getId());
            System.out.println("Tipo de reserva: " + bono.getTipoReserva());
            System.out.println("Tamaño de pista: " + bono.getPistaTamano());
            System.out.println("Sesiones restantes: " + bono.getSesiones());
            System.out.println("------------------------");
        }
        System.out.println("");
        BonoDTO bonoSeleccionado = null;
        System.out.println("Introduzca el ID del bono con el que desea hacer la reserva o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String idBono = new String(buffer, 0, leido).trim();
            if (idBono.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            for (BonoDTO bono : bonos) {
                if (bono.getId() == Integer.parseInt(idBono)) {
                bonoSeleccionado = bono;
                break;
                }
            }
            if (bonoSeleccionado == null) {
                System.out.println("\nError: ID de bono no válido. Por favor, introduzca un ID de bono válido.\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
            } catch (NumberFormatException e) {
            System.out.println("\nError: formato de ID de bono no válido. Por favor, introduzca un número entero.\n");
            }
        } while (bonoSeleccionado == null);

        
        String tipoReserva = bonoSeleccionado.getTipoReserva();
        PistaDTO.TamanoPista pistaTamano = bonoSeleccionado.getPistaTamano();
        LocalDate diaYHora = null;
        int duracion = 0;
        int idPista = 0;
        float precio = 0;
        float descuento = 0;
        int numAdultos = 0;
        int numNinos = 0;
        boolean avanzar = false;

        if (tipoReserva.equals("INFANTIL")) {
            System.out.println("NOTA: en las reservas de tipo infantil, se asume que asistirá un adulto además de los niños, que se incluye en el número de jugadores");
        }

        if (tipoReserva.equals("INFANTIL") || tipoReserva.equals("FAMILIAR")) {
            numAdultos = 1;
            System.out.println("Introduzca el número de niños que jugarán o escriba CANCELAR para salir\n");
            do {
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                String input = new String(buffer, 0, leido).trim();
                if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
                }
                numNinos = Integer.parseInt(input);
                if (numNinos <= 0) {
                System.out.println("\nError: número de niños no válido. Por favor, introduzca un número entero positivo.\n");
                } else {
                avanzar = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("\nError: formato de número de niños no válido. Por favor, introduzca un número entero.\n");
            }
            } while (!avanzar);
        }

        avanzar = false;
        if (tipoReserva.equals("ADULTOS") || tipoReserva.equals("FAMILIAR")) {
            System.out.println("Introduzca el número de adultos que jugarán o escriba CANCELAR para salir\n");
            do {
                try {
                    byte[] buffer = new byte[100];
                    int leido = System.in.read(buffer);
                    String input = new String(buffer, 0, leido).trim();
                    if (input.equalsIgnoreCase("CANCELAR")) {
                        System.out.println("Operación cancelada.");
                        return;
                    }
                    numAdultos = Integer.parseInt(input);
                    if (numAdultos <= 0) {
                        System.out.println("\nError: número de adultos no válido. Por favor, introduzca un número entero positivo.\n");
                    } else {
                        avanzar = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("\nError: formato de número de adultos no válido. Por favor, introduzca un número entero.\n");
                }
            } while (!avanzar);
        } 

        System.out.println("Introduzca la fecha de la reserva en formato aaaa-mm-dd o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String input = new String(buffer, 0, leido).trim();
            if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            diaYHora = LocalDate.parse(input);
            avanzar = true;
            } catch (IOException e) {
            e.printStackTrace();
            } catch (Exception e) {
            System.out.println("\nError: formato de fecha no válido. Por favor, introduzca la fecha en formato aaaa-mm-ddTHH:mm.\n");
            }
        } while (!avanzar);

        if (diaYHora.isBefore(LocalDate.now().plusDays(1))) {
            System.out.println("No se admiten reservas con menos de 24 horas de antelación.");
            return;
        }

        avanzar = false;
        System.out.println("Introduzca la duración de la reserva en minutos (60, 90, 120) o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String input = new String(buffer, 0, leido).trim();
            if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            duracion = Integer.parseInt(input);
            if (duracion != 60 && duracion != 90 && duracion != 120) {
                System.out.println("\nError: duración no válida. Por favor, introduzca 60, 90 o 120 minutos.\n");
            } else {
                avanzar = true;
            }
            } catch (IOException e) {
            e.printStackTrace();
            } catch (NumberFormatException e) {
            System.out.println("\nError: formato de duración no válido. Por favor, introduzca un número entero.\n");
            }
        } while (!avanzar);

        avanzar = false;
        int nJugadores = numAdultos + numNinos; 

        List<PistaDTO> listaPistas = GestorDePistas.listarPistasDisponibles(nJugadores, pistaTamano);
        if (listaPistas.size() == 0) {
            System.out.println("No hay pistas libres con las características especificadas.");
            return;
        } else {
            System.out.println("Pistas disponibles:");
            for (PistaDTO pista : listaPistas) {
                System.out.println("ID: " + pista.getId() + ", Nombre: " + pista.getNombre() + ", Exterior: " + (pista.isEsExterior() ? "si" : "no"));
            }
        }

        avanzar = false;
        System.out.println("Introduzca el ID de la pista que desea reservar o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            idPista = System.in.read(buffer);
            for (PistaDTO pista : listaPistas) {
                if (pista.getId() == (idPista)) {
                avanzar = true;
                break;
                }
            }
            if (!avanzar) {
                System.out.println("\nError: ID de pista no válido. Por favor, introduzca un ID de pista válido.\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        } while (!avanzar);

        precio = duracion / 3.0f;
        descuento = 5;
        float precioFinal = precio - (precio * descuento / 100);
        System.out.println("El precio final de la reserva es: " + precioFinal + " euros.");
        System.out.println("¿Desea confirmar la reserva? (si/no)\n");
        avanzar = false;
        do {
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                String respuesta = new String(buffer, 0, leido).trim().toLowerCase();
                if (respuesta.equals("si")) {
                    avanzar = true;
                    GestorDeReservas.hacerReservaBono(tipoReserva, user.getId(), diaYHora, bonoSeleccionado.getId(), bonoSeleccionado.getSesiones(), duracion, idPista, precio, descuento, pistaTamano, numAdultos, numNinos);
                    System.out.println("Reserva confirmada.");
                } else if (respuesta.equals("no")) {
                    avanzar = true;
                    System.out.println("Reserva cancelada.");
                } else {
                    System.out.println("\nError: respuesta no válida. Por favor, introduzca 'sí' o 'no'.\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!avanzar);
    }

    void consultarReservasFuturas() {
        List<ReservaDTO> reservas = GestorDeReservas.consultarReservasFuturas();
        
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas futuras.");
        } else {
            System.out.println("--- Reservas Futuras ---");
            for (ReservaDTO reserva : reservas) {
                System.out.println("ID: " + reserva.getIdReserva());
                System.out.println("ID usuario: " + reserva.getIdUsuario());
                System.out.println("Tipo: " + reserva.getTipoReserva());
                System.out.println("Dia y hora: " + reserva.getDiaYHora());
                if (reserva.getIdBono() != -1) {
                    System.out.println("ID Bono: " + reserva.getIdBono());
                    System.out.println("Nº sesión Bono: " + reserva.getNSesionBono());
                }
                System.out.println("Duracion: " + reserva.getDuracion());
                System.out.println("ID pista: " + reserva.getIdPista());
                System.out.println("Precio (sin aplicar descuento): " + reserva.getPrecio());
                System.out.println("Descuento: " + reserva.getDescuento());
                System.out.println("Precio neto: " + (reserva.getPrecio() - (reserva.getPrecio() * reserva.getDescuento() / 100)));
                System.out.println("Tamaño de pista: " + reserva.getTamano_Pista());
                System.out.println("Número de jugadores: " + (reserva.getNumAdultos() + reserva.getNumNinos()));
                System.out.println("------------------------");
            }
        }
    }

    void consultarReservaPistaDia() {
        List<PistaDTO> pistas = GestorDePistas.listarPistasDisponibles();
        LocalDate dia = null;
        int idPista = 0;
        boolean avanzar = false;

        if (pistas.size() == 0) {
            System.out.println("No hay pistas libres con las características especificadas.");
            return;
        } else {
            System.out.println("Pistas disponibles:");
            for (PistaDTO pista : pistas) {
                System.out.println("ID: " + pista.getId() + ", Nombre: " + pista.getNombre() + ", Exterior: " + pista.isEsExterior());
            }
        }

        avanzar = false;
        System.out.println("Introduzca el ID de la pista o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            idPista = System.in.read(buffer);
            for (PistaDTO pista : pistas) {
                if (pista.getId() == (idPista)) {
                avanzar = true;
                break;
                }
            }
            if (!avanzar) {
                System.out.println("\nError: ID de pista no válido. Por favor, introduzca un ID de pista válido.\n");
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        } while (!avanzar);

        System.out.println("Introduzca la fecha en formato aaaa-mm-dd o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String input = new String(buffer, 0, leido).trim();
            if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            dia = LocalDate.parse(input);
            avanzar = true;
            } catch (IOException e) {
            e.printStackTrace();
            } catch (Exception e) {
            System.out.println("\nError: formato de fecha no válido. Por favor, introduzca la fecha en formato aaaa-mm-ddThh:mm.\n");
            avanzar = false;
            }
        } while (!avanzar);

        List<ReservaDTO> reservas = GestorDeReservas.consultarReservaPistaDia(idPista, dia);
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas para la pista y día especificados.");
        } else {
            System.out.println("--- Reservas para la pista y día especificados ---");
            for (ReservaDTO reserva : reservas) {
                System.out.println("ID: " + reserva.getIdReserva());
                System.out.println("ID usuario: " + reserva.getIdUsuario());
                System.out.println("Tipo: " + reserva.getTipoReserva());
                System.out.println("Dia y hora: " + reserva.getDiaYHora());
                if (reserva.getIdBono() != -1) {
                    System.out.println("ID Bono: " + reserva.getIdBono());
                    System.out.println("Nº sesión Bono: " + reserva.getNSesionBono());
                }
                System.out.println("Duracion: " + reserva.getDuracion());
                System.out.println("ID pista: " + reserva.getIdPista());
                System.out.println("Precio (sin aplicar descuento): " + reserva.getPrecio());
                System.out.println("Descuento: " + reserva.getDescuento());
                System.out.println("Precio neto: " + (reserva.getPrecio() - (reserva.getPrecio() * reserva.getDescuento() / 100)));
                System.out.println("Tamaño de pista: " + reserva.getTamano_Pista());
                System.out.println("Número de jugadores: " + (reserva.getNumAdultos() + reserva.getNumNinos()));
                System.out.println("------------------------");
            }
        }
    }

    void consultarMisReservasFuturas(JugadorDTO user) {
        List<ReservaDTO> reservas = GestorDeReservas.consultarMisReservasFuturas(user.getId());
        if (reservas.isEmpty()) {
            System.out.println("No tienes reservas futuras.");
        } else {
            System.out.println("--- Mis Reservas Futuras ---");
            for (ReservaDTO reserva : reservas) {
                System.out.println("ID: " + reserva.getIdReserva());
                System.out.println("ID usuario: " + reserva.getIdUsuario());
                System.out.println("Tipo: " + reserva.getTipoReserva());
                System.out.println("Dia y hora: " + reserva.getDiaYHora());
                if (reserva.getIdBono() != -1) {
                    System.out.println("ID Bono: " + reserva.getIdBono());
                    System.out.println("Nº sesión Bono: " + reserva.getNSesionBono());
                }
                System.out.println("Duracion: " + reserva.getDuracion());
                System.out.println("ID pista: " + reserva.getIdPista());
                System.out.println("Precio (sin aplicar descuento): " + reserva.getPrecio());
                System.out.println("Descuento: " + reserva.getDescuento());
                System.out.println("Precio neto: " + (reserva.getPrecio() - (reserva.getPrecio() * reserva.getDescuento() / 100)));
                System.out.println("Tamaño de pista: " + reserva.getTamano_Pista());
                System.out.println("Número de jugadores: " + (reserva.getNumAdultos() + reserva.getNumNinos()));
                System.out.println("------------------------");
            }
        }
    }
    
    void eliminarReserva(JugadorDTO user) {

        consultarMisReservasFuturas(user);

        System.out.println("Introduzca el ID de la reserva que desea cancelar o escriba CANCELAR para salir\n");
        String opc = "";
        boolean avanzar = false;
        int idReserva = 0;
        while (!avanzar) {
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                opc = new String(buffer, 0, leido).trim();
                if (opc.equals("CANCELAR")) {
                    System.out.println("\nAbortando...\n");
                    return;
                } else {
                    idReserva = Integer.parseInt(opc);
                    avanzar = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ID no válido. Por favor, introduzca un número entero.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        GestorDeReservas.eliminarReserva(idReserva);
    }

    void listarUsuarios() {
        List<JugadorDTO> usuarios = GestorDeUsuarios.listarUsuarios();
        System.out.println("");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("--- Lista de Usuarios ---");
            for (JugadorDTO usuario : usuarios) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Apellidos: " + usuario.getApellidos());
            System.out.println("Fecha de nacimiento: " + usuario.getFechaNacimiento());
            System.out.println("Fecha de inscripción: " + usuario.getFechaInscripcion());
            System.out.println("------------------------");
            }
        }
        System.out.println("");
    }
  
    void crearBono(JugadorDTO user) {
        int idUser = user.getId();
        String tipoReserva = "";
        PistaDTO.TamanoPista pistaTamano = null;
        boolean avanzar = false;

        System.out.println("Introduzca el tipo de reserva del bono (infantil, adultos o familiar) o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            tipoReserva = new String(buffer, 0, leido).trim();
            if (tipoReserva.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            if (!tipoReserva.equals("infantil") && !tipoReserva.equals("adultos") && !tipoReserva.equals("familiar")) {
                System.out.println("\nError: tipo de reserva no válido. Por favor, introduzca 'infantil', 'adultos' o 'familiar'.\n");
            } else {
                avanzar = true;
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        } while (avanzar == false);

        avanzar = false;
        System.out.println("Introduzca el tipo de pista que desea reservar con el bono (MINIBASKET, ADULTOS, VS3) o escriba CANCELAR para salir\n");
        do {
            try {
            byte[] buffer = new byte[100];
            int leido = System.in.read(buffer);
            String input = new String(buffer, 0, leido).trim();
            if (input.equalsIgnoreCase("CANCELAR")) {
                System.out.println("Operación cancelada.");
                return;
            }
            pistaTamano = PistaDTO.TamanoPista.valueOf(input);
            avanzar = true;
            } catch (IOException e) {
            e.printStackTrace();
            } catch (IllegalArgumentException e) {
            System.out.println("\nError: tipo de pista no válido. Por favor, introduzca 'MINIBASKET', 'ADULTOS' o 'VS3'.\n");
            }
        } while (avanzar == false);

        System.out.println("¿Desea confirmar el bono? (si/no)\n");
        avanzar = false;
        do {
            try {
                byte[] buffer = new byte[100];
                int leido = System.in.read(buffer);
                String respuesta = new String(buffer, 0, leido).trim().toLowerCase();
                if (respuesta.equals("si")) {
                    avanzar = true;
                    GestorDeReservas.crearBono(5,idUser,tipoReserva,pistaTamano);
                    System.out.println("Bono confirmado.");
                } else if (respuesta.equals("no")) {
                    avanzar = true;
                    System.out.println("Bono cancelado.");
                } else {
                    System.out.println("\nError: respuesta no válida. Por favor, introduzca 'sí' o 'no'.\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (avanzar == false); 
    }
}
