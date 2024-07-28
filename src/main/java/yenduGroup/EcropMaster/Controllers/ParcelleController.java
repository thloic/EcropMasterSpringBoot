package yenduGroup.EcropMaster.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yenduGroup.EcropMaster.DTO.CodeDto;
import yenduGroup.EcropMaster.DTO.ParcelleDto;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Services.ParcelleService;
import yenduGroup.EcropMaster.Services.ProducteurService;

import java.util.List;

@RestController
@RequestMapping("/parcelle")
@CrossOrigin("*")
public class ParcelleController {
    private final ParcelleService parcelleService;
    private final ProducteurService producteurService;
    public ParcelleController(ParcelleService parcelleService, ProducteurService producteurService) {
        this.parcelleService = parcelleService;
        this.producteurService = producteurService;
    }

    @PostMapping("/create")
    public ResponseEntity<ParcelleDto> createParcelle(@RequestBody CodeDto codeDto) {
        ProducteurDto producteurDto = producteurService.findByNom(codeDto.getProducteurDto().getNom());
        if (producteurDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ParcelleDto createdParcelle = parcelleService.createParcelle(
                codeDto.getParcelleDto(),
                codeDto.getProducteurDto(),
                codeDto.getCultureDto()
        );
        if (createdParcelle != null) {
            return new ResponseEntity<>(createdParcelle, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<ParcelleDto>> getAllParcelles(@RequestParam Long producteurId) {
        List<ParcelleDto> parcelles = parcelleService.getAllParcellesByProducteurId(producteurId);
        return new ResponseEntity<>(parcelles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelleDto> getParcelleById(@PathVariable Long id) {
        ParcelleDto parcelle = parcelleService.getParcelleById(id);
        if (parcelle != null) {
            return new ResponseEntity<>(parcelle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ParcelleDto> updateParcelle(@PathVariable Long id, @RequestBody ParcelleDto parcelleDto) {
        ParcelleDto updatedParcelle = parcelleService.updateParcelle(id, parcelleDto);
        if (updatedParcelle != null) {
            return new ResponseEntity<>(updatedParcelle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteParcelle(@PathVariable Long id) {
        parcelleService.deleteParcelle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/verifier-rotations")
    public ResponseEntity<String> verifierRotationsCulturales() {
        parcelleService.verifierRotationsCulturales();
        return ResponseEntity.ok("Vérification des rotations culturales effectuée et alertes envoyées si nécessaire.");
    }

}
