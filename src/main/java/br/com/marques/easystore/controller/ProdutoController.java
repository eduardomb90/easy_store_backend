package br.com.marques.easystore.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.marques.easystore.model.Categoria;
import br.com.marques.easystore.model.Produto;
import br.com.marques.easystore.model.FileEntity;
import br.com.marques.easystore.services.IProdutoService;
import br.com.marques.easystore.services.IUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin("http://localhost:4200/")
public class ProdutoController {
    @Autowired
    private IProdutoService service;
    @Autowired
    private IUploadService upload;

    @GetMapping("/produto")
    public ResponseEntity<ArrayList<Produto>> recuperarTodos() {
        return ResponseEntity.ok(service.listarDestaques());
    }

    @GetMapping("/produto/todos")
    public ResponseEntity<ArrayList<Produto>> buscarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/produto/categoria/{id}")
    public ResponseEntity<ArrayList<Produto>> recuperarPorCategoria(@PathVariable(name = "id") int idCategoria) {
        Categoria categoria = new Categoria();
        categoria.setId(idCategoria);
        return ResponseEntity.ok(service.listarPorCategoria(categoria));
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> recuperarPorId(@PathVariable(name = "id") int idProduto) {
        Produto produto = service.recuperarPorId(idProduto);

        if(produto != null)
            return ResponseEntity.ok(produto);

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/produto/busca")
    public ResponseEntity<ArrayList<Produto>> buscarPorPalavraChave (@RequestParam(name = "key") String key) {
        if(key != null)
            return ResponseEntity.ok(service.listarPorPalavraChave(key));

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/produto")
    public ResponseEntity<Produto> novoProduto(@RequestBody Produto novoProduto) {


        try {
            service.inserirNovoProduto(novoProduto);
            return ResponseEntity.status(201).body(novoProduto);
        } catch (Exception e) {

        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/produto/upload")
    public ResponseEntity<FileEntity> uploadFoto(@RequestParam("file") MultipartFile  file) {

        String path = upload.uploadFile(file);

        if(path != null) {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setPath(path);
            return ResponseEntity.status(201).body(fileEntity);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizaProduto(@PathVariable int id, @RequestBody Produto produto) {
        try {

            if(id != produto.getId()) {
                return ResponseEntity.badRequest().build();
            }

            Produto atualizado = service.alterarProduto(produto);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
