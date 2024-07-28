package yenduGroup.EcropMaster.Services.Implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yenduGroup.EcropMaster.DTO.VarieteDto;
import yenduGroup.EcropMaster.Entities.Variete;
import yenduGroup.EcropMaster.Repositories.VarieteRepository;
import yenduGroup.EcropMaster.Services.VarieteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VarieteServiceImpl implements VarieteService {
    @Autowired
    public final VarieteRepository varieteRepository;

    @Override
    public VarieteDto createVariete(VarieteDto varieteDto) {
        Variete variete = new Variete();
        variete.setNom(varieteDto.getNom());
        Variete savedVariete = varieteRepository.save(variete);
        return convertToDTO(savedVariete);
    }

    @Override
    public List<VarieteDto> getAllVarietes() {
        List<Variete> varietes = varieteRepository.findAll();
        return varietes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVariete(Long id) {
        varieteRepository.deleteById(id);
    }

    @Override
    public VarieteDto getVarieteById(Long id) {
        Optional<Variete> variete = varieteRepository.findById(id);
        return variete.map(this::convertToDTO).orElse(null);
    }

    @Override
    public VarieteDto updateVariete(Long id, VarieteDto varieteDto) {
        Optional<Variete> optionalVariete = varieteRepository.findById(id);
        if (optionalVariete.isPresent()) {
            Variete variete = optionalVariete.get();
            variete.setNom(varieteDto.getNom());
            Variete updatedVariete = varieteRepository.save(variete);
            return convertToDTO(updatedVariete);
        } else {
            return null;
        }
    }

    @Override
    public VarieteDto convertToDTO(Variete variete) {
        VarieteDto varieteDto = new VarieteDto();
        varieteDto.setId(variete.getId());
        varieteDto.setNom(variete.getNom());
        return varieteDto;
    }
}
