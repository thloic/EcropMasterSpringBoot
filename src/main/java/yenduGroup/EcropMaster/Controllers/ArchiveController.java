package yenduGroup.EcropMaster.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yenduGroup.EcropMaster.DTO.ArchiveDto;
import yenduGroup.EcropMaster.Services.ArchiveService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/archive")
public class ArchiveController {
    public final ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @PostMapping("/create")
    public ResponseEntity<ArchiveDto> createArchive(@RequestBody ArchiveDto archiveDto) {
        ArchiveDto createdArchive = archiveService.CreateArchive(archiveDto);
        return ResponseEntity.ok(createdArchive);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArchive(@PathVariable Long id) {
        ArchiveDto archiveDto = archiveService.getArchiveById(id);
        if (archiveDto != null) {
            archiveService.DeleteArchive(archiveDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("list")
    public ResponseEntity<List<ArchiveDto>> getAllArchives() {
        List<ArchiveDto> archives = archiveService.getAllArchive();
        return ResponseEntity.ok(archives);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchiveDto> getArchiveById(@PathVariable Long id) {
        ArchiveDto archiveDto = archiveService.getArchiveById(id);
        if (archiveDto != null) {
            return ResponseEntity.ok(archiveDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArchiveDto> updateArchive(@PathVariable Long id, @RequestBody ArchiveDto archiveDto) {
        ArchiveDto updatedArchive = archiveService.updateArchive(id, archiveDto);
        if (updatedArchive != null) {
            return ResponseEntity.ok(updatedArchive);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
