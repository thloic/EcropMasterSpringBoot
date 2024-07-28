package yenduGroup.EcropMaster.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Culture")

public class Culture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    private String moisSemis;
    private Date dateRecolte;
    private String cultureType;
    private float surface;
    private String etat;
    private String rendement;
    private String intrant;
    private String photo;



    @ManyToOne
    @JoinColumn(name = "parcelle_id")
    private Parcelle parcelle;
    @OneToMany(mappedBy = "culture", cascade = CascadeType.ALL)
    private Set<Variete> varietes;

}
