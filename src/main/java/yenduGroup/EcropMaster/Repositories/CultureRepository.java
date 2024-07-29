package yenduGroup.EcropMaster.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yenduGroup.EcropMaster.Entities.Culture;

import java.util.Optional;

public interface CultureRepository extends JpaRepository<Culture,Long> {
    Optional<Culture> findByNom(String nom);
}
