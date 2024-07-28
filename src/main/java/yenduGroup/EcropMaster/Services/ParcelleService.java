package yenduGroup.EcropMaster.Services;

import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.DTO.ParcelleDto;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Entities.Culture;
import yenduGroup.EcropMaster.Entities.Parcelle;
import yenduGroup.EcropMaster.Entities.Producteur;

import java.util.List;

public interface ParcelleService {
    ParcelleDto createParcelle(ParcelleDto parcelleDto, ProducteurDto producteurDto, CultureDto cultureDto);


    public List<ParcelleDto> getAllParcellesByProducteurId(Long producerId);


    public ParcelleDto getParcelleById(Long id);
    public ParcelleDto updateParcelle(Long id, ParcelleDto parcelleDto);
    public void deleteParcelle(Long id);
    public ParcelleDto convertToDTO(Parcelle parcelle);
    public void verifierRotationsCulturales();
    public void envoyerAlerte(ParcelleDto parcelleDto);

}
