package yenduGroup.EcropMaster.Services;

import yenduGroup.EcropMaster.DTO.AlerteDto;
import yenduGroup.EcropMaster.Entities.Alerte;

import java.util.List;

public interface AlerteService {
    public AlerteDto createAlerte(AlerteDto alerteDto);
    public void DeleteAlerte(AlerteDto alerteDto);
    public List<AlerteDto>getAllAlerte();
    public AlerteDto getAlllerteById(Long id);
    public AlerteDto updateAlerte(Long id,AlerteDto alerteDto);
    public AlerteDto convertToDTO(Alerte alerte);
}
