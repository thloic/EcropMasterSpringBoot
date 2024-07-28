package yenduGroup.EcropMaster.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.DTO.ParcelleDto;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Entities.Parcelle;
import yenduGroup.EcropMaster.Repositories.ParcelleRepository;
import yenduGroup.EcropMaster.Repositories.ProducteurRepository;
import yenduGroup.EcropMaster.Services.CodeService;
import yenduGroup.EcropMaster.Services.ParcelleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ParcelleServiceImpl implements ParcelleService {
    @Autowired
    public final ParcelleRepository parcelleRepository;
    public final ProducteurRepository producteurRepository;
    @Autowired
    private final CodeService codeService;


    @Override
    public ParcelleDto createParcelle(ParcelleDto parcelleDto, ProducteurDto producteurDto, CultureDto cultureDto) {
        Parcelle parcelle = new Parcelle();
        parcelle.setSuperficie(parcelleDto.getSuperficie());
        parcelle.setSituation(parcelleDto.getSituation());
        parcelle.setRegion(parcelleDto.getRegion());
        parcelle.setVillage(parcelleDto.getVillage());
        parcelle.setReferentiel(parcelleDto.getReferentiel());
        parcelle.setNombredeParcelle(parcelleDto.getNombredeParcelle());

        // Génération du code de la parcelle
        String codeParcelle = codeService.generateCode(parcelleDto, producteurDto, cultureDto);
        parcelle.setCodeParcelle(codeParcelle);

        // Sauvegarde de la parcelle
        Parcelle savedParcelle = parcelleRepository.save(parcelle);

        return convertToDTO(savedParcelle);
    }


    @Override
    public List<ParcelleDto> getAllParcellesByProducteurId(Long producteurId) {
        List<Parcelle> parcelles = parcelleRepository.findByProducteurId(producteurId);
        return parcelles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParcelleDto getParcelleById(Long id) {
        Optional<Parcelle> parcelle = parcelleRepository.findById(id);
        return parcelle.map(this::convertToDTO).orElse(null);
    }

    @Override
    public ParcelleDto updateParcelle(Long id, ParcelleDto parcelleDto) {
        Optional<Parcelle> optionalParcelle = parcelleRepository.findById(id);
        if (optionalParcelle.isPresent()) {
            Parcelle parcelle = optionalParcelle.get();
            parcelle.setSuperficie(parcelleDto.getSuperficie());
            parcelle.setSituation(parcelleDto.getSituation());
            parcelle.setRegion(parcelleDto.getRegion());
            parcelle.setVillage(parcelleDto.getVillage());
            parcelle.setCodeParcelle(parcelleDto.getCodeParcelle());
            Parcelle updatedParcelle = parcelleRepository.save(parcelle);
            return convertToDTO(updatedParcelle);
        } else {
            return null;
        }
    }

    @Override
    public void deleteParcelle(Long id) {
        parcelleRepository.deleteById(id);
    }

    @Override
    public ParcelleDto convertToDTO(Parcelle parcelle) {
        return ParcelleDto.builder()
                .id(parcelle.getId())
                .superficie(parcelle.getSuperficie())
                .situation(parcelle.getSituation())
                .region(parcelle.getRegion())
                .village(parcelle.getVillage())
                .codeParcelle(parcelle.getCodeParcelle())
                .build();
    }

    @Override
    public void verifierRotationsCulturales() {
        List<Parcelle> parcelles = parcelleRepository.findAll();

        for (Parcelle parcelle : parcelles) {
            ParcelleDto parcelleDto = convertToDTO(parcelle);
            if (parcelleDto.getAnneesConsecutivesCulturePrincipale() >= 3) {
                envoyerAlerte(parcelleDto);
            }
        }
    }
    @Override
    public void envoyerAlerte(ParcelleDto parcelleDto) {

    }




}


