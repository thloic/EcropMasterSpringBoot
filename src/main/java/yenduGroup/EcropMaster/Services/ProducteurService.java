package yenduGroup.EcropMaster.Services;

import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Entities.Culture;
import yenduGroup.EcropMaster.Entities.Producteur;

import java.util.List;
import java.util.Optional;

public interface ProducteurService {
    public ProducteurDto createProducteur(ProducteurDto producteurDto);
    public List<ProducteurDto> getAllProducteur();
    Optional<ProducteurDto> getProducteurById(Long producteurId);
    public ProducteurDto updateProducteur(Long id, ProducteurDto producteurDto);
    public  void deleteProducteur(Long id);
    Optional<Producteur> findByNom(String nom);

    public ProducteurDto convertToDTO(Producteur producteur);


}
