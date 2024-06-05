package br.com.marques.easystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.marques.easystore.model.Usuario;
import br.com.marques.easystore.security.JWTToken;
import br.com.marques.easystore.security.JWTTokenUtil;
import br.com.marques.easystore.services.IUsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UsuarioController {
    @Autowired
    private IUsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<JWTToken> fazerLogin(@RequestBody Usuario dadosLogin) {

        Usuario user = service.recuperarUsuario(dadosLogin);

        if(user != null) {
            if(user.getSenha().equals(dadosLogin.getSenha())){
                //aqui eu preciso criar o token do usuário
                JWTToken jwtToken = new JWTToken();

                jwtToken.setToken(JWTTokenUtil.generateToken(user));

                return ResponseEntity.ok(jwtToken);
            }
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.notFound().build();
    }
}
