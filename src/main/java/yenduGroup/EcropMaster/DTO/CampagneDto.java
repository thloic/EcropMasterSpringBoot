package yenduGroup.EcropMaster.DTO;

import lombok.*;

import java.util.Date;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CampagneDto {
    private Long id;

    private int numAnnee;
    private Date dateDebut;
    private Date dateFin;
    private String statut;
    private Set<Long> activiteIds;
    private Long archiveId;
    private Set<Long> alerteIds;
    private Set<Long> parcelleIds;
    private boolean changementCultureRecommande;

}
