package yenduGroup.EcropMaster.Services;

import yenduGroup.EcropMaster.DTO.CampagneDto;
import yenduGroup.EcropMaster.Entities.Campagne;

import java.util.List;

public interface CampagneService {
    public CampagneDto CreateCampagne(CampagneDto campagneDto);
    public void DeleteCampagne(CampagneDto campagneDto);
    public List<CampagneDto> getAllCampagnes();
    public CampagneDto getCamapagneById(Long id);
    public CampagneDto updateCampagne(Long id,CampagneDto campagneDto);
    public void finCampagneArchivage();
    public CampagneDto convertToDTO(Campagne campagne);
    public  Campagne gererAlertesPourCampagne(Campagne campagne);
}
