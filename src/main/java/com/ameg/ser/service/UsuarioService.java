package com.ameg.ser.service;



import com.ameg.ser.model.Usuario;
import com.ameg.ser.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        // Validar que el nombre de usuario no esté en uso
        Optional<Usuario> usuarioExistenteNombre = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());
        if (usuarioExistenteNombre.isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        // Verificar si el usuario ya está registrado con el correo electrónico proporcionado
        Optional<Usuario> usuarioExistenteCorreo = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (usuarioExistenteCorreo.isPresent()) {
            throw new IllegalArgumentException("El usuario ya está registrado con este correo electrónico");
        }

        // Validar el formato del correo electrónico
        if (!isValidEmail(usuario.getCorreo())) {
            throw new IllegalArgumentException("Formato de correo electrónico no válido");
        }

        // Validar la contraseña
        if (!isValidPassword(usuario.getContraseña())) {
            throw new IllegalArgumentException("La contraseña no cumple con los requisitos de seguridad");
        }

        // Aquí puedes agregar más validaciones según tus requerimientos

        // Guardar el usuario si todas las validaciones son exitosas
        return usuarioRepository.save(usuario);
    }

    private boolean isValidEmail(String email) {
        // Aquí puedes implementar la lógica para validar el formato del correo electrónico
        return true; // Por ahora, simplemente retornamos true para fines de demostración
    }

    private boolean isValidPassword(String password) {
        // Aquí puedes implementar la lógica para validar la contraseña
        return true; // Por ahora, simplemente retornamos true para fines de demostración
    }
}
