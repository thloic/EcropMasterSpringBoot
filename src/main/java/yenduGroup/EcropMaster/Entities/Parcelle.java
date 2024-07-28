package yenduGroup.EcropMaster.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Entity
@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Parcelle")


public class Parcelle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float superficie;
    private String situation;
    private String region;
    private String village;
    private String codeParcelle;
    private String Referentiel;
    private int NombredeParcelle;
    private Date anneeAdhesion;

    @ManyToOne
    @JoinColumn(name = "producteur_id")
    private Producteur producteur;

    @OneToMany(mappedBy = "parcelle")
    private Set<Culture> cultures;

    @OneToOne(mappedBy = "parcelle")
    private SysGps sysGps;
    @ElementCollection
    private List<String> rotation;
    private String culturePrincipale;
    private int anneesCulturePrincipale;
    @ManyToOne
    private Campagne campagne;
}
