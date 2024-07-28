package yenduGroup.EcropMaster.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yenduGroup.EcropMaster.DTO.CultureDto;
import yenduGroup.EcropMaster.Entities.Culture;
import yenduGroup.EcropMaster.Repositories.CultureRepository;
import yenduGroup.EcropMaster.Services.CultureService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CultureServiceImpl implements CultureService {
    private final CultureRepository cultureRepository;

    @Override
    public CultureDto createCulture(CultureDto cultureDto) {
        Culture culture = new Culture();
        culture.setMoisSemis(cultureDto.getMoisSemis());
        culture.setDateRecolte(cultureDto.getDateRecolte());
        culture.setCultureType(cultureDto.getCultureType());
        culture.setSurface(cultureDto.getSurface());
        culture.setEtat(cultureDto.getEtat());
        culture.setRendement(cultureDto.getRendement());
        culture.setIntrant(cultureDto.getIntrant());
        culture.setNom(cultureDto.getNom());
        culture.setPhoto(cultureDto.getPhoto());

        Culture savedCulture = cultureRepository.save(culture);
        return convertToDTO(savedCulture);
    }

    @Override
    public List<CultureDto> getAllCultures() {
        List<Culture> cultures = cultureRepository.findAll();
        return cultures.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CultureDto getCultureById(Long id) {
        Optional<Culture> culture = cultureRepository.findById(id);
        return culture.map(this::convertToDTO).orElse(null);
    }

    @Override
    public CultureDto updateCulture(Long id, CultureDto cultureDto) {
        Optional<Culture> optionalCulture = cultureRepository.findById(id);
        if (optionalCulture.isPresent()) {
            Culture culture = optionalCulture.get();
            culture.setMoisSemis(cultureDto.getMoisSemis());
            culture.setDateRecolte(cultureDto.getDateRecolte());
            culture.setCultureType(cultureDto.getCultureType());
            culture.setSurface(cultureDto.getSurface());
            culture.setEtat(cultureDto.getEtat());
            culture.setRendement(cultureDto.getRendement());
            culture.setIntrant(cultureDto.getIntrant());
            culture.setNom(cultureDto.getNom());
            culture.setPhoto(cultureDto.getPhoto());

            Culture updatedCulture = cultureRepository.save(culture);
            return convertToDTO(updatedCulture);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCulture(Long id) {
        cultureRepository.deleteById(id);
    }

    public CultureDto convertToDTO(Culture culture) {
        CultureDto cultureDto = new CultureDto();
        cultureDto.setId(culture.getId());
        cultureDto.setMoisSemis(culture.getMoisSemis());
        cultureDto.setDateRecolte(culture.getDateRecolte());
        cultureDto.setCultureType(culture.getCultureType());
        cultureDto.setSurface(culture.getSurface());
        cultureDto.setEtat(culture.getEtat());
        cultureDto.setRendement(culture.getRendement());
        cultureDto.setIntrant(culture.getIntrant());
        cultureDto.setNom(culture.getNom());
        cultureDto.setPhoto(culture.getPhoto());
        return cultureDto;
    }

    public Culture convertToEntity(CultureDto cultureDto) {
        Culture culture = new Culture();
        culture.setId(cultureDto.getId());
        culture.setNom(cultureDto.getNom());
        culture.setMoisSemis(cultureDto.getMoisSemis());
        culture.setPhoto(cultureDto.getPhoto());
        return culture;
    }
}
