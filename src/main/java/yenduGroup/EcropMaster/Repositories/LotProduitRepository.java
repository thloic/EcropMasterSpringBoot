package yenduGroup.EcropMaster.Repositories;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import yenduGroup.EcropMaster.Entities.LotProduit;

public interface LotProduitRepository extends JpaRepository<LotProduit,Long> {
}
