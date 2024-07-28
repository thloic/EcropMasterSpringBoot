package yenduGroup.EcropMaster.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yenduGroup.EcropMaster.Entities.Producteur;

import java.util.Optional;

public interface ProducteurRepository extends JpaRepository<Producteur,Long> {
    Optional<Producteur> findByNom(String nom);

}
