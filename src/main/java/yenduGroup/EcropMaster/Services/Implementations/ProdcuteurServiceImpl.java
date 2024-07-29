package yenduGroup.EcropMaster.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Entities.Producteur;
import yenduGroup.EcropMaster.Repositories.ProducteurRepository;
import yenduGroup.EcropMaster.Services.ProducteurService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProdcuteurServiceImpl implements ProducteurService {
    @Autowired
    private final ProducteurRepository producteurRepository;

    @Override
    public ProducteurDto createProducteur(ProducteurDto producteurDto) {
        Producteur producteur = convertToEntity(producteurDto);
        producteur.setDateAdhesion(producteurDto.getDateAdhesion());
        producteur = producteurRepository.save(producteur);
        return convertToDTO(producteur);
    }

    @Override
    public List<ProducteurDto> getAllProducteur() {
        List<Producteur> producteurs = producteurRepository.findAll();
        return producteurs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProducteurDto> getProducteurById(Long producteurId) {
        Optional<Producteur> producteurOptional = producteurRepository.findById(producteurId);
        return producteurOptional.map(this::convertToDTO);
    }


    @Override
    public ProducteurDto updateProducteur(Long id, ProducteurDto producteurDto) {
        Producteur existingProducteur = producteurRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producteur non trouvé"));
        existingProducteur.setNom(producteurDto.getNom());
        existingProducteur.setPrenom(producteurDto.getPrenom());
        existingProducteur.setEmail(producteurDto.getEmail());
        existingProducteur.setTelephone(producteurDto.getTelephone());
        existingProducteur.setDateNaissance(producteurDto.getDateNaissance());
        existingProducteur.setDateAdhesion(producteurDto.getDateAdhesion());
        producteurRepository.save(existingProducteur);
        return convertToDTO(existingProducteur);
    }

    @Override
    public void deleteProducteur(Long id) {
        if (producteurRepository.existsById(id)) {
            producteurRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Producteur non trouvé");
        }
    }

    @Override
    public Optional<Producteur> findByNom(String nom) {
        return producteurRepository.findByNom(nom);
    }

    @Override
    public ProducteurDto convertToDTO(Producteur producteur) {
        if (producteur == null) {
            throw new IllegalArgumentException("le producteur ne peut pas etre null");
        }
        return ProducteurDto.builder()
                .id(producteur.getId())
                .nom(producteur.getNom())
                .prenom(producteur.getPrenom())
                .email(producteur.getEmail())
                .telephone(producteur.getTelephone())
                .adresse(producteur.getAdresse())
                .dateNaissance(producteur.getDateNaissance())
                .dateAdhesion(producteur.getDateAdhesion())
                .build();
    }



    private Producteur convertToEntity(ProducteurDto producteurDto) {
        if (producteurDto == null) {
            throw new IllegalArgumentException("DTO ne peut pas etre null");
        }
        if (producteurDto.getNom() == null || producteurDto.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom est requis");
        }
        if (producteurDto.getPrenom() == null || producteurDto.getPrenom().isEmpty()) {
            throw new IllegalArgumentException("Le prenom est requis");
        }
        if (producteurDto.getAdresse() == null || producteurDto.getAdresse().isEmpty()) {
            throw new IllegalArgumentException("Le nom est requis");
        }
        if (producteurDto.getTelephone() <= 0) { // Vérifie si le numéro de téléphone est positif
            throw new IllegalArgumentException("Le téléphone est requis et doit être un nombre positif");
        }

            return Producteur.builder()
                    .id(producteurDto.getId())
                    .nom(producteurDto.getNom())
                    .prenom(producteurDto.getPrenom())
                    .email(producteurDto.getEmail())
                    .telephone(producteurDto.getTelephone())
                    .dateNaissance(producteurDto.getDateNaissance())
                    .adresse(producteurDto.getAdresse())
                    .dateAdhesion(producteurDto.getDateAdhesion())
                    .build();
        }
}

