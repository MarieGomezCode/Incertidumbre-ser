package com.ameg.ser.model;


//import javax.persistence.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Cambio

    private String nombre;
    private String nombreUsuario;
    private LocalDate fechaNacimiento;
    private LocalDateTime horaCreacion;
    private String correo;
    private String contraseña;
    private String numeroTelefono;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(LocalDateTime horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    // Método para establecer la hora de creación automáticamente
    @PrePersist
    public void setHoraCreacionNow() {
        this.horaCreacion = LocalDateTime.now(); // Obtiene la fecha y hora actual del sistema
    }
}
