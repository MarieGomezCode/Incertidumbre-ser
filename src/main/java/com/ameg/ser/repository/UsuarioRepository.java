package com.ameg.ser.repository;


import com.ameg.ser.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByCorreo(String correo);
}
