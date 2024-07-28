package yenduGroup.EcropMaster.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yenduGroup.EcropMaster.DTO.AlerteDto;
import yenduGroup.EcropMaster.Services.AlerteService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/alerte")
public class AlerteController {
    public final AlerteService alerteService;
    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    @PostMapping
    public ResponseEntity<AlerteDto> createAlerte(@RequestBody AlerteDto alerteDto) {
        AlerteDto createdAlerte = alerteService.createAlerte(alerteDto);
        return ResponseEntity.ok(createdAlerte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlerte(@PathVariable Long id) {
        AlerteDto alerteDto = alerteService.getAlllerteById(id);
        if (alerteDto != null) {
            alerteService.DeleteAlerte(alerteDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AlerteDto>> getAllAlertes() {
        List<AlerteDto> alertes = alerteService.getAllAlerte();
        return ResponseEntity.ok(alertes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlerteDto> getAlerteById(@PathVariable Long id) {
        AlerteDto alerteDto = alerteService.getAlllerteById(id);
        if (alerteDto != null) {
            return ResponseEntity.ok(alerteDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlerteDto> updateAlerte(@PathVariable Long id, @RequestBody AlerteDto alerteDto) {
        AlerteDto updatedAlerte = alerteService.updateAlerte(id, alerteDto);
        if (updatedAlerte != null) {
            return ResponseEntity.ok(updatedAlerte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

