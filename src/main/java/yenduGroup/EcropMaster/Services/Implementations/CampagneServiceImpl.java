package yenduGroup.EcropMaster.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yenduGroup.EcropMaster.DTO.ArchiveDto;
import yenduGroup.EcropMaster.DTO.CampagneDto;
import yenduGroup.EcropMaster.Entities.Campagne;
import yenduGroup.EcropMaster.Repositories.AlerteRepository;
import yenduGroup.EcropMaster.Repositories.CampagneRepository;
import yenduGroup.EcropMaster.Services.ArchiveService;
import yenduGroup.EcropMaster.Services.CampagneService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import yenduGroup.EcropMaster.Entities.Parcelle;
import yenduGroup.EcropMaster.Entities.Activite;
import yenduGroup.EcropMaster.Entities.Alerte;



@Service
@Transactional
@RequiredArgsConstructor
public class CampagneServiceImpl implements CampagneService {
    @Autowired

    private final CampagneRepository campagneRepository;
    @Autowired

    private final AlerteRepository alerteRepository;
    @Autowired

    private final ArchiveService archiveService;

    @Override
    public CampagneDto CreateCampagne(CampagneDto campagneDto) {
        Campagne campagne = new Campagne();
        campagne.setNumAnnee(campagneDto.getNumAnnee());
        campagne.setDateDebut(campagneDto.getDateDebut());
        campagne.setDateFin(campagneDto.getDateFin());
        campagne.setStatut(campagneDto.getStatut());
        campagne.setChangementCultureRecommande(campagneDto.isChangementCultureRecommande());

        Campagne savedCampagne = campagneRepository.save(campagne);
        return convertToDTO(savedCampagne);
    }

    @Override
    public void DeleteCampagne(CampagneDto campagneDto) {
        campagneRepository.deleteById(campagneDto.getId());
    }

    @Override
    public List<CampagneDto> getAllCampagnes() {
        List<Campagne> campagnes = campagneRepository.findAll();
        return campagnes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CampagneDto getCamapagneById(Long id) {
        Optional<Campagne> campagne = campagneRepository.findById(id);
        return campagne.map(this::convertToDTO).orElse(null);
    }

    @Override
    public CampagneDto updateCampagne(Long id, CampagneDto campagneDto) {
        Optional<Campagne> optionalCampagne = campagneRepository.findById(id);
        if (optionalCampagne.isPresent()) {
            Campagne campagne = optionalCampagne.get();
            campagne.setNumAnnee(campagneDto.getNumAnnee());
            campagne.setDateDebut(campagneDto.getDateDebut());
            campagne.setDateFin(campagneDto.getDateFin());
            campagne.setStatut(campagneDto.getStatut());
            campagne.setChangementCultureRecommande(campagneDto.isChangementCultureRecommande());

            // Ajout des autres attributs manquants

            Campagne updatedCampagne = campagneRepository.save(campagne);
            return convertToDTO(updatedCampagne);
        } else {
            return null;
        }
    }


    @Override
    public CampagneDto convertToDTO(Campagne campagne) {
        return CampagneDto.builder()
                .id(campagne.getId())
                .numAnnee(campagne.getNumAnnee())
                .dateDebut(campagne.getDateDebut())
                .dateFin(campagne.getDateFin())
                .statut(campagne.getStatut())
                .changementCultureRecommande(campagne.isChangementCultureRecommande())
                .activiteIds(campagne.getActivites().stream().map(Activite::getId).collect(Collectors.toSet()))
                .archiveId(campagne.getArchive() != null ? campagne.getArchive().getId() : null)
                .alerteIds(campagne.getAlertes().stream().map(Alerte::getId).collect(Collectors.toSet()))
                .parcelleIds(campagne.getParcelles().stream().map(Parcelle::getId).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Campagne gererAlertesPourCampagne(Campagne campagne) {
        if (campagne.getStatut().equals("Important")) {
            Alerte alerte = new Alerte();
            alerte.setMessage("Activité importante à surveiller");
            alerte.setDate(new Date());
            alerte.setCampagne(campagne);
            alerteRepository.save(alerte);
        }
        return campagne;
    }
    private ArchiveDto convertToArchiveDTO(Campagne campagne) {
        ArchiveDto archiveDto = new ArchiveDto();
        archiveDto.setEnsCampagne(campagne.getNumAnnee());
        archiveDto.setEnsCulture("Culture");
        return archiveDto;
    }

    public void finCampagneArchivage() {
        List<Campagne> campagnes = campagneRepository.findAllByOrderByNumAnneeDesc();
        if (!campagnes.isEmpty()) {
            Campagne derniereCampagne = campagnes.get(0);
            archiveService.CreateArchive(convertToArchiveDTO(derniereCampagne));

            if (campagnes.size() > 3) {
                for (int i = 3; i < campagnes.size(); i++) {
                    campagneRepository.delete(campagnes.get(i));
                }
            }
        }
    }
}
