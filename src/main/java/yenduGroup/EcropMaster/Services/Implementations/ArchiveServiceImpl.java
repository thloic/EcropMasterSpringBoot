package yenduGroup.EcropMaster.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yenduGroup.EcropMaster.DTO.ArchiveDto;
import yenduGroup.EcropMaster.Entities.Archive;
import yenduGroup.EcropMaster.Repositories.ArchiveRepository;
import yenduGroup.EcropMaster.Services.ArchiveService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private final ArchiveRepository archiveRepository;

    public ArchiveServiceImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }


    @Override
    public ArchiveDto CreateArchive(ArchiveDto archiveDto) {
        Archive archive = new Archive();
        archive.setEnsCampagne(archiveDto.getEnsCampagne());
        archive.setEnsCulture(archiveDto.getEnsCulture());

        Archive savedArchive = archiveRepository.save(archive);
        return convertToDTO(savedArchive);
    }

    @Override
    public void DeleteArchive(ArchiveDto archiveDto) {
        archiveRepository.deleteById(archiveDto.getId());
    }

    @Override
    public List<ArchiveDto> getAllArchive() {
        List<Archive> archives = archiveRepository.findAll();
        return archives.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArchiveDto getArchiveById(Long id) {
        Optional<Archive> archive = archiveRepository.findById(id);
        return archive.map(this::convertToDTO).orElse(null);
    }

    @Override
    public ArchiveDto updateArchive(Long id, ArchiveDto archiveDto) {
        Optional<Archive> optionalArchive = archiveRepository.findById(id);
        if (optionalArchive.isPresent()) {
            Archive archive = optionalArchive.get();
            archive.setEnsCampagne(archiveDto.getEnsCampagne());
            archive.setEnsCulture(archiveDto.getEnsCulture());

            Archive updatedArchive = archiveRepository.save(archive);
            return convertToDTO(updatedArchive);
        } else {
            return null;
        }
    }

    @Override
    public ArchiveDto convertToDTO(Archive archive) {
        return ArchiveDto.builder()
                .id(archive.getId())
                .ensCampagne(archive.getEnsCampagne())
                .ensCulture(archive.getEnsCulture())
                .build();
    }
}
