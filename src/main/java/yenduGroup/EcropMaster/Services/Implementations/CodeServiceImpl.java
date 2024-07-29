package yenduGroup.EcropMaster.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.DTO.ParcelleDto;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Entities.Culture;
import yenduGroup.EcropMaster.Entities.Producteur;
import yenduGroup.EcropMaster.Repositories.CultureRepository;
import yenduGroup.EcropMaster.Repositories.ProducteurRepository;
import yenduGroup.EcropMaster.Services.CodeService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CodeServiceImpl implements CodeService {

    private final ProducteurRepository producteurRepository;
    private final CultureRepository cultureRepository;

    @Override
    public String generateCode(ParcelleDto parcelleDto, ProducteurDto producteurDto, CultureDto cultureDto) {
        // Trouver le producteur par nom
        Optional<Producteur> optionalProducteur = producteurRepository.findByNom(producteurDto.getNom());

        // Vérifier si le producteur a été trouvé
        if (optionalProducteur.isEmpty()) {
            throw new RuntimeException("Producteur non trouvé");

        }
        Optional<Culture> optionalCulture = cultureRepository.findByNom(cultureDto.getNom());
        if (optionalCulture.isEmpty()) {
            throw new RuntimeException("Culture non trouvée");
        }

        // Obtenir l'ID du producteur
        Producteur producteur = optionalProducteur.get();
        String producteurIdStr = String.format("%03d", producteur.getId());

        // Construire le code de la parcelle
        StringBuilder refSection = new StringBuilder();
        String[] referentiels = parcelleDto.getReferentiel().split(",");
        for (String ref : referentiels) {
            refSection.append(ref.trim());
        }

        String cultureInitial = cultureDto.getNom().substring(0, 1).toUpperCase();

        int anneeAdhesion = producteur.getDateAdhesion().getYear() % 100;
        String anneeAdhesionStr = String.format("%02d", anneeAdhesion);

        String regionInitial = parcelleDto.getRegion().substring(0, 1).toUpperCase();

        String villageInitial = parcelleDto.getVillage().substring(0, 1).toUpperCase();

        char parcelleId = (char) ('A' + parcelleDto.getNombredeParcelle() - 1);

        return String.format("%s-%s-%s-%s-%s-%s-%s",
                refSection.toString(), cultureInitial, anneeAdhesionStr, regionInitial, villageInitial, producteurIdStr, parcelleId);
    }
}
