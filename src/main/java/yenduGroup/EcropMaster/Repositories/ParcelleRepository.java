package yenduGroup.EcropMaster.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yenduGroup.EcropMaster.Entities.Parcelle;

import java.util.List;

public interface ParcelleRepository extends JpaRepository<Parcelle,Long> {
    List<Parcelle> findByProducteurId(Long producteurId);
}
