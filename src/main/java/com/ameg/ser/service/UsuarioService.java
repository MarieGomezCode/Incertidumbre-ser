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
    public void eliminarUsuario(Long id) {
        // Verificar si el usuario existe
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        // Verificar si el usuario existe
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            // Actualizar los campos del usuario
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setNombreUsuario(usuarioActualizado.getNombreUsuario());
            usuarioExistente.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setContraseña(usuarioActualizado.getContraseña());
            usuarioExistente.setNumeroTelefono(usuarioActualizado.getNumeroTelefono());
            // Guardar los cambios
            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }
    public Usuario iniciarSesion(String usuario, String contraseña) {
        // Buscar usuario por nombre de usuario o correo electrónico
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuarioOrCorreo(usuario, usuario);
        // Verificar si se encontró un usuario
        if (usuarioOptional.isPresent()) {
            Usuario usuarioEncontrado = usuarioOptional.get();
            // Verificar si la contraseña coincide
            if (usuarioEncontrado.getContraseña().equals(contraseña)) {
                return usuarioEncontrado;
            }
        }
        // Si no se encontró un usuario o la contraseña no coincide, lanzar una excepción
        throw new IllegalArgumentException("Credenciales inválidas");
    }



}
