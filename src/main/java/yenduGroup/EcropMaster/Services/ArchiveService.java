package yenduGroup.EcropMaster.Services;

import yenduGroup.EcropMaster.DTO.ArchiveDto;
import yenduGroup.EcropMaster.DTO.CampagneDto;
import yenduGroup.EcropMaster.Entities.Archive;
import yenduGroup.EcropMaster.Entities.Campagne;

import java.util.List;

public interface ArchiveService {
    public ArchiveDto CreateArchive(ArchiveDto archiveDto);
    public void DeleteArchive(ArchiveDto archiveDto);
    public List<ArchiveDto> getAllArchive();
    public ArchiveDto getArchiveById(Long id);
    public ArchiveDto updateArchive(Long id,ArchiveDto archiveDto);
    public ArchiveDto convertToDTO(Archive archive);
}
