package yenduGroup.EcropMaster.Services;

import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.DTO.ParcelleDto;
import yenduGroup.EcropMaster.DTO.ProducteurDto;

public interface CodeService {
    public String generateCode(ParcelleDto parcelleDto, ProducteurDto producteurDto, CultureDto cultureDto);


}
