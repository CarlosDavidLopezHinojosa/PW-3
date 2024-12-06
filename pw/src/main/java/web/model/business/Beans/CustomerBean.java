package web.model.business.Beans;

import java.io.Serializable;
import java.time.LocalDate;

import web.model.business.DTOs.JugadorDTO;
import web.model.business.DTOs.JugadorDTO.Roles;

public class CustomerBean implements Serializable {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDate fechaNacimiento;
    private String password;
    private Roles rol;


    public void setData(JugadorDTO jugador) {
        this.id = jugador.getId();
        this.nombre = jugador.getNombre();
        this.apellidos = jugador.getApellidos();
        this.email = jugador.getEmail();
        this.fechaNacimiento = jugador.getFechaNacimiento();
        this.password = jugador.getPassword();
        this.rol = jugador.getRol();
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
}