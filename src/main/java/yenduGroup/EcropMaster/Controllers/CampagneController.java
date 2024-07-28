package yenduGroup.EcropMaster.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yenduGroup.EcropMaster.DTO.CampagneDto;
import yenduGroup.EcropMaster.Services.CampagneService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/campagne")
public class CampagneController {
    public CampagneService campagneService;
    public CampagneController (CampagneService campagneService){
        this.campagneService = campagneService;
    }
    @PostMapping("/create")
    public ResponseEntity<CampagneDto> createCampagne(@RequestBody CampagneDto campagneDto) {
        CampagneDto createdCampagne = campagneService.CreateCampagne(campagneDto);
        return ResponseEntity.ok(createdCampagne);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCampagne(@PathVariable Long id) {
        CampagneDto campagneDto = campagneService.getCamapagneById(id);
        if (campagneDto != null) {
            campagneService.DeleteCampagne(campagneDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/List")
    public ResponseEntity<List<CampagneDto>> getAllCampagnes() {
        List<CampagneDto> campagnes = campagneService.getAllCampagnes();
        return ResponseEntity.ok(campagnes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampagneDto> getCampagneById(@PathVariable Long id) {
        CampagneDto campagneDto = campagneService.getCamapagneById(id);
        if (campagneDto != null) {
            return ResponseEntity.ok(campagneDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CampagneDto> updateCampagne(@PathVariable Long id, @RequestBody CampagneDto campagneDto) {
        CampagneDto updatedCampagne = campagneService.updateCampagne(id, campagneDto);
        if (updatedCampagne != null) {
            return ResponseEntity.ok(updatedCampagne);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/archivage")
    public ResponseEntity<Void> finCampagneArchivage() {
        campagneService.finCampagneArchivage();
        return ResponseEntity.ok().build();
    }
}

