package br.com.marques.easystore.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadServiceImpl implements IUploadService {

    @Override
    public String uploadFile(MultipartFile arquivo) {

        try {
            System.out.println("DEBUG - "+arquivo.getOriginalFilename());
            String caminho = "E:\\Projetos\\easystore\\easystore_admin\\src\\assets\\images";
            Path path = Paths.get(caminho + File.separator + arquivo.getOriginalFilename());
            Files.copy(arquivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("DEBUG - Arquivo copiado....");
            return arquivo.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
