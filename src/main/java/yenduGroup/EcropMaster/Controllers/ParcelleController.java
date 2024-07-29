package yenduGroup.EcropMaster.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yenduGroup.EcropMaster.DTO.CodeDto;
import yenduGroup.EcropMaster.DTO.ParcelleDto;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Entities.Producteur;
import yenduGroup.EcropMaster.Services.ParcelleService;
import yenduGroup.EcropMaster.Services.ProducteurService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parcelle")
@RequiredArgsConstructor
@CrossOrigin("*")


public class ParcelleController {
    private final ParcelleService parcelleService;
    private final ProducteurService producteurService;

    @PostMapping("/create")
    public ResponseEntity<ParcelleDto> createParcelle(@RequestBody CodeDto codeDto) {
        if (codeDto.getParcelleDto() == null || codeDto.getProducteurDto() == null || codeDto.getCultureDto() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Rechercher le producteur par son nom
        Optional<Producteur> optionalProducteur = producteurService.findByNom(codeDto.getProducteurDto().getNom());
        if (optionalProducteur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Ajouter l'ID du producteur au DTO
        ProducteurDto producteurDto = codeDto.getProducteurDto();
        producteurDto.setId(optionalProducteur.get().getId());

        // Créer la parcelle
        ParcelleDto newParcelle = parcelleService.createParcelle(codeDto.getParcelleDto(), producteurDto, codeDto.getCultureDto());
        return new ResponseEntity<>(newParcelle, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParcelleDto>> getAllParcelles() {
        List<ParcelleDto> parcelles = parcelleService.getAllParcelles();
        return new ResponseEntity<>(parcelles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelleDto> getParcelleById(@PathVariable Long id) {
        ParcelleDto parcelle = parcelleService.getParcelleById(id);
        return new ResponseEntity<>(parcelle, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ParcelleDto> updateParcelle(@PathVariable Long id, @RequestBody ParcelleDto parcelleDto) {
        ParcelleDto updatedParcelle = parcelleService.updateParcelle(id, parcelleDto);
        return new ResponseEntity<>(updatedParcelle, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteParcelle(@PathVariable Long id) {
        parcelleService.deleteParcelle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
