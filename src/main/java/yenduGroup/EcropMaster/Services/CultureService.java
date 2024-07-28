package yenduGroup.EcropMaster.Services;

import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.Entities.Culture;

import java.util.List;

public interface CultureService {
    public CultureDto createCulture(CultureDto cultureDTO);

    public List<CultureDto> getAllCultures();

    public CultureDto getCultureById(Long id);

    public CultureDto updateCulture(Long id, CultureDto cultureDTO);

    public void deleteCulture(Long id);

    public CultureDto convertToDTO(Culture culture);

}
