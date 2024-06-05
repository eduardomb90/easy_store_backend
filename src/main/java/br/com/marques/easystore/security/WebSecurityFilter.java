package br.com.marques.easystore.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WebSecurityFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                System.out.println("-----> REQUISIÇÃO PASSOU PELO FILTRO <--------");

                if(request.getHeader("Authorization") != null) {
                    // Se eu tiver um cabeçalho com token, preciso decodificar este token
                    Authentication auth = JWTTokenUtil.decodeToken(request);

                    // Se for válido, vai um objeto, para o contexto da requisição, que representa o token
                    // Se não, vai null.
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

                filterChain.doFilter(request, response);
    }
}
