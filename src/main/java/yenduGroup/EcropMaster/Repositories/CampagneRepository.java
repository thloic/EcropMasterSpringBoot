package yenduGroup.EcropMaster.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yenduGroup.EcropMaster.Entities.Campagne;

import java.util.List;

public interface CampagneRepository extends JpaRepository<Campagne,Long> {
    List<Campagne> findAllByOrderByNumAnneeDesc();
}
