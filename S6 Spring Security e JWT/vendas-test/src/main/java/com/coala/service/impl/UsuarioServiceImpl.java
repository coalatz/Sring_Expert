package com.coala.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coala.domain.entity.Usuario;
import com.coala.domain.repository.UsuarioRespository;
import com.coala.exception.SenhaIvalidException;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRespository usuarioRespository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario login = usuarioRespository.findByLogin(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado."));

        String[] roles = login.isAdmin() ? 
        new String[] {"ADMIN", "USER"} : new String[]{"USER"};

        return User.builder()
                    .username(login.getLogin())
                    .password(login.getSenha())
                    .roles(roles)
                    .build();
    }

    public Usuario salvar( Usuario usuario) {
        usuarioRespository.save(usuario);

        return usuario;
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
        if(senhasBatem) {
            return user;
        }
        throw new SenhaIvalidException();
    }

    

}
