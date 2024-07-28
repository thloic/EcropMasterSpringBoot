package yenduGroup.EcropMaster.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Campagne")

public class Campagne implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numAnnee;
    private Date dateDebut;
    private Date dateFin;
    private String statut;

    @OneToMany(mappedBy = "campagne")
    private Set<Activite> activites;

    @ManyToOne
    @JoinColumn(name = "archive_id")
    private Archive archive;
    @OneToMany(mappedBy = "campagne")
    private Set<Alerte> alertes = new HashSet<>();
    @OneToMany(mappedBy = "campagne")
    private Set<Parcelle> parcelles;
    private boolean changementCultureRecommande;
}
