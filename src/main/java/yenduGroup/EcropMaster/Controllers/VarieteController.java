package yenduGroup.EcropMaster.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yenduGroup.EcropMaster.DTO.VarieteDto;
import yenduGroup.EcropMaster.Services.VarieteService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/variete")
public class VarieteController {
    public  final VarieteService varieteService;
    public VarieteController(VarieteService varieteService) {
        this.varieteService = varieteService;
    }
    @PostMapping("/create")
    public ResponseEntity<VarieteDto> createVariete(@RequestBody VarieteDto varieteDto) {
        VarieteDto createdVariete = varieteService.createVariete(varieteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVariete);
    }

    @GetMapping("/list")
    public ResponseEntity<List<VarieteDto>> getAllVarietes() {
        List<VarieteDto> varietes = varieteService.getAllVarietes();
        return ResponseEntity.ok(varietes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VarieteDto> getVarieteById(@PathVariable Long id) {
        VarieteDto variete = varieteService.getVarieteById(id);
        if (variete != null) {
            return ResponseEntity.ok(variete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VarieteDto> updateVariete(@PathVariable Long id, @RequestBody VarieteDto varieteDto) {
        VarieteDto updatedVariete = varieteService.updateVariete(id, varieteDto);
        if (updatedVariete != null) {
            return ResponseEntity.ok(updatedVariete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVariete(@PathVariable Long id) {
        varieteService.deleteVariete(id);
        return ResponseEntity.noContent().build();
    }
}
