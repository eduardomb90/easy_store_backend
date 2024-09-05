package br.com.marques.easystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.marques.easystore.model.Usuario;
import br.com.marques.easystore.security.JWTToken;
import br.com.marques.easystore.security.JWTTokenUtil;
import br.com.marques.easystore.services.IUsuarioService;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@CrossOrigin("")
public class UsuarioController {
    @Autowired
    private IUsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<JWTToken> fazerLogin(@RequestBody Usuario dadosLogin) {

        Usuario user = service.recuperarUsuario(dadosLogin);

        if(user != null) {
            //aqui eu preciso criar o token do usuário
            JWTToken jwtToken = new JWTToken();

            jwtToken.setToken(JWTTokenUtil.generateToken(user));

            return ResponseEntity.ok(jwtToken);
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/usuario")
    public ResponseEntity<ArrayList<Usuario>> recuperarTodos() {
        return ResponseEntity.ok(service.resuperarTodos());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> recuperarPorId(@PathVariable(name = "id") int idUsuario) {
        Usuario usuario = service.recuperarPorId(idUsuario);

        if(usuario != null)
            return ResponseEntity.ok(usuario);

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> adicionarNovoUsuario(@RequestBody Usuario usuario) {
        Usuario res = service.adicionarNovo(usuario);

        if(res != null) {
            return ResponseEntity.status(201).body(res);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("usuario/{id}")
    public ResponseEntity<Usuario> alterarUsuario(@PathVariable(name = "id") int id, @RequestBody Usuario usuario) {
        Usuario oldUser = service.recuperarPorId(id);

        usuario.setId(id);
        usuario.setSenha(oldUser.getSenha());
        Usuario res = service.atualizarUsuario(usuario);

        if(res != null){
            return ResponseEntity.ok(res);
        }

        return ResponseEntity.badRequest().build();
    }
}
