package yenduGroup.EcropMaster.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.Services.CultureService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/culture")
@CrossOrigin("*")
public class CultureController {
    public final CultureService cultureService;
    public CultureController(CultureService cultureService) {
        this.cultureService = cultureService;
    }
    @PostMapping("/create")
    public ResponseEntity<CultureDto> createCulture(
            @RequestParam("nom") String nom,
            @RequestParam("photo") MultipartFile photo) {

        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        // Chemin absolu
        Path path = Paths.get("C:\\Users\\THON Loic brunel\\Documents\\PROJET\\EcropMaster\\src\\main\\resources\\templates\\" + fileName);

        // Vérification de l'existence du dossier
        File directory = new File("C:\\Users\\THON Loic brunel\\Documents\\PROJET\\EcropMaster\\src\\main\\resources\\templates");
        if (!directory.exists()) {
            directory.mkdirs(); // Crée le dossier si nécessaire
        }

        try {
            Files.write(path, photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        CultureDto cultureDto = new CultureDto();
        cultureDto.setNom(nom);
        cultureDto.setPhoto("/images/" + fileName);

        CultureDto createdCulture = cultureService.createCulture(cultureDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCulture);
    }



    @GetMapping("/list")
    public ResponseEntity<List<CultureDto>> getAllCultures() {
        List<CultureDto> cultures = cultureService.getAllCultures();
        return ResponseEntity.ok(cultures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CultureDto> getCultureById(@PathVariable Long id) {
        CultureDto culture = cultureService.getCultureById(id);
        if (culture != null) {
            return ResponseEntity.ok(culture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CultureDto> updateCulture(@PathVariable Long id, @RequestBody CultureDto cultureDto) {
        CultureDto updatedCulture = cultureService.updateCulture(id, cultureDto);
        if (updatedCulture != null) {
            return ResponseEntity.ok(updatedCulture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCulture(@PathVariable Long id) {
        cultureService.deleteCulture(id);
        return ResponseEntity.noContent().build();
    }
}
