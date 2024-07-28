package yenduGroup.EcropMaster.DTO;

import lombok.*;
import yenduGroup.EcropMaster.Entities.Cooperative;
import yenduGroup.EcropMaster.Entities.Culture;
import yenduGroup.EcropMaster.Entities.Producteur;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcelleDto {
    private Long id;
    private float superficie;
    private String situation;
    private String region;
    private String village;
    private String codeParcelle;
    private String referentiel;
    private int nombredeParcelle;
    private Date anneeAdhesion;

    private Set<ProducteurDto> producteurDtos;
    private Set<Culture> cultures;
    private Set<Cooperative> cooperatives;
    private int anneesConsecutivesCulturePrincipale;


}
