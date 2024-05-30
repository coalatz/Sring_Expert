package com.coala.security.swt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.coala.domain.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.lang.Long;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAss;

    public String gerarToken(Usuario usuario) {
        long expString = Long.valueOf(expiracao);
        //esta pegando a hora atual e somando com o valor de expString
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);
        //criando um jws e retornando o meu token
        return Jwts.builder()
                    .setSubject(usuario.getLogin())
                    .setExpiration(data)
                    .signWith(SignatureAlgorithm.HS512, chaveAss)
                    .compact();

    }

    //neste metodo esta sendo decodificado as claims jws
    private Claims obterClaims (String token) throws ExpiredJwtException{
        return Jwts.parser()
                    .setSigningKey(chaveAss)
                    .parseClaimsJws(token)
                    .getBody();
    }

    public boolean tokenValido (String token) {

        try {
            Claims claims = obterClaims(token);
            Date dataExp = claims.getExpiration();
            LocalDateTime data = dataExp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(data);

        } catch (Exception e) {
           return false;
        }
    }

    public String obterLoginUsuario(String login) throws ExpiredJwtException{
        return obterClaims(login).getSubject();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("coala").build();
        String token = service.gerarToken(usuario);
        System.out.println(token);

        boolean isTokenValid = service.tokenValido(token);
        System.out.println("o token esta valido?" + isTokenValid);

        System.out.println(service.obterLoginUsuario(token));
    }
}
