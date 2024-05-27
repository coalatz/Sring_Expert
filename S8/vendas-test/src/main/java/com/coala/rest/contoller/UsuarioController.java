package com.coala.rest.contoller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coala.domain.entity.Usuario;
import com.coala.exception.SenhaIvalidException;
import com.coala.rest.dto.CredenciasDTO;
import com.coala.rest.dto.TokenDTO;
import com.coala.security.swt.JwtService;
import com.coala.service.impl.UsuarioServiceImpl;

import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/usuarios")
@Api("Api de usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioController(UsuarioServiceImpl usuarioService,
     PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salvar um usuario")
    @ApiResponse(code = 201, message = "Usuario cadastrado")
    public Usuario save(@RequestBody @Valid @ApiParam("Obejto do tipo usuario") Usuario usuario) {
        String senhaCrip = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCrip);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    @ApiOperation("Autenticar um usuario")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuario autenticado"),
        @ApiResponse(code = 401, message = "Sem autorizacao")
    })
    public TokenDTO autenticar(@RequestBody @ApiParam("DTO com informacoes relevantes para a autenticacao") CredenciasDTO credencias) {
        try{
            Usuario usuario = Usuario.builder()
                .login(credencias.getLogin())
                .senha(credencias.getSenha())
                .build();

            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);

        }catch( UsernameNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }catch( SenhaIvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    
}
