package yenduGroup.EcropMaster.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yenduGroup.EcropMaster.DTO.AlerteDto;
import yenduGroup.EcropMaster.DTO.CampagneDto;
import yenduGroup.EcropMaster.Entities.Alerte;
import yenduGroup.EcropMaster.Entities.Campagne;
import yenduGroup.EcropMaster.Repositories.AlerteRepository;
import yenduGroup.EcropMaster.Services.AlerteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@Transactional
@RequiredArgsConstructor
public class AlerteServiceImpl implements AlerteService {
    @Autowired
    public final AlerteRepository alerteRepository;


    @Override
    public AlerteDto createAlerte(AlerteDto alerteDto) {
        Alerte alerte = new Alerte();
        alerte.setMessage(alerteDto.getMessage());
        alerte.setDate(alerteDto.getDate());

        Alerte savedAlerte = alerteRepository.save(alerte);
        return convertToDTO(savedAlerte);
    }

    @Override
    public void DeleteAlerte(AlerteDto alerteDto) {
        alerteRepository.deleteById(alerteDto.getId());
    }

    @Override
    public List<AlerteDto> getAllAlerte() {
        List<Alerte> alertes = alerteRepository.findAll();
        return alertes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlerteDto getAlllerteById(Long id) {
        Optional<Alerte> alerte = alerteRepository.findById(id);
        return alerte.map(this::convertToDTO).orElse(null);
    }

    @Override
    public AlerteDto updateAlerte(Long id, AlerteDto alerteDto) {
        Optional<Alerte> optionalAlerte = alerteRepository.findById(id);
        if (optionalAlerte.isPresent()) {
            Alerte alerte = optionalAlerte.get();
            alerte.setMessage(alerteDto.getMessage());
            alerte.setDate(alerteDto.getDate());

            Alerte updatedAlerte = alerteRepository.save(alerte);
            return convertToDTO(updatedAlerte);
        } else {
            return null;
        }
    }

    @Override
    public AlerteDto convertToDTO(Alerte alerte) {
        return AlerteDto.builder()
                .date(alerte.getDate())
                .id(alerte.getId())
                .message(alerte.getMessage())
                .campagne(alerte.getCampagne() != null ? convertCampagneToDTO(alerte.getCampagne()) : null).build();
    }

    private CampagneDto convertCampagneToDTO(Campagne campagne) {
        return CampagneDto.builder()
                .id(campagne.getId())
                .numAnnee(campagne.getNumAnnee())
                .dateDebut(campagne.getDateDebut())
                .dateFin(campagne.getDateFin())
                .statut(campagne.getStatut())
                .changementCultureRecommande(campagne.isChangementCultureRecommande())
                .build();
    }
}
