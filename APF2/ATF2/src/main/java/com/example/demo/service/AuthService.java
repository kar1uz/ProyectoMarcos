package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegistroRequest;
import com.example.demo.modelo.Administrador;
import com.example.demo.modelo.Usuario;
import com.example.demo.repository.AdministradorRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AdministradorRepository administradorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository, AdministradorRepository administradorRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.administradorRepository = administradorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(RegistroRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent() ||
                administradorRepository.findByEmail(request.getEmail()).isPresent()) {
            return null;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setApellido(request.getApellido());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        nuevoUsuario.setTipoUsuario("USUARIO");

        return usuarioRepository.save(nuevoUsuario);
    }

    public String autenticar(String email, String password) {
        Optional<Administrador> adminOptional = administradorRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            Administrador admin = adminOptional.get();
            if (passwordEncoder.matches(password, admin.getPasswordHash())) {
                return "ADMIN";
            }
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(password, usuario.getPasswordHash())) {
                return "USUARIO";
            }
        }
        return null;
    }
}