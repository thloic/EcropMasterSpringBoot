package yenduGroup.EcropMaster.Services;

import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.DTO.VarieteDto;
import yenduGroup.EcropMaster.Entities.Culture;
import yenduGroup.EcropMaster.Entities.Variete;

import java.util.List;

public interface VarieteService {
    public VarieteDto createVariete (VarieteDto varieteDto);
    public List<VarieteDto>  getAllVarietes();
    public void deleteVariete(Long id);
    public VarieteDto getVarieteById(Long id);
    public VarieteDto convertToDTO(Variete variete);
    public VarieteDto updateVariete(Long id, VarieteDto varieteDto);


}
